package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        task1SortByNameAndSurname("task1input1.txt", "task1output1.txt");
        task2FindDuplicatePhoneNumbers("task2input1.txt", "task2output1.txt");
    }

    public static void task1SortByNameAndSurname(String inputFilename, String outputFilename) {
        try {
            File inputFile = new File(inputFilename);
            Scanner scanner = new Scanner(inputFile);

            List<String> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            Collections.sort(lines, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String[] parts1 = o1.split(" ");
                    String[] parts2 = o2.split(" ");
                    int lastNameCompare = parts1[1].compareTo(parts2[1]);
                    if (lastNameCompare != 0) {
                        return lastNameCompare;
                    } else {
                        return parts1[0].compareTo(parts2[0]);
                    }
                }
            });

            scanner.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));

            for (String sortedLine : lines) {
                writer.write(sortedLine);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void task2FindDuplicatePhoneNumbers(String inputFilename, String outputFilename) {
        try {
            File inputFile = new File(inputFilename);
            Scanner scanner = new Scanner(inputFile);

            Map<String, Integer> phoneNumberCount = new HashMap<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String phoneNumber = parts[2];
                phoneNumberCount.put(phoneNumber, phoneNumberCount.getOrDefault(phoneNumber, 0) + 1);
            }

            scanner.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));

            for (Map.Entry<String, Integer> entry : phoneNumberCount.entrySet()) {
                if (entry.getValue() >= 2) {
                    writer.write(entry.getKey());
                    writer.newLine();
                }
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}