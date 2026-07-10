// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
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

/** Create a Dream */
class DreamCreateParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputs(): List<BetaDreamInput> = body.inputs()

    /**
     * Model identifier and configuration applied to every pipeline stage.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun model(): Model = body.model()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun instructions(): Optional<String> = body.instructions()

    /**
     * Returns the raw JSON value of [inputs].
     *
     * Unlike [inputs], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _inputs(): JsonField<List<BetaDreamInput>> = body._inputs()

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _model(): JsonField<Model> = body._model()

    /**
     * Returns the raw JSON value of [instructions].
     *
     * Unlike [instructions], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _instructions(): JsonField<String> = body._instructions()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [DreamCreateParams].
         *
         * The following fields are required:
         * ```java
         * .inputs()
         * .model()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DreamCreateParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(dreamCreateParams: DreamCreateParams) = apply {
            betas = dreamCreateParams.betas?.toMutableList()
            body = dreamCreateParams.body.toBuilder()
            additionalHeaders = dreamCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = dreamCreateParams.additionalQueryParams.toBuilder()
        }

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
         * - [inputs]
         * - [model]
         * - [instructions]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        fun inputs(inputs: List<BetaDreamInput>) = apply { body.inputs(inputs) }

        /**
         * Sets [Builder.inputs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputs] with a well-typed `List<BetaDreamInput>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun inputs(inputs: JsonField<List<BetaDreamInput>>) = apply { body.inputs(inputs) }

        /**
         * Adds a single [BetaDreamInput] to [inputs].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInput(input: BetaDreamInput) = apply { body.addInput(input) }

        /** Alias for calling [addInput] with `BetaDreamInput.ofMemoryStore(memoryStore)`. */
        fun addInput(memoryStore: BetaDreamMemoryStoreInput) = apply { body.addInput(memoryStore) }

        /**
         * Alias for calling [addInput] with the following:
         * ```java
         * BetaDreamMemoryStoreInput.builder()
         *     .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
         *     .memoryStoreId(memoryStoreId)
         *     .build()
         * ```
         */
        fun addMemoryStoreInput(memoryStoreId: String) = apply {
            body.addMemoryStoreInput(memoryStoreId)
        }

        /** Alias for calling [addInput] with `BetaDreamInput.ofSessions(sessions)`. */
        fun addInput(sessions: BetaDreamSessionsInput) = apply { body.addInput(sessions) }

        /**
         * Alias for calling [addInput] with the following:
         * ```java
         * BetaDreamSessionsInput.builder()
         *     .type(BetaDreamSessionsInput.Type.SESSIONS)
         *     .sessionIds(sessionIds)
         *     .build()
         * ```
         */
        fun addSessionsInput(sessionIds: List<String>) = apply { body.addSessionsInput(sessionIds) }

        /** Model identifier and configuration applied to every pipeline stage. */
        fun model(model: Model) = apply { body.model(model) }

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [Model] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<Model>) = apply { body.model(model) }

        /** Alias for calling [model] with `Model.ofString(string)`. */
        fun model(string: String) = apply { body.model(string) }

        /**
         * Alias for calling [model] with
         * `Model.ofBetaDreamModelConfigParam(betaDreamModelConfigParam)`.
         */
        fun model(betaDreamModelConfigParam: BetaDreamModelConfigParam) = apply {
            body.model(betaDreamModelConfigParam)
        }

        fun instructions(instructions: String?) = apply { body.instructions(instructions) }

        /** Alias for calling [Builder.instructions] with `instructions.orElse(null)`. */
        fun instructions(instructions: Optional<String>) = instructions(instructions.getOrNull())

        /**
         * Sets [Builder.instructions] to an arbitrary JSON value.
         *
         * You should usually call [Builder.instructions] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun instructions(instructions: JsonField<String>) = apply {
            body.instructions(instructions)
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
         * Returns an immutable instance of [DreamCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .inputs()
         * .model()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): DreamCreateParams =
            DreamCreateParams(
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
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val inputs: JsonField<List<BetaDreamInput>>,
        private val model: JsonField<Model>,
        private val instructions: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("inputs")
            @ExcludeMissing
            inputs: JsonField<List<BetaDreamInput>> = JsonMissing.of(),
            @JsonProperty("model") @ExcludeMissing model: JsonField<Model> = JsonMissing.of(),
            @JsonProperty("instructions")
            @ExcludeMissing
            instructions: JsonField<String> = JsonMissing.of(),
        ) : this(inputs, model, instructions, mutableMapOf())

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun inputs(): List<BetaDreamInput> = inputs.getRequired("inputs")

        /**
         * Model identifier and configuration applied to every pipeline stage.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun model(): Model = model.getRequired("model")

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun instructions(): Optional<String> = instructions.getOptional("instructions")

        /**
         * Returns the raw JSON value of [inputs].
         *
         * Unlike [inputs], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("inputs")
        @ExcludeMissing
        fun _inputs(): JsonField<List<BetaDreamInput>> = inputs

        /**
         * Returns the raw JSON value of [model].
         *
         * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

        /**
         * Returns the raw JSON value of [instructions].
         *
         * Unlike [instructions], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("instructions")
        @ExcludeMissing
        fun _instructions(): JsonField<String> = instructions

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
             * .inputs()
             * .model()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var inputs: JsonField<MutableList<BetaDreamInput>>? = null
            private var model: JsonField<Model>? = null
            private var instructions: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                inputs = body.inputs.map { it.toMutableList() }.takeUnless { it.isMissing() }
                model = body.model
                instructions = body.instructions
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            fun inputs(inputs: List<BetaDreamInput>) = inputs(JsonField.of(inputs))

            /**
             * Sets [Builder.inputs] to an arbitrary JSON value.
             *
             * You should usually call [Builder.inputs] with a well-typed `List<BetaDreamInput>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun inputs(inputs: JsonField<List<BetaDreamInput>>) = apply {
                this.inputs = inputs.map { it.toMutableList() }
            }

            /**
             * Adds a single [BetaDreamInput] to [inputs].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addInput(input: BetaDreamInput) = apply {
                inputs =
                    (inputs ?: JsonField.of(mutableListOf())).also {
                        checkKnown("inputs", it).add(input)
                    }
            }

            /** Alias for calling [addInput] with `BetaDreamInput.ofMemoryStore(memoryStore)`. */
            fun addInput(memoryStore: BetaDreamMemoryStoreInput) =
                addInput(BetaDreamInput.ofMemoryStore(memoryStore))

            /**
             * Alias for calling [addInput] with the following:
             * ```java
             * BetaDreamMemoryStoreInput.builder()
             *     .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
             *     .memoryStoreId(memoryStoreId)
             *     .build()
             * ```
             */
            fun addMemoryStoreInput(memoryStoreId: String) =
                addInput(
                    BetaDreamMemoryStoreInput.builder()
                        .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                        .memoryStoreId(memoryStoreId)
                        .build()
                )

            /** Alias for calling [addInput] with `BetaDreamInput.ofSessions(sessions)`. */
            fun addInput(sessions: BetaDreamSessionsInput) =
                addInput(BetaDreamInput.ofSessions(sessions))

            /**
             * Alias for calling [addInput] with the following:
             * ```java
             * BetaDreamSessionsInput.builder()
             *     .type(BetaDreamSessionsInput.Type.SESSIONS)
             *     .sessionIds(sessionIds)
             *     .build()
             * ```
             */
            fun addSessionsInput(sessionIds: List<String>) =
                addInput(
                    BetaDreamSessionsInput.builder()
                        .type(BetaDreamSessionsInput.Type.SESSIONS)
                        .sessionIds(sessionIds)
                        .build()
                )

            /** Model identifier and configuration applied to every pipeline stage. */
            fun model(model: Model) = model(JsonField.of(model))

            /**
             * Sets [Builder.model] to an arbitrary JSON value.
             *
             * You should usually call [Builder.model] with a well-typed [Model] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun model(model: JsonField<Model>) = apply { this.model = model }

            /** Alias for calling [model] with `Model.ofString(string)`. */
            fun model(string: String) = model(Model.ofString(string))

            /**
             * Alias for calling [model] with
             * `Model.ofBetaDreamModelConfigParam(betaDreamModelConfigParam)`.
             */
            fun model(betaDreamModelConfigParam: BetaDreamModelConfigParam) =
                model(Model.ofBetaDreamModelConfigParam(betaDreamModelConfigParam))

            fun instructions(instructions: String?) =
                instructions(JsonField.ofNullable(instructions))

            /** Alias for calling [Builder.instructions] with `instructions.orElse(null)`. */
            fun instructions(instructions: Optional<String>) =
                instructions(instructions.getOrNull())

            /**
             * Sets [Builder.instructions] to an arbitrary JSON value.
             *
             * You should usually call [Builder.instructions] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun instructions(instructions: JsonField<String>) = apply {
                this.instructions = instructions
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
             * .inputs()
             * .model()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("inputs", inputs).map { it.toImmutable() },
                    checkRequired("model", model),
                    instructions,
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

            inputs().forEach { it.validate() }
            model().validate()
            instructions()
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
            (inputs.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (model.asKnown().getOrNull()?.validity() ?: 0) +
                (if (instructions.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                inputs == other.inputs &&
                model == other.model &&
                instructions == other.instructions &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(inputs, model, instructions, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{inputs=$inputs, model=$model, instructions=$instructions, additionalProperties=$additionalProperties}"
    }

    /** Model identifier and configuration applied to every pipeline stage. */
    @JsonDeserialize(using = Model.Deserializer::class)
    @JsonSerialize(using = Model.Serializer::class)
    class Model
    private constructor(
        private val string: String? = null,
        private val betaDreamModelConfigParam: BetaDreamModelConfigParam? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): Optional<String> = Optional.ofNullable(string)

        /** Model identifier and configuration applied to every pipeline stage. */
        fun betaDreamModelConfigParam(): Optional<BetaDreamModelConfigParam> =
            Optional.ofNullable(betaDreamModelConfigParam)

        fun isString(): Boolean = string != null

        fun isBetaDreamModelConfigParam(): Boolean = betaDreamModelConfigParam != null

        fun asString(): String = string.getOrThrow("string")

        /** Model identifier and configuration applied to every pipeline stage. */
        fun asBetaDreamModelConfigParam(): BetaDreamModelConfigParam =
            betaDreamModelConfigParam.getOrThrow("betaDreamModelConfigParam")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = model.accept(new Model.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitString(String string) {
         *         return Optional.of(string.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                string != null -> visitor.visitString(string)
                betaDreamModelConfigParam != null ->
                    visitor.visitBetaDreamModelConfigParam(betaDreamModelConfigParam)
                else -> visitor.unknown(_json)
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
        fun validate(): Model = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitString(string: String) {}

                    override fun visitBetaDreamModelConfigParam(
                        betaDreamModelConfigParam: BetaDreamModelConfigParam
                    ) {
                        betaDreamModelConfigParam.validate()
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
                    override fun visitString(string: String) = 1

                    override fun visitBetaDreamModelConfigParam(
                        betaDreamModelConfigParam: BetaDreamModelConfigParam
                    ) = betaDreamModelConfigParam.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Model &&
                string == other.string &&
                betaDreamModelConfigParam == other.betaDreamModelConfigParam
        }

        override fun hashCode(): Int = Objects.hash(string, betaDreamModelConfigParam)

        override fun toString(): String =
            when {
                string != null -> "Model{string=$string}"
                betaDreamModelConfigParam != null ->
                    "Model{betaDreamModelConfigParam=$betaDreamModelConfigParam}"
                _json != null -> "Model{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Model")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Model(string = string)

            /** Model identifier and configuration applied to every pipeline stage. */
            @JvmStatic
            fun ofBetaDreamModelConfigParam(betaDreamModelConfigParam: BetaDreamModelConfigParam) =
                Model(betaDreamModelConfigParam = betaDreamModelConfigParam)
        }

        /** An interface that defines how to map each variant of [Model] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitString(string: String): T

            /** Model identifier and configuration applied to every pipeline stage. */
            fun visitBetaDreamModelConfigParam(
                betaDreamModelConfigParam: BetaDreamModelConfigParam
            ): T

            /**
             * Maps an unknown variant of [Model] to a value of type [T].
             *
             * An instance of [Model] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Model: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Model>(Model::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Model {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<BetaDreamModelConfigParam>())?.let {
                                Model(betaDreamModelConfigParam = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                Model(string = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Model(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Model>(Model::class) {

            override fun serialize(
                value: Model,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.string != null -> generator.writeObject(value.string)
                    value.betaDreamModelConfigParam != null ->
                        generator.writeObject(value.betaDreamModelConfigParam)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Model")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DreamCreateParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "DreamCreateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
