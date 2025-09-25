package ru.practicum.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryTaskManagerTest {
    private InMemoryTaskManager manager;
    private Epic epic;
    private Task task;
    private SubTask subTask;

    @BeforeEach
    public void setup() {
        manager = new InMemoryTaskManager();
        epic = new Epic("epic0", "epicDesc0", TaskStatus.NEW);
        task = new Task("task0", "desc0", TaskStatus.NEW);
        subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, epic.getId());
    }

    @Test
    public void checkInMemoryTaskManagerAddTaskAndCanFindID() throws CloneNotSupportedException {
        manager.createTask(task);
        assertTrue(manager.getAllTasks().size() == 1);
        Task taskFound = manager.getTaskById(task.getId());
        assertTrue(taskFound.equals(task));
    }

    @Test
    public void checkInMemoryTaskManagerAddEpicAndCanFindID() throws CloneNotSupportedException {
        manager.createEpic(epic);
        assertTrue(manager.getAllEpics().size() == 1);
        Epic epicFound = manager.getEpicById(epic.getId());
        assertTrue(epicFound.equals(epic));
    }

    @Test
    public void checkInMemoryTaskManagerAddSubTaskAndCanFindID() throws CloneNotSupportedException {
        manager.createEpic(epic);
        manager.createSubTask(subTask);
        assertTrue(manager.getAllSubTask().size() == 1);
        SubTask subTaskFound = manager.getSubTaskById(subTask.getId());
        assertTrue(subTaskFound.equals(subTask));
    }

    @Test
    public void checkManagerDontChangeTheTaskFields() throws CloneNotSupportedException {
        Task task = new Task("task0", "desc0", TaskStatus.NEW);
        manager.createTask(task);
        int taskId = task.getId();
        Task taskCreated = manager.getTaskById(taskId);
        assertTrue(taskCreated.getName().equals("task0"));
        assertTrue(taskCreated.getDescription().equals("desc0"));
        assertTrue(taskCreated.getTaskStatus().equals(TaskStatus.NEW));
    }
}