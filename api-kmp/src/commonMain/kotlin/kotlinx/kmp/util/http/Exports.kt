/**
 * kotlinx.kmp.util.http — generic HTTP infrastructure.
 * Typealiases to com.anthropic.core.http for gradual package migration.
 */
package kotlinx.kmp.util.http

typealias HttpClient = com.anthropic.core.http.HttpClient
typealias HttpRequest = com.anthropic.core.http.HttpRequest
typealias HttpResponse = com.anthropic.core.http.HttpResponse
typealias HttpResponseFor<T> = com.anthropic.core.http.HttpResponseFor<T>
typealias HttpRequestBody = com.anthropic.core.http.HttpRequestBody
typealias HttpMethod = com.anthropic.core.http.HttpMethod
typealias Headers = com.anthropic.core.http.Headers
typealias QueryParams = com.anthropic.core.http.QueryParams
typealias StreamResponse<T> = com.anthropic.core.http.StreamResponse<T>
typealias AsyncStreamResponse<T> = com.anthropic.core.http.AsyncStreamResponse<T>
typealias SseMessage = com.anthropic.core.http.SseMessage
typealias KtorHttpClient = com.anthropic.core.http.KtorHttpClient
typealias RetryingHttpClient = com.anthropic.core.http.RetryingHttpClient
