public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Task task = new Task("task1", "desc1", TaskStatus.NEW);
        Task task1 = new Task("task2", "desc2", TaskStatus.NEW);
        Task task2 = new Task("task5", "desc4", TaskStatus.NEW);

        manager.createTask(task);
        manager.createTask(task1);
        manager.createTask(task2);
        System.out.println(manager.getAllTasks());
        Task task4 = new Task("task2", "desc2", TaskStatus.DONE, 45);
        manager.updateTask(task4);
        manager.getAllTasks();
        System.out.println();
        System.out.println(manager.getTaskById(45));

        Epic epic = new Epic("epic1", "epicDesc1", TaskStatus.DONE);
        Epic epic1 = new Epic("epic2", "epicDesc2", TaskStatus.NEW);


        SubTask subTask = new SubTask("subTask0", "subTaskDesc0", TaskStatus.DONE, 3);
        SubTask subTask1 = new SubTask("subTask1", "subTaskDesc1", TaskStatus.NEW, 3);
        SubTask subTask2 = new SubTask("subTask2", "subTaskDesc2", TaskStatus.NEW, 4);

        manager.createEpic(epic);
        manager.createEpic(epic1);

        manager.updateEpic(new Epic("epic1", "epicDesc1", TaskStatus.NEW, 3));
        manager.updateEpic(new Epic("epic2", "epicDesc2", TaskStatus.NEW, 4));
        System.out.println(manager.getEpicById(4));
        manager.createSubTask(subTask);
        manager.createSubTask(subTask1);
        manager.createSubTask(subTask2);


        // manager.deleteEpicById(4);
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubTask());
        // System.out.println(manager.getAllEpicSubtasks(4));
        manager.deleteSubTaskById(subTask2.getId());
        // manager.deleteAllSubTasks();

        System.out.println("После удаления");
        System.out.println(manager.getAllEpics());


        System.out.println(manager.getAllSubTask());
        System.out.println(manager.getAllEpicSubtasks(3));
        System.out.println("3 айди" + manager.getAllEpicSubtasks(3));
        //  System.out.println("4 айди" + manager.getAllEpicSubtasks(4));


    }
}