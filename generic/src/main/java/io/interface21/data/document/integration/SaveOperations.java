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
package io.interface21.data.document.integration;

import java.util.Collection;

/**
 * A SaveOperations.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.0
 */
public interface SaveOperations<T extends BaseEntity<?>> {

    /**
     * Saves the entity instance and return the saved instance.
     *
     * @param entity The instance to save
     * @return The saved instance, this may differ from the instance to save
     */
    T save(T entity);

    /**
     * Saves a collection of entities and returns the saved entity collection.
     *
     * @param entities The collection of instances to save
     * @return The collection of saved entities
     */
    Collection<T> save(Collection<T> entities);
}
