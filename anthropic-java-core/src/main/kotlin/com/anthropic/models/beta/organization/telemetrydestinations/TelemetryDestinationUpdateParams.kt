package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
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
 * Change a telemetry destination's settings, its state, or which workspaces it covers.
 *
 * Send at least one field. Omitted fields keep their stored values, except the registered headers,
 * which a change of the endpoint's hostname or port removes. Only `display_name`, `traces`,
 * `traces.endpoint_url`, `traces.content_mode`, `traces.max_content_bytes`, and individual
 * `headers` values accept `null`.
 *
 * If another request changes the destination while the update runs, the API returns a 409
 * `conflict_error`. Read the destination again, then retry the update.
 *
 * See the
 * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#delivery-and-health)
 * to learn more about changing a destination.
 */
class TelemetryDestinationUpdateParams
private constructor(
    private val destinationId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the destination (`tdst_...`). */
    fun destinationId(): Optional<String> = Optional.ofNullable(destinationId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * New human-readable name, 1 to 255 characters. Null clears it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayName(): Optional<String> = body.displayName()

    /**
     * The new base URL of the destination's OTLP/HTTP endpoint. The same rules as on create apply.
     *
     * A change of hostname or port first removes every registered header, so send the headers for
     * the new endpoint in the same request. When the destination has a `traces.endpoint_url`, such
     * a change must also set `traces.endpoint_url` to a URL with the new hostname and port, or to
     * `null`. A change of the path alone keeps the headers.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun endpointUrl(): Optional<String> = body.endpointUrl()

    /**
     * Headers to set or remove. The API merges them into the destination's headers by name.
     *
     * Names compare case-insensitively. A string value sets or replaces that header. A `null` value
     * removes it. Headers that you omit don't change. The field itself can't be `null`.
     *
     * A request that sets a header value must also include `endpoint_url`. The current URL is
     * accepted.
     *
     * The resulting set must stay within the same limits as on create. When the request sets a
     * value, each stored value that it doesn't set counts as 4,096 bytes toward the 16,384-byte
     * limit, because you can't read its real length. So to change one header of a destination that
     * has four or more other headers, send the values of the others again in the same request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun headers(): Optional<DestinationHeaders> = body.headers()

    /**
     * Whether the destination receives deliveries after the update. The field can't be `null`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun state(): Optional<BetaTelemetryDestinationStateParams> = body.state()

    /**
     * The traces settings to change. Omitted settings keep their stored values.
     *
     * `null` sets every traces setting to its create default.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun traces(): Optional<BetaTelemetryDestinationTracesParams> = body.traces()

    /**
     * The new workspace scope, in the same form as on create. The field can't be `null`.
     *
     * The API refuses an update that changes the type or sends `workspace_ids` while the
     * destination's list has more than 750 workspaces. Remove workspaces one at a time until 750 or
     * fewer remain, then send the update again.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workspaces(): Optional<BetaTelemetryDestinationWorkspacesParams> = body.workspaces()

    /**
     * Returns the raw JSON value of [displayName].
     *
     * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _displayName(): JsonField<String> = body._displayName()

    /**
     * Returns the raw JSON value of [endpointUrl].
     *
     * Unlike [endpointUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _endpointUrl(): JsonField<String> = body._endpointUrl()

    /**
     * Returns the raw JSON value of [headers].
     *
     * Unlike [headers], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _headers_(): JsonField<DestinationHeaders> = body._headers_()

    /**
     * Returns the raw JSON value of [state].
     *
     * Unlike [state], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _state(): JsonField<BetaTelemetryDestinationStateParams> = body._state()

    /**
     * Returns the raw JSON value of [traces].
     *
     * Unlike [traces], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _traces(): JsonField<BetaTelemetryDestinationTracesParams> = body._traces()

    /**
     * Returns the raw JSON value of [workspaces].
     *
     * Unlike [workspaces], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _workspaces(): JsonField<BetaTelemetryDestinationWorkspacesParams> = body._workspaces()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): TelemetryDestinationUpdateParams = builder().build()

        /**
         * Returns a mutable builder for constructing an instance of
         * [TelemetryDestinationUpdateParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TelemetryDestinationUpdateParams]. */
    class Builder internal constructor() {

        private var destinationId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(telemetryDestinationUpdateParams: TelemetryDestinationUpdateParams) =
            apply {
                destinationId = telemetryDestinationUpdateParams.destinationId
                betas = telemetryDestinationUpdateParams.betas?.toMutableList()
                body = telemetryDestinationUpdateParams.body.toBuilder()
                additionalHeaders = telemetryDestinationUpdateParams.additionalHeaders.toBuilder()
                additionalQueryParams =
                    telemetryDestinationUpdateParams.additionalQueryParams.toBuilder()
            }

        /** ID of the destination (`tdst_...`). */
        fun destinationId(destinationId: String?) = apply { this.destinationId = destinationId }

        /** Alias for calling [Builder.destinationId] with `destinationId.orElse(null)`. */
        fun destinationId(destinationId: Optional<String>) =
            destinationId(destinationId.getOrNull())

        /** Optional header to specify the beta version(s) you want to use. */
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
         * - [displayName]
         * - [endpointUrl]
         * - [headers]
         * - [state]
         * - [traces]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** New human-readable name, 1 to 255 characters. Null clears it. */
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
         * The new base URL of the destination's OTLP/HTTP endpoint. The same rules as on create
         * apply.
         *
         * A change of hostname or port first removes every registered header, so send the headers
         * for the new endpoint in the same request. When the destination has a
         * `traces.endpoint_url`, such a change must also set `traces.endpoint_url` to a URL with
         * the new hostname and port, or to `null`. A change of the path alone keeps the headers.
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

        /**
         * Headers to set or remove. The API merges them into the destination's headers by name.
         *
         * Names compare case-insensitively. A string value sets or replaces that header. A `null`
         * value removes it. Headers that you omit don't change. The field itself can't be `null`.
         *
         * A request that sets a header value must also include `endpoint_url`. The current URL is
         * accepted.
         *
         * The resulting set must stay within the same limits as on create. When the request sets a
         * value, each stored value that it doesn't set counts as 4,096 bytes toward the 16,384-byte
         * limit, because you can't read its real length. So to change one header of a destination
         * that has four or more other headers, send the values of the others again in the same
         * request.
         */
        fun headers(headers: DestinationHeaders?) = apply { body.headers(headers) }

        /** Alias for calling [Builder.headers] with `headers.orElse(null)`. */
        fun headers(headers: Optional<DestinationHeaders>) = headers(headers.getOrNull())

        /**
         * Sets [Builder.headers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.headers] with a well-typed [DestinationHeaders] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun headers(headers: JsonField<DestinationHeaders>) = apply { body.headers(headers) }

        /**
         * Whether the destination receives deliveries after the update. The field can't be `null`.
         */
        fun state(state: BetaTelemetryDestinationStateParams) = apply { body.state(state) }

        /**
         * Sets [Builder.state] to an arbitrary JSON value.
         *
         * You should usually call [Builder.state] with a well-typed
         * [BetaTelemetryDestinationStateParams] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun state(state: JsonField<BetaTelemetryDestinationStateParams>) = apply {
            body.state(state)
        }

        /**
         * Alias for calling [state] with `BetaTelemetryDestinationStateParams.ofEnabled(enabled)`.
         */
        fun state(enabled: BetaTelemetryDestinationStateEnabledParams) = apply {
            body.state(enabled)
        }

        /**
         * Alias for calling [state] with
         * `BetaTelemetryDestinationStateParams.ofDisabled(disabled)`.
         */
        fun state(disabled: BetaTelemetryDestinationStateDisabledParams) = apply {
            body.state(disabled)
        }

        /**
         * The traces settings to change. Omitted settings keep their stored values.
         *
         * `null` sets every traces setting to its create default.
         */
        fun traces(traces: BetaTelemetryDestinationTracesParams?) = apply { body.traces(traces) }

        /** Alias for calling [Builder.traces] with `traces.orElse(null)`. */
        fun traces(traces: Optional<BetaTelemetryDestinationTracesParams>) =
            traces(traces.getOrNull())

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

        /**
         * The new workspace scope, in the same form as on create. The field can't be `null`.
         *
         * The API refuses an update that changes the type or sends `workspace_ids` while the
         * destination's list has more than 750 workspaces. Remove workspaces one at a time until
         * 750 or fewer remain, then send the update again.
         */
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
         * Returns an immutable instance of [TelemetryDestinationUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): TelemetryDestinationUpdateParams =
            TelemetryDestinationUpdateParams(
                destinationId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> destinationId ?: ""
            else -> ""
        }

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
            name = "anthropic.organization.telemetry_destinations.update",
            route = "/v1/organizations/telemetry_destinations/{destination_id}",
            attributes = mapOf("anthropic.organization.telemetry_destination.id" to _pathParam(0)),
        )

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val displayName: JsonField<String>,
        private val endpointUrl: JsonField<String>,
        private val headers: JsonField<DestinationHeaders>,
        private val state: JsonField<BetaTelemetryDestinationStateParams>,
        private val traces: JsonField<BetaTelemetryDestinationTracesParams>,
        private val workspaces: JsonField<BetaTelemetryDestinationWorkspacesParams>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("display_name")
            @ExcludeMissing
            displayName: JsonField<String> = JsonMissing.of(),
            @JsonProperty("endpoint_url")
            @ExcludeMissing
            endpointUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("headers")
            @ExcludeMissing
            headers: JsonField<DestinationHeaders> = JsonMissing.of(),
            @JsonProperty("state")
            @ExcludeMissing
            state: JsonField<BetaTelemetryDestinationStateParams> = JsonMissing.of(),
            @JsonProperty("traces")
            @ExcludeMissing
            traces: JsonField<BetaTelemetryDestinationTracesParams> = JsonMissing.of(),
            @JsonProperty("workspaces")
            @ExcludeMissing
            workspaces: JsonField<BetaTelemetryDestinationWorkspacesParams> = JsonMissing.of(),
        ) : this(displayName, endpointUrl, headers, state, traces, workspaces, mutableMapOf())

        /**
         * New human-readable name, 1 to 255 characters. Null clears it.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun displayName(): Optional<String> = displayName.getOptional("display_name")

        /**
         * The new base URL of the destination's OTLP/HTTP endpoint. The same rules as on create
         * apply.
         *
         * A change of hostname or port first removes every registered header, so send the headers
         * for the new endpoint in the same request. When the destination has a
         * `traces.endpoint_url`, such a change must also set `traces.endpoint_url` to a URL with
         * the new hostname and port, or to `null`. A change of the path alone keeps the headers.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun endpointUrl(): Optional<String> = endpointUrl.getOptional("endpoint_url")

        /**
         * Headers to set or remove. The API merges them into the destination's headers by name.
         *
         * Names compare case-insensitively. A string value sets or replaces that header. A `null`
         * value removes it. Headers that you omit don't change. The field itself can't be `null`.
         *
         * A request that sets a header value must also include `endpoint_url`. The current URL is
         * accepted.
         *
         * The resulting set must stay within the same limits as on create. When the request sets a
         * value, each stored value that it doesn't set counts as 4,096 bytes toward the 16,384-byte
         * limit, because you can't read its real length. So to change one header of a destination
         * that has four or more other headers, send the values of the others again in the same
         * request.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun headers(): Optional<DestinationHeaders> = headers.getOptional("headers")

        /**
         * Whether the destination receives deliveries after the update. The field can't be `null`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun state(): Optional<BetaTelemetryDestinationStateParams> = state.getOptional("state")

        /**
         * The traces settings to change. Omitted settings keep their stored values.
         *
         * `null` sets every traces setting to its create default.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun traces(): Optional<BetaTelemetryDestinationTracesParams> = traces.getOptional("traces")

        /**
         * The new workspace scope, in the same form as on create. The field can't be `null`.
         *
         * The API refuses an update that changes the type or sends `workspace_ids` while the
         * destination's list has more than 750 workspaces. Remove workspaces one at a time until
         * 750 or fewer remain, then send the update again.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun workspaces(): Optional<BetaTelemetryDestinationWorkspacesParams> =
            workspaces.getOptional("workspaces")

        /**
         * Returns the raw JSON value of [displayName].
         *
         * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("display_name")
        @ExcludeMissing
        fun _displayName(): JsonField<String> = displayName

        /**
         * Returns the raw JSON value of [endpointUrl].
         *
         * Unlike [endpointUrl], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("endpoint_url")
        @ExcludeMissing
        fun _endpointUrl(): JsonField<String> = endpointUrl

        /**
         * Returns the raw JSON value of [headers].
         *
         * Unlike [headers], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("headers")
        @ExcludeMissing
        fun _headers_(): JsonField<DestinationHeaders> = headers

        /**
         * Returns the raw JSON value of [state].
         *
         * Unlike [state], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("state")
        @ExcludeMissing
        fun _state(): JsonField<BetaTelemetryDestinationStateParams> = state

        /**
         * Returns the raw JSON value of [traces].
         *
         * Unlike [traces], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("traces")
        @ExcludeMissing
        fun _traces(): JsonField<BetaTelemetryDestinationTracesParams> = traces

        /**
         * Returns the raw JSON value of [workspaces].
         *
         * Unlike [workspaces], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("workspaces")
        @ExcludeMissing
        fun _workspaces(): JsonField<BetaTelemetryDestinationWorkspacesParams> = workspaces

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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var displayName: JsonField<String> = JsonMissing.of()
            private var endpointUrl: JsonField<String> = JsonMissing.of()
            private var headers: JsonField<DestinationHeaders> = JsonMissing.of()
            private var state: JsonField<BetaTelemetryDestinationStateParams> = JsonMissing.of()
            private var traces: JsonField<BetaTelemetryDestinationTracesParams> = JsonMissing.of()
            private var workspaces: JsonField<BetaTelemetryDestinationWorkspacesParams> =
                JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                displayName = body.displayName
                endpointUrl = body.endpointUrl
                headers = body.headers
                state = body.state
                traces = body.traces
                workspaces = body.workspaces
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /** New human-readable name, 1 to 255 characters. Null clears it. */
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
             * The new base URL of the destination's OTLP/HTTP endpoint. The same rules as on create
             * apply.
             *
             * A change of hostname or port first removes every registered header, so send the
             * headers for the new endpoint in the same request. When the destination has a
             * `traces.endpoint_url`, such a change must also set `traces.endpoint_url` to a URL
             * with the new hostname and port, or to `null`. A change of the path alone keeps the
             * headers.
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

            /**
             * Headers to set or remove. The API merges them into the destination's headers by name.
             *
             * Names compare case-insensitively. A string value sets or replaces that header. A
             * `null` value removes it. Headers that you omit don't change. The field itself can't
             * be `null`.
             *
             * A request that sets a header value must also include `endpoint_url`. The current URL
             * is accepted.
             *
             * The resulting set must stay within the same limits as on create. When the request
             * sets a value, each stored value that it doesn't set counts as 4,096 bytes toward the
             * 16,384-byte limit, because you can't read its real length. So to change one header of
             * a destination that has four or more other headers, send the values of the others
             * again in the same request.
             */
            fun headers(headers: DestinationHeaders?) = headers(JsonField.ofNullable(headers))

            /** Alias for calling [Builder.headers] with `headers.orElse(null)`. */
            fun headers(headers: Optional<DestinationHeaders>) = headers(headers.getOrNull())

            /**
             * Sets [Builder.headers] to an arbitrary JSON value.
             *
             * You should usually call [Builder.headers] with a well-typed [DestinationHeaders]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun headers(headers: JsonField<DestinationHeaders>) = apply { this.headers = headers }

            /**
             * Whether the destination receives deliveries after the update. The field can't be
             * `null`.
             */
            fun state(state: BetaTelemetryDestinationStateParams) = state(JsonField.of(state))

            /**
             * Sets [Builder.state] to an arbitrary JSON value.
             *
             * You should usually call [Builder.state] with a well-typed
             * [BetaTelemetryDestinationStateParams] value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun state(state: JsonField<BetaTelemetryDestinationStateParams>) = apply {
                this.state = state
            }

            /**
             * Alias for calling [state] with
             * `BetaTelemetryDestinationStateParams.ofEnabled(enabled)`.
             */
            fun state(enabled: BetaTelemetryDestinationStateEnabledParams) =
                state(BetaTelemetryDestinationStateParams.ofEnabled(enabled))

            /**
             * Alias for calling [state] with
             * `BetaTelemetryDestinationStateParams.ofDisabled(disabled)`.
             */
            fun state(disabled: BetaTelemetryDestinationStateDisabledParams) =
                state(BetaTelemetryDestinationStateParams.ofDisabled(disabled))

            /**
             * The traces settings to change. Omitted settings keep their stored values.
             *
             * `null` sets every traces setting to its create default.
             */
            fun traces(traces: BetaTelemetryDestinationTracesParams?) =
                traces(JsonField.ofNullable(traces))

            /** Alias for calling [Builder.traces] with `traces.orElse(null)`. */
            fun traces(traces: Optional<BetaTelemetryDestinationTracesParams>) =
                traces(traces.getOrNull())

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

            /**
             * The new workspace scope, in the same form as on create. The field can't be `null`.
             *
             * The API refuses an update that changes the type or sends `workspace_ids` while the
             * destination's list has more than 750 workspaces. Remove workspaces one at a time
             * until 750 or fewer remain, then send the update again.
             */
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
             */
            fun build(): Body =
                Body(
                    displayName,
                    endpointUrl,
                    headers,
                    state,
                    traces,
                    workspaces,
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

            displayName()
            endpointUrl()
            headers().ifPresent { it.validate() }
            state().ifPresent { it.validate() }
            traces().ifPresent { it.validate() }
            workspaces().ifPresent { it.validate() }
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
            (if (displayName.asKnown().isPresent) 1 else 0) +
                (if (endpointUrl.asKnown().isPresent) 1 else 0) +
                (headers.asKnown().getOrNull()?.validity() ?: 0) +
                (state.asKnown().getOrNull()?.validity() ?: 0) +
                (traces.asKnown().getOrNull()?.validity() ?: 0) +
                (workspaces.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                displayName == other.displayName &&
                endpointUrl == other.endpointUrl &&
                headers == other.headers &&
                state == other.state &&
                traces == other.traces &&
                workspaces == other.workspaces &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                displayName,
                endpointUrl,
                headers,
                state,
                traces,
                workspaces,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{displayName=$displayName, endpointUrl=$endpointUrl, headers=$headers, state=$state, traces=$traces, workspaces=$workspaces, additionalProperties=$additionalProperties}"
    }

    /**
     * Headers to set or remove. The API merges them into the destination's headers by name.
     *
     * Names compare case-insensitively. A string value sets or replaces that header. A `null` value
     * removes it. Headers that you omit don't change. The field itself can't be `null`.
     *
     * A request that sets a header value must also include `endpoint_url`. The current URL is
     * accepted.
     *
     * The resulting set must stay within the same limits as on create. When the request sets a
     * value, each stored value that it doesn't set counts as 4,096 bytes toward the 16,384-byte
     * limit, because you can't read its real length. So to change one header of a destination that
     * has four or more other headers, send the values of the others again in the same request.
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

        return other is TelemetryDestinationUpdateParams &&
            destinationId == other.destinationId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(destinationId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "TelemetryDestinationUpdateParams{destinationId=$destinationId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
