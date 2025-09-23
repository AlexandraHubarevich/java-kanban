package ru.practicum.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManagersTest {
    TaskManager taskManager = Managers.getDefault();

    @Test
    public void checkManagerReturnNewManager() {
        assertNotNull(taskManager);
        assertTrue(taskManager instanceof InMemoryTaskManager);
        assertTrue(taskManager.getHistory() != null);
    }

    @Test
    public void checkManagerDontChangeTheTaskFields() {
        Task task = new Task("task0", "desc0", TaskStatus.NEW);
        taskManager.createTask(task);
        int taskId = task.getId();
        Task taskCreated = taskManager.getTaskById(taskId);
        assertTrue(taskCreated.getName().equals("task0"));
        assertTrue(taskCreated.getDescription().equals("desc0"));
        assertTrue(taskCreated.getTaskStatus().equals(TaskStatus.NEW));
    }
}




