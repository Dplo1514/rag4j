package com.rag.rag4j.embadding.adapter.out;

import com.rag.rag4j.embadding.application.port.out.ChromaPort;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ChromaClient implements ChromaPort {

    private final ChromaConfig chromaConfig;
    private final RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers;

    @PostConstruct
    void init() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(chromaConfig.getUsername(), chromaConfig.getPassword());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        this.headers = httpHeaders;
    }

    @Override
    public void createTenant(String tenantName) {
        String url = chromaConfig.getHost() + "/api/v1/tenants";
        Map<String, String> requestBody = Map.of("name", tenantName);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to create tenant: " + response.getStatusCode());
        }
    }

    @Override
    public void createDatabase(String tenantName, String databaseName) {
        String url = chromaConfig.getHost() + "/api/v1/databases?tenant=" + tenantName;
        Map<String, String> requestBody = Map.of("name", databaseName);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to create database: " + response.getStatusCode());
        }
    }

    @Override
    public String createCollection(String tenantName, String databaseName, String collectionName,
                                   Map<String, Object> configuration, Map<String, Object> metadata, boolean getOrCreate) {
        String url = chromaConfig.getHost() + "/api/v1/collections?tenant=" + tenantName + "&database=" + databaseName;

        Map<String, Object> requestBody = Map.of(
                "name", collectionName,
                "configuration", configuration != null ? configuration : new HashMap<>(),
                "metadata", metadata != null ? metadata : new HashMap<>(),
                "get_or_create", getOrCreate
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to create collection: " + response.getStatusCode());
        }

        return response.getBody().get("id").toString();
    }

    @Override
    public void saveEmbedding(String collectionName, List<double[]> embeddings, List<String> ids,
                              List<String> documents, List<Map<String, Object>> metadatas, List<String> uris) {
        String url = chromaConfig.getHost() + "/api/v1/collections/" + collectionName + "/add";

        List<List<Float>> floatEmbeddings = new ArrayList<>();
        for (double[] embedding : embeddings) {
            List<Float> floatEmbedding = new ArrayList<>();
            for (double value : embedding) {
                floatEmbedding.add((float) value);
            }
            floatEmbeddings.add(floatEmbedding);
        }

        Map<String, Object> requestBody = Map.of(
                "embeddings", floatEmbeddings,
                "ids", ids,
                "documents", documents,
                "metadatas", metadatas != null ? metadatas : new ArrayList<>(),
                "uris", uris != null ? uris : new ArrayList<>()
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to save embeddings: " + response.getStatusCode());
        }
    }

    @Override
    public Map<String, Object> retrieveNearestNeighbors(String collectionId, double[] queryEmbedding, int topK) {
        String url = chromaConfig.getHost() + "/api/v1/collections/" + collectionId + "/query";

        List<Float> floatQueryEmbedding = Arrays.stream(queryEmbedding)
                .mapToObj(value -> (float) value)
                .toList();

        Map<String, Object> requestBody = Map.of(
                "query_embeddings", List.of(floatQueryEmbedding),
                "n_results", topK,
                "include", List.of("metadatas", "documents", "distances")
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to retrieve nearest neighbors: " + response.getStatusCode());
        }

        return response.getBody();
    }
}
