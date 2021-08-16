package com.sp.logparser.core;

import com.sp.logparser.input.InputReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser {

    private String ipDigit = "\\d{1,3}";                 // at least 1 but not more than 3 times
    private String client = "(\\S+)";                    // '\S' is 'non-whitespace character'
    private String user = "(\\S+)";
    private String dateTime = "(\\[.+?\\])";              // like `[21/Jul/2009:02:48:13 -0700]`
    private String request = "\"(.*?)\"";                 // any number of any character
    private String status = "(\\d{3})";
    private String bytes = "(\\S+)";                      // this can be a "-"
    private String referer = "\"(.*?)\"";
    private String agent = "\"(.*?)\"";


    private InputReader reader;
    private List<LogRecord> parsedRecords;
    private List<String> parseFailedRecords;
    private Map<String, List<Integer>> ipRecordsMap;
    private Map<String, List<Integer>> urlRecordsMap;


    public LogParser(InputReader reader) {
        this.reader = reader;
        this.parsedRecords = new ArrayList<>();
        this.parseFailedRecords = new ArrayList<>();
        this.ipRecordsMap = new HashMap<>();
        this.urlRecordsMap = new HashMap<>();
    }


    public void parse() {
        List<String> inputLines = reader.readInput();
        String regex = regex();

        inputLines.forEach(line -> {
            LogRecord record = parseRecord(line, regex);

            if (record == null) {
                this.parseFailedRecords.add(line);
            } else {
                this.parsedRecords.add(record);
            }

            int index = this.parsedRecords.size() - 1;
            putToMap(ipRecordsMap, record.getClientIpAddress(), index);
            putToMap(urlRecordsMap, parseUrlFromRequest(record.getRequest()), index);

        });
    }

    private void putToMap(Map<String, List<Integer>> map, String key, int index) {
        List<Integer> mappedRecords = map.get(key);
        if (mappedRecords == null) {
            map.put(key, new ArrayList<Integer>(Arrays.asList(index)));
        } else {
            mappedRecords.add(index);
            map.put(key, mappedRecords);
        }
    }


    public Set<String> uniqueIps() {
        return this.ipRecordsMap.keySet();
    }


    public Set<String> topNIps(int n) {
        return topN(n, ipRecordsMap);
    }

    public Set<String> topActiveUrl(int n) {
        return topN(n, urlRecordsMap);
    }

    public Set<String> uniqueUrls() {
        return this.urlRecordsMap.keySet();
    }

    private Set<String> topN(int n, Map<String, List<Integer>> map) {
        return map.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }


    private LogRecord parseRecord(String line, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        return matcher.find() ? buildLogRecord(matcher) : null;
    }

    private LogRecord buildLogRecord(Matcher matcher) {
        return new LogRecord(
                matcher.group(1), // IP
                matcher.group(2), // clientIdentity
                matcher.group(3), // remote User
                matcher.group(4), // date
                matcher.group(5), // request
                matcher.group(6), // http status code
                matcher.group(7), // byte send
                matcher.group(8), // referer
                matcher.group(9)); // user agent
    }

    public List<String> getParseFailedRecords() {
        return parseFailedRecords;
    }

    private String parseUrlFromRequest(String request) {
        String[] reqItems = request.split(" ");
        return (reqItems == null || reqItems.length != 3) ? null : reqItems[1];

    }

    private String regex() {
        String ipRegex = "(" + ipDigit + "\\." + ipDigit + "\\." + ipDigit + "\\." + ipDigit + ")?";
        String[] subRegex = {ipRegex, client, user, dateTime, request, status, bytes, referer, agent};
        return String.join(" ", subRegex);
    }
}

