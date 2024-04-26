package task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplexTask implements Itask{

	// taches formants la tâche complexe et priorité donnée par l'utilisateur
	protected List<SingleTask> tasks = new ArrayList<SingleTask>();
	private Priority priority;

	public ComplexTask() {}

	public ComplexTask(List<SingleTask> tasks, Priority priority){
		this.tasks = tasks;
		this.priority = priority;
	}

	@Override
	public Date getDueDate() {
		List<Date> dates = new ArrayList<Date>();
		// Recupération de toutes les dates des sous tâches
		for (SingleTask task : tasks) {
			dates.add(task.getDueDate());
		}
		if (dates == null || dates.isEmpty()) {
			return null; // Retourne null si la liste est vide ou nulle
		}
		Date maxDate = dates.get(0); // Initialise avec la première date de la liste
		// Parcourt la liste pour trouver la plus grande date
		for (Date date : dates) {
			if (date.after(maxDate)) {
				maxDate = date;
			}
		}
		return maxDate;
	}

	@Override
	public Priority getPriority() {
		return priority;
	}

	@Override
	public Integer getEstimatedDate() {
		Integer totalestimdate = 0;
		// Somme des durées estimées
		for(SingleTask task : tasks){
			totalestimdate += task.getEstimatedDate();
		}
		return totalestimdate;
	}

	@Override
	public String getDescription() {
		String desc = "";
		// Sommation des descriptions des sous taches
		for (SingleTask task : tasks) {
			desc += (" "+task.getDescription());
		}
		return desc;
	}

	@Override
	public float getProgress() {
		// Calcul des progressions pondérées par les durées estimées des sous tâches
		int progression = 0;
		int totalestimdate = getEstimatedDate();
		for (SingleTask task : tasks) {
			progression += task.getProgress() * (task.getEstimatedDate() / totalestimdate);
		}
		return progression;
	}

}
