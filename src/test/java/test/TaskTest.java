package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.practicum.manager.InMemoryTaskManager;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    InMemoryTaskManager manager = new InMemoryTaskManager();

    @Test
    public void checkTaskIsEqualIfSameID() {
        Task task = new Task("task1", "desc1", TaskStatus.NEW);
        Task task1 = new Task("task2", "desc2", TaskStatus.NEW);
        manager.createTask(task);
        manager.createTask(task1);
        final int firstID = task.getId();
        final int secondID = task.getId();
        Assertions.assertNotEquals(firstID, secondID, "Ids should not be equal");

        assertTrue(manager.getTaskById(firstID).getName().equals(task.getName()));
        assertTrue(manager.getTaskById(firstID).getTaskStatus().equals(task.getTaskStatus()));
        assertTrue(manager.getTaskById(firstID).getDescription().equals(task.getDescription()));
        assertTrue(manager.getTaskById(firstID).getId() == task.getId());

    }
  }
