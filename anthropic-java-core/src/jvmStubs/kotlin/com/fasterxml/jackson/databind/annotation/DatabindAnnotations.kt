package com.fasterxml.jackson.databind.annotation
import kotlin.reflect.KClass
annotation class JsonDeserialize(val using: KClass<*> = KClass::class, val contentUsing: KClass<*> = KClass::class)
annotation class JsonSerialize(val using: KClass<*> = KClass::class, val contentUsing: KClass<*> = KClass::class)
