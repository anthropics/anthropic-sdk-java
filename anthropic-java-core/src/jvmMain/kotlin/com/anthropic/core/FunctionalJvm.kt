@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
package com.anthropic.core

/**
 * JVM actuals: typealias to java.util.function interfaces.
 * SAM conversion works automatically — Kotlin lambdas become Java functional interfaces.
 */

actual typealias Function<T, R> = java.util.function.Function<T, R>
actual typealias Supplier<T> = java.util.function.Supplier<T>
actual typealias Consumer<T> = java.util.function.Consumer<T>
actual typealias Predicate<T> = java.util.function.Predicate<T>
actual typealias BiFunction<T, U, R> = java.util.function.BiFunction<T, U, R>
