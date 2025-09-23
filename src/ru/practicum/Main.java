package ru.practicum;

import ru.practicum.manager.Managers;
import ru.practicum.manager.TaskManager;
import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        TaskManager manager = Managers.getDefault();
        Task task = new Task("task0", "desc0", TaskStatus.NEW);
        Task task1 = new Task("task1", "desc1", TaskStatus.NEW);
        manager.createTask(task);
        manager.createTask(task1);

        Epic epic = new Epic("epic0", "epicDesc0", TaskStatus.NEW);
        Epic epic1 = new Epic("epic1", "epicDesc1", TaskStatus.NEW);
        manager.createEpic(epic);
        manager.createEpic(epic1);
        SubTask subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, epic.getId());
        SubTask subTask1 = new SubTask("subTask1", "subTaskDesc1", TaskStatus.DONE, epic.getId());
        SubTask subTask2 = new SubTask("subTask2", "subTaskDesc2", TaskStatus.DONE, epic1.getId());

        manager.createSubTask(subTask);
        manager.createSubTask(subTask1);
        manager.createSubTask(subTask2);


        manager.getTaskById(task.getId());
        manager.getTaskById(task1.getId());
        manager.getEpicById(epic.getId());
        manager.getEpicById(epic1.getId());
        manager.getSubTaskById(subTask.getId());
        manager.getSubTaskById(subTask1.getId());
        manager.getSubTaskById(subTask2.getId());

        Task task2 = new Task("task1changed", "desc1changed", TaskStatus.NEW);
        task2.setId(task1.getId());
        Epic epic2 = new Epic("epic1changed", "epicDesc1changed", TaskStatus.NEW);
        epic2.setId(epic1.getId());
        SubTask subTask3 = new SubTask("subTask2changed", "subTaskDesc2changed", TaskStatus.DONE, epic1.getId());
        subTask3.setId(subTask2.getId());

        manager.updateTask(task2);
        manager.updateEpic(epic2);
        manager.updateSubTask(subTask3);


        manager.getTaskById(task2.getId());
        manager.getEpicById(epic2.getId());
        manager.getSubTaskById(subTask3.getId());

        printAllTasks(manager);
    }

    public static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Task task : manager.getAllEpicSubtasks(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubTask()) {
            System.out.println(subtask);
        }
        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}