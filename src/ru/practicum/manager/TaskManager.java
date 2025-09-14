package ru.practicum.manager;

import ru.practicum.model.Epic;
import ru.practicum.model.SubTask;
import ru.practicum.model.Task;
import ru.practicum.model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> task = new HashMap<>();
    private final HashMap<Integer, Epic> epic = new HashMap<>();
    private final HashMap<Integer, SubTask> subTask = new HashMap<>();

    private int id = 0;

    public Integer createTask(Task taskNew) {
        taskNew.setId(id);
        task.put(id, taskNew);
        id++;
        return taskNew.getId();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(task.values());
    }

    public void deleteAllTasks() {
        task.clear();
    }

    public Task getTaskById(Integer id) {
        return task.get(id);
    }

    public void deleteTaskById(Integer id) {
        if (task.containsKey(id)) {
            task.remove(id);
        } else {
            System.out.println("Такого номера Task нет.");
        }
    }

    public Integer updateTask(Task taskNew) {
        if (task.containsKey(taskNew.getId())) {
            task.put(taskNew.getId(), taskNew);
        }
        return taskNew.getId();
    }

    public Integer createEpic(Epic epicNew) {
        epicNew.setId(id);
        epicNew.setTaskStatus(TaskStatus.NEW);
        epic.put(id, epicNew);
        id++;
        return epicNew.getId();
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epic.values());
    }

    public Epic getEpicById(Integer id) {
        return epic.get(id);
    }

    public void deleteEpicById(Integer id) {
        if (epic.containsKey(id)) {
            for (int i = 0; i < getAllEpicSubtasks(id).size(); i++) {
                subTask.remove(getAllEpicSubtasks(id).get(i));
            }
            epic.remove(id);
        } else {
            System.out.println("Такого номера Epic нет.");
        }
    }

    public ArrayList<Integer> getAllEpicSubtasks(int id) {
        ArrayList<Integer> newL = new ArrayList<>();
        if (epic.containsKey(id)) {
            newL = epic.get(id).getEpicSubTasksID(id);
        } else {
            System.out.println("Такого epic нет.");
        }
        return newL;
    }

    private void epicCheckStatus(int id) {
        ArrayList<TaskStatus> subList = new ArrayList<>();
        if (epic.containsKey(id)) {
            if (getAllEpicSubtasks(id).isEmpty()) {
                epic.get(id).setTaskStatus(TaskStatus.NEW);
                return;
            }
            for (int i = 0; i < getAllEpicSubtasks(id).size(); i++) {
                subList.add(getSubTaskById(getAllEpicSubtasks(id).get(i)).getTaskStatus());
            }
            boolean allSame = subList.stream().distinct().count() == 1;
            for (int i = 0; i < getAllEpicSubtasks(id).size(); i++) {
                if (allSame && subList.get(i).equals(TaskStatus.DONE)) {
                    epic.get(id).setTaskStatus(TaskStatus.DONE);
                } else if (allSame && subList.get(i).equals(TaskStatus.NEW)) {
                    epic.get(id).setTaskStatus(TaskStatus.NEW);
                } else {
                    epic.get(id).setTaskStatus(TaskStatus.IN_PROGRESS);
                }
            }
        }
    }

    public Integer updateEpic(Epic epicNew) {
        if (epic.containsKey(epicNew.getId())) {
            getEpicById(epicNew.getId()).setName(epicNew.getName());
            getEpicById(epicNew.getId()).setDescription(epicNew.getDescription());
        }
        return epicNew.getId();
    }

    public void deleteAllEpics() {
        epic.clear();
        subTask.clear();
    }

    public Integer createSubTask(SubTask subTaskNew) {
        if (!epic.containsKey(subTaskNew.getEpicId())) {
            System.out.println("Такого epic нет.");
            return -1;
        }
        subTaskNew.setId(id);
        subTask.put(id, subTaskNew);
        epic.get(subTaskNew.getEpicId()).addSubTaskToEpicId(subTaskNew);
        epicCheckStatus(subTaskNew.getEpicId());
        id++;
        return subTaskNew.getId();
    }

    public ArrayList<SubTask> getAllSubTask() {
        return new ArrayList<>(subTask.values());
    }

    public SubTask getSubTaskById(Integer id) {
        return subTask.get(id);
    }

    public void deleteSubTaskById(Integer id) {
        if (subTask.containsKey(id)) {
            int epicId = getSubTaskById(id).getEpicId();
            epic.get(epicId).deleteEpicSubTask(id);
            subTask.remove(id);
            epicCheckStatus(epicId);
        }
    }

    public Integer updateSubTask(SubTask subTaskNew) {
        if (subTask.containsKey(subTaskNew.getId())) {
            subTask.put(subTaskNew.getId(), subTaskNew);
            epicCheckStatus(subTaskNew.getEpicId());
        }
        return subTaskNew.getId();
    }

    public void deleteAllSubTasks() {
        for (int i = 0; i < getAllEpics().size(); i++) {
            getAllEpics().get(i).cleanEpicSubTask();
            epicCheckStatus(getAllEpics().get(i).getId());
        }
        subTask.clear();
    }
}
