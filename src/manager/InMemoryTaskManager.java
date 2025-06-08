package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    private final Map<Integer, Task> tasksById = new HashMap<>();
    private final Map<Integer, Epic> epicById = new HashMap<>();
    private final Map<Integer, Subtask> subtasksById = new HashMap<>();


    private Integer nextId = 1;

    @Override
    public void createTask(Task task) {
        task.setId(nextId++);
        tasksById.put(task.getId(), task);
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(nextId++);
        epicById.put(epic.getId(), epic);
    }

    @Override
    public void createSubtask(Subtask subtask) {
        subtask.setId(nextId++);
        subtasksById.put(subtask.getId(), subtask);
        epicById.get(subtask.getEpicId()).getSubtasksForEpic().add(subtask.getId());
        updateEpicStatus(subtask.getEpicId());
    }

    @Override
    public ArrayList<Task> getListOfTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task value : tasksById.values()) {
            tasks.add(value);
        }
        return tasks;
    }

    @Override
    public ArrayList<Epic> getListOfEpics() {
        ArrayList<Epic> epics = new ArrayList<>();
        for (Epic value : epicById.values()) {
            epics.add(value);
        }
        return epics;
    }

    @Override
    public ArrayList<Subtask> getListOfSubtasks() {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        for (Subtask value : subtasksById.values()) {
            subtasks.add(value);
        }
        return subtasks;
    }

    @Override
    public void updateTask(Task task) {
        tasksById.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epicById.put(epic.getId(), epic);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasksById.put(subtask.getId(), subtask);
        updateEpicStatus(subtask.getEpicId());

    }

    @Override
    public void deleteTask(int id) {
        if (tasksById.containsKey(id)) {
            tasksById.remove(id);
        } else {
            System.out.println("Задачи с таким id нет!");
        }
    }

    @Override
    public void deleteEpic(int id) {
        if (epicById.containsKey(id)) {
            for (int subtasks : epicById.get(id).getSubtasksForEpic()) {
                subtasksById.remove(subtasks);
            }
            epicById.remove(id);
        } else {
            System.out.println("Задачи с таким id нет!");
        }
    }

    @Override
    public void deleteSubtask(int id) {
        if (subtasksById.containsKey(id)) {
            int epicId = subtasksById.get(id).getEpicId();
            subtasksById.remove(id);
            for (Integer i : epicById.get(epicId).getSubtasksForEpic()) {
                if (i == id) {
                    epicById.get(epicId).getSubtasksForEpic().remove(i);
                    break;
                }
            }
            updateEpicStatus(epicId);
        } else {
            System.out.println("Задачи с таким id нет!");
        }
    }

    @Override
    public Task getTaskById(int id) {
        Task task = tasksById.get(id);
        if (task != null) {
            historyManager.addTaskView(task);
            return task;
        } else {
            System.out.println("Задачи с таким id нет!");
            return null;
        }
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epicById.get(id);
        if (epic != null) {
            historyManager.addTaskView(epic);
            return epic;
        } else {
            System.out.println("Задачи с таким id нет!");
            return null;
        }
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasksById.get(id);
        if (subtask != null) {
            historyManager.addTaskView(subtask);
            return subtask;
        } else {
            System.out.println("Задачи с таким id нет!");
            return null;
        }
    }

    @Override
    public void deleteAllTasks() {
        tasksById.clear();
    }

    @Override
    public void deleteAllEpics() {
        subtasksById.clear();
        epicById.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        subtasksById.clear();
        for (Integer id : epicById.keySet()) {
            epicById.get(id).getSubtasksForEpic().clear();
            updateEpicStatus(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public ArrayList<Subtask> getSubtaskByEpic(Epic epic) {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        for (int id : epic.getSubtasksForEpic()) {
            subtasks.add(subtasksById.get(id));
        }
        return subtasks;
    }

    @Override
    public void updateEpicStatus(int id) {
        if (epicById.get(id).getSubtasksForEpic().isEmpty()) {
            epicById.get(id).setTaskStatus(TaskStatus.NEW);
            return;
        }
        int statusNew = 0;
        int statusDone = 0;
        int statusInProgress = 0;
        for (int subtaskId : epicById.get(id).getSubtasksForEpic()) {
            if (subtasksById.get(subtaskId).getTaskStatus() == TaskStatus.DONE) {
                statusDone += 1;
            } else if (subtasksById.get(subtaskId).getTaskStatus() == TaskStatus.NEW) {
                statusNew += 1;
            } else {
                statusInProgress += 1;
                break;
            }
        }
        if ((statusDone == 0) && (statusInProgress == 0)) {
            epicById.get(id).setTaskStatus(TaskStatus.NEW);
        } else if ((statusNew == 0) && (statusInProgress == 0)) {
            epicById.get(id).setTaskStatus(TaskStatus.DONE);
        } else {
            epicById.get(id).setTaskStatus(TaskStatus.IN_PROGRESS);
        }

    }
}





