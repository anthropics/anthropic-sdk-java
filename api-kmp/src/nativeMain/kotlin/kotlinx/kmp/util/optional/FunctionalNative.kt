@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package kotlinx.kmp.util.optional

actual fun interface Function<T, R> { actual fun apply(t: T): R }
actual fun interface Supplier<T> { actual fun get(): T }
actual fun interface Consumer<T> { actual fun accept(t: T) }
actual fun interface Predicate<T> { actual fun test(t: T): Boolean }
actual fun interface BiFunction<T, U, R> { actual fun apply(t: T, u: U): R }
