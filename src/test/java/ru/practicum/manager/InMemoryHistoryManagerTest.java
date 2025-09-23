package ru.practicum.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.model.Epic;
import ru.practicum.model.TaskStatus;

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
        assertTrue(manager.getHistory().get(0).getName().equals("epic1"));
        assertTrue(manager.getHistory().get(0).getDescription().equals("epicDesc1"));
        assertTrue(manager.getHistory().get(0).getTaskStatus().equals(TaskStatus.NEW));
    }
}