package org.adventcode.java;

import org.adventcode.java.day01.PartOne;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public final class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static List<String> getFileContentFromResources(String path) throws IOException {

        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found! " + path);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.toList());
        }
    }
}
