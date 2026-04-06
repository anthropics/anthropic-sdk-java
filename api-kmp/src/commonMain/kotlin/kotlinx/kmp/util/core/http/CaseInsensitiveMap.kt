package kotlinx.kmp.util.core.http

/**
 * A mutable map with case-insensitive string keys.
 * KMP-compatible replacement for `sortedMapOf(String.CASE_INSENSITIVE_ORDER)`.
 */
internal class CaseInsensitiveMap<V>(
    private val delegate: MutableMap<String, V> = linkedMapOf()
) : MutableMap<String, V> by delegate {

    private fun normalizeKey(key: String): String = key.lowercase()

    // Map a normalized key back to the original casing stored
    private val originalKeys = mutableMapOf<String, String>()

    override val size: Int get() = delegate.size
    override val entries: MutableSet<MutableMap.MutableEntry<String, V>> get() = delegate.entries
    override val keys: MutableSet<String> get() = delegate.keys
    override val values: MutableCollection<V> get() = delegate.values

    override fun containsKey(key: String): Boolean = delegate.containsKey(normalizeKey(key))
    override fun containsValue(value: V): Boolean = delegate.containsValue(value)
    override fun get(key: String): V? = delegate[normalizeKey(key)]
    override fun isEmpty(): Boolean = delegate.isEmpty()

    override fun put(key: String, value: V): V? {
        val norm = normalizeKey(key)
        originalKeys[norm] = key
        return delegate.put(norm, value)
    }

    override fun putAll(from: Map<out String, V>) {
        from.forEach { (k, v) -> put(k, v) }
    }

    override fun remove(key: String): V? {
        val norm = normalizeKey(key)
        originalKeys.remove(norm)
        return delegate.remove(norm)
    }

    override fun clear() {
        originalKeys.clear()
        delegate.clear()
    }
}
