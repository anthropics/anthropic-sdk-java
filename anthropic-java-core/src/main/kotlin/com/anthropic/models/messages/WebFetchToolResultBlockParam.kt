// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class WebFetchToolResultBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<Content>,
    private val toolUseId: JsonField<String>,
    private val type: JsonValue,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val caller: JsonField<Caller>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content") @ExcludeMissing content: JsonField<Content> = JsonMissing.of(),
        @JsonProperty("tool_use_id")
        @ExcludeMissing
        toolUseId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("caller") @ExcludeMissing caller: JsonField<Caller> = JsonMissing.of(),
    ) : this(content, toolUseId, type, cacheControl, caller, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): Content = content.getRequired("content")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun toolUseId(): String = toolUseId.getRequired("tool_use_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_fetch_tool_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<CacheControlEphemeral> = cacheControl.getOptional("cache_control")

    /**
     * Tool invocation directly from the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun caller(): Optional<Caller> = caller.getOptional("caller")

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<Content> = content

    /**
     * Returns the raw JSON value of [toolUseId].
     *
     * Unlike [toolUseId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_use_id") @ExcludeMissing fun _toolUseId(): JsonField<String> = toolUseId

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [caller].
     *
     * Unlike [caller], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("caller") @ExcludeMissing fun _caller(): JsonField<Caller> = caller

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
         * Returns a mutable builder for constructing an instance of [WebFetchToolResultBlockParam].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .toolUseId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WebFetchToolResultBlockParam]. */
    class Builder internal constructor() {

        private var content: JsonField<Content>? = null
        private var toolUseId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("web_fetch_tool_result")
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var caller: JsonField<Caller> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(webFetchToolResultBlockParam: WebFetchToolResultBlockParam) = apply {
            content = webFetchToolResultBlockParam.content
            toolUseId = webFetchToolResultBlockParam.toolUseId
            type = webFetchToolResultBlockParam.type
            cacheControl = webFetchToolResultBlockParam.cacheControl
            caller = webFetchToolResultBlockParam.caller
            additionalProperties = webFetchToolResultBlockParam.additionalProperties.toMutableMap()
        }

        fun content(content: Content) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [Content] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<Content>) = apply { this.content = content }

        /**
         * Alias for calling [content] with
         * `Content.ofWebFetchToolResultErrorBlockParam(webFetchToolResultErrorBlockParam)`.
         */
        fun content(webFetchToolResultErrorBlockParam: WebFetchToolResultErrorBlockParam) =
            content(Content.ofWebFetchToolResultErrorBlockParam(webFetchToolResultErrorBlockParam))

        /** Alias for calling [content] with `webFetchToolResultErrorBlockParam.toParam()`. */
        fun content(webFetchToolResultErrorBlockParam: WebFetchToolResultErrorBlock) =
            content(webFetchToolResultErrorBlockParam.toParam())

        /** Alias for calling [content] with `Content.ofWebFetchBlockParam(webFetchBlockParam)`. */
        fun content(webFetchBlockParam: WebFetchBlockParam) =
            content(Content.ofWebFetchBlockParam(webFetchBlockParam))

        /** Alias for calling [content] with `webFetchBlockParam.toParam()`. */
        fun content(webFetchBlockParam: WebFetchBlock) = content(webFetchBlockParam.toParam())

        fun toolUseId(toolUseId: String) = toolUseId(JsonField.of(toolUseId))

        /**
         * Sets [Builder.toolUseId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolUseId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun toolUseId(toolUseId: JsonField<String>) = apply { this.toolUseId = toolUseId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_fetch_tool_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed [CacheControlEphemeral]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        /** Tool invocation directly from the model. */
        fun caller(caller: Caller) = caller(JsonField.of(caller))

        /**
         * Sets [Builder.caller] to an arbitrary JSON value.
         *
         * You should usually call [Builder.caller] with a well-typed [Caller] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun caller(caller: JsonField<Caller>) = apply { this.caller = caller }

        /** Alias for calling [caller] with `Caller.ofDirect(direct)`. */
        fun caller(direct: DirectCaller) = caller(Caller.ofDirect(direct))

        /**
         * Alias for calling [caller] with `Caller.ofCodeExecution20250825(codeExecution20250825)`.
         */
        fun caller(codeExecution20250825: ServerToolCaller) =
            caller(Caller.ofCodeExecution20250825(codeExecution20250825))

        /**
         * Alias for calling [caller] with the following:
         * ```java
         * ServerToolCaller.builder()
         *     .toolId(toolId)
         *     .build()
         * ```
         */
        fun codeExecution20250825Caller(toolId: String) =
            caller(ServerToolCaller.builder().toolId(toolId).build())

        /**
         * Alias for calling [caller] with `Caller.ofCodeExecution20260120(codeExecution20260120)`.
         */
        fun caller(codeExecution20260120: Caller.CodeExecution20260120) =
            caller(Caller.ofCodeExecution20260120(codeExecution20260120))

        /**
         * Alias for calling [caller] with the following:
         * ```java
         * Caller.CodeExecution20260120.builder()
         *     .toolId(toolId)
         *     .build()
         * ```
         */
        fun codeExecution20260120Caller(toolId: String) =
            caller(Caller.CodeExecution20260120.builder().toolId(toolId).build())

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
         * Returns an immutable instance of [WebFetchToolResultBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .toolUseId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): WebFetchToolResultBlockParam =
            WebFetchToolResultBlockParam(
                checkRequired("content", content),
                checkRequired("toolUseId", toolUseId),
                type,
                cacheControl,
                caller,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): WebFetchToolResultBlockParam = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        toolUseId()
        _type().let {
            if (it != JsonValue.from("web_fetch_tool_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        caller().ifPresent { it.validate() }
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (content.asKnown().getOrNull()?.validity() ?: 0) +
            (if (toolUseId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("web_fetch_tool_result")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (caller.asKnown().getOrNull()?.validity() ?: 0)

    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val webFetchToolResultErrorBlockParam: WebFetchToolResultErrorBlockParam? = null,
        private val webFetchBlockParam: WebFetchBlockParam? = null,
        private val _json: JsonValue? = null,
    ) {

        fun webFetchToolResultErrorBlockParam(): Optional<WebFetchToolResultErrorBlockParam> =
            Optional.ofNullable(webFetchToolResultErrorBlockParam)

        fun webFetchBlockParam(): Optional<WebFetchBlockParam> =
            Optional.ofNullable(webFetchBlockParam)

        fun isWebFetchToolResultErrorBlockParam(): Boolean =
            webFetchToolResultErrorBlockParam != null

        fun isWebFetchBlockParam(): Boolean = webFetchBlockParam != null

        fun asWebFetchToolResultErrorBlockParam(): WebFetchToolResultErrorBlockParam =
            webFetchToolResultErrorBlockParam.getOrThrow("webFetchToolResultErrorBlockParam")

        fun asWebFetchBlockParam(): WebFetchBlockParam =
            webFetchBlockParam.getOrThrow("webFetchBlockParam")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                webFetchToolResultErrorBlockParam != null ->
                    visitor.visitWebFetchToolResultErrorBlockParam(
                        webFetchToolResultErrorBlockParam
                    )
                webFetchBlockParam != null -> visitor.visitWebFetchBlockParam(webFetchBlockParam)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Content = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitWebFetchToolResultErrorBlockParam(
                        webFetchToolResultErrorBlockParam: WebFetchToolResultErrorBlockParam
                    ) {
                        webFetchToolResultErrorBlockParam.validate()
                    }

                    override fun visitWebFetchBlockParam(webFetchBlockParam: WebFetchBlockParam) {
                        webFetchBlockParam.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitWebFetchToolResultErrorBlockParam(
                        webFetchToolResultErrorBlockParam: WebFetchToolResultErrorBlockParam
                    ) = webFetchToolResultErrorBlockParam.validity()

                    override fun visitWebFetchBlockParam(webFetchBlockParam: WebFetchBlockParam) =
                        webFetchBlockParam.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Content &&
                webFetchToolResultErrorBlockParam == other.webFetchToolResultErrorBlockParam &&
                webFetchBlockParam == other.webFetchBlockParam
        }

        override fun hashCode(): Int =
            Objects.hash(webFetchToolResultErrorBlockParam, webFetchBlockParam)

        override fun toString(): String =
            when {
                webFetchToolResultErrorBlockParam != null ->
                    "Content{webFetchToolResultErrorBlockParam=$webFetchToolResultErrorBlockParam}"
                webFetchBlockParam != null -> "Content{webFetchBlockParam=$webFetchBlockParam}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic
            fun ofWebFetchToolResultErrorBlockParam(
                webFetchToolResultErrorBlockParam: WebFetchToolResultErrorBlockParam
            ) = Content(webFetchToolResultErrorBlockParam = webFetchToolResultErrorBlockParam)

            @JvmStatic
            fun ofWebFetchBlockParam(webFetchBlockParam: WebFetchBlockParam) =
                Content(webFetchBlockParam = webFetchBlockParam)
        }

        /**
         * An interface that defines how to map each variant of [Content] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitWebFetchToolResultErrorBlockParam(
                webFetchToolResultErrorBlockParam: WebFetchToolResultErrorBlockParam
            ): T

            fun visitWebFetchBlockParam(webFetchBlockParam: WebFetchBlockParam): T

            /**
             * Maps an unknown variant of [Content] to a value of type [T].
             *
             * An instance of [Content] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Content: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Content>(Content::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Content {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<WebFetchToolResultErrorBlockParam>(),
                                )
                                ?.let {
                                    Content(webFetchToolResultErrorBlockParam = it, _json = json)
                                },
                            tryDeserialize(node, jacksonTypeRef<WebFetchBlockParam>())?.let {
                                Content(webFetchBlockParam = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Content(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Content>(Content::class) {

            override fun serialize(
                value: Content,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.webFetchToolResultErrorBlockParam != null ->
                        generator.writeObject(value.webFetchToolResultErrorBlockParam)
                    value.webFetchBlockParam != null ->
                        generator.writeObject(value.webFetchBlockParam)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
                }
            }
        }
    }

    /** Tool invocation directly from the model. */
    @JsonDeserialize(using = Caller.Deserializer::class)
    @JsonSerialize(using = Caller.Serializer::class)
    class Caller
    private constructor(
        private val direct: DirectCaller? = null,
        private val codeExecution20250825: ServerToolCaller? = null,
        private val codeExecution20260120: CodeExecution20260120? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Tool invocation directly from the model. */
        fun direct(): Optional<DirectCaller> = Optional.ofNullable(direct)

        /** Tool invocation generated by a server-side tool. */
        fun codeExecution20250825(): Optional<ServerToolCaller> =
            Optional.ofNullable(codeExecution20250825)

        fun codeExecution20260120(): Optional<CodeExecution20260120> =
            Optional.ofNullable(codeExecution20260120)

        fun isDirect(): Boolean = direct != null

        fun isCodeExecution20250825(): Boolean = codeExecution20250825 != null

        fun isCodeExecution20260120(): Boolean = codeExecution20260120 != null

        /** Tool invocation directly from the model. */
        fun asDirect(): DirectCaller = direct.getOrThrow("direct")

        /** Tool invocation generated by a server-side tool. */
        fun asCodeExecution20250825(): ServerToolCaller =
            codeExecution20250825.getOrThrow("codeExecution20250825")

        fun asCodeExecution20260120(): CodeExecution20260120 =
            codeExecution20260120.getOrThrow("codeExecution20260120")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                direct != null -> visitor.visitDirect(direct)
                codeExecution20250825 != null ->
                    visitor.visitCodeExecution20250825(codeExecution20250825)
                codeExecution20260120 != null ->
                    visitor.visitCodeExecution20260120(codeExecution20260120)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Caller = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitDirect(direct: DirectCaller) {
                        direct.validate()
                    }

                    override fun visitCodeExecution20250825(
                        codeExecution20250825: ServerToolCaller
                    ) {
                        codeExecution20250825.validate()
                    }

                    override fun visitCodeExecution20260120(
                        codeExecution20260120: CodeExecution20260120
                    ) {
                        codeExecution20260120.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitDirect(direct: DirectCaller) = direct.validity()

                    override fun visitCodeExecution20250825(
                        codeExecution20250825: ServerToolCaller
                    ) = codeExecution20250825.validity()

                    override fun visitCodeExecution20260120(
                        codeExecution20260120: CodeExecution20260120
                    ) = codeExecution20260120.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Caller &&
                direct == other.direct &&
                codeExecution20250825 == other.codeExecution20250825 &&
                codeExecution20260120 == other.codeExecution20260120
        }

        override fun hashCode(): Int =
            Objects.hash(direct, codeExecution20250825, codeExecution20260120)

        override fun toString(): String =
            when {
                direct != null -> "Caller{direct=$direct}"
                codeExecution20250825 != null ->
                    "Caller{codeExecution20250825=$codeExecution20250825}"
                codeExecution20260120 != null ->
                    "Caller{codeExecution20260120=$codeExecution20260120}"
                _json != null -> "Caller{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Caller")
            }

        companion object {

            /** Tool invocation directly from the model. */
            @JvmStatic fun ofDirect(direct: DirectCaller) = Caller(direct = direct)

            /** Tool invocation generated by a server-side tool. */
            @JvmStatic
            fun ofCodeExecution20250825(codeExecution20250825: ServerToolCaller) =
                Caller(codeExecution20250825 = codeExecution20250825)

            @JvmStatic
            fun ofCodeExecution20260120(codeExecution20260120: CodeExecution20260120) =
                Caller(codeExecution20260120 = codeExecution20260120)
        }

        /** An interface that defines how to map each variant of [Caller] to a value of type [T]. */
        interface Visitor<out T> {

            /** Tool invocation directly from the model. */
            fun visitDirect(direct: DirectCaller): T

            /** Tool invocation generated by a server-side tool. */
            fun visitCodeExecution20250825(codeExecution20250825: ServerToolCaller): T

            fun visitCodeExecution20260120(codeExecution20260120: CodeExecution20260120): T

            /**
             * Maps an unknown variant of [Caller] to a value of type [T].
             *
             * An instance of [Caller] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Caller: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Caller>(Caller::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Caller {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "direct" -> {
                        return tryDeserialize(node, jacksonTypeRef<DirectCaller>())?.let {
                            Caller(direct = it, _json = json)
                        } ?: Caller(_json = json)
                    }
                    "code_execution_20250825" -> {
                        return tryDeserialize(node, jacksonTypeRef<ServerToolCaller>())?.let {
                            Caller(codeExecution20250825 = it, _json = json)
                        } ?: Caller(_json = json)
                    }
                    "code_execution_20260120" -> {
                        return tryDeserialize(node, jacksonTypeRef<CodeExecution20260120>())?.let {
                            Caller(codeExecution20260120 = it, _json = json)
                        } ?: Caller(_json = json)
                    }
                }

                return Caller(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Caller>(Caller::class) {

            override fun serialize(
                value: Caller,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.direct != null -> generator.writeObject(value.direct)
                    value.codeExecution20250825 != null ->
                        generator.writeObject(value.codeExecution20250825)
                    value.codeExecution20260120 != null ->
                        generator.writeObject(value.codeExecution20260120)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Caller")
                }
            }
        }

        class CodeExecution20260120
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val toolId: JsonField<String>,
            private val type: JsonValue,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("tool_id")
                @ExcludeMissing
                toolId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            ) : this(toolId, type, mutableMapOf())

            /**
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun toolId(): String = toolId.getRequired("tool_id")

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("code_execution_20260120")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * Returns the raw JSON value of [toolId].
             *
             * Unlike [toolId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("tool_id") @ExcludeMissing fun _toolId(): JsonField<String> = toolId

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
                 * Returns a mutable builder for constructing an instance of
                 * [CodeExecution20260120].
                 *
                 * The following fields are required:
                 * ```java
                 * .toolId()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [CodeExecution20260120]. */
            class Builder internal constructor() {

                private var toolId: JsonField<String>? = null
                private var type: JsonValue = JsonValue.from("code_execution_20260120")
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(codeExecution20260120: CodeExecution20260120) = apply {
                    toolId = codeExecution20260120.toolId
                    type = codeExecution20260120.type
                    additionalProperties = codeExecution20260120.additionalProperties.toMutableMap()
                }

                fun toolId(toolId: String) = toolId(JsonField.of(toolId))

                /**
                 * Sets [Builder.toolId] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.toolId] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun toolId(toolId: JsonField<String>) = apply { this.toolId = toolId }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("code_execution_20260120")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [CodeExecution20260120].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .toolId()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): CodeExecution20260120 =
                    CodeExecution20260120(
                        checkRequired("toolId", toolId),
                        type,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): CodeExecution20260120 = apply {
                if (validated) {
                    return@apply
                }

                toolId()
                _type().let {
                    if (it != JsonValue.from("code_execution_20260120")) {
                        throw AnthropicInvalidDataException("'type' is invalid, received $it")
                    }
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
                (if (toolId.asKnown().isPresent) 1 else 0) +
                    type.let { if (it == JsonValue.from("code_execution_20260120")) 1 else 0 }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is CodeExecution20260120 &&
                    toolId == other.toolId &&
                    type == other.type &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(toolId, type, additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "CodeExecution20260120{toolId=$toolId, type=$type, additionalProperties=$additionalProperties}"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WebFetchToolResultBlockParam &&
            content == other.content &&
            toolUseId == other.toolUseId &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            caller == other.caller &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, toolUseId, type, cacheControl, caller, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "WebFetchToolResultBlockParam{content=$content, toolUseId=$toolUseId, type=$type, cacheControl=$cacheControl, caller=$caller, additionalProperties=$additionalProperties}"
}
