package fr.gosecuri.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AzureFace {

    private String endpoint;

    private String contentType;

    private HttpClient httpclient;

    private URI uri;

    public AzureFace(String endpoint, String contentType) {
        this.endpoint = endpoint;
        this.contentType = contentType;
    }

    /**
     * Create the builder to call the API
     * @throws URISyntaxException
     */
    private void createBuilder() throws URISyntaxException {
        // Create http client
        this.httpclient = HttpClientBuilder.create().build();

        // Create builder
        URIBuilder builder = new URIBuilder(Property.getProperty("azure.uriBase") + this.endpoint);

        // Request parameters
        builder.setParameter("returnFaceId", "true");
        builder.setParameter("returnFaceLandmarks", "false");

        this.uri = builder.build();
    }

    public String post(HttpEntity bodyEntity) throws IOException, URISyntaxException {
        this.createBuilder();

        // Create request
        HttpPost request = new HttpPost(this.uri);

        // Request headers.
        request.setHeader("Content-Type", this.contentType);
        request.setHeader("Ocp-Apim-Subscription-Key", Property.getProperty("azure.subscriptionKey"));

        // Request body
        request.setEntity(bodyEntity);

        // Execute the REST API call and get the response entity.
        HttpResponse response = this.httpclient.execute(request);
        HttpEntity resEntity = response.getEntity();

        return EntityUtils.toString(resEntity).trim();
    }

}
