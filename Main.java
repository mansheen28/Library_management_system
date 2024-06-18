import java.util.Scanner;

import Itemmanagement.*;
import Usermanagement.User;
import common.Common;

class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    callInsert();
                    break;
                case 2:
                    callUpdateItem();
                    break;
                case 3:
                    callRemove();
                    break;
                case 4:
                    callSearch();
                    break;
                case 5:
                    callUserDetailsRole();

                    break;
                case 6:
                    callUpdateUser();

                    break;
                case 7:
                    callRemoveUser();

                    break;
                case 8:
                    exit = true;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Insert New Item");
        System.out.println("2. Update Existing Item");
        System.out.println("3. Remove Item by ID");
        System.out.println("4. Search an Item");
        System.out.println("5. Enter the User details to add a new user: ");
        System.out.println("6. Update User Information");
        System.out.println("7. Remove User");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void callInsert() {
        System.out.print("Enter the item type (Book, Magazine, DVD): ");
        String itemType = sc.nextLine();
        System.out.print("Id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        System.out.print("Availability (true/false): ");
        String availability = sc.nextLine();

        Item item = null;

        // defining additional type
        if (itemType.equalsIgnoreCase("Book")) {
            System.out.print("Author: ");
            String author = sc.nextLine();
            item = new Book(id, title, genre, availability, author);
        } else if (itemType.equalsIgnoreCase("Magazine")) {
            System.out.print("Issue Number: ");
            String issueNumber = sc.nextLine();
            item = new Magazine(id, title, genre, availability, issueNumber);
        } else if (itemType.equalsIgnoreCase("DVD")) {
            System.out.print("Director: ");
            String director = sc.nextLine();
            item = new Dvd(id, title, genre, availability, director);
        } else {
            System.out.println("Invalid type entered");
            return;
        }
        item.insertItem();
    }

    public static void callUpdateItem() {
        System.out.println("Enter the Item to update (Book, Magazine, Dvd): ");
        String inputUpdatedItem = sc.nextLine();

        System.out.print("Enter the ID of the item to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Item item = Item.searchItemById(id, inputUpdatedItem);
        if (item != null) {
            System.out.println("Enter new details for the item:");
            item.updateItem(sc);
            // Code to save updated item back to file
            Common.saveUpdatedItem(item);
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    public static void callRemove() {
        System.out.print("Enter the item type (Book, Magazine, DVD): ");
        String itemType = sc.nextLine();
        System.out.print("Enter the ID of the item to remove: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean removed = Item.removeItemById(id, itemType);
        if (removed) {
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    public static void callSearch() {
        System.out.println("Enter the Item to search (Book, Magazine, Dvd): ");
        String inputUpdatedItem = sc.nextLine();

        System.out.print("Enter the ID of the item to search: ");
        int id = sc.nextInt();
        sc.nextLine();
        Item searchedItem = Item.searchItemById(id, inputUpdatedItem);
        System.out.println(searchedItem.toString());
    }

    public static void callUserDetailsRole() {
        System.out.print("Enter User ID: ");
        String userID = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Role (Admin, Librarian, Member): ");
        String role = sc.nextLine();

        System.out.print("Enter Contact Information: ");
        String contactInfo = sc.nextLine();

        // Create a new User object
        User newUser = new User(userID, name, role, contactInfo);

        // Save user details to users.txt file
        User.saveUserToFile(newUser);
    }

    public static void callUpdateUser() {
        System.out.print("Enter User ID to update: ");
        String userID = sc.nextLine();

        User user = User.findUserById(userID);
        if (user != null) {
            System.out.println("Current details: " + user.toString());

            System.out.print("Enter new Name (leave blank to keep current): ");
            String name = sc.nextLine();
            if (!name.isEmpty()) {
                user.setName(name);
            }

            System.out.print("Enter new Role (leave blank to keep current): ");
            String role = sc.nextLine();
            if (!role.isEmpty()) {
                user.setRole(role);
            }

            System.out.print("Enter new Contact Information (leave blank to keep current): ");
            String contactInfo = sc.nextLine();
            if (!contactInfo.isEmpty()) {
                user.setContactInfo(contactInfo);
            }

            // Update user details in the file
            User.updateUserInFile(user);
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }

    public static void callRemoveUser() {
        System.out.print("Enter User ID to remove: ");
        String userID = sc.nextLine();

        User user = User.findUserById(userID);
        if (user != null) {
            User.removeUserById(userID);
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }
}
