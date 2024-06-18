package Usermanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String userID;
    private String name;
    private String role;
    private String contactInfo;

    // Constructor to initialize User object
    public User(String userID, String name, String role, String contactInfo) {
        this.userID = userID;
        this.name = name;
        this.role = role;
        this.contactInfo = contactInfo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Method to get user details in a formatted string
    @Override
    public String toString() {
        return userID + "," + name + "," + role + "," + contactInfo;
    }

    // Method to save user details to a file
    public static void saveUserToFile(User user) {
        try (FileWriter fileWriter = new FileWriter("users.txt", true)) {
            fileWriter.write(user.toString() + System.lineSeparator());
            System.out.println("User details saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving user details.");
            e.printStackTrace();
        }
    }

    // Method to read all users from the file
    public static List<User> readUsersFromFile() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) {
                    users.add(new User(details[0], details[1], details[2], details[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading user details.");
            e.printStackTrace();
        }
        return users;
    }

    // Method to update user details in the file
    public static void updateUserInFile(User updatedUser) {
        List<User> users = readUsersFromFile();
        try (FileWriter fileWriter = new FileWriter("users.txt")) {
            for (User user : users) {
                if (user.getUserID().equals(updatedUser.getUserID())) {
                    fileWriter.write(updatedUser.toString() + System.lineSeparator());
                } else {
                    fileWriter.write(user.toString() + System.lineSeparator());
                }
            }
            System.out.println("User details updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating user details.");
            e.printStackTrace();
        }
    }

    // Method to find a user by ID
    public static User findUserById(String userID) {
        List<User> users = readUsersFromFile();
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    // Method to remove a user by ID
    public static void removeUserById(String userID) {
        List<User> users = readUsersFromFile();
        try (FileWriter fileWriter = new FileWriter("users.txt")) {
            for (User user : users) {
                if (!user.getUserID().equals(userID)) {
                    fileWriter.write(user.toString() + System.lineSeparator());
                }
            }
            System.out.println("User removed successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while removing user details.");
            e.printStackTrace();
        }
    }
}
