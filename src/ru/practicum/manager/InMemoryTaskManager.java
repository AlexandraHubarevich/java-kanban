package ru.practicum.manager;

import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private int id = 0;
    final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public Integer createTask(Task taskNew) {
        taskNew.setId(id);
        tasks.put(id, taskNew);
        id++;
        return taskNew.getId();
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public Task getTaskById(int id) throws CloneNotSupportedException {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public void deleteTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else {
            System.out.println("Такого номера Task нет.");
        }
    }

    @Override
    public Task updateTask(Task taskNew) {
        if (tasks.containsKey(taskNew.getId())) {
            tasks.put(taskNew.getId(), taskNew);
        }
        return taskNew;
    }

    @Override
    public Integer createEpic(Epic epicNew) {
        epicNew.setId(id);
        epicNew.setTaskStatus(TaskStatus.NEW);
        epics.put(id, epicNew);
        id++;
        return epicNew.getId();
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public Epic getEpicById(int id) throws CloneNotSupportedException {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
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

    @Override
    public List<SubTask> getAllEpicSubtasks(int id) {
        List<SubTask> newL = new ArrayList<>();
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
        List<TaskStatus> subList = new ArrayList<>();
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

    @Override
    public Epic updateEpic(Epic epicNew) {
        if (epics.containsKey(epicNew.getId())) {
            Epic epic = epics.get(epicNew.getId());
            epic.setName(epicNew.getName());
            epic.setDescription(epicNew.getDescription());

        }
        return epicNew;
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    @Override
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

    @Override
    public List<SubTask> getAllSubTask() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public SubTask getSubTaskById(int id) throws CloneNotSupportedException {
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public void deleteSubTaskById(int id) {
        if (subTasks.containsKey(id)) {
            int epicId = subTasks.get(id).getEpicId();
            epics.get(epicId).deleteEpicSubTask(id);
            subTasks.remove(id);
            epicCheckStatus(epicId);
        }
    }

    @Override
    public SubTask updateSubTask(SubTask subTaskNew) {
        if (subTasks.containsKey(subTaskNew.getId())) {
            subTasks.put(subTaskNew.getId(), subTaskNew);
            epicCheckStatus(subTaskNew.getEpicId());
        }
        return subTaskNew;
    }

    @Override
    public void deleteAllSubTasks() {
        for (Epic epic : epics.values()) {
            epic.cleanEpicSubTask();
            epicCheckStatus(epic.getId());
        }
        subTasks.clear();
    }
}
