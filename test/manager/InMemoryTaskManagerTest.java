package manager;

import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {


    private final HashMap<Integer, Task> tasksByIdTest = new HashMap<>();
    private final HashMap<Integer, Epic> epicByIdTest = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasksByIdTest = new HashMap<>();

    private Integer nextId = 1;


    @Test
    void addingTasksOfDifferentTypesAndCanFindThemById() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", TaskStatus.NEW);
        task.setId(nextId++);
        tasksByIdTest.put(task.getId(), task);
        assertNotNull(task, "Задача не найдена.");
        assertEquals(task, tasksByIdTest.get(1), "Задачи не совпадают.");
        assertEquals(1, tasksByIdTest.size(), "Неверное количество задач.");
        assertNotNull(tasksByIdTest, "Задачи не возвращаются.");

        Epic epic3 = new Epic("Test addNewEpic", "Test addNewEpic description");
        epic3.setId(nextId++);
        epicByIdTest.put(epic3.getId(), epic3);
        assertNotNull(epic3, "Задача не найдена.");
        assertEquals(epic3, epicByIdTest.get(2), "Задачи не совпадают.");
        assertEquals(1, epicByIdTest.size(), "Неверное количество задач.");
        assertNotNull(epicByIdTest, "Задачи не возвращаются.");

        Subtask subtask3 = new Subtask("Test addNewSubtask", "Test addNewSubtask description",
                                        TaskStatus.NEW, epic3.getId());
        subtask3.setId(nextId++);
        subtasksByIdTest.put(subtask3.getId(), subtask3);
        assertNotNull(subtask3, "Задача не найдена.");
        assertEquals(subtask3, subtasksByIdTest.get(3), "Задачи не совпадают.");
        assertEquals(1, subtasksByIdTest.size(), "Неверное количество задач.");
        assertNotNull(subtasksByIdTest, "Задачи не возвращаются.");
    }

}