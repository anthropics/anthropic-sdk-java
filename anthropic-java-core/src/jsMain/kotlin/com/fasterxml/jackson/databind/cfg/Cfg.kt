// JS compile-only stubs for com.fasterxml.jackson.databind.cfg
package com.fasterxml.jackson.databind.cfg

import com.fasterxml.jackson.core.util.Version

enum class CoercionAction { Fail, TryConvert, AsNull, AsEmpty }

enum class CoercionInputShape { Boolean, Integer, Float, String, Array, Object, EmptyArray, EmptyObject, EmptyString }

class CoercionConfig {
    fun setCoercion(inputShape: CoercionInputShape, action: CoercionAction): CoercionConfig = this
}

object PackageVersion {
    val VERSION: Version = Version(2, 18, 2, "js-stub")
}
