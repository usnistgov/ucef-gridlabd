package gov.nist.hla.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HTTPClient {
    private static final Logger logger = LogManager.getLogger();

    // IPv4 address and port number
    private String authority;

    private HttpClient client;

    public HTTPClient(String authority) {
        this.authority = authority;
        this.client = HttpClients.createDefault();
    }
    
    public String getAuthority() {
        return authority;
    }

    public String get(String path)
            throws StatusCodeException,
                   HTTPClientException {
        String uri = "http://" + authority + path;
        HttpGet request = new HttpGet(uri);
        HttpResponse response = sendRequest(request);

        HttpEntity entity = response.getEntity();
        if (entity == null) {
            throw new HTTPClientException("HTTP response contained no data");
        }

        try {
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            throw new HTTPClientException("Unable to interpret HTTP response");
        }
    }

    private HttpResponse sendRequest(HttpUriRequest request)
            throws StatusCodeException,
                   HTTPClientException {
        logger.debug(request.getMethod() + " request for URI: " + request.getURI());

        HttpResponse response;
        try {
            response = client.execute(request); // can this return null?
        } catch (IOException e) {
            throw new HTTPClientException(e);
        }
        
        StatusLine status = response.getStatusLine();
        if (status == null) {
            throw new StatusCodeException("HTTP response omitted status line");
        }

        int statusCode = status.getStatusCode();
        if (statusCode < 200 || statusCode >= 300) {
            throw new StatusCodeException(status.getReasonPhrase(), statusCode);
        }
        logger.debug("received status code " + statusCode + ": " + status.getReasonPhrase());

        return response;
    }
}
