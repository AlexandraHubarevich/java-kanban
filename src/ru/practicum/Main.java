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
        Task task2 = new Task("task5", "desc4", TaskStatus.NEW);
        manager.createTask(task);
        manager.createTask(task1);
        manager.createTask(task2);
        System.out.println(manager.getAllTasks());
        Task task4 = new Task("task2", "desc2", TaskStatus.DONE, 45);
        manager.updateTask(task4);
        manager.getAllTasks();
        System.out.println();
        System.out.println(manager.getTaskById(45));
        Epic epic = new Epic("epic1", "epicDesc1", TaskStatus.DONE);
        Epic epic1 = new Epic("epic2", "epicDesc2", TaskStatus.NEW);
        Epic epic3 = new Epic("epic44", "ep4434c2", TaskStatus.DONE, 10);
        manager.createEpic(epic);
        manager.createEpic(epic1);
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllEpicSubtasks(0));
        SubTask subTask = new SubTask("subTask1", "subTaskDesc1", TaskStatus.DONE, 0);
        SubTask subTask1 = new SubTask("subTask2", "subTaskDesc2", TaskStatus.NEW, 0);
        SubTask subTask2 = new SubTask("subTask3", "subTaskDesc3", TaskStatus.DONE, 10);
        manager.createSubTask(subTask);
        manager.createSubTask(subTask1);
        manager.createSubTask(subTask2);
        System.out.println(manager.getAllSubTask());
        manager.deleteSubTaskById(subTask.getId());
        manager.deleteAllSubTasks();
        manager.deleteEpicById(1);
        System.out.println("After deleting");
        manager.deleteAllEpics();
        manager.updateEpic(epic3);
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllEpicSubtasks(0));
        System.out.println(manager.getAllSubTask());
    }
}