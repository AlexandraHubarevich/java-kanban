package ru.practicum.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.model.Epic;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryHistoryManagerTest {

    InMemoryTaskManager manager = new InMemoryTaskManager();

    @Test
    public void checkPreviousVersionIsNotUpdated() throws CloneNotSupportedException {
        Epic epic = new Epic("epic1", "epicDesc1", TaskStatus.NEW);
        manager.createEpic(epic);
        Epic epic1 = new Epic("epic1UPD", "epicDesc2UPD", TaskStatus.DONE);
        epic1.setId(epic.getId());
        manager.getEpicById(epic.getId());
        manager.getHistory();
        assertTrue(manager.getHistory().size() == 1);
        manager.updateEpic(epic1);
        manager.getEpicById(epic.getId());
        assertTrue(manager.getHistory().size() == 2);
        Task epicFirst = manager.getHistory().get(0);
        Task epicSecond = manager.getHistory().get(1);
        assertFalse(epicFirst.getName().equals(epicSecond.getName()));
        assertFalse(epicFirst.getDescription().equals(epicSecond.getDescription()));
    }
}