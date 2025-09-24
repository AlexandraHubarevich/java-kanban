package ru.practicum.manager;

import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {


    Integer createTask(Task taskNew);
    List<Task> getAllTasks();
    void deleteAllTasks();
    Task getTaskById(int id) throws CloneNotSupportedException;
    void deleteTaskById(int id);
    Task updateTask(Task taskNew);
    Integer createEpic(Epic epicNew);
    List<Epic> getAllEpics();
    Epic getEpicById(int id) throws CloneNotSupportedException;
    void deleteEpicById(int id);
    List<SubTask> getAllEpicSubtasks(int id);
    Epic updateEpic(Epic epicNew);
    void deleteAllEpics();
    Integer createSubTask(SubTask subTaskNew);
    List<SubTask> getAllSubTask();
    SubTask getSubTaskById(int id) throws CloneNotSupportedException;
    void deleteSubTaskById(int id);
    SubTask updateSubTask(SubTask subTaskNew);
    void deleteAllSubTasks();
    List<Task> getHistory();
}
