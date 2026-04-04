/**
 * kotlinx.kmp.util.http — generic KMP HTTP infrastructure.
 *
 * Re-exports from com.anthropic.core.http. Any API-key-based MCP service
 * can depend on these types without coupling to the Anthropic SDK.
 *
 * Canonical implementations live in com.anthropic.core.http during migration.
 * Once all internal SDK code is updated, the canonical location will move here.
 */
package kotlinx.kmp.util.http

// --- Core HTTP types ---
typealias HttpClient = com.anthropic.core.http.HttpClient
typealias HttpRequest = com.anthropic.core.http.HttpRequest
typealias HttpResponse = com.anthropic.core.http.HttpResponse
typealias HttpResponseFor<T> = com.anthropic.core.http.HttpResponseFor<T>
typealias HttpRequestBody = com.anthropic.core.http.HttpRequestBody
typealias HttpMethod = com.anthropic.core.http.HttpMethod
typealias Headers = com.anthropic.core.http.Headers
typealias QueryParams = com.anthropic.core.http.QueryParams

// --- Streaming ---
typealias StreamResponse<T> = com.anthropic.core.http.StreamResponse<T>
typealias AsyncStreamResponse<T> = com.anthropic.core.http.AsyncStreamResponse<T>
// SseMessage is internal — available only within the SDK

// --- Ktor ---
typealias KtorHttpClient = com.anthropic.core.http.KtorHttpClient

// --- Retry ---
typealias RetryingHttpClient = com.anthropic.core.http.RetryingHttpClient
