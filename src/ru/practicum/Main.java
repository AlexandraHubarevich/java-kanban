package ru.practicum;

import ru.practicum.manager.TaskManager;
import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Task task = new Task("task1", "desc1", TaskStatus.NEW);
        Task task1 = new Task("task2", "desc2", TaskStatus.NEW);
        manager.createTask(task);
        manager.createTask(task1);
        System.out.println(manager.getAllTasks());

        Task task14 = new Task("task2change", "desc2change", TaskStatus.DONE, task1.getId());

        Epic epic = new Epic("epic1", "epicDesc1", TaskStatus.NEW);
        Epic epic1 = new Epic("epic2", "epicDesc2", TaskStatus.NEW);

        manager.createEpic(epic);
        manager.createEpic(epic1);
        System.out.println(manager.getAllEpics());

        Epic epic21 = new Epic("epic2ch", "epicDesc2ch", TaskStatus.DONE, epic.getId());
        SubTask subTask = new SubTask("subTask1", "subTaskDesc1", TaskStatus.DONE, epic.getId());
        SubTask subTask1 = new SubTask("subTask2", "subTaskDesc2", TaskStatus.DONE, epic.getId());
        SubTask subTask2 = new SubTask("subTask3", "subTaskDesc3", TaskStatus.DONE, epic1.getId());

        manager.createSubTask(subTask);
        manager.createSubTask(subTask1);
        manager.createSubTask(subTask2);
        SubTask subTask23 = new SubTask("subTask3ch", "subTaskDesc3ch", TaskStatus.NEW, epic.getId(), subTask.getId());

        System.out.println(manager.getAllSubTask());
        manager.updateTask(task14);
        manager.updateEpic(epic21);

        manager.updateSubTask(subTask23);
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubTask());

        manager.deleteEpicById(epic.getId());
        manager.deleteSubTaskById(subTask.getId());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubTask());
    }
}