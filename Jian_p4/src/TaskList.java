import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class TaskList {
    public ArrayList<TaskItem> taskList = new ArrayList<>();

    public void addTaskItems(TaskItem task) {
        taskList.add(task);
    }

    public int getSize() {
        return taskList.size();
    }

    public int changeStatus(String status, int index) {
        String taskStatus = "";
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
        } catch (IndexOutOfBoundsException in) {
            System.out.println("WARNING: invalid index, could not edit task");
            return false;
        }
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
        } catch (FileNotFoundException ex) {
            System.out.println("WARNING: File not found, cannot load file");
            return false;
        }
    }
}
