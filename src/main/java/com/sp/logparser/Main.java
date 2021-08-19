package com.sp.logparser;

import com.sp.logparser.core.LogParser;
import com.sp.logparser.input.FileInputReader;
import com.sp.logparser.input.InputReader;

public class Main {
    
    public static void main(String[] args) {

        InputReader reader = new FileInputReader("src/main/resources/log.txt");
        LogParser parser = new LogParser(reader);
        parser.parse();
        System.out.println(parser.uniqueIps());
        System.out.println(parser.topNIps(3));
        System.out.println(parser.topActiveUrl(3));
    }
}
