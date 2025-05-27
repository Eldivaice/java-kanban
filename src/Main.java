import manager.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("task1", "desc1", TaskStatus.NEW);
        Epic epic1 = new Epic("epic1", "desc1", new ArrayList<>());
        Task task2 = new Task("task2", "desc2", TaskStatus.IN_PROGRESS);
        Epic epic2 = new Epic("epic2", "desc2", new ArrayList<>());
        Subtask sub1 = new Subtask("sub1", "epic1", TaskStatus.NEW);
        Subtask sub2 = new Subtask("sub2", "epic2", TaskStatus.NEW);
        Subtask sub3 = new Subtask("sub3", "epic2", TaskStatus.NEW);

        taskManager.createTask(task1);
        taskManager.createEpic(epic1);
        taskManager.createTask(task2);
        taskManager.createEpic(epic2);
        taskManager.createSubtask(sub1, epic1);
        taskManager.createSubtask(sub2, epic2);
        taskManager.createSubtask(sub3, epic2);

        System.out.println("Cписок тасок, эпиков и сабтасок");
        System.out.println(taskManager.printAllTasks());
        System.out.println(taskManager.printAllEpics());
        System.out.println(taskManager.printAllSubtasks());
        System.out.println("========");
        System.out.println();

        System.out.println("Изменили статус таски, эпик должен стать DONE");
        System.out.println(taskManager.printAllEpics());
        sub1.setTaskStatus(TaskStatus.DONE);
        taskManager.updateSubtask(sub1);
        System.out.println(taskManager.printAllSubtasks());
        System.out.println(taskManager.printAllEpics());
        System.out.println("======");
        System.out.println();

        System.out.println("Удаляем таску 2");
        taskManager.deleteTask(3);
        System.out.println(taskManager.printAllTasks());
        System.out.println("=======");
        System.out.println();

        System.out.println("Вывод всех тасок эпика №2");
        taskManager.getSubtaskByEpic(epic2);
        System.out.println("=========");
        System.out.println();

        System.out.println("Меняем статус сабтаски, эпик №2 должен стать IN_PROGRESS");
        sub2.setTaskStatus(TaskStatus.DONE);
        taskManager.updateSubtask(sub2);
        System.out.println(taskManager.printAllEpics());
        System.out.println("=======");
        System.out.println();

        System.out.println("Удаляем эпик №2, должны удалиться 2 сабтаски");
        taskManager.deleteTask(4);
        System.out.println(taskManager.printAllEpics());
        System.out.println(taskManager.printAllSubtasks());

    }
}
