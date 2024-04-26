

public interface TaskBuilder {
	TaskBuilder addTask(TaskComponent task, TaskComponent parentTask);
	TaskBuilder deleteTask(TaskComponent task);
	TaskBuilder editTask(TaskComponent task, String newDescription, Date newDueDate, Priority newPriority, int newEstimatedDuration);
	TaskBuilder saveTaskListToFile(String filePath);
	TaskBuilder loadTaskListFromFile(String filePath);
	TaskEditor build();
}
