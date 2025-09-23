package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.practicum.manager.InMemoryTaskManager;
import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.TaskStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EpicTest {
    InMemoryTaskManager manager = new InMemoryTaskManager();

    @Test
    public void checkEpicIsEqualIfSameID() throws CloneNotSupportedException {
        Epic epic = new Epic("epic1", "epicDesc1", TaskStatus.NEW);
        Epic epic1 = new Epic("epic2", "epicDesc2", TaskStatus.NEW);
        manager.createEpic(epic);
        manager.createEpic(epic1);
        final int firstID = epic.getId();
        final int secondID = epic1.getId();
        Assertions.assertNotEquals(firstID, secondID, "Ids should not be equal");
        System.out.println(manager.getEpicById(firstID));
        assertTrue(manager.getEpicById(firstID).getName().equals(epic.getName()));
        assertTrue(manager.getEpicById(firstID).getTaskStatus().equals(epic.getTaskStatus()));
        assertTrue(manager.getEpicById(firstID).getDescription().equals(epic.getDescription()));
        assertTrue(manager.getEpicById(firstID).getId() == epic.getId());
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
        ArrayList<SubTask> returnFromEpic = manager.getAllEpicSubtasks(epic3ID);
        assertTrue(returnFromEpic.size() ==2);
        assertTrue(returnFromEpic.contains(subTask1));
        assertTrue(returnFromEpic.contains(subTask2));
        assertTrue(epic3.getTaskStatus().equals(TaskStatus.IN_PROGRESS));
    }
}