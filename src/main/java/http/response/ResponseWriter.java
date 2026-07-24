package http.response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ResponseWriter {

    public static void write(HttpResponse response, OutputStream outputStream)
            throws IOException {

        String body = response.getBody();

        if (body == null) {
            body = "";
        }

        response.getHeaders().put(
                "content-length",
                String.valueOf(body.getBytes(StandardCharsets.UTF_8).length)
        );

        response.getHeaders().putIfAbsent(
                "connection",
                "close"
        );

        StringBuilder builder = new StringBuilder();

        builder.append("HTTP/1.1 ")
                .append(response.getStatus().getStatusCode())
                .append(" ")
                .append(response.getStatus().getReasonPhrase())
                .append("\r\n");

        for (Map.Entry<String, String> header
                : response.getHeaders().entrySet()) {

            builder.append(header.getKey())
                    .append(": ")
                    .append(header.getValue())
                    .append("\r\n");
        }

        builder.append("\r\n");

        builder.append(body);

        outputStream.write(
                builder.toString().getBytes(StandardCharsets.UTF_8)
        );

        outputStream.flush();
    }

}
