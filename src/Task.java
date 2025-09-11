import java.util.Objects;

public class Task {
    private String name;//Название, кратко описывающее суть задачи (например, «Переезд»).
    private String description; //Описание, в котором раскрываются детали.
    private Integer id;//Уникальный идентификационный номер задачи, по которому её можно будет найти
    private TaskStatus taskStatus;

    public Task(String name, String description, TaskStatus taskStatus, Integer id) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.taskStatus = taskStatus;
    }

    public Task() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Task(String name, String description, TaskStatus taskStatus) {
        this.name = name;
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false; // можно оформить и так
        Task otherTask = (Task) obj;
        return Objects.equals(name, otherTask.name);
    }

    @Override
    public String toString() {
        return "{Название задачи='" + name + '\'' +
                ", Описание='" + description + '\'' +
                ", Статус задачи =" + taskStatus + '\'' +
                ", ID=" + id +
                '}';
    }
}

