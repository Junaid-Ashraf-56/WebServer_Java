package request;

import java.util.Map;

public class HttpRequest {
    String method;
    String path;
    String httpVersion;
    Map<String,String> header;
    Map<String,String> queryParameter;
    String body;

    public HttpRequest(String method, String path, String httpVersion, Map<String, String> header, Map<String, String> queryParameter, String body) {
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
        this.header = header;
        this.queryParameter = queryParameter;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, String> getQueryParameter() {
        return queryParameter;
    }

    public void setQueryParameter(Map<String, String> queryParameter) {
        this.queryParameter = queryParameter;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
