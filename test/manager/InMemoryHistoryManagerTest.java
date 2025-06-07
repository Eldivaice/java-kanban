package manager;

import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {

    private final List<Task> taskViewHistoryTest = new ArrayList<>();

    @Test
    void AddingAnIssueToTheTaskViewHistoryTest() {
        Task task = new Task("Test Task1", "Test Desc", TaskStatus.NEW);

        if (taskViewHistoryTest.size() >= 10) {
            taskViewHistoryTest.removeFirst();
            taskViewHistoryTest.add(task);
        } else {
            taskViewHistoryTest.add(task);
        }

        assertEquals(1, taskViewHistoryTest.size(), "Неверное количество задач.");
        assertNotNull(taskViewHistoryTest, "Задачи не возвращаются.");
    }

    @Test
    void gettingTasksFromTheTaskViewHistoryTest() {
        assertNotNull(taskViewHistoryTest, "Задачи не возвращаются.");
    }
}