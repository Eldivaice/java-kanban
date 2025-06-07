package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void tasksAreEqualToEachOtherIfTheirIdIsEqual() {
        Task task1 = new Task("Task1", "Desc1", TaskStatus.NEW);
        Task task2 = new Task("Task2", "Desc2", TaskStatus.NEW);
        task1.setId(1);
        task2.setId(1);

        assertEquals(task1, task2, "Задачи с одинаковым id, должны быть равны друг другу");
    }

    @Test
    void mustReturnTheTaskId() {
        Task task3 = new Task("Task3", "Desc3", TaskStatus.NEW);
        task3.setId(3);
        assertEquals(3, task3.getId(), "Id task3 равно 3");
    }
}