/*
 * Copyright 2021 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.search.index;

import org.elasticsearch.client.indices.GetIndexResponse;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Provides functionality for index management.
 */
public interface IndexManagementService {

    String NAME = "search_IndexManagementService";

    /**
     * Creates index if not exists using provided {@link IndexConfiguration}.
     *
     * @param indexConfiguration index configuration
     * @return true if index was successfully created, false otherwise
     * @throws IOException if there is a problem with request/response processing
     */
    boolean createIndex(@Nullable IndexConfiguration indexConfiguration) throws IOException;

    /**
     * Drops index by name.
     *
     * @param indexName index name
     * @return true if index was successfully dropped, false otherwise
     * @throws IOException if there is a problem with request/response processing
     */
    boolean dropIndex(@Nullable String indexName) throws IOException;

    /**
     * Drops (if exists) and creates index using provided {@link IndexConfiguration}.
     *
     * @param indexConfiguration index configuration
     * @return true if index was successfully recreated, false otherwise
     * @throws IOException if there is a problem with request/response processing
     */
    boolean recreateIndex(@Nullable IndexConfiguration indexConfiguration) throws IOException;

    /**
     * Checks if index exists.
     *
     * @param indexName index name
     * @return true if index exists, false otherwise
     * @throws IOException in case of request failure
     */
    boolean isIndexExist(@Nullable String indexName) throws IOException;

    /**
     * Checks if index has actual configuration.
     *
     * @param indexConfiguration actual configuration
     * @return true if existing index has the same configuration as the provided one, false otherwise
     * @throws IOException in case of request failure
     */
    boolean isIndexActual(@Nullable IndexConfiguration indexConfiguration) throws IOException;

    /**
     * Requests info about index from ES cluster.
     *
     * @param indexName index name
     * @return response
     * @throws IOException in case of request failure
     */
    GetIndexResponse getIndex(@Nullable String indexName) throws IOException;

    /**
     * Prepares all search indices defined in application.<br/>
     * See {@link IndexManagementService#prepareIndex(IndexConfiguration)}
     */
    void prepareIndexes();

    /**
     * Updates index to the actual state.<br/>
     * Non-existent index will be created, irrelevant index will be recreated.
     *
     * @param indexConfiguration actual index configuration
     */
    void prepareIndex(@Nullable IndexConfiguration indexConfiguration);
}
