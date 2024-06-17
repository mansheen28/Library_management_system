import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Itemmanagement.*;
import common.Common;

class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        callInsert();
        callUpdate();

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
            sc.close();
            return;
        }
         item.insertItem();
    }

    public static void callUpdate() {
        System.out.println("Enter the Item to update (Book, Magazine, Dvd): ");
        String inputUpdatedItem = sc.nextLine();

        System.out.print("Enter the ID of the item to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Item item = Item.searchItemById(id, inputUpdatedItem);
        if (item != null) {
            System.out.println("Item found: " + item);
            System.out.println("Enter new details for the item:");
            item.updateItem(sc);
            // Code to save updated item back to file
            Common.saveUpdatedItem(item);
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }

        sc.close();
    } 

}
