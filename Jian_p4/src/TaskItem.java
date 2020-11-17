import java.util.InputMismatchException;

public class TaskItem {
    /*
    A task list shall contain 0 or more task items
An task item shall contain a title
A title shall be 1 or more characters in length
An task item shall contain a description
A description shall be 0 or more characters in length
An task item shall contain a due date
A due date shall be in the format of YYYY-MM-DD
    */
    public String taskTitle = "", taskDescription = "", dueDate = "";

    public TaskItem() {

    }

    public TaskItem(String title, String description, String date) {
        if (this.setTitle(title)) {
            this.taskTitle = title;
        }
        else {
            this.taskTitle = "";
        }
        this.taskDescription = description;
        if (this.setDate(date)) {
            this.dueDate = date;
        }
        else {
            this.dueDate = "";
        }
    }

    public String getDate() {
        return this.dueDate;
    }

    public String getTitle() {
        return this.taskTitle;
    }

    public String getDescription() {
        return this.taskDescription;
    }

    public boolean setDate(String taskDueDate) {
        try {
            this.dueDate = taskDueDate;
            return true;
        } catch (IllegalArgumentException dueDate) {
            System.out.println("WARNING: invalid due date; task not created");
            return false;
        }
    }

    public boolean setTitle(String newTitle) {
        try {
            this.taskTitle = newTitle;
            return true;
        } catch (IllegalArgumentException title) {
            System.out.println("WARNING: title must be at least 1 character long; task not created");
            return false;
        } catch (InputMismatchException title) {
            System.out.println("WARNING: title must be at least 1 character long; task not created");
            return false;
        }
    }

    public void setDescription(String newDescription) {
        this.taskTitle = newDescription;
    }

    public boolean isValidDate(String date) {
        return date.length() == 10;
    }

    public boolean isValidTitle(String title) {
        return title.length() >= 1;
    }
}

