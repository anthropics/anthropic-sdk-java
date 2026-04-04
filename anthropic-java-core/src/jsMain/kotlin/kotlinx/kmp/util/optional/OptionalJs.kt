@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package kotlinx.kmp.util.optional

actual typealias Optional<T> = java.util.Optional<T>

actual fun <T : Any> optionalOf(value: T): Optional<T> = java.util.Optional.of(value)

@Suppress("UNCHECKED_CAST")
actual fun <T : Any> optionalOfNullable(value: T?): Optional<T> =
    java.util.Optional.ofNullable(value) as Optional<T>

@Suppress("UNCHECKED_CAST")
actual fun <T : Any> emptyOptional(): Optional<T> = java.util.Optional.empty<Any>() as Optional<T>
