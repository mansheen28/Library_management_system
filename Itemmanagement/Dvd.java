package Itemmanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.Common;

public class Dvd extends Item {
    private String director;
    private static String fileName = "dvd.txt";

    public Dvd(int id, String title, String genre, String availability, String director) {
        super(id, title, genre, availability);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String dataToSave() {
        return super.getId() + "," + super.getTitle() + "," + super.getGenre() + "," + super.getAvailability() + ","
                + this.getDirector();
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

    public static List<Dvd> readAllDvds() {
        List<Dvd> dvds = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                dvds.add(new Dvd(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dvds;
    }

    @Override
    public void updateItem(Scanner sc) {
        System.out.print("Enter new title: ");
        this.title = sc.nextLine();
        System.out.print("Enter new genre: ");
        this.genre = sc.nextLine();
        System.out.print("Enter new availability (true/false): ");
        this.availability = sc.nextLine();
        System.out.print("Enter new director: ");
        this.director = sc.nextLine();
    }

}
