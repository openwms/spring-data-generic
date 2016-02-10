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
package io.interface21.data.document.integration.jpa;

import io.interface21.data.document.integration.SaveOperations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * A JpaRepositoryFunctions.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.0
 */
interface JpaRepositoryOperations<T extends JpaBaseEntity> {

    /**
     * Implementations must provide the concrete repository they're working on.
     *
     * @return A typed JpaRepository
     */
    JpaRepository<T, Long> getRepository();

    /**
     * Adapter to {@link SaveOperations SaveOperations}.
     *
     * @param <T> Any type of {@link JpaBaseEntity}
     */
    interface Save<T extends JpaBaseEntity> extends JpaRepositoryOperations<T>, SaveOperations<T> {

        /**
         * {@inheritDoc}
         * Uses an instance of {@link org.springframework.data.jpa.repository.JpaRepository JpaRepository} to save the
         * entity.
         */
        @Override
        default T save(T entity) {
            return getRepository().save(entity);
        }

        /**
         * {@inheritDoc}
         * Uses an instance of {@link org.springframework.data.jpa.repository.JpaRepository JpaRepository} to save the
         * entities.
         */
        @Override
        default Collection<T> save(Collection<T> entities) {
            return getRepository().save(entities);
        }
    }
}
