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

public class Magazine extends Item {
    private String issueNumber;
    protected static String fileName = "magazine.txt";

    public Magazine(int id, String title, String genre, String availability, String issueNumber) {
        super(id, title, genre, availability);
        this.issueNumber = issueNumber;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String dataToSave() {
        return super.getId() + "," + super.getTitle() + "," + super.getGenre() + "," + super.getAvailability() + ","
                + this.getIssueNumber();
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

    public static List<Magazine> readAllMagazines() {
        List<Magazine> magazines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                magazines.add(new Magazine(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return magazines;
    }

    @Override
    public void updateItem(Scanner sc) {
        System.out.print("Enter new title: ");
        this.title = sc.nextLine();
        System.out.print("Enter new genre: ");
        this.genre = sc.nextLine();
        System.out.print("Enter new availability (true/false): ");
        this.availability = sc.nextLine();
        System.out.print("Enter new issuenumber: ");
        this.issueNumber = sc.nextLine();
    }


    public static boolean removeItemById(int id) {
        List<Magazine> magazines = readAllMagazines();
        boolean itemRemoved = magazines.removeIf(magazine -> magazine.getId() == id);

        if (itemRemoved) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (Magazine magazine : magazines) {
                    writer.write(magazine.dataToSave());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return itemRemoved;
    }
}
