import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {
        String choiceFromMainMenu;
        while (true) {
            displaySelectYourApplication();
            choiceFromMainMenu = getMenuChoice();

            if (choiceFromMainMenu.contains("1") || choiceFromMainMenu.contains("task")) {
                TaskApp taskapp = new TaskApp();
                taskapp.run();
            } else if (choiceFromMainMenu.contains("2") || choiceFromMainMenu.contains("contact")) {
                ContactApp contactApp = new ContactApp();
                contactApp.run();
            } else if (choiceFromMainMenu.contains("3") || choiceFromMainMenu.contains("quit")) {
                break;
            } else {
                System.out.println("Invalid menu option.");
            }
        }
    }

    private void displaySelectYourApplication() {
        System.out.println("\nSelect Your Application\n" + "-----------------------\n");
        System.out.println("1) task list\n" + "2) contact list\n" + "3) quit\n");
    }

    private String getMenuChoice() {
        System.out.println("> ");
        return scan.nextLine();
    }
}
