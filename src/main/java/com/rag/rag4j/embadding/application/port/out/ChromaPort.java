package com.rag.rag4j.embadding.application.port.out;

import java.util.List;
import java.util.Map;

public interface ChromaPort {

    void createTenant(String tenantName);

    void createDatabase(String tenantName, String databaseName);

    String createCollection(String tenantName,
                            String databaseName,
                            String collectionName,
                            Map<String, Object> configuration,
                            Map<String, Object> metadata,
                            boolean getOrCreate);

    void saveEmbedding(
            String collectionName,
            List<double[]> embeddings,
            List<String> ids,
            List<String> documents,
            List<Map<String, Object>> metadatas
    );

    Map<String, Object> retrieveNearestNeighbors (String collectionId,
                                                 double[] queryEmbedding,
                                                 int topK);
}
