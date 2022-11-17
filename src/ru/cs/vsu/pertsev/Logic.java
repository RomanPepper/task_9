package ru.cs.vsu.pertsev;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    private static final String listSizeIsZero = "Error";
    public static List<Integer> solution(List<Integer> primaryList) {
        List<Integer> currentSequence = new ArrayList<>();
        List<Integer> longestSequence = new ArrayList<>();

        //Обработка особых случаев
        if(primaryList.size() == 1) {
            return primaryList;
        } else if(primaryList.size() == 2) {
            return primaryList;
        }

        //Обработка нормальных случаев
        for(int i = 0; i < primaryList.size(); i++) {
            currentSequence.add(primaryList.get(i));

            for(int secondElemIndex = i+1; secondElemIndex < primaryList.size(); secondElemIndex++) {

                currentSequence.add(primaryList.get(secondElemIndex));
                int diff = currentSequence.get(0) - currentSequence.get(1);

                for(int j = secondElemIndex+1; j < primaryList.size(); j++) {
                    if(getLastListElement(currentSequence) - primaryList.get(j) == diff) {
                        currentSequence.add(primaryList.get(j));
                    }
                }

                longestSequence = getLongestSequence(longestSequence, currentSequence);
                currentSequence = new ArrayList<>();
                currentSequence.add(primaryList.get(i));
            }
            currentSequence = new ArrayList<>();
        }

        return longestSequence;
    }
    public static List<Integer> getLongestSequence(List<Integer> firstSequence, List<Integer> secondSequence) {
        //Обработка нетипичных случаев
        if(firstSequence == null && secondSequence != null) {
            return secondSequence;
        } else if(firstSequence != null && secondSequence == null) {
            return firstSequence;
        } else if(firstSequence == null && secondSequence == null) {
            System.err.println(listSizeIsZero);
        }

        //Обработка типичных случаев
        if(firstSequence.size() > secondSequence.size()) {
            return firstSequence;
        } else if (secondSequence.size() > firstSequence.size()) {
            return secondSequence;
        } else {
            return firstSequence;
        }
    }
    public static Integer getLastListElement(List<Integer> list) {
        return list.get(list.size() - 1);
    }
}
