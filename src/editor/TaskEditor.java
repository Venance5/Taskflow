import java.util.List;

public class TaskEditor {
	private List<TaskComponent> taskList;

	public TaskEditor(List<TaskComponent> taskList) {
		this.taskList = taskList;
	}

	// Méthode pour ajouter une nouvelle tâche à la liste principale ou à une sous-tâche
	public void addTask(TaskComponent task, TaskComponent parentTask) {
		if (parentTask == null) {
			taskList.add(task); // Ajouter à la liste principale
		} else if (parentTask instanceof CompositeTask) {
			((CompositeTask) parentTask).addSubTask(task); // Ajouter à une sous-tâche
		} else {
			System.out.println("Erreur : Impossible d'ajouter une tâche à ce parent.");
		}
	}

	// Méthode pour supprimer une tâche de la liste
	public void deleteTask(TaskComponent task) {
		taskList.remove(task);
	}

	// Méthode pour modifier une tâche
	public void editTask(TaskComponent task, String newDescription, Date newDueDate, Priority newPriority, int newEstimatedDuration) {
		task.setDescription(newDescription);
		task.setDueDate(newDueDate);
		task.setPriority(newPriority);
		task.setEstimatedDuration(newEstimatedDuration);
	}

	// Méthode pour sauvegarder la liste de tâches dans un fichier XML
	public void saveTaskListToFile(String filePath) {
		// Implémenter la logique pour sauvegarder la liste de tâches dans un fichier XML
	}

	// Méthode pour charger une liste de tâches depuis un fichier XML
	public void loadTaskListFromFile(String filePath) {
		// Implémenter la logique pour charger la liste de tâches depuis un fichier XML
	}


	// Méthode pour marquer une tâche comme complétée
	public void markTaskAsCompleted(TaskComponent task) {
		task.setCompleted(true);
	}

	// Méthode pour obtenir la liste de tâches actuelle
	public List<TaskComponent> getTaskList() {
		return taskList;
	}

	// Méthode pour récupérer une tâche spécifique par son index dans la liste
	public TaskComponent getTaskByIndex(int index) {
		if (index >= 0 && index < taskList.size()) {
			return taskList.get(index);
		} else {
			return null;
		}
	}
}
