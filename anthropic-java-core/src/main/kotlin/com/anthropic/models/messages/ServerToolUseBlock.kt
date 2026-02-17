// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired

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


class ServerToolUseBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
private constructor(
    private val id: JsonField<String>,
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val caller: JsonField<Caller>,
    private val input: JsonValue,
    private val name: JsonValue,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("caller") @ExcludeMissing caller: JsonField<Caller> = JsonMissing.of(),
        @JsonProperty("input") @ExcludeMissing input: JsonValue = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, caller, input, name, type, mutableMapOf())

    fun toParam(): ServerToolUseBlockParam =
        ServerToolUseBlockParam.builder()
            .id(_id())
            .input(_input())
            .name(_name().map { ServerToolUseBlockParam.Name.of(it.toString()) })
            .caller(
                _caller().map {
                    it.accept(
                        object : ServerToolUseBlock.Caller.Visitor<ServerToolUseBlockParam.Caller> {
                            override fun visitDirect(
                                direct: DirectCaller
                            ): ServerToolUseBlockParam.Caller =
                                ServerToolUseBlockParam.Caller.ofDirect(direct)

                            override fun visitCodeExecution20250825(
                                codeExecution20250825: ServerToolCaller
                            ): ServerToolUseBlockParam.Caller =
                                ServerToolUseBlockParam.Caller.ofDirect(
                                    DirectCaller.builder().build()
                                )

                            override fun visitCodeExecution20260120(
                                codeExecution20260120:
                                    ServerToolUseBlock.Caller.CodeExecution20260120
                            ): ServerToolUseBlockParam.Caller =
                                ServerToolUseBlockParam.Caller.ofDirect(
                                    DirectCaller.builder().build()
                                )
                        }
                    )
                }
            )
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    @JsonProperty("input") @ExcludeMissing fun _input(): JsonValue = input

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): Name = name.getRequired("name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("server_tool_use")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id


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
         * Returns a mutable builder for constructing an instance of [ServerToolUseBlock].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .caller()
         * .input()
         * .name()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ServerToolUseBlock]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var id: JsonField<String>? = null
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ServerToolUseBlock]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var caller: JsonField<Caller>? = null
        private var input: JsonValue? = null
        private var name: JsonValue = JsonValue.from("web_search")
        private var type: JsonValue = JsonValue.from("server_tool_use")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(serverToolUseBlock: ServerToolUseBlock) = apply {
            id = serverToolUseBlock.id
            caller = serverToolUseBlock.caller
            input = serverToolUseBlock.input
            name = serverToolUseBlock.name
            type = serverToolUseBlock.type
            additionalProperties = serverToolUseBlock.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

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

        fun input(input: JsonValue) = apply { this.input = input }

        fun name(name: Name) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [Name] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<Name>) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("server_tool_use")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [ServerToolUseBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .caller()
         * .input()
         * .name()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ServerToolUseBlock =
            ServerToolUseBlock(
                checkRequired("id", id),
                checkRequired("caller", caller),
                checkRequired("input", input),
                checkRequired("name", name),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ServerToolUseBlock = apply {
        if (validated) {
            return@apply
        }

        id()
        caller().validate()
        _name().let {
            if (it != JsonValue.from("web_search")) {
                throw AnthropicInvalidDataException("'name' is invalid, received $it")
            }
        }
        _type().let {
            if (it != JsonValue.from("server_tool_use")) {
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (caller.asKnown().getOrNull()?.validity() ?: 0) +
            (input.asKnown().getOrNull()?.validity() ?: 0) +
            (name.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("server_tool_use")) 1 else 0 }

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

    class Name @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val WEB_SEARCH = of("web_search")

            @JvmField val WEB_FETCH = of("web_fetch")

            @JvmField val CODE_EXECUTION = of("code_execution")

            @JvmField val BASH_CODE_EXECUTION = of("bash_code_execution")

            @JvmField val TEXT_EDITOR_CODE_EXECUTION = of("text_editor_code_execution")

            @JvmField val TOOL_SEARCH_TOOL_REGEX = of("tool_search_tool_regex")

            @JvmField val TOOL_SEARCH_TOOL_BM25 = of("tool_search_tool_bm25")

            @JvmStatic fun of(value: String) = Name(JsonField.of(value))
        }

        /** An enum containing [Name]'s known values. */
        enum class Known {
            WEB_SEARCH,
            WEB_FETCH,
            CODE_EXECUTION,
            BASH_CODE_EXECUTION,
            TEXT_EDITOR_CODE_EXECUTION,
            TOOL_SEARCH_TOOL_REGEX,
            TOOL_SEARCH_TOOL_BM25,
        }

        /**
         * An enum containing [Name]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Name] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            WEB_SEARCH,
            WEB_FETCH,
            CODE_EXECUTION,
            BASH_CODE_EXECUTION,
            TEXT_EDITOR_CODE_EXECUTION,
            TOOL_SEARCH_TOOL_REGEX,
            TOOL_SEARCH_TOOL_BM25,
            /** An enum member indicating that [Name] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                WEB_SEARCH -> Value.WEB_SEARCH
                WEB_FETCH -> Value.WEB_FETCH
                CODE_EXECUTION -> Value.CODE_EXECUTION
                BASH_CODE_EXECUTION -> Value.BASH_CODE_EXECUTION
                TEXT_EDITOR_CODE_EXECUTION -> Value.TEXT_EDITOR_CODE_EXECUTION
                TOOL_SEARCH_TOOL_REGEX -> Value.TOOL_SEARCH_TOOL_REGEX
                TOOL_SEARCH_TOOL_BM25 -> Value.TOOL_SEARCH_TOOL_BM25
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                WEB_SEARCH -> Known.WEB_SEARCH
                WEB_FETCH -> Known.WEB_FETCH
                CODE_EXECUTION -> Known.CODE_EXECUTION
                BASH_CODE_EXECUTION -> Known.BASH_CODE_EXECUTION
                TEXT_EDITOR_CODE_EXECUTION -> Known.TEXT_EDITOR_CODE_EXECUTION
                TOOL_SEARCH_TOOL_REGEX -> Known.TOOL_SEARCH_TOOL_REGEX
                TOOL_SEARCH_TOOL_BM25 -> Known.TOOL_SEARCH_TOOL_BM25
                else -> throw AnthropicInvalidDataException("Unknown Name: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): Name = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Name && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ServerToolUseBlock &&
            id == other.id &&
            caller == other.caller &&
            input == other.input &&
            name == other.name &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, caller, input, name, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ServerToolUseBlock{id=$id, caller=$caller, input=$input, name=$name, type=$type, additionalProperties=$additionalProperties}"
}
