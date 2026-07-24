package main.java.http.request;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpRequestParser {
    public HttpRequest parseData(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );

        String requestLine = bufferedReader.readLine();

        if (requestLine == null || requestLine.isBlank()){
            throw new IllegalArgumentException("Empty HTTP request");
        }

        String[] requestParts = requestLine.split(" ",3);

        if (requestParts.length!=3){
            throw new IllegalArgumentException("Invalid main.java.http main.java.http.request line");
        }

        String method = requestParts[0];
        String requestTarget = requestParts[1];
        String httpVersion = requestParts[2];

        String path = requestTarget;
        Map<String,String> queryParameter = new HashMap<>();

        int questionMarkIndex = requestTarget.indexOf("?");

        if (questionMarkIndex>=0){
            path = requestTarget.substring(0,questionMarkIndex);

            String queryString = requestTarget.substring(questionMarkIndex+1);

            parseQueryParameters(queryString,queryParameter);
        }

        Map<String,String> headers = new HashMap<>();
        String line;

        while((line = bufferedReader.readLine())!= null && !line.isEmpty()){
            int colonIndex = line.indexOf(":");

            if (colonIndex<=0){
                throw new IllegalArgumentException("Invalid Http header"+ line);
            }

            String headerName = line.substring(0,colonIndex).trim().toLowerCase();
            String headerValue = line.substring(colonIndex+1).trim();

            headers.put(headerName,headerValue);
        }

        String body = "";
        String contentLengthValue = headers.get("content-length");
        if (contentLengthValue != null) {
            int contentLength;

            try {
                contentLength = Integer.parseInt(contentLengthValue);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException(
                        "Invalid Content-Length"
                );
            }

            char[] bodyCharacters = new char[contentLength];

            int totalRead = 0;

            while (totalRead < contentLength) {
                int readCount = bufferedReader.read(
                        bodyCharacters,
                        totalRead,
                        contentLength - totalRead
                );

                if (readCount == -1) {
                    throw new IOException(
                            "Connection closed before body was fully read"
                    );
                }

                totalRead += readCount;
            }
            body = new String(bodyCharacters);
        }
        return new HttpRequest(
                method,
                path,
                httpVersion,
                headers,
                queryParameter,
                body
        );
    }
    public void parseQueryParameters(String queryString,Map<String,String> queryParameter){
        if (queryString.isBlank()){
            return;
        }

        String[] pairs = queryString.split("&");

        for (String pair:pairs){
            String[] keyValue = pair.split("=",2);

            String key = decode(keyValue[0]);
            String value = keyValue.length==2?decode(keyValue[1]):"";

            queryParameter.put(key,value);
        }
    }

    public String decode(String value){
        return URLDecoder.decode(
                value,
                StandardCharsets.UTF_8
        );
    }
}

//
//GET /users?id=5 HTTP/1.1
//Host: localhost:9090
//User-Agent: Chrome
//Accept: */*
