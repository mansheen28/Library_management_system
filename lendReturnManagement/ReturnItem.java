package lendReturnManagement;

import Itemmanagement.Item;
import common.Common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReturnItem {
    public static Scanner sc = new Scanner(System.in);

    // Method to mark item as available and update lending.txt
    public static void returnItem(int itemID) {
        // Search for the item in lending.txt
        List<Lending> lendings = readLendingsFromFile();
        Lending foundLending = null;

        for (Lending lending : lendings) {
            if (lending.getItemID() == itemID) {
                foundLending = lending;
                break;
            }
        }

        if (foundLending != null) {
            // Mark the item as available
            Item item = Item.searchItemById(itemID, foundLending.getItemType());
            if (item != null) {
                item.setAvailability("true");
                Common.saveUpdatedItem(item);
            } else {
                System.out.println("Item with ID " + itemID + " not found.");
                return;
            }

            // Remove the lending record from lending.txt
            lendings.remove(foundLending);
            saveLendingsToFile(lendings);
            System.out.println("Item with ID " + itemID + " has been returned successfully.");
        } else {
            System.out.println("No active lending found for item with ID " + itemID);
        }
    }

    // Method to read lendings from lending.txt
    private static List<Lending> readLendingsFromFile() {
        List<Lending> lendings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("lending.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    lendings.add(new Lending(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading lending details.");
            e.printStackTrace();
        }
        return lendings;
    }

    // Method to save lendings back to lending.txt
    private static void saveLendingsToFile(List<Lending> lendings) {
        try (FileWriter writer = new FileWriter("lending.txt")) {
            for (Lending lending : lendings) {
                writer.write(lending.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving lending details.");
            e.printStackTrace();
        }
    }
}
