import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ContactList {
    /*
    A user shall be able to create a new contact list
A user shall be able to load an existing contact list
A user shall be able to view the current contact list
A user shall be able to save the current contact list
A user shall be able to add a contact to the current contact list
A user shall be able to edit a contact in the current contact list
A user shall be able to remove a contact from the current contact list
     */
    private List<ContactItem> contacts;

    public ContactList() {
        this.contacts = new ArrayList<>();
    }

    public int size() {
        return contacts.size();
    }

    public void add(ContactItem contact) {
        contacts.add(contact);
    }

    private ContactItem get(int index) {
        return contacts.get(index);
    }

    public void remove(int index) {
        contacts.remove(index);
    }

    public String view() {
        StringBuilder s = new StringBuilder();
        ContactItem contact;
        for (int i = 0; i < this.size(); i++) {
            contact = this.get(i);
            s.append(String.format("%d) %s%n", i, contact));
        }
        return s.toString();
    }

    public void update(int index, String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.get(index).update(firstName, lastName, phoneNumber, emailAddress);
    }

    public void save(String fileName) {
        try (Formatter output = new Formatter(fileName)) {
            output.format("tasks%n");
            for (ContactItem contact : contacts) {
                output.format("%s%n", contact.getFirstname());
                output.format(" %s%n", contact.getLastname());
                output.format("%s%n", contact.getPhonenumber());
                output.format("%s%n", contact.getEmailaddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String fileName) {
        List<ContactItem> backupList = contacts;

        contacts = new ArrayList<>();
        try (Scanner scan = new Scanner(Paths.get(fileName))) {
            String header = scan.nextLine();
            if (header.equalsIgnoreCase("contacts")) {
                while (scan.hasNext()) {
                    String firstname = scan.nextLine();
                    String lastname = scan.nextLine();
                    String phonenumber = scan.nextLine();
                    String emailaddress = scan.nextLine();

                    ContactItem item = new ContactItem(firstname, lastname, phonenumber, emailaddress);
                    this.add(item);
                }
            }
            else {
                contacts = backupList;
                throw new InputMismatchException("WARNING: Filename is a not a valid task list. No data loaded");
            }
        } catch (FileNotFoundException e) {
            contacts = backupList;
            throw new IllegalArgumentException("WARNING: Task file not found. No task list loaded");
        } catch (IOException e) {
            contacts = backupList;
            throw new IllegalArgumentException("WARNING: Error loading the file. No task list loaded");
        }
    }

    public String getContactFirstName(int index) {
        return this.get(index).getFirstname();
    }

    public String getContactLastName(int index) {
        return this.get(index).getLastname();
    }

    public String getContactPhoneNumber(int index) {
        return this.get(index).getPhonenumber();
    }

    public String getContactEmailAddress(int index) {
        return this.get(index).getEmailaddress();
    }

    public String getTaskText(int index) {
        return this.get(index).toString();
    }
}
