import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;

public class App {
    /*
    A user shall be able to view the current task list
A user shall be able to save the current task list
A user shall be able to add an item to the current task list
A user shall be able to edit an item in the current task list
A user shall be able to remove an item from the current task list
A user shall be able to mark an item in the current task list as completed
A user shall be able to unmark an item in the current task list as completed

You must handle input errors, including invalid titles, invalid due dates, and trying to access a task that doesn't exist.
You must also handle the case when there are no tasks to edit, remove, mark, unmark, or save.
None of these expected errors should crash the program.
     */
    public static Scanner scan = new Scanner(System.in);
    public static TaskList loadedTaskList = new TaskList();

    public static void main(String[] args) {
        int input;
        boolean done = false;

        System.out.println("Main Menu\n" + "---------\n");
            System.out.println("1) create a new list\n" + "2) load an existing list\n" + "3) quit");
            input = scan.nextInt();
            scan.nextLine();

            while (input < 0 || input > 3) {
                System.out.println("Please enter an integer value from 1-3");
                System.out.println("Main Menu\n" + "---------\n");
                System.out.println("1) create a new list\n" + "2) load an existing list\n" + "3) quit");
                input = scan.nextInt();
                scan.nextLine();
            }

            System.out.println("> " + input);

            switch (input) {
                case 1:
                    TaskList taskList = new TaskList();
                    System.out.println("new task list has been created");
                    while (!done) {
                        int input2 = listOperationMenu(taskList);
                        if (input2 < 0 || input2 > 8) {
                            System.out.println("Please enter an integer value from 1-8");
                            input2 = listOperationMenu(taskList);
                        }
                        System.out.println("> " + input2);

                        switch (input2) {
                            case 1:
                                displayCurrentTasks(taskList);
                                break;
                            case 2:
                                TaskItem task = new TaskItem();

                                try {
                                    System.out.println("Task title: ");
                                    String title = scan.nextLine();
                                    char firstCharacter = title.charAt(0);
                                    if (title.length() == 0 || !Character.isAlphabetic(firstCharacter)) {
                                        System.out.println("WARNING: title must be at least 1 character long; task not created");
                                        break;
                                    }
                                    if (!task.setTitle(title)) {
                                        System.out.println("WARNING: title must be at least 1 character long; task not created");
                                        break;
                                    }
                                } catch (InputMismatchException title) {
                                    System.out.println("WARNING: title must be at least 1 character long; task not created");
                                    break;
                                }

                                System.out.println("Task description: ");
                                String description = scan.nextLine();
                                task.setDescription(description);

                                try {
                                    System.out.println("Task due date (YYYY-MM-DD): ");
                                    String dueDate = scan.nextLine();
                                    if (dueDate.length() == 0) {
                                        System.out.println("WARNING: invalid due date; task not created");
                                        break;
                                    }
                                    if (!task.setDate(dueDate)) {
                                        System.out.println("WARNING: invalid due date; task not created");
                                        break;
                                    }
                                } catch (InputMismatchException date) {
                                    System.out.println("WARNING: invalid due date; task not created");
                                    break;
                                }
                                taskList.addTaskItems(task);
                                break;
                            case 3:
                                int choice = 0;
                                displayCurrentTasks(taskList);

                                System.out.println("Which task will you edit? ");
                                choice = scan.nextInt();
                                scan.nextLine();

                                TaskItem checkIfValidTask;
                                try {
                                    checkIfValidTask = taskList.get(choice);
                                } catch (IndexOutOfBoundsException in) {
                                    System.out.println("WARNING: invalid index, could not edit task");
                                    break;
                                }

                                System.out.println("Enter a new title for task " + choice);
                                String edit = scan.nextLine();

                                if (taskList.editTaskItemTitle(edit, choice)) {
                                    //System.out.println("WARNING: Could not edit task");
                                    System.out.println("Enter a new description for task " + choice);
                                    edit = scan.nextLine();
                                    if (taskList.editTaskItemDescription(edit, choice)) {
                                        //System.out.println("WARNING: Could not edit task");
                                        System.out.println("Enter a new task due date");
                                        edit = scan.nextLine();
                                        if (taskList.editTaskItemDueDate(edit, choice)) {
                                            //System.out.println("WARNING: Could not edit task");
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                                System.out.println("WARNING: Could not edit task");
                                break;
                            case 4:
                                displayCurrentTasks(taskList);

                                System.out.println("Which task will you remove?");
                                choice = scan.nextInt();
                                scan.nextLine();

                                try {
                                    checkIfValidTask = taskList.get(choice);
                                } catch (IndexOutOfBoundsException in) {
                                    System.out.println("WARNING: invalid index, could not remove task");
                                    break;
                                }

                                if (taskList.removeTaskItems(choice)) {
                                    break;
                                }
                                System.out.println("WARNING: Could not remove task");
                                break;
                            case 5:
                                displayUncompletedTasks(taskList);

                                System.out.println("Which task will you mark as completed? ");
                                choice = scan.nextInt();
                                scan.nextLine();

                                try {
                                    checkIfValidTask = taskList.get(choice);
                                } catch (IndexOutOfBoundsException in) {
                                    System.out.println("WARNING: invalid index, could not mark task as completed");
                                    break;
                                }

                                if (taskList.changeStatus("completed", choice) == 1) {
                                    break;
                                }
                                System.out.println("WARNING: Could not mark task as completed");
                                break;
                            case 6:
                                displayCompletedTasks(taskList);

                                System.out.println("Which task will you unmark as completed? ");
                                choice = scan.nextInt();
                                scan.nextLine();

                                try {
                                    checkIfValidTask = taskList.get(choice);
                                } catch (IndexOutOfBoundsException in) {
                                    System.out.println("WARNING: invalid index, could not mark task as uncomplete");
                                    break;
                                }

                                if (taskList.changeStatus("uncomplete", choice) == 2) {
                                    break;
                                }
                                System.out.println("WARNING: Could not mark task as uncomplete");
                                break;
                            case 7:
                                System.out.println("Enter the filename to save as (example: NameOfFile.txt): ");
                                String nameOfFile = scan.nextLine();

                                taskList.saveTaskList(nameOfFile);
                                loadedTaskList = taskList.loadTaskListFromFile(nameOfFile);
                                if (loadedTaskList.isLoadValid(taskList)) {
                                    System.out.println("task list has been saved");
                                } else {
                                    System.out.println("WARNING: Could not save file");
                                }
                                break;
                            case 8:
                                done = true;
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + input2);
                        }
                    }
                break;
                case 2:
                    System.out.println("Enter the filename to load (example NameOfFile.txt): ");
                    String fileName = scan.nextLine();
                    loadedTaskList.loadTaskListFromFile(fileName);
                    if (loadedTaskList.isLoadValid(loadedTaskList)) {
                        System.out.println("task list has been loaded");
                        break;
                    }
                    System.out.println("WARNING: File could not be loaded");
                    break;
                case 3:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + input);
            }

        System.exit(0);
    }

    private static void displayCompletedTasks(TaskList taskList) {
        System.out.println("Completed Tasks\n" + "-------------\n");
        if (taskList.isTaskListEmpty(taskList)) {
            System.out.println("\n");
        } else {
            int i, size = taskList.getSize();
            for (i = 0; i < size; i++) {
                String title = taskList.getTaskItemTitle(i);
                if (title.startsWith("*")) {
                    String date = taskList.getTaskItemDueDate(i);
                    String descript = taskList.getTaskItemDescription(i);
                    System.out.println(i + ")" + title + ":" + descript + "[" + date + "]");
                }
                else {
                    continue;
                }
            }
        }
    }

    private static void displayUncompletedTasks(TaskList taskList) {
        System.out.println("Uncompleted Tasks\n" + "-------------\n");
        if (taskList.isTaskListEmpty(taskList)) {
            System.out.println("\n");
        } else {
            int i, size = taskList.getSize();
            for (i = 0; i < size; i++) {
                String title = taskList.getTaskItemTitle(i);
                if (!title.startsWith("*")) {
                    String date = taskList.getTaskItemDueDate(i);
                    String descript = taskList.getTaskItemDescription(i);
                    System.out.println(i + ")" + title + ":" + descript + "[" + date + "]");
                }
               else {
                   continue;
                }
            }
        }
    }

    private static int listOperationMenu(TaskList taskList) {
        int input;
        try {
            System.out.println("\nList Operation Menu\n" + "---------\n");
            System.out.println("1) view the list\n" + "2) add an item\n" + "3) edit an item\n" + "4) remove an item\n" +
                    "5) mark an item as completed\n" + "6) unmark an item as completed\n" + "7) save the current list\n" +
                    "8) quit to the main menu");
            input = scan.nextInt();
            scan.nextLine();
            return input;
        } catch (InputMismatchException in) {
            System.out.println("You must enter in an integer value from 1-8");
            return -1;
        }
    }

    private static void displayCurrentTasks(TaskList taskList) {
        System.out.println("Current Tasks\n" + "-------------\n");
        if (taskList.isTaskListEmpty(taskList)) {
            System.out.println("\n");
        }
        else {
            int i, size = taskList.getSize();
            for (i = 0; i < size; i++) {
                String date = taskList.getTaskItemDueDate(i);
                String title = taskList.getTaskItemTitle(i);
                String descript = taskList.getTaskItemDescription(i);
                System.out.println(i + ")" + title + ":" + descript + "[" + date + "]");
            }
        }
    }
}
