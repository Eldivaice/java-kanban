package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private final HashMap<Integer, Task> tasksById = new HashMap<>();
    private final HashMap<Integer, Epic> epicById = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasksById = new HashMap<>();

    private Integer nextId = 1;

    public void createTask(Task task) {
        task.setId(nextId++);
        tasksById.put(task.getId(), task);
    }

    public void createEpic(Epic epic) {
        epic.setId(nextId++);
        epicById.put(epic.getId(), epic);
    }

    public void createSubtask(Subtask subtask) {
        subtask.setId(nextId++);
        subtasksById.put(subtask.getId(), subtask);
        epicById.get(subtask.getEpicId()).getSubtasksForEpic().add(subtask.getId());
        updateEpicStatus(subtask.getEpicId());
    }

    public ArrayList<Task> getListOfTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task value : tasksById.values()) {
            tasks.add(value);
        }
        return tasks;
    }

    public ArrayList<Epic> getListOfEpics() {
        ArrayList<Epic> epics = new ArrayList<>();
        for (Epic value : epicById.values()) {
            epics.add(value);
        }
        return epics;
    }

    public ArrayList<Subtask> getListOfSubtasks() {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        for (Subtask value : subtasksById.values()) {
            subtasks.add(value);
        }
        return subtasks;
    }

    public void updateTask(Task task) {
        tasksById.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epicById.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        subtasksById.put(subtask.getId(), subtask);
        updateEpicStatus(subtask.getEpicId());

    }

    public void deleteTask(int id) {
        if (tasksById.containsKey(id)) {
            tasksById.remove(id);
        } else {
            System.out.println("Задачи с таким id нет!");
        }
    }

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

    public Task getTaskById(int id) {
        if (tasksById.get(id) != null) {
            return tasksById.get(id);
        } else {
            System.out.println("Задачи с таким id нет!");
            return null;
        }
    }

    public Epic getEpicById(int id) {
        if (epicById.get(id) != null) {
            return epicById.get(id);
        } else {
            System.out.println("Задачи с таким id нет!");
            return null;
        }
    }

    public Subtask getSubtaskById(int id) {
        if (subtasksById.get(id) != null) {
            return subtasksById.get(id);
        } else {
            System.out.println("Задачи с таким id нет!");
            return null;
        }
    }

    public void deleteAllTasks() {
        tasksById.clear();
    }

    public void deleteAllEpics() {
        subtasksById.clear();
        epicById.clear();
    }

    public void deleteAllSubtasks() {
        subtasksById.clear();
        for (Integer id : epicById.keySet()) {
            epicById.get(id).getSubtasksForEpic().clear();
            updateEpicStatus(id);
        }
    }

    public ArrayList<Subtask> getSubtaskByEpic(Epic epic) {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        for (int id : epic.getSubtasksForEpic()) {
            subtasks.add(subtasksById.get(id));
        }
        return subtasks;
    }

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





