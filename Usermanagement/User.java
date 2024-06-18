package Usermanagement;

import java.io.FileWriter;
import java.io.IOException;

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
}
