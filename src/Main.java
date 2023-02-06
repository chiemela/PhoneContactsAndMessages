import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;

    public static void main(String[] args) {

        contacts = new ArrayList<>();
        System.out.println("Welcome to my humble world of programming");
        showInitialOptions();
    }

    private static void showInitialOptions() {
        System.out.println("Please select one:" +
                "\n\t1. Manage Contacts" +
                "\n\t2. Messages" +
                "\n\t3. Quit");

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
        }
    }

    private static void manageMessages() {
        System.out.println("Please select one:" +
                "\n\t1. Show all messages" +
                "\n\t2. Send a new message" +
                "\n\t3. Go Back");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void sendNewMessage() {
        // give instructions to the user
        System.out.println("Who are you going to sent a message?");
        // accept input from user
        String name = scanner.next();
        // check if user entered an input
        if (name.equals("")) {
            System.out.println("Please enter the name of the contact");
            sendNewMessage();
        }else {
            boolean doesExist = false;
            // check if contact name exists
            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                }
            }

            // performt this action if contact exists
            if (doesExist) {
                // give the user instructions
                System.out.println("What are you going to say?");
                // accept input from user
                String text = scanner.next();
                // check if user entered something
                if (text.equals("")) {
                    System.out.println("Please enter some message");
                    sendNewMessage();
                }else {
                    // create an unique id for each message
                    id++;
                    // initialize and assign values to constructor
                    Message newMessage = new Message(text, name, id);
                    for (Contact c: contacts) {
                        if (c.getName().equals(name)) {
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                            Contact currentContact = c;
                            c.setMessages(newMessages);
                        }
                    }
                }
            }else {
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void showAllMessages() {
        // save all messages from the contacts into an ArrayList
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c: contacts) {
            allMessages.addAll(c.getMessages());
        }

        // check if more than 1 message is saved
        if (allMessages.size() > 0) {
            for (Message m: allMessages) {
                m.getDetails();
                System.out.println("***********");
            }
        }else {
            System.out.println("You don't have any message");
        }

        showInitialOptions();

    }

    private static void manageContacts() {
        System.out.println("Please select one:" +
                "\n\t1. Show all contacts" +
                "\n\t2. Add a new contact" +
                "\n\t3. Search for a contact" +
                "\n\t4. Delete a contact" +
                "\n\t5. Go back");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void deleteContact() {
        System.out.println("Please enter the name");    // give user instructions
        String name = scanner.next();   // accept user input
        // check if user entered something
        if (name.equals("")) {
            System.out.println("Please enter a name");
            deleteContact();
        }else {
            boolean doesExist = false;
            // loop through the "contacts" ArrayList<>() in "Contact" class
            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                    contacts.remove(c); // remove the contact details at "c" position
                }
            }
            // do this is contact does not exist
            if (!doesExist) {
                System.out.println("There is no such contact");
            }
        }

        showInitialOptions();

    }

    private static void searchForContact() {
        System.out.println("Please enter the contact name:");   // display instruction to user
        String name = scanner.next();   // accept input from user
        // check if name is empty
        if (name.equals("")) {
            System.out.println("Please enter the name");
            searchForContact();
        }else {
            boolean doesExist = false;
            // loop through the "contacts" ArrayList<>() in "Contact" class
            for (Contact c: contacts) {
                // check if name matches contact in ArrayList<>()
                if (c.getName().equals(name)) {
                    doesExist = true;
                    c.getDetails();
                }
            }
            // perform this operation if "name" does not match any contact
            if (!doesExist) {
                System.out.println("There is no such contact in your phone");
            }
        }
        showInitialOptions();
    }

    private static void addNewContact() {
        // give instruction to the user on what to do
        System.out.println("Adding a new contact..." +
                "\nPlease enter the contact's name:");
        // accept name input from user
        String name = scanner.next();
        // give instruction to the user on what to do
        System.out.println("Please enter contact's number:");
        // accept number input from user
        String number = scanner.next();
        // give instruction to the user on what to do
        System.out.println("Please enter contact's email:");
        // accept email input from user
        String email = scanner.next();

        // check if all inputs are entered
        if (name.equals("") || number.equals("") || email.equals("")) {
            System.out.println("Please enter all of the information");
            addNewContact();
        }else {

            // check if user already has this contact name in their device
            boolean doesExist = false;
            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                }
            }

            // if contact exits then repeat the entire process again
            if (doesExist) {
                System.out.println("We have a contact named " + name +
                        " saved on this device");
                addNewContact();
            }else {
                // this code adds the unique contact to the list
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println(name + " added successfully");
            }
        }

        showInitialOptions();

    }

    private static void showAllContacts() {
        // check if you have any contact
        if (contacts.size() > 0) {
            for (Contact c: contacts) {
                c.getDetails();
                System.out.println("***********");
            }

            showInitialOptions();

        }else {
            System.out.println("You do not have any contact");
            showInitialOptions();
        }
    }

}
