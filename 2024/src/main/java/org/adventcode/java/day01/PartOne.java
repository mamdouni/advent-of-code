package org.adventcode.java.day01;

import org.adventcode.java.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PartOne {

    public static final String FILE_TEST_NAME = "day01/input.txt";

    public static void main(String[] args) throws IOException {

        AtomicInteger index = new AtomicInteger();

        List<Integer> firstList = FileUtils.getFileContentFromResources(FILE_TEST_NAME)
                .stream()
                .flatMap(string ->
                        Arrays.asList(string.split(" ")).stream()
                )
                .map(String::trim)
                .filter(StringUtils::isNotEmpty)
                .map(Integer::parseInt)
                .filter(_ -> index.getAndIncrement() % 2 == 0)
                .sorted()
                .toList();

        List<Integer> secondList = FileUtils.getFileContentFromResources(FILE_TEST_NAME)
                .stream()
                .flatMap(string ->
                        Arrays.asList(string.split(" ")).stream()
                )
                .map(String::trim)
                .filter(StringUtils::isNotEmpty)
                .map(Integer::parseInt)
                .filter(_ -> index.getAndIncrement() % 2 == 1)
                .sorted()
                .toList();

        int total = 0;
        for (int i = 0; i < firstList.size(); i++) {

            total += Math.abs(firstList.get(i) - secondList.get(i));
        }
        System.out.println(total);
    }
}