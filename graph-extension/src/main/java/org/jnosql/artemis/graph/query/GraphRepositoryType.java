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
package org.jnosql.artemis.graph.query;

import java.lang.reflect.Method;

public enum GraphRepositoryType {

    DEFAULT, FIND_BY, DELETE_BY, UNKNOWN;


    static GraphRepositoryType of(Method method, Object[] args) {
        String methodName = method.getName();
        switch (methodName) {
            case "save":
            case "deleteById":
            case "delete":
            case "findById":
            case "existsById":
                return DEFAULT;
            default:
        }

        if (methodName.startsWith("findBy")) {
            return FIND_BY;
        } else if (methodName.startsWith("deleteBy")) {
            return DELETE_BY;
        }
        return UNKNOWN;
    }


}
