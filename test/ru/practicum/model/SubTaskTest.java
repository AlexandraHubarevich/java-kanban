package ru.practicum.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.practicum.manager.InMemoryTaskManager;

import java.util.List;

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
        Assertions.assertTrue(subTask.equals(subTask1));
    }

    @Test
    public void checkSubTaskCannotBeCreatedIfIncorrectEpicID() throws CloneNotSupportedException {
        SubTask subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, 256);
        manager.createSubTask(subTask);
        Assertions.assertTrue(manager.createSubTask(subTask) == -1);
        Assertions.assertTrue(manager.getSubTaskById(subTask.getId()) == null);
        Assertions.assertTrue(manager.getAllSubTask().size() == 0);
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
        epic4.addSubTaskToEpicId(idSubTask);
        List<SubTask> listSub = manager.getAllEpicSubtasks(idSubTask);
        Assertions.assertTrue(listSub.size() == 0);
        Assertions.assertTrue(!listSub.contains(subTask1));
    }
}
