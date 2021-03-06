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
package org.jnosql.artemis.orientdb.document;


import org.jnosql.artemis.Converters;
import org.jnosql.artemis.document.AbstractDocumentTemplateAsync;
import org.jnosql.artemis.document.DocumentEntityConverter;
import org.jnosql.artemis.reflection.ClassRepresentations;
import org.jnosql.diana.api.document.DocumentCollectionManagerAsync;
import org.jnosql.diana.api.document.DocumentEntity;
import org.jnosql.diana.orientdb.document.OrientDBDocumentCollectionManagerAsync;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

/**
 * The default implementation of {@link OrientDBTemplateAsync}
 */
@Typed(OrientDBTemplateAsync.class)
class DefaultOrientDBTemplateAsync extends AbstractDocumentTemplateAsync implements
        OrientDBTemplateAsync {

    private DocumentEntityConverter converter;

    private Instance<OrientDBDocumentCollectionManagerAsync> manager;

    private ClassRepresentations classRepresentations;

    private Converters converters;

    @Inject
    DefaultOrientDBTemplateAsync(DocumentEntityConverter converter,
                                 Instance<OrientDBDocumentCollectionManagerAsync> manager,
                                 ClassRepresentations classRepresentations, Converters converters) {
        this.converter = converter;
        this.manager = manager;
        this.classRepresentations = classRepresentations;
        this.converters = converters;
    }

    DefaultOrientDBTemplateAsync() {
    }

    @Override
    protected DocumentEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected DocumentCollectionManagerAsync getManager() {
        return manager.get();
    }

    @Override
    protected ClassRepresentations getClassRepresentations() {
        return classRepresentations;
    }

    @Override
    protected Converters getConverters() {
        return converters;
    }

    @Override
    public <T> void sql(String query, Consumer<List<T>> callBack, Object... params) {
        requireNonNull(query, "query is required");
        requireNonNull(callBack, "callBack is required");

        Consumer<List<DocumentEntity>> dianaCallBack = d -> callBack.accept(
                d.stream()
                        .map(getConverter()::toEntity)
                        .map(o -> (T) o)
                        .collect(toList()));

        manager.get().sql(query, dianaCallBack, params);

    }

    @Override
    public <T> void sql(String query, Consumer<List<T>> callBack, Map<String, Object> params) {
        requireNonNull(query, "query is required");
        requireNonNull(callBack, "callBack is required");
        requireNonNull(params, "params is required");

        Consumer<List<DocumentEntity>> dianaCallBack = d -> callBack.accept(
                d.stream()
                        .map(getConverter()::toEntity)
                        .map(o -> (T) o)
                        .collect(toList()));

        manager.get().sql(query, dianaCallBack, params);
    }
}
