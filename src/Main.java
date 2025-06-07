import manager.Managers;
import manager.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;


public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("task1", "desc1", TaskStatus.NEW);
        taskManager.createTask(task1);
        Epic epic1 = new Epic("epic1", "desc1");
        taskManager.createEpic(epic1);
        Task task2 = new Task("task2", "desc2", TaskStatus.IN_PROGRESS);
        taskManager.createTask(task2);
        Epic epic2 = new Epic("epic2", "desc2");
        taskManager.createEpic(epic2);
        Subtask sub1 = new Subtask("sub1", "epic1", TaskStatus.NEW, epic1.getId());
        taskManager.createSubtask(sub1);
        Subtask sub2 = new Subtask("sub2", "epic2", TaskStatus.NEW, epic2.getId());
        taskManager.createSubtask(sub2);
        Subtask sub3 = new Subtask("sub3", "epic2", TaskStatus.NEW, epic2.getId());
        taskManager.createSubtask(sub3);

        System.out.println("Cписок тасок, эпиков и сабтасок");
        System.out.println(taskManager.getListOfTasks());
        System.out.println(taskManager.getListOfEpics());
        System.out.println(taskManager.getListOfSubtasks());
        System.out.println("========");
        System.out.println();

        System.out.println("Изменили статус таски, эпик1 должен стать DONE");
        System.out.println(taskManager.getListOfEpics());
        sub1.setTaskStatus(TaskStatus.DONE);
        taskManager.updateSubtask(sub1);
        System.out.println(taskManager.getListOfSubtasks());
        System.out.println(taskManager.getListOfEpics());
        System.out.println("======");
        System.out.println();

        System.out.println("Просмотр истории 1 элемент");
        taskManager.getTaskById(1);
        System.out.println(taskManager.getHistory());
        System.out.println("++++++++++");
        System.out.println();
        System.out.println("Просмотр истории 10 элементов");
        taskManager.getEpicById(2);
        taskManager.getTaskById(3);
        taskManager.getEpicById(4);
        taskManager.getSubtaskById(5);
        taskManager.getSubtaskById(6);
        taskManager.getSubtaskById(7);
        taskManager.getSubtaskById(5);
        taskManager.getEpicById(4);
        taskManager.getTaskById(3);
        System.out.println(taskManager.getHistory());
        System.out.println();
        System.out.println("++++++++++");
        System.out.println();
        System.out.println("Просмотр истории после добавления 11 просмотра, первым должен стать Эпик1");
        taskManager.getTaskById(3);
        System.out.println(taskManager.getHistory());
        System.out.println("=======");
        System.out.println();

        System.out.println("Удаляем subtask 1-ого эпика");
        taskManager.deleteSubtask(3);
        System.out.println(taskManager.getSubtaskById(4));
        System.out.println(taskManager.getSubtaskById(5));
        taskManager.deleteSubtask(5);
        System.out.println(taskManager.getListOfTasks());
        System.out.println(taskManager.getListOfEpics());
        System.out.println("=======");
        System.out.println();

        System.out.println("Вывод всех тасок эпика №2");
        System.out.println(taskManager.getSubtaskByEpic(epic2));
        System.out.println("=========");
        System.out.println();

        System.out.println("Меняем статус сабтаски, эпик №2 должен стать IN_PROGRESS");
        sub2.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(sub2);
        System.out.println(taskManager.getListOfEpics());
        System.out.println("=======");
        System.out.println();

        System.out.println("Удаляем эпик №2, должны удалиться 2 сабтаски");
        taskManager.deleteEpic(4);
        System.out.println(taskManager.getListOfEpics());
        System.out.println(taskManager.getListOfSubtasks());
    }
}
