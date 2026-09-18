package com.anthropic.core;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Java fixtures used to verify which kinds of nested class are accepted as tool parameter types and
 * structured output types. A static nested class can be instantiated from JSON, whereas a
 * non-static member class, a local class and an anonymous class cannot.
 */
public final class NestedClassJavaFixtures {
    private NestedClassJavaFixtures() {}

    /** A static nested class. */
    @JsonClassDescription("Get the weather in a given location")
    public static final class GetWeather {
        @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
        public String location;
    }

    /** A non-static member class. */
    public final class Member {
        public String location;
    }

    /** Returns a local class. */
    public static Class<?> localClass() {
        class Local {
            public String location;
        }
        return Local.class;
    }

    /** Returns an anonymous class. */
    public static Class<?> anonymousClass() {
        Object anonymous = new Object() {
            public String location;
        };
        return anonymous.getClass();
    }
}
