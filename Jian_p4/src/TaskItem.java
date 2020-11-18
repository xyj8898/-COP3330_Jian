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
       if (isValidDate(taskDueDate)) {
           this.dueDate = taskDueDate;
           return true;
       }
       return false;
    }

    public boolean setTitle(String newTitle) {
       if (isValidTitle(newTitle)) {
           this.taskTitle = newTitle;
           return true;
       }
       return false;
    }

    public void setDescription(String newDescription) {
        this.taskTitle = newDescription;
    }

    public boolean isValidDate(String date) {
        if (date.length() == 10) {
            if (date.charAt(4) != '-') {
                return false;
            }
            if (date.charAt(7) != '-') {
                return false;
            }

            String year = date.substring(0, 4);
            int isValidYear = Integer.parseInt(year);
            String month = date.substring(5, 7);
            int validMonth = Integer.parseInt(month);
            String day = date.substring(8);
            int validDay = Integer.parseInt(day);

            return isValidMonth(validMonth, validDay, isValidYear, day);
        }
        return false;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private boolean isValidMonth(int month, int validDay, int isValidYear, String day) {
        if (month > 0 && month <= 12) {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (day.length() == 2) {
                    return validDay >= 1 && validDay <= 31;
                }
                return false;
            }
            else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day.length() == 2) {
                    return validDay >= 1 && validDay <= 30;
                }
                return false;
            }
            else {
                if (isLeapYear(isValidYear)) {
                    if (day.length() == 2) {
                        return validDay >= 1 && validDay <= 29;
                    }
                }
                else {
                    if (day.length() == 2) {
                        return validDay >= 1 && validDay <= 28;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public boolean isValidTitle(String title) {
        return title.length() >= 1;
    }
}

