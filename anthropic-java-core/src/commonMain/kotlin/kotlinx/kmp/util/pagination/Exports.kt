/**
 * kotlinx.kmp.util.pagination — generic KMP pagination infrastructure.
 *
 * Re-exports from com.anthropic.core for cursor-based pagination types.
 * Any API-key-based service can use these without Anthropic SDK coupling.
 */
package kotlinx.kmp.util.pagination

// --- Pagination ---
typealias Page<T> = com.anthropic.core.Page<T>
typealias PageAsync<T> = com.anthropic.core.PageAsync<T>
typealias AutoPager<T> = com.anthropic.core.AutoPager<T>
typealias AutoPagerAsync<T> = com.anthropic.core.AutoPagerAsync<T>
