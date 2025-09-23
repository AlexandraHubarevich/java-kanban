package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.practicum.manager.InMemoryTaskManager;
import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubTaskTest {
    InMemoryTaskManager manager = new InMemoryTaskManager();

    @Test
    public void checkSubTaskIsEqualIfSameID() {
        Epic epic = new Epic("epic1", "epicDesc1", TaskStatus.NEW);
        manager.createEpic(epic);

        SubTask subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, epic.getId());
        SubTask subTask1 = new SubTask("subTask1", "subTaskDesc1", TaskStatus.DONE, epic.getId());
        manager.createSubTask(subTask);
        manager.createSubTask(subTask1);
        final int firstID = subTask.getId();
        final int secondID = subTask1.getId();
        Assertions.assertNotEquals(firstID, secondID, "Ids should not be equal");

        assertTrue(manager.getSubTaskById(firstID).getName().equals(subTask.getName()));
        assertTrue(manager.getSubTaskById(firstID).getTaskStatus().equals(subTask.getTaskStatus()));
        assertTrue(manager.getSubTaskById(firstID).getDescription().equals(subTask.getDescription()));
        assertTrue(manager.getSubTaskById(firstID).getId() == subTask.getId());
    }

    @Test
    public void checkSubTaskCannotBeCreatedIfIncorrectEpicID() {
        SubTask subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, 256);
        manager.createSubTask(subTask);
        assertTrue(manager.createSubTask(subTask) == -1);
        assertTrue(manager.getSubTaskById(subTask.getId()) == null);
        assertTrue(manager.getSubTasks().size()==0);
    }
}
