import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactApp extends App {
    private static final Scanner scan = new Scanner(System.in);
    private ContactList contactList;

    /*public static void main(String[] args) {
        ContactApp contactApp = new ContactApp();
        contactApp.run();
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
        // create a new contact list
        contactList = new ContactList();
        System.out.println("new contact list has been created");
    }

    private void loadList() {
        System.out.println("Enter the filename to load: ");
        String fileName = scan.nextLine();

        contactList = new ContactList();
        contactList.load(fileName);
    }

    private void modifyList() {
        String choiceFromContactMenu;
        while (true) {
            displayOperationMenu();
            choiceFromContactMenu = getMenuChoice();

            if (choiceFromContactMenu.contains("1") || choiceFromContactMenu.contains("view")) {
                displayCurrentItems();
            } else if (choiceFromContactMenu.contains("2") || choiceFromContactMenu.contains("add")) {
                addItems();
            } else if (choiceFromContactMenu.contains("3") || choiceFromContactMenu.contains("edit")) {
                if (contactList.size() > 0) {
                    editItem();
                } else {
                    System.out.println("No contacts to edit");
                }
            } else if (choiceFromContactMenu.contains("4") || choiceFromContactMenu.contains("remove")) {
                if (contactList.size() > 0) {
                    removeItem();
                } else {
                    System.out.println("No contacts to remove");
                }
            } else if (choiceFromContactMenu.contains("5") || choiceFromContactMenu.contains("save")) {
                if (contactList.size() > 0) {
                    saveItems();
                } else {
                    System.out.println("No contacts to save");
                }
            } else if (choiceFromContactMenu.contains("6") || choiceFromContactMenu.contains("quit")) {
                break;
            } else {
                System.out.println("Invalid menu option.");
            }
        }
    }

    private void displayOperationMenu() {
        System.out.println("\nList Operation Menu\n" + "---------\n");
        System.out.println("1) view the list\n" + "2) add an item\n" + "3) edit an item\n" + "4) remove an item\n" +
                "5) save the current list\n" + "6) quit to the main menu");
    }

    private void displayCurrentItems() {
        System.out.println("Current Contacts\n" + "-------------\n");
        System.out.println(contactList.view());
        System.out.println();
    }

    private void addItems() {
        System.out.println("First name: ");
        String firstname = scan.nextLine();

        System.out.println("Last name: ");
        String lastname = scan.nextLine();

        System.out.println("Phone number (xxx-xxx-xxxx): ");
        String phonenumber = scan.nextLine();

        System.out.println("Email address (x@y.z): ");
        String emailaddress = scan.nextLine();

        try {
            contactList.add(new ContactItem(firstname, lastname, phonenumber, emailaddress));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editItem() {
        displayCurrentItems();

        System.out.println("Which task will you edit? ");
        int index = scan.nextInt();
        scan.nextLine();

        if (index < contactList.size()) {
            System.out.println("Enter a new first name: ");
            String firstname = scan.nextLine();

            System.out.println("Enter a new last name: ");
            String lastname = scan.nextLine();

            System.out.println("Enter a new phone number (xxx-xxx-xxxx): ");
            String phonenumber = scan.nextLine();

            System.out.println("Enter a new email address (x@y.z): ");
            String emailaddress = scan.nextLine();

            try {
                contactList.update(index, firstname, lastname, phonenumber, emailaddress);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("WARNING: Invalid contact number");
        }
    }

    private void removeItem() {
        displayCurrentItems();

        System.out.println("Which contact will you remove? ");
        int index = scan.nextInt();
        scan.nextLine();

        if (index < contactList.size()) {
            contactList.remove(index);
        } else {
            System.out.println("WARNING: Invalid contact number");
        }
    }

    private void saveItems() {
        if (contactList.size() > 0) {
            System.out.println("Enter the filename to save as: ");
            String nameOfFile = scan.nextLine();
            contactList.save(nameOfFile);

            System.out.println("Contact list has been saved");
        }
        else {
            System.out.println("No contacts to save");
        }
    }
}
