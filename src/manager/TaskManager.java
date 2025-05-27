package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

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

    public void createSubtask(Subtask subtask, Epic epic) {
        subtask.setId(nextId++);
        subtasksById.put(subtask.getId(), subtask);
        subtask.setEpicId(epic.getId());
        epic.getSubtasksForEpic().add(subtask.getId());
        updateEpicStatus(epic.getId());
    }

    public HashMap<Integer, Task> printAllTasks() {
        return tasksById;
    }

    public HashMap<Integer, Epic> printAllEpics() {
        return epicById;
    }

    public HashMap<Integer, Subtask> printAllSubtasks() {
        return subtasksById;
    }

    public void updateTask(Task task) {
        tasksById.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) { //ToDo
        epicById.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {  //Todo
        subtasksById.put(subtask.getId(), subtask);
        updateEpicStatus(subtask.getEpicId());

    }

    public void deleteTask(int id) { //todo
        if (tasksById.containsKey(id)) {
            tasksById.remove(id);
        } else if (epicById.containsKey(id)) {
            for (int subtasks : epicById.get(id).getSubtasksForEpic()) {
                subtasksById.remove(subtasks);
            }
            epicById.remove(id);
        } else if (subtasksById.containsKey(id)) {
            int epicId = subtasksById.get(id).getEpicId();
            subtasksById.remove(id);
            updateEpicStatus(epicId);
        } else {
            System.out.println("Задачи с таким id нет!");
        }
    }

    public Task getTaskById(int id) {
        if (tasksById.get(id) != null) {
            return tasksById.get(id);
        } else if (epicById.get(id) != null) {
            return epicById.get(id);
        } else if (subtasksById.get(id) != null) {
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
    }

    public void getSubtaskByEpic(Epic epic) {
        for (int id : epic.getSubtasksForEpic()) {
            System.out.println(subtasksById.get(id));
        }
    }

    public void updateEpicStatus(int id) {
        if (epicById.get(id).getSubtasksForEpic().isEmpty()) {
            epicById.get(id).setTaskStatus(TaskStatus.NEW);
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


