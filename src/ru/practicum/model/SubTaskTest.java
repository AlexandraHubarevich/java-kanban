package ru.practicum.model;

import org.junit.jupiter.api.Test;
import ru.practicum.manager.InMemoryTaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubTaskTest {
    InMemoryTaskManager manager = new InMemoryTaskManager();

    @Test
    public void checkSubTaskIsEqualIfSameID() {
        Epic epic = new Epic("epic1", "epicDesc1", TaskStatus.NEW);
        manager.createEpic(epic);

        SubTask subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, epic.getId());
        SubTask subTask1 = new SubTask("subTask1", "subTaskDesc1", TaskStatus.DONE, epic.getId());
        subTask.setId(1);
        subTask1.setId(1);
        assertTrue(subTask.equals(subTask1));
    }

    @Test
    public void checkSubTaskCannotBeCreatedIfIncorrectEpicID() throws CloneNotSupportedException {
        SubTask subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, 256);
        manager.createSubTask(subTask);
        assertTrue(manager.createSubTask(subTask) == -1);
        assertTrue(manager.getSubTaskById(subTask.getId()) == null);
        assertTrue(manager.getAllSubTask().size() == 0);
    }

    @Test
    void testSubTaskCannotBeAddedAsEpic() {
        Epic epic4 = new Epic("epic4", "epicDesc4", TaskStatus.NEW);
        manager.createEpic(epic4);
        SubTask subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, epic4.getId());
        manager.createSubTask(subTask);
        int idSubTask = subTask.getId();
        SubTask subTask1 = new SubTask("subTask0UPD", "subTaskDescUPD", TaskStatus.DONE, idSubTask);
        subTask1.setId(idSubTask);
        manager.updateSubTask(subTask1);
        List<SubTask> listSub = manager.getAllEpicSubtasks(idSubTask);
        assertTrue(listSub.size() == 0);
        assertTrue(!listSub.contains(subTask1));
    }
}

