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
package org.jnosql.artemis.cassandra.column;


import com.datastax.driver.core.ConsistencyLevel;
import org.jnosql.diana.api.column.ColumnQuery;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CassandraReturnTypeConverterUtil {

    private CassandraReturnTypeConverterUtil() {
    }

    static Object returnObject(List result, Class typeClass, Method method) {
        Class<?> returnType = method.getReturnType();

        if (typeClass.equals(returnType)) {
            if (!result.isEmpty()) {
                return result.get(0);
            } else {
                return null;
            }

        } else if (Optional.class.equals(returnType)) {
            return result.stream().findFirst();
        } else if (List.class.equals(returnType)
                || Iterable.class.equals(returnType)
                || Collection.class.equals(returnType)) {
            return result;
        } else if (Set.class.equals(returnType)) {
            return result.stream().collect(Collectors.toSet());
        } else if (Queue.class.equals(returnType)) {
            return result.stream().collect(Collectors.toCollection(PriorityQueue::new));
        } else if (Stream.class.equals(returnType)) {
            return result.stream();
        }

        return result;
    }

    static Object returnObject(ColumnQuery query, CassandraColumnRepository repository, Class typeClass, Method method, ConsistencyLevel level) {
        Class<?> returnType = method.getReturnType();

        if (typeClass.equals(returnType)) {
            List<Object> result = repository.find(query, level);
            if (!result.isEmpty()) {
                return result.get(0);
            } else {
                return null;
            }

        } else if (Optional.class.equals(returnType)) {
            List<Object> result = repository.find(query, level);
            return result.stream().findFirst();
        } else if (List.class.equals(returnType)
                || Iterable.class.equals(returnType)
                || Collection.class.equals(returnType)) {
            return repository.find(query, level);
        } else if (Set.class.equals(returnType)) {
            return repository.find(query, level).stream().collect(Collectors.toSet());
        } else if (Queue.class.equals(returnType)) {
            return repository.find(query, level).stream().collect(Collectors.toCollection(PriorityQueue::new));
        } else if (Stream.class.equals(returnType)) {
            return repository.find(query, level).stream();
        }

        return repository.find(query, level);
    }
}
