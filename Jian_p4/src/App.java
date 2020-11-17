import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

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

    public static void main(String[] args) {
        int input = 0, done = 0;
        String fileName = "";

        try {
            System.out.println("Main Menu\n" + "---------\n");
            System.out.println("1) create a new list\n" + "2) load an existing list\n" + "3) quit");
            input = scan.nextInt();
            System.out.println("> " + input);
        } catch (InputMismatchException in) {
            System.out.println("You must enter in an integer value from 1-3");
        }

        switch (input) {
            case 1:
                TaskList taskList = new TaskList();
                System.out.println("new task list has been created");
                break;
            case 2:
                scan.nextLine();
                System.out.println("Enter the filename to load: ");
                fileName = scan.nextLine();
                // load up file
                System.out.println("task list has been loaded");
                break;
            case 3:
                System.out.println("Process finished with exit code 0");
                exit(1);
                break;
        }
        while (listOperationMenu() > 0) {
            System.out.println("> " + listOperationMenu());
            switch (input) {
                case 1:
                    System.out.println("Current Tasks\n" + "-------------\n");
                    // show current tasks in task list
                    break;
                case 2:
                    TaskItem task = new TaskItem();

                    System.out.println("Task title: ");
                    String title = scan.nextLine();
                    task.setTitle(title);

                    System.out.println("Task description: ");
                    String description = scan.nextLine();
                    task.setDescription(description);

                    System.out.println("Task due date: ");
                    String dueDate = scan.nextLine();
                    task.setDate(dueDate);
                    break;
            }
            listOperationMenu();
        }
    }
    /*public static int listOperationMenu() {
        int input = 0;
        try {
            System.out.println("List Operation Menu\n" + "---------\n");
            System.out.println("1) view the list\n" + "2) add an item\n" + "3) edit an item\n" + "4) remove an item\n" +
                    "5) mark an item as completed\n" + "6) unmark an item as completed\n" + "7) save the current list\n" +
                    "8) quit to the main menu");
            input = scan.nextInt();
            return input;
            scan.nextLine();
        } catch (InputMismatchException in) {
            System.out.println("You must enter in an integer value from 1-8");
        }
    }*/
}
catch (FileNotFoundException ex) {
        System.out.println("Unable to find file");