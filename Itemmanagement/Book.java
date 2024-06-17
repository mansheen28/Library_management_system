package Itemmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.Common;

public class Book extends Item {
    private String author;
    protected static String fileName = "book.txt";

    public Book(int id, String title, String genre, String availability, String author) {
        super(id, title, genre, availability);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String dataToSave() {
        return super.getId() + "," + super.getTitle() + "," + super.getGenre() + "," + super.getAvailability() + ","
                + this.getAuthor();
    }

    @Override
    public void insertItem() {
        String dataToSave = dataToSave();
        Common.saveToFile(fileName, dataToSave);
        System.out.println("Details saved successfully to " + fileName);
    }

    @Override
    public String getFilePath() {
        return fileName;
    }

    public static List<Book> readAllBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                books.add(new Book(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateItem(Scanner sc) {
        System.out.print("Enter new title: ");
        this.title = sc.nextLine();
        System.out.print("Enter new genre: ");
        this.genre = sc.nextLine();
        System.out.print("Enter new availability (true/false): ");
        this.availability = sc.nextLine();
        System.out.print("Enter new author: ");
        this.author = sc.nextLine();
    }

    public static boolean removeItemById(int id) {
        List<Book> books = readAllBooks();
        boolean itemRemoved = books.removeIf(book -> book.getId() == id);

        if (itemRemoved) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (Book book : books) {
                    writer.write(book.dataToSave());
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
    public String toString() {
        return "Book [ID=" + id + ", Title=" + title + ", Genre=" + genre + ", Availability=" + availability
                + ", Author=" + author + "]";
    }

}
