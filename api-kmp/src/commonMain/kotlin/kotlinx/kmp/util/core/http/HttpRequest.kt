package kotlinx.kmp.util.core.http

import kotlin.jvm.JvmSynthetic
import kotlin.jvm.JvmStatic
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.toImmutable
import kotlinx.kmp.util.core.urlEncode

class HttpRequest
private constructor(
    val method: HttpMethod,
    val baseUrl: String?,
    val pathSegments: List<String>,
    val headers: Headers,
    val queryParams: QueryParams,
    val body: HttpRequestBody?,
) {

    fun url(): String = buildString {
        append(baseUrl)

        pathSegments.forEach { segment ->
            if (!endsWith("/")) {
                append("/")
            }
            append(urlEncode(segment))
        }

        if (queryParams.isEmpty()) {
            return@buildString
        }

        append("?")
        var isFirst = true
        queryParams.keys().forEach { key ->
            queryParams.values(key).forEach { value ->
                if (!isFirst) {
                    append("&")
                }
                append(urlEncode(key))
                append("=")
                append(urlEncode(value))
                isFirst = false
            }
        }
    }

    fun toBuilder(): Builder = Builder().from(this)

    override fun toString(): String =
        "HttpRequest{method=$method, baseUrl=$baseUrl, pathSegments=$pathSegments, headers=$headers, queryParams=$queryParams, body=$body}"

    companion object {
        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var method: HttpMethod? = null
        private var baseUrl: String? = null
        private var pathSegments: MutableList<String> = mutableListOf()
        private var headers: Headers.Builder = Headers.builder()
        private var queryParams: QueryParams.Builder = QueryParams.builder()
        private var body: HttpRequestBody? = null

        @JvmSynthetic fun from(request: HttpRequest) = apply {
            method = request.method
            baseUrl = request.baseUrl
            pathSegments = request.pathSegments.toMutableList()
            headers = request.headers.toBuilder()
            queryParams = request.queryParams.toBuilder()
            body = request.body
        }

        fun method(method: HttpMethod) = apply { this.method = method }

        fun baseUrl(baseUrl: String?) = apply { this.baseUrl = baseUrl }

        fun addPathSegment(pathSegment: String) = apply { pathSegments.add(pathSegment) }

        fun addPathSegments(vararg pathSegments: String) = apply {
            this.pathSegments.addAll(pathSegments)
        }

        fun replaceAllPathSegments(vararg pathSegments: String) = apply {
            this.pathSegments = pathSegments.toMutableList()
        }

        fun headers(headers: Headers) = apply {
            this.headers.clear()
            putAllHeaders(headers)
        }

        fun headers(headers: Map<String, Iterable<String>>) = apply {
            this.headers.clear()
            putAllHeaders(headers)
        }

        fun putHeader(name: String, value: String) = apply { headers.put(name, value) }

        fun putHeaders(name: String, values: Iterable<String>) = apply { headers.put(name, values) }

        fun putAllHeaders(headers: Headers) = apply { this.headers.putAll(headers) }

        fun putAllHeaders(headers: Map<String, Iterable<String>>) = apply {
            this.headers.putAll(headers)
        }

        fun replaceHeaders(name: String, value: String) = apply { headers.replace(name, value) }

        fun replaceHeaders(name: String, values: Iterable<String>) = apply {
            headers.replace(name, values)
        }

        fun replaceAllHeaders(headers: Headers) = apply { this.headers.replaceAll(headers) }

        fun replaceAllHeaders(headers: Map<String, Iterable<String>>) = apply {
            this.headers.replaceAll(headers)
        }

        fun removeHeaders(name: String) = apply { headers.remove(name) }

        fun removeAllHeaders(names: Set<String>) = apply { headers.removeAll(names) }

        fun queryParams(queryParams: QueryParams) = apply {
            this.queryParams.clear()
            putAllQueryParams(queryParams)
        }

        fun queryParams(queryParams: Map<String, Iterable<String>>) = apply {
            this.queryParams.clear()
            putAllQueryParams(queryParams)
        }

        fun putQueryParam(key: String, value: String) = apply { queryParams.put(key, value) }

        fun putQueryParams(key: String, values: Iterable<String>) = apply {
            queryParams.put(key, values)
        }

        fun putAllQueryParams(queryParams: QueryParams) = apply {
            this.queryParams.putAll(queryParams)
        }

        fun putAllQueryParams(queryParams: Map<String, Iterable<String>>) = apply {
            this.queryParams.putAll(queryParams)
        }

        fun replaceQueryParams(key: String, value: String) = apply {
            queryParams.replace(key, value)
        }

        fun replaceQueryParams(key: String, values: Iterable<String>) = apply {
            queryParams.replace(key, values)
        }

        fun replaceAllQueryParams(queryParams: QueryParams) = apply {
            this.queryParams.replaceAll(queryParams)
        }

        fun replaceAllQueryParams(queryParams: Map<String, Iterable<String>>) = apply {
            this.queryParams.replaceAll(queryParams)
        }

        fun removeQueryParams(key: String) = apply { queryParams.remove(key) }

        fun removeAllQueryParams(keys: Set<String>) = apply { queryParams.removeAll(keys) }

        fun body(body: HttpRequestBody) = apply { this.body = body }

        fun build(): HttpRequest =
            HttpRequest(
                checkRequired("method", method),
                baseUrl,
                pathSegments.toImmutable(),
                headers.build(),
                queryParams.build(),
                body,
            )
    }
}
