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
package org.jnosql.artemis.hazelcast.key;

import com.hazelcast.query.Predicate;
import org.jnosql.artemis.key.KeyValueTemplate;

import java.util.Collection;
import java.util.Map;

/**
 * A template layer to Hazelcast key-value type
 */
public interface HazelcastTemplate extends KeyValueTemplate {

    /**
     * Executes hazelcast query
     *
     * @param <T>   the entity type
     * @param query the query
     * @return the result query
     * @throws NullPointerException when there is null query
     */
    <T> Collection<T> query(String query) throws NullPointerException;

    /**
     * Executes hazelcast query with named query.
     * E.g.:  bucketManager.query("name = :name", singletonMap("name", "Matrix"))
     *
     * @param query  the query
     * @param <T>    the entity type
     * @param params the params to bind
     * @return the result query
     * @throws NullPointerException when there is null query
     */
    <T> Collection<T> query(String query, Map<String, Object> params) throws NullPointerException;

    /**
     * Executes hazelcast query
     *
     * @param predicate the hazelcast predicate
     * @param <K>       the key type
     * @param <V>       the value type
     * @return the result query
     * @throws NullPointerException when there is null predicate
     */
    <K, V> Collection<V> query(Predicate<K, V> predicate) throws NullPointerException;

}
