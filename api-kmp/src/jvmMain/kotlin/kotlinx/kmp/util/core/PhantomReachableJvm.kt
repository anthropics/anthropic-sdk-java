package kotlinx.kmp.util.core

import kotlinx.kmp.util.core.errors.ApiException
import java.lang.reflect.InvocationTargetException

/**
 * JVM actual: uses Java 9+ Cleaner via reflection, or no-op on Java 8.
 */
actual fun closeWhenPhantomReachable(observed: Any, close: () -> Unit) {
    closeWhenPhantomReachableImpl?.let { it(observed, close) }
}

private val closeWhenPhantomReachableImpl: ((Any, () -> Unit) -> Unit)? by lazy {
    try {
        val cleanerClass = Class.forName("java.lang.ref.Cleaner")
        val cleanerCreate = cleanerClass.getMethod("create")
        val cleanerRegister =
            cleanerClass.getMethod("register", Any::class.java, Runnable::class.java)
        val cleanerObject = cleanerCreate.invoke(null);

        { observed, close ->
            try {
                cleanerRegister.invoke(cleanerObject, observed, Runnable { close() })
            } catch (e: ReflectiveOperationException) {
                if (e is InvocationTargetException) {
                    when (val cause = e.cause) {
                        is RuntimeException,
                        is Error -> throw cause
                    }
                }
                throw ApiException("Unexpected reflective invocation failure", e)
            }
        }
    } catch (e: ReflectiveOperationException) {
        // We're running Java 8, which has no Cleaner.
        null
    }
}
