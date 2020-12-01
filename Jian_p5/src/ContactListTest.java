import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactListTest {
    @Test
    public void addingItemsIncreasesSize() {
        ContactItem contact = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        ContactList cl1 = new ContactList();
        cl1.add(contact);

        assertEquals(1, cl1.size());
    }

    @Test
    public void editingItemsFailsWithAllBlankValues() {
        ContactItem contact = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            contact.update("", "", "", "");
        });
    }

    @Test
    public void editingItemsFailsWithInvalidIndex() {
        ContactItem contact = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        ContactList cl1 = new ContactList();
        cl1.add(contact);

        assertThrows(IndexOutOfBoundsException.class, () -> cl1.update(1, contact.getFirstname(), contact.getLastname(), contact.getPhonenumber(), contact.getEmailaddress()));
    }

    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactItem contact = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        ContactList cl1 = new ContactList();
        cl1.add(contact);
        contact.update("", contact.getLastname(), contact.getPhonenumber(), contact.getEmailaddress());

        assertEquals("", cl1.getContactFirstName(0));
        assertEquals("Doe", cl1.getContactLastName(0));
        assertEquals("123-456-7890", cl1.getContactPhoneNumber(0));
        assertEquals("doe@gmail.com", cl1.getContactEmailAddress(0));
    }

    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactItem contact = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        ContactList cl1 = new ContactList();
        cl1.add(contact);
        contact.update(contact.getFirstname(), "", contact.getPhonenumber(), contact.getEmailaddress());

        assertEquals("John", cl1.getContactFirstName(0));
        assertEquals("", cl1.getContactLastName(0));
        assertEquals("123-456-7890", cl1.getContactPhoneNumber(0));
        assertEquals("doe@gmail.com", cl1.getContactEmailAddress(0));
    }

    @Test
    public void editingSucceedsWithBlankPhone() {
        ContactItem contact = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        ContactList cl1 = new ContactList();
        cl1.add(contact);
        contact.update(contact.getFirstname(), contact.getLastname(), "", contact.getEmailaddress());

        assertEquals("John", cl1.getContactFirstName(0));
        assertEquals("Doe", cl1.getContactLastName(0));
        assertEquals("", cl1.getContactPhoneNumber(0));
        assertEquals("doe@gmail.com", cl1.getContactEmailAddress(0));
    }

    @Test
    public void editingSucceedsWithNonBlankValues() {
        ContactItem contact = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        ContactList cl1 = new ContactList();
        cl1.add(contact);
        contact.update("Bob", "Smith", "012-345-6789", "bobs@gmail.com");

        assertEquals("Bob", cl1.getContactFirstName(0));
        assertEquals("Smith", cl1.getContactLastName(0));
        assertEquals("012-345-6789", cl1.getContactPhoneNumber(0));
        assertEquals("bobs@gmail.com", cl1.getContactEmailAddress(0));
    }

    @Test
    public void newListIsEmpty() {
        ContactList cl1 = new ContactList();
        assertEquals(0, cl1.size());
    }

    @Test
    public void removingItemsDecreasesSize() {
        ContactItem contact = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");
        ContactItem contact2 = new ContactItem("bob", "smith", "123-456-7890", "bobs@gmail.com");
        ContactList cl1 = new ContactList();
        cl1.add(contact);
        cl1.add(contact2);
        cl1.remove(0);

        assertEquals(1, cl1.size());
    }

    @Test
    public void removingItemsFailsWithInvalidIndex() {
        ContactList cl1 = new ContactList();

        assertThrows(IndexOutOfBoundsException.class, () -> cl1.remove(0));
    }

    @Test
    public void savedContactListCanBeLoaded() {
        ContactList cl1 = new ContactList();
        cl1.add(new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com"));
        cl1.add(new ContactItem("bob", "smith", "123-456-7800", "bobs@gmail.com"));
        cl1.save("test_contacts.txt");

        cl1.load("test_contacts.txt");

        assertEquals(2, cl1.size());
        assertEquals("Name: John Doe, Phone: 123-456-7890, Email: doe@gmail.com", cl1.getContactText(0));
        assertEquals("Name: bob smith, Phone: 123-456-7800, Email: bobs@gmail.com", cl1.getContactText(1));
    }
}