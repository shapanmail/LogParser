package com.sp.logparser;

import com.sp.logparser.core.LogParser;
import com.sp.logparser.input.FileInputReader;
import com.sp.logparser.input.InputReader;

public class Main {
    static String rec = "177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"";

    public static void main(String[] args) {
//        InputReader reader = new InputReader() {
//            @Override
//            public List<String> readInput() {
//                return List.of(rec);
//            }
//        };
        InputReader reader = new FileInputReader("src/main/resources/log.txt");
        LogParser parser = new LogParser(reader);
        parser.parse();
        System.out.println(parser.uniqueIps());
        System.out.println(parser.topNIps(3));
        System.out.println(parser.topActiveUrl(3));
    }
}
