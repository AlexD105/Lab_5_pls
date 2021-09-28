package service;

import i18n.Messenger;
import ticket.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converter {

    public static Messenger messenger;

    public static ArrayList<Ticket> read(String fileName) {

        File file = new File(fileName);

        try(Scanner scanner = new Scanner(file)) {
            if(file.length() == 0) {
                System.out.println("Файл пустой. Вы должны заполнить его, прежде чем использовать.");
                return new ArrayList<>();
            } else {
                ArrayList<Ticket> vehicles = new ArrayList<>();
                while (scanner.hasNextLine()) {

                    Ticket vehicle = Ticket.setStringFields(getRecordFromLine(scanner.nextLine()));
                    vehicles.add(vehicle);
                }

                settleIds(vehicles);

                return vehicles;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
        }
        throw new RuntimeException("Ошибка чтения");
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static String write(ArrayList<Ticket> dataSet, String fileName) {
        try(PrintWriter pw = new PrintWriter(fileName)) {
            if(dataSet.size() == 0) {
                pw.write("");
            } else {
                StringBuilder result = new StringBuilder();
                for (Ticket v: dataSet) {
                    result.append(convertToCSV(v.getStringFields())).append("\n");
                }
                pw.write(result.toString());
            }
            return "Коллекция была сохранена";
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Ошибка сохранения");
    }

    private static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(Converter::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    private static void settleIds(ArrayList<Ticket> organizations) {
        long id = 0;
        for (Ticket vehicles : organizations) {
            vehicles.setId(++id);
        }
    }
}
