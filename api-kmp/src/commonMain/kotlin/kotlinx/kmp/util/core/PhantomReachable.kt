
package kotlinx.kmp.util.core

/**
 * Closes [closeable] when [observed] becomes only phantom reachable.
 *
 * On JVM: wrapper around Java 9+ [java.lang.ref.Cleaner], or no-op on older JVMs.
 * On non-JVM: no-op (no phantom reachability concept).
 */
fun closeWhenPhantomReachable(observed: Any, closeable: AutoCloseable) {
    check(observed !== closeable) {
        "`observed` cannot be the same object as `closeable` because it would never become phantom reachable"
    }
    closeWhenPhantomReachable(observed, closeable::close)
}

/**
 * Calls [close] when [observed] becomes only phantom reachable.
 *
 * Platform-specific implementation via expect/actual.
 */
expect fun closeWhenPhantomReachable(observed: Any, close: () -> Unit)
