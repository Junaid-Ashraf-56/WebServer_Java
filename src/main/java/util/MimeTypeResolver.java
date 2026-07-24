package util;

public class MimeTypeResolver {

    public static String resolve(String path) {

        int index = path.lastIndexOf(".");

        if (index == -1) {
            return "application/octet-stream";
        }

        String extension = path.substring(index + 1).toLowerCase();

        switch (extension) {
            case "html":
                return "text/html";

            case "css":
                return "text/css";

            case "js":
                return "application/javascript";

            case "json":
                return "application/json";

            case "txt":
                return "text/plain";

            case "png":
                return "image/png";

            case "jpg":
            case "jpeg":
                return "image/jpeg";

            case "gif":
                return "image/gif";

            case "svg":
                return "image/svg+xml";

            case "ico":
                return "image/x-icon";

            default:
                return "application/octet-stream";
        }
    }
}