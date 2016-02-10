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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A JpaDocumentRepositoryImpl.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.0
 */
@Repository
class JpaDocumentRepositoryImpl implements JpaRepositoryOperations.Save<DocumentEO> {

    private JpaDocumentRepository jpaRepository;

    /**
     * Constructor with not-null members.
     *
     * @param repository The repo to work on
     */
    @Autowired
    protected JpaDocumentRepositoryImpl(JpaDocumentRepository repository) {
        this.jpaRepository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JpaRepository<DocumentEO, Long> getRepository() {
        return jpaRepository;
    }

    /**
     * The inner definition of Spring Data repository. No need to put this into a separate class file.
     */
    interface JpaDocumentRepository extends JpaRepository<DocumentEO, Long> {

    }
}
