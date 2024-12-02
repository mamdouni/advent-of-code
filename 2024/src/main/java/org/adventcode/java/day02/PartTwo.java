package org.adventcode.java.day02;

import org.adventcode.java.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PartTwo extends PartOne {

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

            if(isSafeReport(report)) {
                total += 1;
            }else {
                total += isSafeDampenerReport(report) ? 1 : 0;
            }

        }
        System.out.println("Total safe reports: " + total);
    }

    private boolean isSafeDampenerReport(List<Integer> report) {

        for (int i = 0; i < report.size(); i++) {

            List<Integer> clonedReport = new java.util.ArrayList<>(List.copyOf(report));
            clonedReport.remove(i);
            if(isSafeReport(clonedReport)) {

                System.out.printf("finding a Dampener report %17s %n", clonedReport);
                return true;
            }
        }
        return false;
    }
}
