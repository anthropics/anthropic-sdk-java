// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.outputTypeFromJson
import com.anthropic.core.toJsonString
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

class BetaToolUseBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val input: JsonValue,
    private val name: JsonField<String>,
    private val type: JsonValue,
    private val caller: JsonField<Caller>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("input") @ExcludeMissing input: JsonValue = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("caller") @ExcludeMissing caller: JsonField<Caller> = JsonMissing.of(),
    ) : this(id, input, name, type, caller, mutableMapOf())

    fun toParam(): BetaToolUseBlockParam =
        BetaToolUseBlockParam.builder()
            .id(_id())
            .input(_input())
            .name(_name())
            .caller(
                _caller().map {
                    it.accept(
                        object : BetaToolUseBlock.Caller.Visitor<BetaToolUseBlockParam.Caller> {
                            override fun visitDirect(
                                direct: BetaDirectCaller
                            ): BetaToolUseBlockParam.Caller =
                                BetaToolUseBlockParam.Caller.ofDirect(direct)

                            override fun visitCodeExecution20250825(
                                codeExecution20250825: BetaServerToolCaller
                            ): BetaToolUseBlockParam.Caller =
                                BetaToolUseBlockParam.Caller.ofDirect(
                                    BetaDirectCaller.builder().build()
                                )

                            override fun visitCodeExecution20260120(
                                codeExecution20260120: BetaServerToolCaller20260120
                            ): BetaToolUseBlockParam.Caller =
                                BetaToolUseBlockParam.Caller.ofDirect(
                                    BetaDirectCaller.builder().build()
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
     * Gets the input parameters to the tool use invocation, converting the values from the model in
     * JSON format to an instance of a class that holds those values. The class must previously have
     * been used to define the JSON schema for the tool use definition, so that the JSON corresponds
     * to the structure of the given class.
     *
     * @throws AnthropicInvalidDataException If the JSON data is missing, `null`, or cannot be
     *   parsed to an instance of the [parametersType] class. This might occur if the class is not
     *   the same as the class that was originally used to define the tool use, or if the data from
     *   the AI model is invalid or incomplete (e.g., truncated).
     * @see MessageCreateParams.Builder.addTool
     * @see _input
     */
    fun <T : Any> input(parametersType: Class<T>): T? =
        outputTypeFromJson(toJsonString(input), parametersType)

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tool_use")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Tool invocation directly from the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun caller(): Optional<Caller> = caller.getOptional("caller")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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
         * Returns a mutable builder for constructing an instance of [BetaToolUseBlock].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .name()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaToolUseBlock]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var input: JsonValue? = null
        private var name: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("tool_use")
        private var caller: JsonField<Caller> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolUseBlock: BetaToolUseBlock) = apply {
            id = betaToolUseBlock.id
            input = betaToolUseBlock.input
            name = betaToolUseBlock.name
            type = betaToolUseBlock.type
            caller = betaToolUseBlock.caller
            additionalProperties = betaToolUseBlock.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun input(input: JsonValue) = apply { this.input = input }

        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tool_use")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
        fun caller(direct: BetaDirectCaller) = caller(Caller.ofDirect(direct))

        /**
         * Alias for calling [caller] with `Caller.ofCodeExecution20250825(codeExecution20250825)`.
         */
        fun caller(codeExecution20250825: BetaServerToolCaller) =
            caller(Caller.ofCodeExecution20250825(codeExecution20250825))

        /**
         * Alias for calling [caller] with the following:
         * ```java
         * BetaServerToolCaller.builder()
         *     .toolId(toolId)
         *     .build()
         * ```
         */
        fun codeExecution20250825Caller(toolId: String) =
            caller(BetaServerToolCaller.builder().toolId(toolId).build())

        /**
         * Alias for calling [caller] with `Caller.ofCodeExecution20260120(codeExecution20260120)`.
         */
        fun caller(codeExecution20260120: BetaServerToolCaller20260120) =
            caller(Caller.ofCodeExecution20260120(codeExecution20260120))

        /**
         * Alias for calling [caller] with the following:
         * ```java
         * BetaServerToolCaller20260120.builder()
         *     .toolId(toolId)
         *     .build()
         * ```
         */
        fun codeExecution20260120Caller(toolId: String) =
            caller(BetaServerToolCaller20260120.builder().toolId(toolId).build())

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
         * Returns an immutable instance of [BetaToolUseBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .input()
         * .name()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaToolUseBlock =
            BetaToolUseBlock(
                checkRequired("id", id),
                checkRequired("input", input),
                checkRequired("name", name),
                type,
                caller,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaToolUseBlock = apply {
        if (validated) {
            return@apply
        }

        id()
        name()
        _type().let {
            if (it != JsonValue.from("tool_use")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("tool_use")) 1 else 0 } +
            (caller.asKnown().getOrNull()?.validity() ?: 0)

    /** Tool invocation directly from the model. */
    @JsonDeserialize(using = Caller.Deserializer::class)
    @JsonSerialize(using = Caller.Serializer::class)
    class Caller
    private constructor(
        private val direct: BetaDirectCaller? = null,
        private val codeExecution20250825: BetaServerToolCaller? = null,
        private val codeExecution20260120: BetaServerToolCaller20260120? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Tool invocation directly from the model. */
        fun direct(): Optional<BetaDirectCaller> = Optional.ofNullable(direct)

        /** Tool invocation generated by a server-side tool. */
        fun codeExecution20250825(): Optional<BetaServerToolCaller> =
            Optional.ofNullable(codeExecution20250825)

        fun codeExecution20260120(): Optional<BetaServerToolCaller20260120> =
            Optional.ofNullable(codeExecution20260120)

        fun isDirect(): Boolean = direct != null

        fun isCodeExecution20250825(): Boolean = codeExecution20250825 != null

        fun isCodeExecution20260120(): Boolean = codeExecution20260120 != null

        /** Tool invocation directly from the model. */
        fun asDirect(): BetaDirectCaller = direct.getOrThrow("direct")

        /** Tool invocation generated by a server-side tool. */
        fun asCodeExecution20250825(): BetaServerToolCaller =
            codeExecution20250825.getOrThrow("codeExecution20250825")

        fun asCodeExecution20260120(): BetaServerToolCaller20260120 =
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
                    override fun visitDirect(direct: BetaDirectCaller) {
                        direct.validate()
                    }

                    override fun visitCodeExecution20250825(
                        codeExecution20250825: BetaServerToolCaller
                    ) {
                        codeExecution20250825.validate()
                    }

                    override fun visitCodeExecution20260120(
                        codeExecution20260120: BetaServerToolCaller20260120
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
                    override fun visitDirect(direct: BetaDirectCaller) = direct.validity()

                    override fun visitCodeExecution20250825(
                        codeExecution20250825: BetaServerToolCaller
                    ) = codeExecution20250825.validity()

                    override fun visitCodeExecution20260120(
                        codeExecution20260120: BetaServerToolCaller20260120
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
            @JvmStatic fun ofDirect(direct: BetaDirectCaller) = Caller(direct = direct)

            /** Tool invocation generated by a server-side tool. */
            @JvmStatic
            fun ofCodeExecution20250825(codeExecution20250825: BetaServerToolCaller) =
                Caller(codeExecution20250825 = codeExecution20250825)

            @JvmStatic
            fun ofCodeExecution20260120(codeExecution20260120: BetaServerToolCaller20260120) =
                Caller(codeExecution20260120 = codeExecution20260120)
        }

        /** An interface that defines how to map each variant of [Caller] to a value of type [T]. */
        interface Visitor<out T> {

            /** Tool invocation directly from the model. */
            fun visitDirect(direct: BetaDirectCaller): T

            /** Tool invocation generated by a server-side tool. */
            fun visitCodeExecution20250825(codeExecution20250825: BetaServerToolCaller): T

            fun visitCodeExecution20260120(codeExecution20260120: BetaServerToolCaller20260120): T

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
                        return tryDeserialize(node, jacksonTypeRef<BetaDirectCaller>())?.let {
                            Caller(direct = it, _json = json)
                        } ?: Caller(_json = json)
                    }
                    "code_execution_20250825" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaServerToolCaller>())?.let {
                            Caller(codeExecution20250825 = it, _json = json)
                        } ?: Caller(_json = json)
                    }
                    "code_execution_20260120" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaServerToolCaller20260120>())
                            ?.let { Caller(codeExecution20260120 = it, _json = json) }
                            ?: Caller(_json = json)
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
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolUseBlock &&
            id == other.id &&
            input == other.input &&
            name == other.name &&
            type == other.type &&
            caller == other.caller &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, input, name, type, caller, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolUseBlock{id=$id, input=$input, name=$name, type=$type, caller=$caller, additionalProperties=$additionalProperties}"
}
