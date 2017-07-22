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
package org.jnosql.artemis.graph;

import org.jnosql.diana.api.Value;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultArtemisElementTest {



    @Test(expected = NullPointerException.class)
    public void shouldReturnErrorWhenKeyINull() {
        ArtemisElement.of(null, 10L);
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnErrorWhenValueINull() {
        ArtemisElement.of("key", null);
    }

    @Test
    public void shouldReturnKey() {
        ArtemisElement element = ArtemisElement.of("key", 10L);
        assertEquals("key", element.getKey());
    }

    @Test
    public void shouldReturnValue() {
        ArtemisElement element = ArtemisElement.of("key", 10L);
        assertEquals(Value.of(10L), element.getValue());
    }

    @Test
    public void shouldReturnValueAsObject() {
        long value = 10L;
        ArtemisElement element = ArtemisElement.of("key", value);
        assertEquals(value, element.get());
    }


    @Test
    public void shouldCreateInstanceValue() {
        ArtemisElement element = ArtemisElement.of("key", Value.of(10L));
        assertEquals(Value.of(10L), element.getValue());
        assertEquals("key", element.getKey());
        assertEquals(10L, element.get());
    }

}