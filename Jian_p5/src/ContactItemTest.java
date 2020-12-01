import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ContactItem("", "", "", "");
        });
    }

    @Test
    public void creationSucceedsWithBlankEmail() {
        ContactItem c = new ContactItem("John", "Doe", "123-456-7890", "");

        assertEquals("John", c.getFirstname());
        assertEquals("Doe", c.getLastname());
        assertEquals("123-456-7890", c.getPhonenumber());
        assertEquals("", c.getEmailaddress());
    }

    @Test
    public void creationSucceedsWithBlankFirstName() {
        ContactItem c = new ContactItem("", "Doe", "123-456-7890", "doe@gmail.com");

        assertEquals("", c.getFirstname());
        assertEquals("Doe", c.getLastname());
        assertEquals("123-456-7890", c.getPhonenumber());
        assertEquals("doe@gmail.com", c.getEmailaddress());
    }

    @Test
    public void creationSucceedsWithBlankLastName() {
        ContactItem c = new ContactItem("John", "", "123-456-7890", "doe@gmail.com");

        assertEquals("John", c.getFirstname());
        assertEquals("", c.getLastname());
        assertEquals("123-456-7890", c.getPhonenumber());
        assertEquals("doe@gmail.com", c.getEmailaddress());
    }

    @Test
    public void creationSucceedsWithBlankPhone() {
        ContactItem c = new ContactItem("John", "Doe", "", "doe@gmail.com");

        assertEquals("John", c.getFirstname());
        assertEquals("Doe", c.getLastname());
        assertEquals("", c.getPhonenumber());
        assertEquals("doe@gmail.com", c.getEmailaddress());
    }

    @Test
    public void creationSucceedsWithNonBlankValues() {
        ContactItem c = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        assertEquals("John", c.getFirstname());
        assertEquals("Doe", c.getLastname());
        assertEquals("123-456-7890", c.getPhonenumber());
        assertEquals("doe@gmail.com", c.getEmailaddress());
    }

    @Test
    public void editingFailsWithAllBlankValues() {
        ContactItem c = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            c.update("", "", "", "");
        });
    }

    @Test
    public void editingSucceedsWithBlankEmail() {
        ContactItem c = new ContactItem("John", "Doe", "123-456-7890", "");
        c.update(c.getFirstname(), c.getLastname(), c.getPhonenumber(), "");

        assertEquals("John", c.getFirstname());
        assertEquals("Doe", c.getLastname());
        assertEquals("123-456-7890", c.getPhonenumber());
        assertEquals("", c.getEmailaddress());
    }

    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactItem c = new ContactItem("", "Doe", "123-456-7890", "doe@gmail.com");
        c.update("", c.getLastname(), c.getPhonenumber(), c.getEmailaddress());

        assertEquals("", c.getFirstname());
        assertEquals("Doe", c.getLastname());
        assertEquals("123-456-7890", c.getPhonenumber());
        assertEquals("doe@gmail.com", c.getEmailaddress());
    }

    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactItem c = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");
        c.update(c.getFirstname(), "", c.getPhonenumber(), c.getEmailaddress());

        assertEquals("John", c.getFirstname());
        assertEquals("", c.getLastname());
        assertEquals("123-456-7890", c.getPhonenumber());
        assertEquals("doe@gmail.com", c.getEmailaddress());
    }

    @Test
    public void editingSucceedsWithBlankPhone() {
        ContactItem c = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");
        c.update(c.getFirstname(), c.getLastname(), "", c.getEmailaddress());

        assertEquals("John", c.getFirstname());
        assertEquals("Doe", c.getLastname());
        assertEquals("", c.getPhonenumber());
        assertEquals("doe@gmail.com", c.getEmailaddress());
    }

    @Test
    public void editingSucceedsWithNonBlankValues() {
        ContactItem c = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");
        c.update("Bob", "Smith", "012-345-6789", "bobs@gmail.com");

        assertEquals("Bob", c.getFirstname());
        assertEquals("Smith", c.getLastname());
        assertEquals("012-345-6789", c.getPhonenumber());
        assertEquals("bobs@gmail.com", c.getEmailaddress());
    }

    @Test
    public void testToString() {
        ContactItem c = new ContactItem("John", "Doe", "123-456-7890", "doe@gmail.com");

        assertEquals("Name: John Doe, Phone: 123-456-7890, Email: doe@gmail.com", c.toString());
    }

}