public class ContactItem {
    private String firstname = "", lastname = "", phonenumber = "", emailaddress = "";

    public ContactItem(String firstname, String lastname, String phonenumber, String emailaddress) {
        if (firstname.isBlank() && lastname.isBlank() && phonenumber.isBlank() && emailaddress.isBlank()) {
            throw new IllegalArgumentException("A contact item shall contain at least one of the following: [first name], [last name], [phone number], or [email address]");
        }

        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.emailaddress = emailaddress;
    }

    public void update(String firstname, String lastname, String phonenumber, String emailaddress) {
        if (firstname.isBlank() && lastname.isBlank() && phonenumber.isBlank() && emailaddress.isBlank()) {
            throw new IllegalArgumentException("A contact item shall contain at least one of the following: [first name], [last name], [phone number], or [email address]");
        }
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.emailaddress = emailaddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s%n Phone: %s%n Email: %s%n", firstname, lastname, phonenumber, emailaddress);
    }
}
