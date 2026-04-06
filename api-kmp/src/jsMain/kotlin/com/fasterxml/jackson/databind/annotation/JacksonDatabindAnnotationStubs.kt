/**
 * JS stubs for Jackson databind annotations.
 */
package com.fasterxml.jackson.databind.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonDeserialize(val using: KClass<*> = Any::class)

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonSerialize(val using: KClass<*> = Any::class)
