// JS compile-only stub for java.util.Optional<T>
package java.util

import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate
import java.util.function.Supplier

class Optional<T : Any> private constructor(private val value: T?) {

    fun isPresent(): Boolean = value != null

    fun get(): T = value ?: throw NoSuchElementException("No value present")

    fun orElse(other: T?): T = value ?: other!!

    fun orElseGet(supplier: Supplier<out T>): T = value ?: supplier.get()

    fun <X : Throwable> orElseThrow(exceptionSupplier: Supplier<out X>): T =
        value ?: throw exceptionSupplier.get()

    fun <U : Any> map(mapper: Function<in T, out U?>): Optional<U> {
        if (value == null) return empty()
        val result = mapper.apply(value)
        @Suppress("UNCHECKED_CAST")
        return if (result == null) empty() else of(result)
    }

    @Suppress("UNCHECKED_CAST")
    fun <U : Any> flatMap(mapper: Function<in T, out Optional<out U>>): Optional<U> {
        if (value == null) return empty()
        return mapper.apply(value) as Optional<U>
    }

    fun filter(predicate: Predicate<in T>): Optional<T> {
        if (value == null) return this
        return if (predicate.test(value)) this else empty()
    }

    fun ifPresent(action: Consumer<in T>) {
        if (value != null) action.accept(value)
    }

    fun stream(): List<T> = if (value != null) listOf(value) else emptyList()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Optional<*>) return false
        return value == other.value
    }

    override fun hashCode(): Int = value?.hashCode() ?: 0

    override fun toString(): String =
        if (value != null) "Optional[$value]" else "Optional.empty"

    companion object {
        private val EMPTY = Optional<Any>(null)

        @Suppress("UNCHECKED_CAST")
        fun <T : Any> empty(): Optional<T> = EMPTY as Optional<T>

        fun <T : Any> of(value: T): Optional<T> = Optional(value)

        @Suppress("UNCHECKED_CAST")
        fun <T : Any> ofNullable(value: T?): Optional<T> =
            if (value == null) empty() else Optional(value)
    }
}
