package ru.practicum.model;
import org.junit.jupiter.api.Test;
import ru.practicum.manager.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    InMemoryTaskManager manager = new InMemoryTaskManager();

    @Test
    public void checkTaskIsEqualIfSameID() {
        Task task = new Task("task1", "desc1", TaskStatus.NEW);
        Task task1 = new Task("task2", "desc2", TaskStatus.NEW);
        task.setId(1);
        task1.setId(1);
        assertTrue(task.equals(task1));
    }
}
