
import java.util.ArrayList;
import java.util.List;

public class TaskListBuilder implements TaskBuilder {
	private List<TaskComponent> taskList;

	public TaskListBuilder() {
		this.taskList = new ArrayList<>();
	}

	@Override
	public TaskBuilder addTask(TaskComponent task, TaskComponent parentTask) {
		if (parentTask == null) {
			taskList.add(task);
		} else if (parentTask instanceof CompositeTask) {
			((CompositeTask) parentTask).addSubTask(task);
		} else {
			System.out.println("Erreur : Impossible d'ajouter une tâche à ce parent.");
		}
		return this;
	}

	@Override
	public TaskBuilder deleteTask(TaskComponent task) {
		taskList.remove(task);
		return this;
	}

	@Override
	public TaskBuilder editTask(TaskComponent task, String newDescription, Date newDueDate, Priority newPriority, int newEstimatedDuration) {
		task.setDescription(newDescription);
		task.setDueDate(newDueDate);
		task.setPriority(newPriority);
		task.setEstimatedDuration(newEstimatedDuration);
		return this;
	}

	@Override
	public TaskBuilder saveTaskListToFile(String filePath) {
		try {
			// Créer le contexte JAXB pour TaskEditor
			JAXBContext context = JAXBContext.newInstance(TaskEditor.class);

			// Créer le marshaller
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Marshal the taskList to XML
			OutputStream outputStream = new FileOutputStream(filePath);
			marshaller.marshal(taskList, outputStream);

			// Fermer le flux
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public TaskBuilder loadTaskListFromFile(String filePath) {
		try {
			// Créer le contexte JAXB pour TaskEditor
			JAXBContext context = JAXBContext.newInstance(TaskEditor.class);

			// Créer l'unmarshaller
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// Unmarshal le fichier XML pour obtenir la liste de tâches
			taskList = (List<TaskComponent>) unmarshaller.unmarshal(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public TaskEditor build() {
		return new TaskEditor(taskList);
	}
}