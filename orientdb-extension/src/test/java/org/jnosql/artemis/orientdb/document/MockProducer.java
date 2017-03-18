/*
 * Copyright 2017 Otavio Santana and others
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
package org.jnosql.artemis.orientdb.document;


import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentEntity;
import org.jnosql.diana.orientdb.document.OrientDBDocumentCollectionManager;
import org.jnosql.diana.orientdb.document.OrientDBDocumentCollectionManagerAsync;
import org.mockito.Mockito;

import javax.enterprise.inject.Produces;

public class MockProducer {


    @Produces
    public OrientDBDocumentCollectionManager getManager() {
        OrientDBDocumentCollectionManager manager = Mockito.mock(OrientDBDocumentCollectionManager.class);
        DocumentEntity entity = DocumentEntity.of("Person");
        entity.add(Document.of("name", "Ada"));
        Mockito.when(manager.save(Mockito.any(DocumentEntity.class))).thenReturn(entity);
        return manager;
    }

    @Produces
    public OrientDBDocumentCollectionManagerAsync getManagerAsync() {
        return Mockito.mock(OrientDBDocumentCollectionManagerAsync.class);
    }
}
