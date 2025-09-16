package ru.practicum.manager;

import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();

    private int id = 0;

    public Integer createTask(Task taskNew) {
        taskNew.setId(id);
        tasks.put(id, taskNew);
        id++;
        return taskNew.getId();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void deleteTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else {
            System.out.println("Такого номера Task нет.");
        }
    }

    public Task updateTask(Task taskNew) {
        if (tasks.containsKey(taskNew.getId())) {
            tasks.put(taskNew.getId(), taskNew);
        }
        return taskNew;
    }

    public Integer createEpic(Epic epicNew) {
        epicNew.setId(id);
        epicNew.setTaskStatus(TaskStatus.NEW);
        epics.put(id, epicNew);
        id++;
        return epicNew.getId();
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public void deleteEpicById(int id) {
        if (epics.containsKey(id)) {
            for (int i : epics.get(id).getEpicSubTasksID()) {
                subTasks.remove(i);
            }
            epics.remove(id);
        } else {
            System.out.println("Такого номера Epic нет.");
        }
    }

    public ArrayList<SubTask> getAllEpicSubtasks(int id) {
        ArrayList<SubTask> newL = new ArrayList<>();
        if (epics.containsKey(id)) {
            for (int i : epics.get(id).getEpicSubTasksID()) {
                newL.add(subTasks.get(i));
            }
        } else {
            System.out.println("Такого epic нет.");
        }
        return newL;
    }

    private void epicCheckStatus(int id) {
        ArrayList<TaskStatus> subList = new ArrayList<>();
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            if (epic.getEpicSubTasksID().isEmpty()) {
                epic.setTaskStatus(TaskStatus.NEW);
                return;
            }
            for (int i : epics.get(id).getEpicSubTasksID())
                subList.add(subTasks.get(i).getTaskStatus());
            boolean allSame = subList.stream().distinct().count() == 1;
            if (allSame && subList.getFirst().equals(TaskStatus.DONE)) {
                epic.setTaskStatus(TaskStatus.DONE);
            } else if (allSame && subList.getFirst().equals(TaskStatus.NEW)) {
                epic.setTaskStatus(TaskStatus.NEW);
            } else {
                epic.setTaskStatus(TaskStatus.IN_PROGRESS);
            }
        }
    }

    public Epic updateEpic(Epic epicNew) {
        if (epics.containsKey(epicNew.getId())) {
            Epic epic = epics.get(epicNew.getId());
            epic.setName(epicNew.getName());
            epic.setDescription(epicNew.getDescription());
        }
        return epicNew;
    }

    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    public Integer createSubTask(SubTask subTaskNew) {
        int epicID = subTaskNew.getEpicId();
        if (!epics.containsKey(epicID)) {
            System.out.println("Такого epic нет.");
            return -1;
        }
        subTaskNew.setId(id);
        subTasks.put(id, subTaskNew);
        epics.get(epicID).addSubTaskToEpicId(subTaskNew);
        epicCheckStatus(epicID);
        id++;
        return subTaskNew.getId();
    }

    public ArrayList<SubTask> getAllSubTask() {
        return new ArrayList<>(subTasks.values());
    }

    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }

    public void deleteSubTaskById(int id) {
        if (subTasks.containsKey(id)) {
            int epicId = subTasks.get(id).getEpicId();
            epics.get(epicId).deleteEpicSubTask(id);
            subTasks.remove(id);
            epicCheckStatus(epicId);
        }
    }

    public SubTask updateSubTask(SubTask subTaskNew) {
        if (subTasks.containsKey(subTaskNew.getId())) {
            subTasks.put(subTaskNew.getId(), subTaskNew);
            epicCheckStatus(subTaskNew.getEpicId());
        }
        return subTaskNew;
    }

    public void deleteAllSubTasks() {
        for (Epic epic : epics.values()) {
            epic.cleanEpicSubTask();
            epicCheckStatus(epic.getId());
        }
        subTasks.clear();
    }

}
