// JS compile-only stub for StdSerializer
package com.fasterxml.jackson.databind.ser.std

import com.fasterxml.jackson.databind.JsonSerializer
import kotlin.reflect.KClass

abstract class StdSerializer<T> : JsonSerializer<T> {
    constructor(t: KClass<*>?)
    constructor(t: Any?) // For type.java patterns
}
