package java.util.stream
class Stream<T> private constructor(private val items: List<T>) {
    fun toList(): List<T> = items
    companion object { fun <T> of(vararg values: T): Stream<T> = Stream(values.toList()); fun <T> empty(): Stream<T> = Stream(emptyList()) }
}
