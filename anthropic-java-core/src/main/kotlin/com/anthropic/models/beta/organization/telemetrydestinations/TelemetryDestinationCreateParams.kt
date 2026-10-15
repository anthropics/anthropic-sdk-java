package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Register an OpenTelemetry destination for the organization.
 *
 * The destination starts in state `enabled`. An organization can have at most 10 destinations,
 * including disabled ones.
 *
 * See the
 * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#add-a-destination)
 * to learn more about adding a destination.
 */
class TelemetryDestinationCreateParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * This endpoint is in beta: requests must send `telemetry-destinations-2026-08-11` in this
     * header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * The base URL of your collector's OTLP/HTTP endpoint, for example
     * `https://gateway.example.com`.
     *
     * The URL must use `https` and a hostname, not an IP address. The port, if given, must be 443.
     * The URL must not contain a query string, a fragment, or a username and password. At most
     * 2,048 characters.
     *
     * A path prefix, such as `/otlp`, is allowed. Don't include the per-signal path (`/v1/traces`),
     * because the API appends it on delivery. If your collector takes traces on another path, also
     * set `traces.endpoint_url`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun endpointUrl(): String = body.endpointUrl()

    /**
     * Which of the organization's workspaces the destination covers.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaces(): BetaTelemetryDestinationWorkspacesParams = body.workspaces()

    /**
     * Human-readable name for the destination. Optional. 1 to 255 characters.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayName(): Optional<String> = body.displayName()

    /**
     * Request headers that the API sends with every delivery, typically your collector's
     * authentication header (for example `authorization`).
     *
     * The API stores the values encrypted and never returns them. Reads show only `header_names`.
     *
     * At most 20 headers. Names are at most 128 characters. Two names can't differ only in case.
     * Values are printable ASCII, at most 4,096 characters, and can't begin or end with a space.
     * Names and values together are at most 16,384 bytes.
     *
     * Reserved names are rejected, for example `host`, `cookie`, `user-agent`, and names that start
     * with `content-`, `proxy-`, `x-forwarded-`, or `anthropic-`. For this check, `_` in a name
     * counts as `-`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun headers(): Optional<DestinationHeaders> = body.headers()

    /**
     * The settings for the traces that the destination receives. If you leave it out, every setting
     * takes its default.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun traces(): Optional<BetaTelemetryDestinationTracesParams> = body.traces()

    /**
     * Returns the raw JSON value of [endpointUrl].
     *
     * Unlike [endpointUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _endpointUrl(): JsonField<String> = body._endpointUrl()

    /**
     * Returns the raw JSON value of [workspaces].
     *
     * Unlike [workspaces], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _workspaces(): JsonField<BetaTelemetryDestinationWorkspacesParams> = body._workspaces()

    /**
     * Returns the raw JSON value of [displayName].
     *
     * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _displayName(): JsonField<String> = body._displayName()

    /**
     * Returns the raw JSON value of [headers].
     *
     * Unlike [headers], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _headers_(): JsonField<DestinationHeaders> = body._headers_()

    /**
     * Returns the raw JSON value of [traces].
     *
     * Unlike [traces], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _traces(): JsonField<BetaTelemetryDestinationTracesParams> = body._traces()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [TelemetryDestinationCreateParams].
         *
         * The following fields are required:
         * ```java
         * .endpointUrl()
         * .workspaces()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TelemetryDestinationCreateParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(telemetryDestinationCreateParams: TelemetryDestinationCreateParams) =
            apply {
                betas = telemetryDestinationCreateParams.betas?.toMutableList()
                body = telemetryDestinationCreateParams.body.toBuilder()
                additionalHeaders = telemetryDestinationCreateParams.additionalHeaders.toBuilder()
                additionalQueryParams =
                    telemetryDestinationCreateParams.additionalQueryParams.toBuilder()
            }

        /**
         * This endpoint is in beta: requests must send `telemetry-destinations-2026-08-11` in this
         * header.
         */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [endpointUrl]
         * - [workspaces]
         * - [displayName]
         * - [headers]
         * - [traces]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * The base URL of your collector's OTLP/HTTP endpoint, for example
         * `https://gateway.example.com`.
         *
         * The URL must use `https` and a hostname, not an IP address. The port, if given, must
         * be 443. The URL must not contain a query string, a fragment, or a username and password.
         * At most 2,048 characters.
         *
         * A path prefix, such as `/otlp`, is allowed. Don't include the per-signal path
         * (`/v1/traces`), because the API appends it on delivery. If your collector takes traces on
         * another path, also set `traces.endpoint_url`.
         */
        fun endpointUrl(endpointUrl: String) = apply { body.endpointUrl(endpointUrl) }

        /**
         * Sets [Builder.endpointUrl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endpointUrl] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun endpointUrl(endpointUrl: JsonField<String>) = apply { body.endpointUrl(endpointUrl) }

        /** Which of the organization's workspaces the destination covers. */
        fun workspaces(workspaces: BetaTelemetryDestinationWorkspacesParams) = apply {
            body.workspaces(workspaces)
        }

        /**
         * Sets [Builder.workspaces] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaces] with a well-typed
         * [BetaTelemetryDestinationWorkspacesParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun workspaces(workspaces: JsonField<BetaTelemetryDestinationWorkspacesParams>) = apply {
            body.workspaces(workspaces)
        }

        /**
         * Alias for calling [workspaces] with
         * `BetaTelemetryDestinationWorkspacesParams.ofAll(all)`.
         */
        fun workspaces(all: BetaTelemetryDestinationWorkspacesAllParams) = apply {
            body.workspaces(all)
        }

        /**
         * Alias for calling [workspaces] with
         * `BetaTelemetryDestinationWorkspacesParams.ofInclude(include)`.
         */
        fun workspaces(include: BetaTelemetryDestinationWorkspacesIncludeParams) = apply {
            body.workspaces(include)
        }

        /**
         * Alias for calling [workspaces] with
         * `BetaTelemetryDestinationWorkspacesParams.ofExclude(exclude)`.
         */
        fun workspaces(exclude: BetaTelemetryDestinationWorkspacesExcludeParams) = apply {
            body.workspaces(exclude)
        }

        /** Human-readable name for the destination. Optional. 1 to 255 characters. */
        fun displayName(displayName: String?) = apply { body.displayName(displayName) }

        /** Alias for calling [Builder.displayName] with `displayName.orElse(null)`. */
        fun displayName(displayName: Optional<String>) = displayName(displayName.getOrNull())

        /**
         * Sets [Builder.displayName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayName(displayName: JsonField<String>) = apply { body.displayName(displayName) }

        /**
         * Request headers that the API sends with every delivery, typically your collector's
         * authentication header (for example `authorization`).
         *
         * The API stores the values encrypted and never returns them. Reads show only
         * `header_names`.
         *
         * At most 20 headers. Names are at most 128 characters. Two names can't differ only in
         * case. Values are printable ASCII, at most 4,096 characters, and can't begin or end with a
         * space. Names and values together are at most 16,384 bytes.
         *
         * Reserved names are rejected, for example `host`, `cookie`, `user-agent`, and names that
         * start with `content-`, `proxy-`, `x-forwarded-`, or `anthropic-`. For this check, `_` in
         * a name counts as `-`.
         */
        fun headers(headers: DestinationHeaders) = apply { body.headers(headers) }

        /**
         * Sets [Builder.headers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.headers] with a well-typed [DestinationHeaders] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun headers(headers: JsonField<DestinationHeaders>) = apply { body.headers(headers) }

        /**
         * The settings for the traces that the destination receives. If you leave it out, every
         * setting takes its default.
         */
        fun traces(traces: BetaTelemetryDestinationTracesParams) = apply { body.traces(traces) }

        /**
         * Sets [Builder.traces] to an arbitrary JSON value.
         *
         * You should usually call [Builder.traces] with a well-typed
         * [BetaTelemetryDestinationTracesParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun traces(traces: JsonField<BetaTelemetryDestinationTracesParams>) = apply {
            body.traces(traces)
        }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [TelemetryDestinationCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .endpointUrl()
         * .workspaces()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): TelemetryDestinationCreateParams =
            TelemetryDestinationCreateParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.let {
                    if (it.isNotEmpty()) {
                        put(
                            "anthropic-beta",
                            it.map { it.toString() }
                                .let { it + (listOf("telemetry-destinations-2026-08-11") - it) }
                                .joinToString(","),
                        )
                    }
                }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.telemetry_destinations.create",
            route = "/v1/organizations/telemetry_destinations",
        )

    /** The settings of a new telemetry destination for the organization. */
    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val endpointUrl: JsonField<String>,
        private val workspaces: JsonField<BetaTelemetryDestinationWorkspacesParams>,
        private val displayName: JsonField<String>,
        private val headers: JsonField<DestinationHeaders>,
        private val traces: JsonField<BetaTelemetryDestinationTracesParams>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("endpoint_url")
            @ExcludeMissing
            endpointUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("workspaces")
            @ExcludeMissing
            workspaces: JsonField<BetaTelemetryDestinationWorkspacesParams> = JsonMissing.of(),
            @JsonProperty("display_name")
            @ExcludeMissing
            displayName: JsonField<String> = JsonMissing.of(),
            @JsonProperty("headers")
            @ExcludeMissing
            headers: JsonField<DestinationHeaders> = JsonMissing.of(),
            @JsonProperty("traces")
            @ExcludeMissing
            traces: JsonField<BetaTelemetryDestinationTracesParams> = JsonMissing.of(),
        ) : this(endpointUrl, workspaces, displayName, headers, traces, mutableMapOf())

        /**
         * The base URL of your collector's OTLP/HTTP endpoint, for example
         * `https://gateway.example.com`.
         *
         * The URL must use `https` and a hostname, not an IP address. The port, if given, must
         * be 443. The URL must not contain a query string, a fragment, or a username and password.
         * At most 2,048 characters.
         *
         * A path prefix, such as `/otlp`, is allowed. Don't include the per-signal path
         * (`/v1/traces`), because the API appends it on delivery. If your collector takes traces on
         * another path, also set `traces.endpoint_url`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun endpointUrl(): String = endpointUrl.getRequired("endpoint_url")

        /**
         * Which of the organization's workspaces the destination covers.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun workspaces(): BetaTelemetryDestinationWorkspacesParams =
            workspaces.getRequired("workspaces")

        /**
         * Human-readable name for the destination. Optional. 1 to 255 characters.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun displayName(): Optional<String> = displayName.getOptional("display_name")

        /**
         * Request headers that the API sends with every delivery, typically your collector's
         * authentication header (for example `authorization`).
         *
         * The API stores the values encrypted and never returns them. Reads show only
         * `header_names`.
         *
         * At most 20 headers. Names are at most 128 characters. Two names can't differ only in
         * case. Values are printable ASCII, at most 4,096 characters, and can't begin or end with a
         * space. Names and values together are at most 16,384 bytes.
         *
         * Reserved names are rejected, for example `host`, `cookie`, `user-agent`, and names that
         * start with `content-`, `proxy-`, `x-forwarded-`, or `anthropic-`. For this check, `_` in
         * a name counts as `-`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun headers(): Optional<DestinationHeaders> = headers.getOptional("headers")

        /**
         * The settings for the traces that the destination receives. If you leave it out, every
         * setting takes its default.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun traces(): Optional<BetaTelemetryDestinationTracesParams> = traces.getOptional("traces")

        /**
         * Returns the raw JSON value of [endpointUrl].
         *
         * Unlike [endpointUrl], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("endpoint_url")
        @ExcludeMissing
        fun _endpointUrl(): JsonField<String> = endpointUrl

        /**
         * Returns the raw JSON value of [workspaces].
         *
         * Unlike [workspaces], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("workspaces")
        @ExcludeMissing
        fun _workspaces(): JsonField<BetaTelemetryDestinationWorkspacesParams> = workspaces

        /**
         * Returns the raw JSON value of [displayName].
         *
         * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("display_name")
        @ExcludeMissing
        fun _displayName(): JsonField<String> = displayName

        /**
         * Returns the raw JSON value of [headers].
         *
         * Unlike [headers], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("headers")
        @ExcludeMissing
        fun _headers_(): JsonField<DestinationHeaders> = headers

        /**
         * Returns the raw JSON value of [traces].
         *
         * Unlike [traces], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("traces")
        @ExcludeMissing
        fun _traces(): JsonField<BetaTelemetryDestinationTracesParams> = traces

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .endpointUrl()
             * .workspaces()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var endpointUrl: JsonField<String>? = null
            private var workspaces: JsonField<BetaTelemetryDestinationWorkspacesParams>? = null
            private var displayName: JsonField<String> = JsonMissing.of()
            private var headers: JsonField<DestinationHeaders> = JsonMissing.of()
            private var traces: JsonField<BetaTelemetryDestinationTracesParams> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                endpointUrl = body.endpointUrl
                workspaces = body.workspaces
                displayName = body.displayName
                headers = body.headers
                traces = body.traces
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * The base URL of your collector's OTLP/HTTP endpoint, for example
             * `https://gateway.example.com`.
             *
             * The URL must use `https` and a hostname, not an IP address. The port, if given, must
             * be 443. The URL must not contain a query string, a fragment, or a username and
             * password. At most 2,048 characters.
             *
             * A path prefix, such as `/otlp`, is allowed. Don't include the per-signal path
             * (`/v1/traces`), because the API appends it on delivery. If your collector takes
             * traces on another path, also set `traces.endpoint_url`.
             */
            fun endpointUrl(endpointUrl: String) = endpointUrl(JsonField.of(endpointUrl))

            /**
             * Sets [Builder.endpointUrl] to an arbitrary JSON value.
             *
             * You should usually call [Builder.endpointUrl] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun endpointUrl(endpointUrl: JsonField<String>) = apply {
                this.endpointUrl = endpointUrl
            }

            /** Which of the organization's workspaces the destination covers. */
            fun workspaces(workspaces: BetaTelemetryDestinationWorkspacesParams) =
                workspaces(JsonField.of(workspaces))

            /**
             * Sets [Builder.workspaces] to an arbitrary JSON value.
             *
             * You should usually call [Builder.workspaces] with a well-typed
             * [BetaTelemetryDestinationWorkspacesParams] value instead. This method is primarily
             * for setting the field to an undocumented or not yet supported value.
             */
            fun workspaces(workspaces: JsonField<BetaTelemetryDestinationWorkspacesParams>) =
                apply {
                    this.workspaces = workspaces
                }

            /**
             * Alias for calling [workspaces] with
             * `BetaTelemetryDestinationWorkspacesParams.ofAll(all)`.
             */
            fun workspaces(all: BetaTelemetryDestinationWorkspacesAllParams) =
                workspaces(BetaTelemetryDestinationWorkspacesParams.ofAll(all))

            /**
             * Alias for calling [workspaces] with
             * `BetaTelemetryDestinationWorkspacesParams.ofInclude(include)`.
             */
            fun workspaces(include: BetaTelemetryDestinationWorkspacesIncludeParams) =
                workspaces(BetaTelemetryDestinationWorkspacesParams.ofInclude(include))

            /**
             * Alias for calling [workspaces] with
             * `BetaTelemetryDestinationWorkspacesParams.ofExclude(exclude)`.
             */
            fun workspaces(exclude: BetaTelemetryDestinationWorkspacesExcludeParams) =
                workspaces(BetaTelemetryDestinationWorkspacesParams.ofExclude(exclude))

            /** Human-readable name for the destination. Optional. 1 to 255 characters. */
            fun displayName(displayName: String?) = displayName(JsonField.ofNullable(displayName))

            /** Alias for calling [Builder.displayName] with `displayName.orElse(null)`. */
            fun displayName(displayName: Optional<String>) = displayName(displayName.getOrNull())

            /**
             * Sets [Builder.displayName] to an arbitrary JSON value.
             *
             * You should usually call [Builder.displayName] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun displayName(displayName: JsonField<String>) = apply {
                this.displayName = displayName
            }

            /**
             * Request headers that the API sends with every delivery, typically your collector's
             * authentication header (for example `authorization`).
             *
             * The API stores the values encrypted and never returns them. Reads show only
             * `header_names`.
             *
             * At most 20 headers. Names are at most 128 characters. Two names can't differ only in
             * case. Values are printable ASCII, at most 4,096 characters, and can't begin or end
             * with a space. Names and values together are at most 16,384 bytes.
             *
             * Reserved names are rejected, for example `host`, `cookie`, `user-agent`, and names
             * that start with `content-`, `proxy-`, `x-forwarded-`, or `anthropic-`. For this
             * check, `_` in a name counts as `-`.
             */
            fun headers(headers: DestinationHeaders) = headers(JsonField.of(headers))

            /**
             * Sets [Builder.headers] to an arbitrary JSON value.
             *
             * You should usually call [Builder.headers] with a well-typed [DestinationHeaders]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun headers(headers: JsonField<DestinationHeaders>) = apply { this.headers = headers }

            /**
             * The settings for the traces that the destination receives. If you leave it out, every
             * setting takes its default.
             */
            fun traces(traces: BetaTelemetryDestinationTracesParams) = traces(JsonField.of(traces))

            /**
             * Sets [Builder.traces] to an arbitrary JSON value.
             *
             * You should usually call [Builder.traces] with a well-typed
             * [BetaTelemetryDestinationTracesParams] value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun traces(traces: JsonField<BetaTelemetryDestinationTracesParams>) = apply {
                this.traces = traces
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .endpointUrl()
             * .workspaces()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("endpointUrl", endpointUrl),
                    checkRequired("workspaces", workspaces),
                    displayName,
                    headers,
                    traces,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            endpointUrl()
            workspaces().validate()
            displayName()
            headers().ifPresent { it.validate() }
            traces().ifPresent { it.validate() }
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            (if (endpointUrl.asKnown().isPresent) 1 else 0) +
                (workspaces.asKnown().getOrNull()?.validity() ?: 0) +
                (if (displayName.asKnown().isPresent) 1 else 0) +
                (headers.asKnown().getOrNull()?.validity() ?: 0) +
                (traces.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                endpointUrl == other.endpointUrl &&
                workspaces == other.workspaces &&
                displayName == other.displayName &&
                headers == other.headers &&
                traces == other.traces &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                endpointUrl,
                workspaces,
                displayName,
                headers,
                traces,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{endpointUrl=$endpointUrl, workspaces=$workspaces, displayName=$displayName, headers=$headers, traces=$traces, additionalProperties=$additionalProperties}"
    }

    /**
     * Request headers that the API sends with every delivery, typically your collector's
     * authentication header (for example `authorization`).
     *
     * The API stores the values encrypted and never returns them. Reads show only `header_names`.
     *
     * At most 20 headers. Names are at most 128 characters. Two names can't differ only in case.
     * Values are printable ASCII, at most 4,096 characters, and can't begin or end with a space.
     * Names and values together are at most 16,384 bytes.
     *
     * Reserved names are rejected, for example `host`, `cookie`, `user-agent`, and names that start
     * with `content-`, `proxy-`, `x-forwarded-`, or `anthropic-`. For this check, `_` in a name
     * counts as `-`.
     */
    class DestinationHeaders
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [DestinationHeaders]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [DestinationHeaders]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(destinationHeaders: DestinationHeaders) = apply {
                additionalProperties = destinationHeaders.additionalProperties.toMutableMap()
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [DestinationHeaders].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): DestinationHeaders = DestinationHeaders(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): DestinationHeaders = apply {
            if (validated) {
                return@apply
            }

            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is DestinationHeaders && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "DestinationHeaders{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TelemetryDestinationCreateParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "TelemetryDestinationCreateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
