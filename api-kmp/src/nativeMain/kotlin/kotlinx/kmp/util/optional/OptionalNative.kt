@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package kotlinx.kmp.util.optional

actual class Optional<T : Any> private constructor(private val value: T?) {
    actual fun isPresent(): Boolean = value != null
    actual fun get(): T = value ?: throw NoSuchElementException("No value present")
    actual fun orElse(other: T?): T = value ?: other!!
    actual fun orElseGet(supplier: Supplier<out T>): T = value ?: supplier.get()
    actual fun <X : Throwable> orElseThrow(exceptionSupplier: Supplier<out X>): T =
        value ?: throw exceptionSupplier.get()
    @Suppress("UNCHECKED_CAST")
    actual fun <U : Any> map(mapper: Function<in T, out U?>): Optional<U> =
        if (value != null) ofNullable(mapper.apply(value)) as Optional<U> else empty()
    @Suppress("UNCHECKED_CAST")
    actual fun <U : Any> flatMap(mapper: Function<in T, out Optional<out U>>): Optional<U> =
        if (value != null) mapper.apply(value) as Optional<U> else empty()
    actual fun filter(predicate: Predicate<in T>): Optional<T> =
        if (value != null && predicate.test(value)) this else empty()
    actual fun ifPresent(action: Consumer<in T>) { if (value != null) action.accept(value) }

    override fun equals(other: Any?) = other is Optional<*> && value == other.value
    override fun hashCode() = value?.hashCode() ?: 0
    override fun toString() = if (value != null) "Optional[$value]" else "Optional.empty"

    companion object {
        fun <T : Any> of(value: T): Optional<T> = Optional(value)
        fun <T : Any> ofNullable(value: T?): Optional<T> = Optional(value)
        fun <T : Any> empty(): Optional<T> = Optional(null)
    }
}

actual fun <T : Any> optionalOf(value: T): Optional<T> = Optional.of(value)
actual fun <T : Any> optionalOfNullable(value: T?): Optional<T> = Optional.ofNullable(value)
actual fun <T : Any> emptyOptional(): Optional<T> = Optional.empty()
