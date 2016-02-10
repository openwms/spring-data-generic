/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.interface21.document.integration.mongo;

import io.interface21.document.integration.SaveOperations;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

/**
 * A MongoRepositoryOperations.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.0
 */
interface MongoRepositoryOperations<T extends MongoBaseEntity> {

    /**
     * Implementations must provide the concrete repository they're working on.
     *
     * @return A typed MongoRepository
     */
    MongoRepository<T, String> getRepository();

    /**
     * Adapter to {@link SaveOperations SaveOperations}.
     *
     * @param <T> Any type of {@link MongoBaseEntity}
     */
    interface Save<T extends MongoBaseEntity> extends MongoRepositoryOperations<T>, SaveOperations<T> {

        /**
         * {@inheritDoc}
         * Uses an instance of {@link org.springframework.data.mongodb.repository.MongoRepository MongoRepository} to save the
         * entity.
         */
        @Override
        default T save(T entity) {
            return getRepository().save(entity);
        }

        /**
         * {@inheritDoc}
         * Uses an instance of {@link org.springframework.data.mongodb.repository.MongoRepository MongoRepository} to save the
         * entities.
         */
        @Override
        default Collection<T> save(Collection<T> entities) {
            return getRepository().save(entities);
        }
    }
}
