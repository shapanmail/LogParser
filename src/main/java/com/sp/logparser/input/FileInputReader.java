package com.sp.logparser.input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileInputReader implements InputReader {
    private String filepath;

    public FileInputReader(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public List<String> readInput() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            return br.lines().collect(Collectors.toList());
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Input file not found : " + filepath);
        }

    }
}
