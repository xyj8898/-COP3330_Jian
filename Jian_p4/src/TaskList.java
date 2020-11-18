import java.io.*;
import java.util.ArrayList;

public class TaskList {
    ArrayList<TaskItem> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public void addTaskItems(TaskItem task) {
        taskList.add(task);
    }

    public TaskItem get(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public int changeStatus(String status, int index) {
        try {
            TaskItem temp = taskList.get(index);
            if (status.equals("completed")) {
                markAsComplete(temp, index);
                return 1;
            }
            else {
                markAsUncomplete(temp, index);
                return 2;
            }
        } catch (IndexOutOfBoundsException in) {
            System.out.println("WARNING: invalid index, could not change status");
            return 3;
        }
    }

    private void markAsUncomplete(TaskItem t, int index) {
        String taskStatus = t.getTitle();
        if (taskStatus.startsWith("*")) {
            taskStatus = taskStatus.substring(3);
            taskList.remove(index);
            t.setTitle(taskStatus);
            taskList.add(index, t);
        }
        else {
            return;
        }
    }

    private void markAsComplete(TaskItem t, int index) {
        String taskStatus = "*** " + t.getTitle();
        t.setTitle(taskStatus);
        taskList.remove(index);
        taskList.add(index, t);
    }

    public boolean editTaskItems(String title, String description, String dueDate, int index) {
        if (!isIndexValid(taskList, index)) {
            return false;
        }
        if (editTaskItemTitle(title, index) && editTaskItemDueDate(dueDate, index) && editTaskItemDescription(description, index)) {
            return true;
        }
        return false;
    }

    private boolean isIndexValid (ArrayList<TaskItem> taskList, int index) {
        int maxValidIndex = taskList.size() - 1;
        if (index >= 0 && index <= maxValidIndex) {
            return true;
        }
        return false;
    }

    public boolean editTaskItemDescription(String description, int index) {
        if (isIndexValid(taskList, index)) {
            TaskItem temp = taskList.get(index);
            temp.setDescription(description);
            return true;
        }
        return false;
    }

    public boolean editTaskItemDueDate(String date, int index) {
        try {
            TaskItem temp = taskList.get(index);
            temp.setDate(date);
            return true;
        } catch (IndexOutOfBoundsException in) {
            System.out.println("WARNING: invalid index, could not edit task");
            return false;
        }
    }

    public boolean editTaskItemTitle(String title, int index) {
        try {
            TaskItem temp = taskList.get(index);
            temp.setTitle(title);
            return true;
        } catch (IndexOutOfBoundsException in) {
            System.out.println("WARNING: invalid index, could not edit task");
            return false;
        }
    }

    public boolean isGetTaskItemDescriptionValid(int index) {
        try {
            String descript = getTaskItemDescription(index);
            return true;
        } catch (IndexOutOfBoundsException in) {
            System.out.println("WARNING: invalid index, could not get task");
            return false;
        }
    }

    public String getTaskItemDescription(int index){
        TaskItem task = taskList.get(index);
        return task.getDescription();
    }

    public boolean isGetTaskItemDueDateValid(int index) {
        try {
            String date = getTaskItemDueDate(index);
            return true;
        } catch (IndexOutOfBoundsException in) {
            System.out.println("WARNING: invalid index, could not get task");
            return false;
        }
    }

    public String getTaskItemDueDate(int index) {
        TaskItem task = taskList.get(index);
        return task.getDate();
    }


    public boolean isGetTaskItemTitleValid(int index) {
        try {
            String title = getTaskItemTitle(index);
            return true;
        } catch (IndexOutOfBoundsException in) {
            System.out.println("WARNING: invalid index, could not get task");
            return false;
        }
    }

    public String getTaskItemTitle(int index) {
        TaskItem task = taskList.get(index);
        return task.getTitle();
    }

    public boolean isTaskListEmpty(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return true;
        }
        return false;
    }

    public boolean removeTaskItems(int index) {
        try {
            taskList.remove(index);
            return true;
        } catch (IndexOutOfBoundsException in) {
            System.out.println("WARNING: invalid index, task not removed");
            return false;
        }
    }

    public void saveTaskList(String fileName) {
        FileWriter saveFile = isCreateFileValid(fileName);
        try (PrintWriter printWriter = new PrintWriter(saveFile)) {
            int i, size = taskList.size();
                for (i = 0; i < size; i++) {
                    TaskItem task = taskList.get(i);
                    printWriter.printf("%d) %s: %s: %s\n", i, task.getTitle(), task.getDescription(), task.getDate());
            }
                printWriter.close();
        } catch (Exception ex) {
            System.out.println("WARNING: Unexpected error, could not save file");
        }
    }

    private FileWriter isCreateFileValid(String fileName) {
        try{
            FileWriter saveTaskListToFile = new FileWriter(fileName);
            return saveTaskListToFile;
        } catch(IOException ex) {
            System.out.println("WARNING: Could not save file");
        }
        return null;
    }

    public boolean isLoadValid(TaskList taskList) {
        int size = 0;
        size = taskList.getSize();
        if (size >= 0) {
            return true;
        }
        return false;
    }

    public TaskList loadTaskListFromFile(String fileName) {
        TaskList taskListFromFile = new TaskList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            TaskItem task = new TaskItem();
            String line;
            int i = 0, descriptStartingIndex;
            while ((line = reader.readLine()) != null)
            {
                while (line.charAt(i) != ':') {
                    if  (i > line.length()) {
                        i++;
                    }
                    break;
            }
                task.setTitle(line.substring(3,i));
                descriptStartingIndex = i;
                while (line.charAt(i) != ':') {
                    if  (i > line.length()) {
                        i++;
                    }
                    break;
                }
                task.setDescription(line.substring(descriptStartingIndex, i));
                int dateStartingIndex = i;
                while (line.charAt(i) != ']') {
                    if  (i > line.length()) {
                        i++;
                    }
                    break;
                }
                task.setDate(line.substring(dateStartingIndex, i));
                taskListFromFile.addTaskItems(task);
            }
            reader.close();
            return taskListFromFile;
        } catch (FileNotFoundException ex) {
            System.out.println("WARNING: Could not find file to read");
            return null;
        } catch (IOException e) {
            System.out.println("WARNING: Unexpected error could not close file");
            return null;
        }
    }
}
