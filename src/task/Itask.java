package task;

import java.util.Date;

public interface Itask {
	
	public Date getDueDate(); // Date d'échéance

	public Priority getPriority(); // Priorités possibles : urgent, normal ou secondaire ;
	
	public Integer getEstimatedDate(); // Durée estimée de la tache en nombre de jours
	
	public String getDescription(); // Description court de la tache
	
	public float getProgress(); // Progression en pourcentage 

}
