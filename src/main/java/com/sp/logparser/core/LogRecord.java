package com.sp.logparser.core;

public class LogRecord {
    private String clientIpAddress;         // ip
    private String rfc1413ClientIdentity;   //  `-`
    private String remoteUser;             // `-`
    private String dateTime;                // [day/month/year:hour:minute:second zone]
    private String request;                 // `GET /foo ...`
    private String httpStatusCode;
    private String bytesSent;
    private String referer;                  // where the visitor came from
    private String userAgent;                // long string to represent the browser and OS

    public LogRecord(String clientIpAddress, String rfc1413ClientIdentity,
                     String remoteUser, String dateTime, String request,
                     String httpStatusCode, String bytesSent,
                     String referer, String userAgent) {
        this.clientIpAddress = clientIpAddress;
        this.rfc1413ClientIdentity = rfc1413ClientIdentity;
        this.remoteUser = remoteUser;
        this.dateTime = dateTime;
        this.request = request;
        this.httpStatusCode = httpStatusCode;
        this.bytesSent = bytesSent;
        this.referer = referer;
        this.userAgent = userAgent;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public String getRfc1413ClientIdentity() {
        return rfc1413ClientIdentity;
    }

    public void setRfc1413ClientIdentity(String rfc1413ClientIdentity) {
        this.rfc1413ClientIdentity = rfc1413ClientIdentity;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getBytesSent() {
        return bytesSent;
    }

    public void setBytesSent(String bytesSent) {
        this.bytesSent = bytesSent;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
