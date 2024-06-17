package Itemmanagement;

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

    public void insertItem() {
        try (FileWriter fw = new FileWriter(getFilePath(), true)) {
            fw.write(dataToSave() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
