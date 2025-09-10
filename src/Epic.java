import java.util.ArrayList;
import java.util.HashMap;


public class Epic extends Task {

    private HashMap<Integer, ArrayList<SubTask>> subTasksEpic = new HashMap<>();


    public ArrayList<TaskStatus> getListOfStatus(int id) {
        ArrayList<TaskStatus> subTaskStatus = new ArrayList<>();
        ArrayList<SubTask> arrSub = subTasksEpic.get(id);
        for (int i = 0; i < arrSub.size(); i++) {
            subTaskStatus.add(arrSub.get(i).getTaskStatus());
        }
        return subTaskStatus;
    }

    public ArrayList<SubTask> getListOfSubTask(int id) {
        ArrayList<SubTask> arrSub = subTasksEpic.get(id);
        return arrSub;
    }

    public void addSubTaskToEpicId(int id, SubTask subTask) {
        subTasksEpic.computeIfAbsent(id, k -> new ArrayList<>()).add(subTask);
    }

    public Epic(String name, String description, TaskStatus taskStatus) {
        super(name, description, taskStatus);
    }

    public Epic(String name, String description, TaskStatus taskStatus, int id) {
        super(name, description, taskStatus, id);
    }


}