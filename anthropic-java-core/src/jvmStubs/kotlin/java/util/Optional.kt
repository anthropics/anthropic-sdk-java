package java.util

class Optional<T : Any> private constructor(private val value: T?) {
    fun isPresent(): Boolean = value != null
    val isPresent: Boolean get() = value != null
    fun get(): T = value ?: throw NoSuchElementException("No value present")
    fun orElse(other: T?): T = value ?: other!!
    fun orElseGet(supplier: java.util.function.Supplier<out T>): T = value ?: supplier.get()
    fun <X : Throwable> orElseThrow(exceptionSupplier: java.util.function.Supplier<out X>): T = value ?: throw exceptionSupplier.get()
    fun <U : Any> map(mapper: java.util.function.Function<in T, out U?>): Optional<U> = if (value != null) ofNullable(mapper.apply(value)) else empty()
    @Suppress("UNCHECKED_CAST") fun <U : Any> flatMap(mapper: java.util.function.Function<in T, out Optional<out U>>): Optional<U> { if (value == null) return empty(); return mapper.apply(value) as Optional<U> }
    fun filter(predicate: java.util.function.Predicate<in T>): Optional<T> = if (value != null && predicate.test(value)) this else empty()
    fun ifPresent(action: java.util.function.Consumer<in T>) { if (value != null) action.accept(value) }
    fun stream(): java.util.stream.Stream<T> = if (value != null) java.util.stream.Stream.of(value) else java.util.stream.Stream.empty()
    override fun equals(other: Any?): Boolean = other is Optional<*> && value == other.value
    override fun hashCode(): Int = value?.hashCode() ?: 0
    override fun toString(): String = if (value != null) "Optional[$value]" else "Optional.empty"
    companion object {
        @Suppress("UNCHECKED_CAST") fun <T : Any> empty(): Optional<T> = Optional<Any>(null) as Optional<T>
        fun <T : Any> of(value: T): Optional<T> = Optional(value)
        fun <T : Any> ofNullable(value: T?): Optional<T> = Optional(value)
    }
}
