public class SubTask extends Task {

    private int epicId;

    public int getEpicId() {
        return epicId;
    }

    public SubTask(String name, String description, TaskStatus taskStatus, int epicId) {
        super(name, description, taskStatus);
        this.epicId= epicId;
    }

    public SubTask(String name, String description, TaskStatus taskStatus, Integer id, int epicId) {
        super(name, description, taskStatus, id);
        this.epicId = epicId;
    }
}