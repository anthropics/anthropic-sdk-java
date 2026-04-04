// JS compile-only stubs for com.fasterxml.jackson.databind.annotation
package com.fasterxml.jackson.databind.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonDeserialize(
    val using: KClass<*> = KClass::class,
    val contentUsing: KClass<*> = KClass::class,
)

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonSerialize(
    val using: KClass<*> = KClass::class,
    val contentUsing: KClass<*> = KClass::class,
)
