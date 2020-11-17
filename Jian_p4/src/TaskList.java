import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class TaskList {
    /*
    A user shall be able to create a new task list
    A user shall be able to load an existing task list
     */
    public ArrayList<TaskItem> taskList = new ArrayList<>();
    //public ArrayList<String> taskListValues = new ArrayList<>();
    String description = "";
    int index = 0;


    public void addTaskItems(TaskItem task) {
        taskList.add(task);
        //String taskName = task.getTitle();
        //taskListValues.add(taskName);
    }

    public int getSize() {
        return taskList.size();
    }


    public boolean changeStatus(String status, int index) {
        String taskStatus = "";
        try {
            TaskItem temp = taskList.get(index);
            if (status.equals("completed")) {
                taskStatus = "*** " + temp.getTitle();
                temp.setTitle(taskStatus);
                taskList.remove(index);
                taskList.add(index, temp);
                return true;
            }
            else {
                taskStatus = temp.getTitle();
                if (taskStatus.substring(3).startsWith("*")) {
                    taskStatus = taskStatus.substring(3);
                    taskList.remove(index);
                    temp.setTitle(taskStatus);
                    taskList.add(index, temp);
                    return true;
                }
                return true;
            }
        } catch (IllegalArgumentException in) {
            System.out.println("WARNING: invalid index, could not change status");
            return false;
        }
    }

    public boolean editTaskItems(String title, String description, String dueDate, int index) {
        if (editTaskItemTitle(title, index) && editTaskItemDueDate(dueDate, index) && editTaskItemDescription(description, index)) {
            return true;
        }
        return false;
    }

    public boolean editTaskItemDescription(String description, int index) {
        try {
            TaskItem temp = taskList.get(index);
            temp.setDescription(description);
            return true;
        } catch (IllegalArgumentException in) {
            System.out.println("WARNING: invalid index, could not edit task");
            return false;
        }
    }

    public boolean editTaskItemDueDate(String date, int index) {
        try {
            TaskItem temp = taskList.get(index);
            temp.setDate(date);
            return true;
        } catch (IllegalArgumentException in) {
            System.out.println("WARNING: invalid index, could not edit task");
            return false;
        }
    }

    public boolean editTaskItemTitle(String title, int index) {
        try {
            TaskItem temp = taskList.get(index);
            temp.setTitle(title);
            return true;
        } catch (IllegalArgumentException in) {
            System.out.println("WARNING: invalid index, could not edit task");
            return false;
        }
    }

    public boolean isGetTaskItemDescriptionValid(int index) {
        try {
            String descript = getTaskItemDescription(index);
            return true;
        } catch (IllegalArgumentException in) {
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
        } catch (IllegalArgumentException in) {
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
        } catch (IllegalArgumentException in) {
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
        } catch (IllegalArgumentException in) {
            System.out.println("WARNING: invalid index, task not removed");
            return false;
        }
    }

    public void saveTaskList(String fileName) {
        try (Formatter out = new Formatter(fileName)) {
            for (int i = 0; i < taskList.size(); i++) {
                TaskItem task = taskList.get(i);
                out.format("%s;%s;%s", task.getTitle(), task.getDescription(), task.getDate());
            }
        } catch (SecurityException | FileNotFoundException | FormatterClosedException ex) {
            System.out.println("WARNING: File not found, cannot save file");
        }
    }

    public boolean isLoadValid(String fileName) {
        try {
            FileReader f = new FileReader(fileName);
            BufferedReader b = new BufferedReader(f);
            return true;
        } catch (IOException ex) {
            System.out.println("WARNING: File not found, cannot load file");
            return false;
        }
    }
}
