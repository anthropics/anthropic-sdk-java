// JS stubs for kotlin.jvm.optionals extensions
package kotlin.jvm.optionals

import java.util.Optional

fun <T : Any> Optional<T>.getOrNull(): T? = if (isPresent()) get() else null

fun <T : Any> Optional<T>.asSequence(): Sequence<T> =
    if (isPresent()) sequenceOf(get()) else emptySequence()
