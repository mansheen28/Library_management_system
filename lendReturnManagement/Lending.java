package lendReturnManagement;

import Itemmanagement.Item;
import Usermanagement.User;
import common.Common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Lending {

    public static Scanner sc = new Scanner(System.in);

    private String userID;
    private int itemID;
    private Date lendingDate;
    private String itemType;

    // Constructor to initialize Lending object
    public Lending(String userID, int itemID, String itemType) {
        this.userID = userID;
        this.itemID = itemID;
        this.lendingDate = new Date(); // set to current date
        this.itemType = itemType;

    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Date getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(Date lendingDate) {
        this.lendingDate = lendingDate;
    }

    // Method to get lending details in a formatted string
    @Override
    public String toString() {
        return userID + "," + itemID + "," + lendingDate.toString() + "," + itemType;
    }

    // Method to save lending details to a file
    public static void saveLendingToFile(Lending lending) {
        try (FileWriter fileWriter = new FileWriter("lending.txt", true)) {
            fileWriter.write(lending.toString() + System.lineSeparator());
            System.out.println("Lending details recorded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while recording lending details.");
            e.printStackTrace();
        }
    }

    // Method to lend an item
    public static void lendItem(String userID, int itemID, String inputUpdatedItem) {
        User user = User.findUserById(userID);
        Item item = Item.searchItemById(itemID, inputUpdatedItem); // We assume the item type is known

        if (user == null) {
            System.out.println("User with ID " + userID + " not found.");
            return;
        }

        if (item == null) {
            System.out.println("Item with ID " + itemID + " not found.");
            return;
        }

        if (item.isAvailability().equalsIgnoreCase("true")) {
            item.setAvailability("false"); // Mark item as not available
            Common.saveUpdatedItem(item);
            saveLendingToFile(new Lending(userID, itemID, inputUpdatedItem));
        } else {
            System.out.println("Item with ID " + itemID + " is currently not available.");
        }
    }



    
}
