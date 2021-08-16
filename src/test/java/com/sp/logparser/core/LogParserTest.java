package com.sp.logparser.core;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LogParserTest extends TestCase {

    String[] data = {
            "177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0\"",
            "168.41.191.40 - - [09/Jul/2018:10:11:30 +0200] \"GET http://example.net/faq/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0\"",
            "168.41.191.41 - - [11/Jul/2018:17:41:30 +0200] \"GET /this/page/does/not/exist/ HTTP/1.1\" 404 3574 \"-\" \"Mozilla/5.0\"",
            "168.41.191.40 - - [09/Jul/2018:10:10:38 +0200] \"GET http://example.net/blog/category/meta/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0\"",
            "177.71.128.21 - - [10/Jul/2018:22:22:08 +0200] \"GET /blog/2018/08/survey-your-opinion-matters/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0\"",
            "168.41.191.9 - - [09/Jul/2018:23:00:42 +0200] \"GET /docs/manage-users/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0\"",
            "168.41.191.40 - - [09/Jul/2018:10:11:56 +0200] \"GET /blog/category/community/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0\"",
            "168.41.191.34 - - [10/Jul/2018:22:01:17 +0200] \"GET /faq/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0\"",
            "177.71.128.21 - - [10/Jul/2018:22:21:03 +0200] \"GET /docs/manage-websites/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0\""
    };

    LogParser parser;

    @Override
    protected void setUp() throws Exception {
        this.parser = new LogParser(() -> Arrays.asList(data));
    }

    @Test
    public void testUniqueIps() {
        parser.parse();
        Set<String> uniqueIps = parser.uniqueIps();
        String[] expected = {"168.41.191.34", "168.41.191.41", "177.71.128.21", "168.41.191.40", "168.41.191.9"};
        assertEquals(new HashSet<>(Arrays.asList(expected)), uniqueIps);
    }

    @Test
    public void testTopNIps() {
        parser.parse();
        Set<String> top3Ips = parser.topNIps(3);
        String[] expected = {"168.41.191.34", "177.71.128.21", "168.41.191.40"};
        System.out.println(top3Ips);
        assertEquals(new HashSet<>(Arrays.asList(expected)), top3Ips);
    }

    @Test
    public void testTopActiveUrl() {
        parser.parse();
        Set<String> topNUrl = parser.topActiveUrl(3);
        String[] expected = {"/blog/2018/08/survey-your-opinion-matters/", "http://example.net/faq/", "/this/page/does/not/exist/"};
        assertEquals(new HashSet<>(Arrays.asList(expected)), topNUrl);
    }

}