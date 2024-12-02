package org.adventcode.java.day01;

import org.adventcode.java.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class PartTwo {

    public static final String FILE_TEST_NAME = "day01/input.txt";

    void main() throws IOException {

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
                .toList();

        Map<Integer, Long> secondListOccurences = FileUtils.getFileContentFromResources(FILE_TEST_NAME)
                .stream()
                .flatMap(string ->
                        Arrays.asList(string.split(" ")).stream()
                )
                .map(String::trim)
                .filter(StringUtils::isNotEmpty)
                .map(Integer::parseInt)
                .filter(_ -> index.getAndIncrement() % 2 == 1)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        AtomicLong total = new AtomicLong();
        firstList.forEach(element -> {

            if (secondListOccurences.containsKey(element)) {
                total.addAndGet(element * secondListOccurences.get(element));
            }
        });
        System.out.println(total);
    }
}
