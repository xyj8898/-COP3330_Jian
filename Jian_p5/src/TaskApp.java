import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskApp extends App {
    private static final Scanner scan = new Scanner(System.in);
    private TaskList taskList;

    /*public static void main(String[] args) {
        TaskApp taskApp = new TaskApp();
        taskApp.run();
    }*/

    @Override
    public void run() {
        String choiceFromMainMenu;
        while (true) {
            displayMainMenu();
            choiceFromMainMenu = getMenuChoice();

            if (choiceFromMainMenu.contains("1") || choiceFromMainMenu.contains("create")) {
                createList();
                modifyList();
            } else if (choiceFromMainMenu.contains("2") || choiceFromMainMenu.contains("load")) {
                try {
                    loadList();
                    modifyList();
                } catch (IllegalArgumentException | InputMismatchException input) {
                    System.out.println(input.getMessage());
                }
            } else if (choiceFromMainMenu.contains("3") || choiceFromMainMenu.contains("quit")) {
                break;
            } else {
                System.out.println("Invalid menu option.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\nMain Menu\n" + "---------\n");
        System.out.println("1) create a new list\n" + "2) load an existing list\n" + "3) quit\n");
    }

    private String getMenuChoice() {
        System.out.println("> ");
        return scan.nextLine();
    }

    private void createList() {
        // create a new task list
        taskList = new TaskList();
        System.out.println("new task list has been created");
    }

    private void loadList() {
        System.out.println("Enter the filename to load: ");
        String fileName = scan.nextLine();

        taskList = new TaskList();
        taskList.load(fileName);
    }

    private void modifyList() {
        String choiceFromTaskMenu;
        while (true) {
            displayOperationMenu();
            choiceFromTaskMenu = getMenuChoice();

            if (choiceFromTaskMenu.contains("1") || choiceFromTaskMenu.contains("view")) {
                displayCurrentItems();
            } else if (choiceFromTaskMenu.contains("2") || choiceFromTaskMenu.contains("add")) {
                addItems();
            } else if (choiceFromTaskMenu.contains("3") || choiceFromTaskMenu.contains("edit")) {
                if (taskList.size() > 0) {
                    editItem();
                } else {
                    System.out.println("No tasks to edit");
                }
            } else if (choiceFromTaskMenu.contains("4") || choiceFromTaskMenu.contains("remove")) {
                if (taskList.size() > 0) {
                    removeItem();
                } else {
                    System.out.println("No tasks to remove");
                }
            } else if (choiceFromTaskMenu.contains("5") || choiceFromTaskMenu.contains("mark")) {
                if (taskList.size() > 0) {
                    markItem();
                    displayCompletedItems();
                } else {
                    System.out.println("No tasks to mark as complete");
                }
            } else if (choiceFromTaskMenu.contains("6") || choiceFromTaskMenu.contains("unmark")) {
                if (taskList.size() > 0) {
                    unmarkItem();
                    displayUncompletedItems();
                } else {
                    System.out.println("No tasks to unmark as uncomplete");
                }
            } else if (choiceFromTaskMenu.contains("7") || choiceFromTaskMenu.contains("save")) {
                if (taskList.size() > 0) {
                    saveItems();
                } else {
                    System.out.println("No tasks to save");
                }
            } else if (choiceFromTaskMenu.contains("8") || choiceFromTaskMenu.contains("quit")) {
                break;
            } else {
                System.out.println("Invalid menu option.");
            }
        }
    }

    private void displayOperationMenu() {
        System.out.println("\nList Operation Menu\n" + "---------\n");
        System.out.println("1) view the list\n" + "2) add an item\n" + "3) edit an item\n" + "4) remove an item\n" +
                "5) mark an item as completed\n" + "6) unmark an item as completed\n" + "7) save the current list\n" +
                "8) quit to the main menu");
    }

    private void displayCurrentItems() {
        System.out.println("Current Tasks\n" + "-------------\n");
        System.out.println(taskList.view());
        System.out.println();
    }

    private void addItems() {
        System.out.println("Task title: ");
        String title = scan.nextLine();

        System.out.println("Task description: ");
        String description = scan.nextLine();

        System.out.println("Task due date (YYYY-MM-DD): ");
        String dueDate = scan.nextLine();

        try {
            taskList.add(new TaskItem(title, description, dueDate));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editItem() {
        displayCurrentItems();

        System.out.println("Which task will you edit? ");
        int index = scan.nextInt();
        scan.nextLine();

        if (index < taskList.size()) {
            System.out.println("Enter a new title for task " + index);
            String title = scan.nextLine();

            System.out.println("Enter a new description for task " + index);
            String description = scan.nextLine();

            System.out.println("Enter a new task due date");
            String dueDate = scan.nextLine();

            try {
                taskList.update(index, title, description, dueDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("WARNING: Invalid task number");
        }
    }

    private void removeItem() {
        displayCurrentItems();

        System.out.println("Which task will you remove? ");
        int index = scan.nextInt();
        scan.nextLine();

        if (index < taskList.size()) {
            taskList.remove(index);
        } else {
            System.out.println("WARNING: Invalid task number");
        }
    }

    private void markItem() {
        displayCurrentItems();

        System.out.println("Which task will you mark as completed? ");
        int index = scan.nextInt();
        scan.nextLine();

        if (index >= taskList.size()) {
            System.out.println("WARNING: Invalid task number");
        } else if (taskList.isTaskComplete(index)) {
            System.out.println("WARNING: Task is already complete. No changes made");
        } else {
            taskList.complete(index, true);
        }
    }

    private void displayCompletedItems() {
        System.out.println("Completed Tasks\n" + "-------------\n");
        System.out.println(taskList.viewCompletedTasks());
        System.out.println();
    }

    private void unmarkItem() {
        displayCurrentItems();

        System.out.println("Which task will you unmark as completed? ");
        int index = scan.nextInt();
        scan.nextLine();

        if (index >= taskList.size()) {
            System.out.println("WARNING: Invalid task number");
        } else if (!taskList.isTaskComplete(index)) {
            System.out.println("WARNING: Task is already incomplete. No changes made");
        } else {
            taskList.complete(index, false);
        }
    }

    private void displayUncompletedItems() {
        System.out.println("Uncompleted Tasks\n" + "-------------\n");
        System.out.println(taskList.viewUncompletedTasks());
        System.out.println();
    }

    private void saveItems() {
        if (taskList.size() > 0) {
            System.out.println("Enter the filename to save as: ");
            String nameOfFile = scan.nextLine();
            taskList.save(nameOfFile);

            System.out.println("Task list has been saved");
        }
        else {
            System.out.println("No tasks to save");
        }
    }
}
