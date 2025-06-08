package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.List;

public interface TaskManager {
    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subtask);

    List<Task> getListOfTasks();

    List<Epic> getListOfEpics();

    List<Subtask> getListOfSubtasks();

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    void deleteTask(int id);

    void deleteEpic(int id);

    void deleteSubtask(int id);

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();

    List<Subtask> getSubtaskByEpic(Epic epic);

    void updateEpicStatus(int id);

    List<Task> getHistory();

}
