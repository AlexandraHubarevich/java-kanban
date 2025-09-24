package ru.practicum.model;

import org.junit.jupiter.api.Test;
import ru.practicum.manager.InMemoryTaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class EpicTest {
    InMemoryTaskManager manager = new InMemoryTaskManager();

    @Test
    public void checkEpicIsEqualIfSameID() {
        Epic epic = new Epic("epic1", "epicDesc1", TaskStatus.NEW);
        Epic epic1 = new Epic("epic2", "epicDesc2", TaskStatus.NEW);
        epic.setId(1);
        epic1.setId(1);
        assertTrue(epic.equals(epic1));
    }

    @Test
    public void checkGetAllEpicSubTaskWorksCorrect() {
        Epic epic3 = new Epic("epic3", "epicDesc3", TaskStatus.NEW);
        SubTask subTask1 = new SubTask("subTask1", "subTaskDesc1", TaskStatus.DONE, epic3.getId());
        SubTask subTask2 = new SubTask("subTask2", "subTaskDesc2", TaskStatus.NEW, epic3.getId());
        manager.createEpic(epic3);
        int epic3ID = epic3.getId();
        manager.createSubTask(subTask1);
        manager.createSubTask(subTask2);
        List<SubTask> returnFromEpic = manager.getAllEpicSubtasks(epic3ID);
        assertTrue(returnFromEpic.size() == 2);
        assertTrue(returnFromEpic.contains(subTask1));
        assertTrue(returnFromEpic.contains(subTask2));
        assertTrue(epic3.getTaskStatus().equals(TaskStatus.IN_PROGRESS));
    }

    @Test
    void testEpicCannotBeAddedAsSubtask() {
        Epic epic4 = new Epic("epic4", "epicDesc4", TaskStatus.NEW);
        manager.createEpic(epic4);
        int idEpic = epic4.getId();
        epic4.getEpicSubTasksID().add(idEpic);
        List<SubTask> listOfSubTask = manager.getAllEpicSubtasks(idEpic);
        assertTrue(listOfSubTask.get(0) == null);
        assertTrue(!listOfSubTask.contains(epic4));
    }
}