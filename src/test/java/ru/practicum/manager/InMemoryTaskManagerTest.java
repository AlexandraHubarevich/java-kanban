package ru.practicum.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryTaskManagerTest {

    InMemoryTaskManager manager = new InMemoryTaskManager();
    Epic epic = new Epic("epic0", "epicDesc0", TaskStatus.NEW);
    Task task = new Task("task0", "desc0", TaskStatus.NEW);
    SubTask subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, epic.getId());

    @Test
    public void checkInMemoryTaskManagerAddTaskAndCanFindID() {
        manager.createTask(task);
        assertTrue(manager.getTasks().size() == 1);
        Task taskFound = manager.getTaskById(task.getId());
        assertTrue(taskFound.equals(task));
    }

    @Test
    public void checkInMemoryTaskManagerAddEpicAndCanFindID() throws CloneNotSupportedException {
        manager.createEpic(epic);
        assertTrue(manager.getEpics().size() == 1);
        Epic epicFound = manager.getEpicById(epic.getId());
        assertTrue(epicFound.equals(epic));
    }

    @Test
    public void checkInMemoryTaskManagerAddSubTaskAndCanFindID() {
        manager.createEpic(epic);
        manager.createSubTask(subTask);
        assertTrue(manager.getSubTasks().size() == 1);
        SubTask subTaskFound = manager.getSubTaskById(subTask.getId());
        assertTrue(subTaskFound.equals(subTask));
    }
}