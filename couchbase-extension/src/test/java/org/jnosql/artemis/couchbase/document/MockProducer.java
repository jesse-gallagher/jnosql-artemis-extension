/*
 *  Copyright (c) 2017 Otávio Santana and others
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.jnosql.artemis.couchbase.document;


import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentEntity;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentCollectionManager;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentCollectionManagerAsync;
import org.mockito.Mockito;

import javax.enterprise.inject.Produces;

public class MockProducer {


    @Produces
    public CouchbaseDocumentCollectionManager getManager() {
        CouchbaseDocumentCollectionManager manager = Mockito.mock(CouchbaseDocumentCollectionManager.class);
        DocumentEntity entity = DocumentEntity.of("Person");
        entity.add(Document.of("name", "Ada"));
        Mockito.when(manager.insert(Mockito.any(DocumentEntity.class))).thenReturn(entity);
        return manager;
    }

    @Produces
    public CouchbaseDocumentCollectionManagerAsync getManagerAsync() {
        return Mockito.mock(CouchbaseDocumentCollectionManagerAsync.class);
    }
}
