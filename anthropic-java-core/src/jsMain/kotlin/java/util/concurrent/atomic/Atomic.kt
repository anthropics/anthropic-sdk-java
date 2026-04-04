// JS compile-only stubs for java.util.concurrent.atomic
package java.util.concurrent.atomic

class AtomicReference<V>(private var value: V) {
    fun get(): V = value
    fun set(newValue: V) { value = newValue }
    fun getAndSet(newValue: V): V { val old = value; value = newValue; return old }
    fun compareAndSet(expect: V, update: V): Boolean {
        if (value === expect || value == expect) { value = update; return true }
        return false
    }
}

class AtomicLong(private var value: Long = 0L) {
    fun get(): Long = value
    fun set(newValue: Long) { value = newValue }
    fun getAndIncrement(): Long { val old = value; value++; return old }
    fun incrementAndGet(): Long { value++; return value }
    fun getAndSet(newValue: Long): Long { val old = value; value = newValue; return old }
}

class AtomicBoolean(private var value: Boolean = false) {
    fun get(): Boolean = value
    fun set(newValue: Boolean) { value = newValue }
    fun getAndSet(newValue: Boolean): Boolean { val old = value; value = newValue; return old }
    fun compareAndSet(expect: Boolean, update: Boolean): Boolean {
        if (value == expect) { value = update; return true }
        return false
    }
}
