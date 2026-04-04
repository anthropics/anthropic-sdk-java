// JS compile-only stubs for com.fasterxml.jackson.module.kotlin
package com.fasterxml.jackson.module.kotlin

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.core.util.Version
import com.fasterxml.jackson.databind.Module as JacksonModule

fun kotlinModule(): JacksonModule = object : JacksonModule() {}

inline fun <reified T> jacksonTypeRef(): TypeReference<T> = object : TypeReference<T>() {}

object PackageVersion {
    val VERSION: Version = Version(2, 18, 2, "js-stub")
}
