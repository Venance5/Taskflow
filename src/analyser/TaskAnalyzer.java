package analyser;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import task.*;

public class TaskAnalyzer {
        // Méthode pour lire une liste de tâches à partir d'un fichier
        public List<Itask> readTaskListFromFile(String filePath) {
            List<Itask> taskList = new ArrayList<>();
            try {
                File file = new File(filePath);
                InputStream inputStream = new FileInputStream(file);

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputStream);
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("task");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        // Parse the task attributes from XML
                        String description = element.getElementsByTagName("description").item(0).getTextContent();
                        String dueDateString = element.getElementsByTagName("dueDate").item(0).getTextContent();
                        // Parse other attributes similarly...

                        // Create Task or CompositeTask object and add to taskList
                        // Example:
                        // Task task = new Task(description, dueDate, priority, estimatedDuration, progress);
                        // taskList.add(task);
                    }
                }

                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return taskList;
        }
        // Méthode pour effectuer l'analyse des tâches
        public List<Itask> analyzeTasks(List<TaskComponent> taskList) {
            // Filtrer les tâches non complétées
            List<TaskComponent> incompleteTasks = filterIncompleteTasks(taskList);
            // Trier les tâches par date d'échéance croissante
            sortTasksByDueDate(incompleteTasks);
            // Retourner les 5 premières tâches
            return incompleteTasks.subList(0, Math.min(5, incompleteTasks.size()));
        }

        // Méthode pour filtrer les tâches non complétées
        private List<TaskComponent> filterIncompleteTasks(List<TaskComponent> taskList) {
            List<TaskComponent> incompleteTasks = new ArrayList<>();
            for (TaskComponent task : taskList) {
                if (!task.isCompleted()) {
                    incompleteTasks.add(task);
                }
            }
            return incompleteTasks;
        }

        // Méthode pour trier les tâches par date d'échéance croissante
        private void sortTasksByDueDate(List<TaskComponent> taskList) {
            Collections.sort(taskList, new Comparator<TaskComponent>() {
                @Override
                public int compare(TaskComponent task1, TaskComponent task2) {
                    return task1.getDueDate().compareTo(task2.getDueDate());
                }
            });
        }
    }
