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
package io.interface21.document;

import io.interface21.document.integration.SaveOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A DocumentServiceImpl.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.0
 */
@Service
class DocumentServiceImpl implements DocumentService {

    private SaveOperations ops;

    @Autowired
    protected DocumentServiceImpl(SaveOperations ops) {
        this.ops = ops;
    }

    /*~
      A service implementation is completely independent from the underlying persistence solution. This might be a NoSQL
      store as well as a relational database accessed via JPA, oder JDBC.
     */

}
