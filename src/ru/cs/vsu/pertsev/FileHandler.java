package ru.cs.vsu.pertsev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static List<Integer> readListFromFile(String filePath) throws FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        Scanner scanner = new Scanner(fr);

        String primaryString = scanner.nextLine();
        List<Integer> finalArray = new ArrayList<>();

        for(String elem: primaryString.split("")) {
            try {
                finalArray.add(Integer.valueOf(elem));
            } catch (Exception ignored) {
                ;
            }
        }

        return  finalArray;
    }

    public static void writeListIntoFile(List<Integer> list, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        String finalString = "";
        for(Integer elem: list) {
            finalString += String.valueOf(elem);
        }

        fw.write(finalString);
        fw.close();
    }
}
