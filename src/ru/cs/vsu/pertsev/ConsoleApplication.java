package ru.cs.vsu.pertsev;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    public ConsoleApplication() throws IOException {
        //Получение путей на ввод/вывод из консоли
        InputArgs inputArgs = parseCmdParameters();

        //Чтение из файла
        List<Integer> inputList = FileHandler.readListFromFile(inputArgs.getInputFilePath());

        //Обработка данных
        List<Integer> outputList = Logic.solution(inputList);

        //Запись в файл
        FileHandler.writeListIntoFile(outputList, inputArgs.getOutputFilePath());
    }
    public InputArgs parseCmdParameters() {
        Scanner scanner = new Scanner(System.in);
        String[] filePaths = scanner.nextLine().split("");

        return new InputArgs(filePaths[0], filePaths[1]);
    }
}
