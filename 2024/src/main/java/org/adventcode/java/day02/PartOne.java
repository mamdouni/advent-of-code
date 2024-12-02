package org.adventcode.java.day02;

import org.adventcode.java.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PartOne {

    public static final String FILE_TEST_NAME = "day02/input.txt";

    void main() throws IOException {

        int total = 0;
        for(String line : FileUtils.getFileContentFromResources(FILE_TEST_NAME)) {

            List<Integer> report = Arrays.asList(line.split(" ")).stream()
                    .map(String::trim)
                    .filter(StringUtils::isNotEmpty)
                    .map(Integer::parseInt)
                    .toList();
            System.out.printf("is the report %32s safe ? , answer is : %b%n", report, isSafeReport(report));
            total += isSafeReport(report) ? 1 : 0;
        }
        System.out.println("Total safe reports: " + total);
    }

    boolean isSafeReport(List<Integer> report) {

        int currentLevel;
        int nextLevel;
        LevelType levelType = report.getFirst() < report.get(1) ? LevelType.ASC : LevelType.DESC;
        for (int i = 0; i < report.size() - 1; i++) {

            currentLevel = report.get(i);
            nextLevel = report.get(i + 1);
            if(!areSafeLevels(currentLevel, nextLevel, levelType)) {
                return false;
            }
        }
        return true;
    }

    private boolean areSafeLevels(int currentLevel, int nextLevel, LevelType levelType) {

        if(Objects.equals(currentLevel, nextLevel)) {
            return false;
        }
        if (currentLevel < nextLevel && levelType == LevelType.DESC) {
            return false;
        }
        if (currentLevel > nextLevel && levelType == LevelType.ASC) {
            return false;
        }
        if(isLevelDifferenceNotValid(currentLevel, nextLevel)) {
            return false;
        }
        return true;
    }

    private boolean isLevelDifferenceNotValid(int currentLevel, int nextLevel) {
        return Math.abs(currentLevel - nextLevel) < 1 || Math.abs(currentLevel - nextLevel) > 3;
    }
}

enum LevelType {
    ASC,
    DESC
}