package ru.practicum.manager;

import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {


    Integer createTask(Task taskNew);
    ArrayList<Task> getAllTasks();
    void deleteAllTasks();
    Task getTaskById(int id);
    void deleteTaskById(int id);
    Task updateTask(Task taskNew);
    Integer createEpic(Epic epicNew);
    ArrayList<Epic> getAllEpics();
    Epic getEpicById(int id) throws CloneNotSupportedException;
    void deleteEpicById(int id);
    ArrayList<SubTask> getAllEpicSubtasks(int id);
    void epicCheckStatus(int id);
    Epic updateEpic(Epic epicNew);
    void deleteAllEpics();
    Integer createSubTask(SubTask subTaskNew);
    ArrayList<SubTask> getAllSubTask();
    SubTask getSubTaskById(int id);
    void deleteSubTaskById(int id);
    SubTask updateSubTask(SubTask subTaskNew);
    void deleteAllSubTasks();
    List<Task> getHistory();
}
