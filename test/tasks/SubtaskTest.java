package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {


    @Test
    void subtacksAreEqualToEachOtherIfTheirIdIsEqual() {
        Subtask subtask1 = new Subtask("Subtask1", "Epic1", TaskStatus.NEW, 1);
        Subtask subtask2 = new Subtask("Subtask2", "Epic2", TaskStatus.NEW, 2);
        subtask1.setId(1);
        subtask2.setId(1);

        assertEquals(subtask1, subtask2, "Задачи с одинаковым id, должны быть равны друг другу");
    }


}