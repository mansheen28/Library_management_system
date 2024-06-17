package Itemmanagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// import java.io.*;
import java.util.*;

public abstract class Item {
    protected int id;
    protected String title;
    protected String genre;
    protected String availability;

    public Item(int id, String title, String genre, String availability) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public String getAvailability() {
        return this.availability;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String isAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public abstract String dataToSave();

    public abstract void insertItem();

    public abstract String getFilePath();

    public static Item searchItemById(int id, String inputUpdatedItem) {

        List<Item> itemsToSearch = new ArrayList<>();
        switch (inputUpdatedItem.toLowerCase()) {
            case "book":
                itemsToSearch.addAll(Book.readAllBooks());
                break;
            case "magazine":
                itemsToSearch.addAll(Magazine.readAllMagazines());
                break;
            case "dvd":
                itemsToSearch.addAll(Dvd.readAllDvds());
                break;
            default:
                System.out.println("Unknown item type: " + inputUpdatedItem);
                return null;
        }

        for (Item item : itemsToSearch) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    public abstract void updateItem(Scanner sc);

    public static boolean removeItemById(int id, String itemType) {
        List<Item> itemsToKeep = new ArrayList<>();
        boolean itemRemoved = false;
        String filePath = "";

        switch (itemType.toLowerCase()) {
            case "book":
                itemsToKeep.addAll(Book.readAllBooks());
                filePath = Book.fileName;
                break;
            case "magazine":
                itemsToKeep.addAll(Magazine.readAllMagazines());
                filePath = Magazine.fileName;
                break;
            case "dvd":
                itemsToKeep.addAll(Dvd.readAllDvds());
                filePath = Dvd.fileName;
                break;
            default:
                System.out.println("Unknown item type: " + itemType);
                return false;
        }

        Iterator<Item> iterator = itemsToKeep.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getId() == id) {
                iterator.remove();
                itemRemoved = true;
            }
        }

        if (itemRemoved) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Item item : itemsToKeep) {
                    writer.write(item.dataToSave());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return itemRemoved;
    }

    @Override
    public abstract String toString();
}
