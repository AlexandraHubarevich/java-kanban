import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TaskManager {
    HashMap<Integer, Task> taskMap = new HashMap<>();
    HashMap<Integer, Epic> epicMap = new HashMap<>();
    HashMap<Integer, SubTask> subTaskMap = new HashMap<>();

    Object object = new Object();
    int id = new Random().nextInt(1000);

    public Task createTask(Task task) {
        task.setId(id);
        taskMap.put(id, new Task(task.getName(), task.getDescription(), task.getTaskStatus(), task.getId()));
        id++;
        return task;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskL = new ArrayList<>();
        for (Task taskValues : taskMap.values()) {
            taskL.add(taskValues);
        }
        return taskL;
    }


    public void deleteAllTasks() {
        taskMap.clear();
    }

    public Object getTaskById(Integer id) {
        if (taskMap.containsKey(id)) {
            object = taskMap.get(id);

        } else {
            System.out.println("Такого номера Task нет.");
        }
        return object;
    }

    public void deleteTaskById(Integer id) {
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
        } else {
            System.out.println("Такого номера Task нет.");
        }
    }

    public Task updateTask(Task taskNew) {
        int idForDelete;
        for (Task task : taskMap.values()) {
            if (taskNew.equals(task)) {//здесь и далее я сравниваю объекты задач по имени, те если есть задача Ремонт, то я могу ее обновить.
                idForDelete = task.getId();
                taskMap.remove(idForDelete);
                taskMap.put(taskNew.getId(), new Task(taskNew.getName(), taskNew.getDescription(), taskNew.getTaskStatus(), taskNew.getId()));
                break;
            }
        }
        return taskNew;
    }

    //EPIC methods EPIC methods EPIC methods  EPIC methods EPIC methods

    public Epic createEpic(Epic epic) {
        epic.setId(id);
        epicMap.put(id, new Epic(epic.getName(), epic.getDescription(), TaskStatus.NEW, epic.getId()));
        id++;
        return epic;
    }

    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> epicL = new ArrayList<>();
        for (Epic epicValues : epicMap.values()) {
            epicL.add(epicValues);
        }
        return epicL;
    }

    public Object getEpicById(Integer id) {

        if (epicMap.containsKey(id)) {
            object = epicMap.get(id);
        } else {
            System.out.println("Такого номера Epic нет.");
        }
        return object;
    }

    public void deleteEpicById(Integer id) {
        if (epicMap.containsKey(id)) {
            epicMap.remove(id);
        } else {
            System.out.println("Такого номера Epic нет.");
        }
    }

    public ArrayList<SubTask> getAllEpicSubtasks(int id) {
        ArrayList<SubTask> newL = new ArrayList<>();
        if (epicMap.containsKey(id)) {
            newL = epicMap.get(id).getListOfSubTask(id);
        } else {
            System.out.println("Такого epic нет.");
        }
        return newL;
    }

    public void epicCheckStatus(int id) {
        if (epicMap.containsKey(id)) {
            for (int i = 0; i < epicMap.get(id).getListOfStatus(id).size(); i++) {

                boolean allSame = epicMap.get(id).getListOfStatus(id).stream().distinct().count() == 1;
                if (epicMap.get(id).getListOfStatus(id).isEmpty()) {
                    epicMap.get(id).setTaskStatus(TaskStatus.NEW);
                } else if (allSame && epicMap.get(id).getListOfStatus(id).get(i).equals(TaskStatus.DONE)) {
                    epicMap.get(id).setTaskStatus(TaskStatus.DONE);
                } else if (allSame && epicMap.get(id).getListOfStatus(id).get(i).equals(TaskStatus.NEW)) {
                    epicMap.get(id).setTaskStatus(TaskStatus.NEW);
                } else {
                    epicMap.get(id).setTaskStatus(TaskStatus.IN_PROGRESS);
                }
            }
        }
    }

    public Epic updateEpic(Epic epicNew) {
        for (Epic epic : epicMap.values()) {
            int idForDelete;
            if (epicNew.equals(epic)) {
                idForDelete = epic.getId();
                epicMap.remove(idForDelete);
                epicMap.put(epicNew.getId(), new Epic(epicNew.getName(), epicNew.getDescription(), TaskStatus.NEW, epicNew.getId()));
                break;
            }
        }
        return epicNew;
    }

    public void deleteAllEpics() {
        epicMap.clear();

    }

    //SubTask methods SubTask methods SubTask methods SubTask methods
    public SubTask createSubTask(SubTask subTask) {
        subTask.setId(id);
        if (epicMap.containsKey(subTask.getEpicId())) {
            subTaskMap.put(id, new SubTask(subTask.getName(), subTask.getDescription(), subTask.getTaskStatus(), subTask.getId(), subTask.getEpicId()));
            epicMap.get(subTask.getEpicId()).addSubTaskToEpicId(subTask.getEpicId(), subTask);
            epicCheckStatus(subTask.getEpicId());
        } else {
            System.out.println("Такого epic нет.");
        }
        id++;
        return subTask;
    }

    public ArrayList<SubTask> getAllSubTask() {
        ArrayList<SubTask> subTaskL = new ArrayList<>();
        for (SubTask subTaskMapValues : subTaskMap.values()) {
            subTaskL.add(subTaskMapValues);
        }
        return subTaskL;
    }


    public Object getSubTaskById(Integer id) {
        if (subTaskMap.containsKey(id)) {
            object = subTaskMap.get(id);
        } else {
            System.out.println("Такого номера SubTask нет.");
        }
        return object;

    }

    public void deleteSubTaskById(Integer id) {
        if (subTaskMap.containsKey(id)) {
            subTaskMap.remove(id);
        } else {
            System.out.println("Такого номера SubTask нет.");
        }
    }

    public SubTask updateSubTask(SubTask subTaskNew) {
        int idForDelete;
        for (SubTask subTask : subTaskMap.values()) {
            if (subTaskNew.equals(subTask)) {
                idForDelete = subTask.getId();
                subTaskMap.remove(idForDelete);
                subTaskMap.put(subTaskNew.getId(), new SubTask(subTaskNew.getName(), subTaskNew.getDescription(), subTaskNew.getTaskStatus(), subTaskNew.getId(), subTaskNew.getEpicId()));
                epicCheckStatus(subTask.getEpicId());
                break;
            }
        }
        return subTaskNew;
    }

    public void deleteAllSubTasks() {
        subTaskMap.clear();

    }
}

