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
            for (int i = 0; i < epics.get(id).getEpicSubTasksID().size(); i++)
                newL.add(getSubTaskById(epics.get(id).getEpicSubTasksID().get(i)));
        } else {
            System.out.println("Такого epic нет.");
        }
        return newL;
    }

    private void epicCheckStatus(int id) {
        ArrayList<TaskStatus> subList = new ArrayList<>();
        if (epics.containsKey(id)) {
            if (getAllEpicSubtasks(id).isEmpty()) {
                epics.get(id).setTaskStatus(TaskStatus.NEW);
                return;
            }
            for (int i = 0; i < epics.get(id).getEpicSubTasksID().size(); i++)
                subList.add(getSubTaskById(epics.get(id).getEpicSubTasksID().get(i)).getTaskStatus());
        }
        boolean allSame = subList.stream().distinct().count() == 1;
        if (allSame && subList.get(0).equals(TaskStatus.DONE)) {
            epics.get(id).setTaskStatus(TaskStatus.DONE);
        } else if (allSame && subList.get(0).equals(TaskStatus.NEW)) {
            epics.get(id).setTaskStatus(TaskStatus.NEW);
        } else {
            epics.get(id).setTaskStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public Epic updateEpic(Epic epicNew) {
        if (epics.containsKey(epicNew.getId())) {
            getEpicById(epicNew.getId()).setName(epicNew.getName());
            getEpicById(epicNew.getId()).setDescription(epicNew.getDescription());
        }
        return epicNew;
    }

    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    public Integer createSubTask(SubTask subTaskNew) {
        if (!epics.containsKey(subTaskNew.getEpicId())) {
            System.out.println("Такого epic нет.");
            return -1;
        }
        subTaskNew.setId(id);
        subTasks.put(id, subTaskNew);
        epics.get(subTaskNew.getEpicId()).addSubTaskToEpicId(subTaskNew);
        epicCheckStatus(subTaskNew.getEpicId());
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
            int epicId = getSubTaskById(id).getEpicId();
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
        for (int i = 0; i < getAllEpics().size(); i++) {
            epics.get(i).cleanEpicSubTask();
            epicCheckStatus(epics.get(i).getId());
        }
        subTasks.clear();
    }
}
