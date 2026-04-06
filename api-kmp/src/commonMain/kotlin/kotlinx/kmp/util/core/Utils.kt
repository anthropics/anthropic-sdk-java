package kotlinx.kmp.util.core

fun <T : Any> T?.getOrThrow(name: String): T =
    this ?: throw IllegalStateException("`${name}` is not present")

// These helpers defensively copy collections. The empty check is an optimization to return
// singleton empty instances instead of allocating, though toList()/toMap() handle empty inputs
// correctly on their own. Kept because they are used widely across the codebase.
fun <T> List<T>.toImmutable(): List<T> = if (isEmpty()) emptyList() else toList()
fun <K, V> Map<K, V>.toImmutable(): Map<K, V> = if (isEmpty()) emptyMap() else toMap()

fun <T, R : Comparable<R>> Sequence<T>.allMaxBy(selector: (T) -> R): List<T> {
    var maxValue: R? = null
    val maxElements = mutableListOf<T>()
    for (element in this) {
        val value = selector(element)
        if (maxValue == null || value > maxValue) { maxValue = value; maxElements.clear(); maxElements.add(element) }
        else if (value == maxValue) { maxElements.add(element) }
    }
    return maxElements
}

infix fun Any?.contentEquals(other: Any?): Boolean = arrayOf(this).contentDeepEquals(arrayOf(other))
fun contentHash(vararg values: Any?): Int = values.contentDeepHashCode()
fun Any?.contentToString(): String {
    var s = arrayOf(this).contentDeepToString()
    if (s.startsWith('[')) s = s.substring(1)
    if (s.endsWith(']')) s = s.substring(0, s.length - 1)
    return s
}

interface Enum

fun checkRequired(name: String, condition: Boolean) = check(condition) { "`$name` is required, but was not set" }
fun <T : Any> checkRequired(name: String, value: T?): T = checkNotNull(value) { "`$name` is required, but was not set" }
fun checkLength(name: String, value: String, length: Int): String =
    value.also { check(it.length == length) { "`$name` must have length $length, but was ${it.length}" } }
fun checkMinLength(name: String, value: String, minLength: Int): String =
    value.also { check(it.length >= minLength) { if (minLength == 1) "`$name` must be non-empty, but was empty" else "`$name` must have at least length $minLength, but was ${it.length}" } }
fun checkMaxLength(name: String, value: String, maxLength: Int): String =
    value.also { check(it.length <= maxLength) { "`$name` must have at most length $maxLength, but was ${it.length}" } }

fun <T : Any> JsonField<T>.getOptional(name: String): kotlinx.kmp.util.optional.Optional<@UnsafeVariance T> =
    kotlinx.kmp.util.optional.optionalOfNullable(getNullable(name))

fun <T : Any> checkKnown(name: String, value: JsonField<T>): T =
    value.asKnown() ?: throw IllegalStateException("`$name` is not a known type: ${value::class.simpleName}")

fun <T : Any> checkKnown(name: String, value: MultipartField<T>): T =
    value.value.asKnown() ?: throw IllegalStateException("`$name` is not a known type: ${value::class.simpleName}")
