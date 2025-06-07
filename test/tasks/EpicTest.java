package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {


    @Test
    void epicsAreEqualToEachOtherIfTheirIdIsEqual() {
        Epic epic1 = new Epic("Epic1", "Desc1");
        Epic epic2 = new Epic("Epic2", "Desc3");
        epic1.setId(1);
        epic2.setId(1);

        assertEquals(epic1, epic2, "Задачи с одинаковым id, должны быть равны друг другу");
    }



}