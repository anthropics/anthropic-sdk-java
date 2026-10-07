package com.anthropic.models.beta.agents

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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Which sources contribute URLs the web_fetch tool may fetch. A key that is null was not set and
 * allows every URL from that source.
 */
class BetaManagedAgentsWebFetchUrlSources
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val clientToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter>,
    private val serverToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter>,
    private val userInput: JsonField<BetaManagedAgentsWebFetchUrlSourceUserInput>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("client_tool_results")
        @ExcludeMissing
        clientToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter> =
            JsonMissing.of(),
        @JsonProperty("server_tool_results")
        @ExcludeMissing
        serverToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter> =
            JsonMissing.of(),
        @JsonProperty("user_input")
        @ExcludeMissing
        userInput: JsonField<BetaManagedAgentsWebFetchUrlSourceUserInput> = JsonMissing.of(),
    ) : this(clientToolResults, serverToolResults, userInput, mutableMapOf())

    /**
     * Which custom tools' results contribute URLs that may be fetched. Null when not set, which
     * allows every custom tool's results.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clientToolResults(): Optional<BetaManagedAgentsWebFetchUrlSourceToolFilter> =
        clientToolResults.getOptional("client_tool_results")

    /**
     * Which of the web_search and web_fetch tools' results contribute URLs that may be fetched.
     * Null when not set, which allows both.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun serverToolResults(): Optional<BetaManagedAgentsWebFetchUrlSourceToolFilter> =
        serverToolResults.getOptional("server_tool_results")

    /**
     * Whether URLs in the text of user messages may be fetched. Null when not set, which allows
     * them.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userInput(): Optional<BetaManagedAgentsWebFetchUrlSourceUserInput> =
        userInput.getOptional("user_input")

    /**
     * Returns the raw JSON value of [clientToolResults].
     *
     * Unlike [clientToolResults], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("client_tool_results")
    @ExcludeMissing
    fun _clientToolResults(): JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter> =
        clientToolResults

    /**
     * Returns the raw JSON value of [serverToolResults].
     *
     * Unlike [serverToolResults], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("server_tool_results")
    @ExcludeMissing
    fun _serverToolResults(): JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter> =
        serverToolResults

    /**
     * Returns the raw JSON value of [userInput].
     *
     * Unlike [userInput], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_input")
    @ExcludeMissing
    fun _userInput(): JsonField<BetaManagedAgentsWebFetchUrlSourceUserInput> = userInput

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
         * [BetaManagedAgentsWebFetchUrlSources].
         *
         * The following fields are required:
         * ```java
         * .clientToolResults()
         * .serverToolResults()
         * .userInput()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsWebFetchUrlSources]. */
    class Builder internal constructor() {

        private var clientToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter>? =
            null
        private var serverToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter>? =
            null
        private var userInput: JsonField<BetaManagedAgentsWebFetchUrlSourceUserInput>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWebFetchUrlSources: BetaManagedAgentsWebFetchUrlSources
        ) = apply {
            clientToolResults = betaManagedAgentsWebFetchUrlSources.clientToolResults
            serverToolResults = betaManagedAgentsWebFetchUrlSources.serverToolResults
            userInput = betaManagedAgentsWebFetchUrlSources.userInput
            additionalProperties =
                betaManagedAgentsWebFetchUrlSources.additionalProperties.toMutableMap()
        }

        /**
         * Which custom tools' results contribute URLs that may be fetched. Null when not set, which
         * allows every custom tool's results.
         */
        fun clientToolResults(clientToolResults: BetaManagedAgentsWebFetchUrlSourceToolFilter?) =
            clientToolResults(JsonField.ofNullable(clientToolResults))

        /** Alias for calling [Builder.clientToolResults] with `clientToolResults.orElse(null)`. */
        fun clientToolResults(
            clientToolResults: Optional<BetaManagedAgentsWebFetchUrlSourceToolFilter>
        ) = clientToolResults(clientToolResults.getOrNull())

        /**
         * Sets [Builder.clientToolResults] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clientToolResults] with a well-typed
         * [BetaManagedAgentsWebFetchUrlSourceToolFilter] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun clientToolResults(
            clientToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter>
        ) = apply { this.clientToolResults = clientToolResults }

        /**
         * Alias for calling [clientToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(all)`.
         */
        fun clientToolResults(all: BetaManagedAgentsWebFetchUrlSourceAll) =
            clientToolResults(BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(all))

        /**
         * Alias for calling [clientToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilter.ofNone(none)`.
         */
        fun clientToolResults(none: BetaManagedAgentsWebFetchUrlSourceNone) =
            clientToolResults(BetaManagedAgentsWebFetchUrlSourceToolFilter.ofNone(none))

        /**
         * Alias for calling [clientToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilter.ofOnly(only)`.
         */
        fun clientToolResults(only: BetaManagedAgentsWebFetchUrlSourceOnly) =
            clientToolResults(BetaManagedAgentsWebFetchUrlSourceToolFilter.ofOnly(only))

        /**
         * Alias for calling [clientToolResults] with the following:
         * ```java
         * BetaManagedAgentsWebFetchUrlSourceOnly.builder()
         *     .tools(tools)
         *     .build()
         * ```
         */
        fun onlyClientToolResults(tools: List<BetaManagedAgentsWebFetchUrlSourceToolReference>) =
            clientToolResults(BetaManagedAgentsWebFetchUrlSourceOnly.builder().tools(tools).build())

        /**
         * Alias for calling [clientToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilter.ofExcept(except)`.
         */
        fun clientToolResults(except: BetaManagedAgentsWebFetchUrlSourceExcept) =
            clientToolResults(BetaManagedAgentsWebFetchUrlSourceToolFilter.ofExcept(except))

        /**
         * Alias for calling [clientToolResults] with the following:
         * ```java
         * BetaManagedAgentsWebFetchUrlSourceExcept.builder()
         *     .tools(tools)
         *     .build()
         * ```
         */
        fun exceptClientToolResults(tools: List<BetaManagedAgentsWebFetchUrlSourceToolReference>) =
            clientToolResults(
                BetaManagedAgentsWebFetchUrlSourceExcept.builder().tools(tools).build()
            )

        /**
         * Which of the web_search and web_fetch tools' results contribute URLs that may be fetched.
         * Null when not set, which allows both.
         */
        fun serverToolResults(serverToolResults: BetaManagedAgentsWebFetchUrlSourceToolFilter?) =
            serverToolResults(JsonField.ofNullable(serverToolResults))

        /** Alias for calling [Builder.serverToolResults] with `serverToolResults.orElse(null)`. */
        fun serverToolResults(
            serverToolResults: Optional<BetaManagedAgentsWebFetchUrlSourceToolFilter>
        ) = serverToolResults(serverToolResults.getOrNull())

        /**
         * Sets [Builder.serverToolResults] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serverToolResults] with a well-typed
         * [BetaManagedAgentsWebFetchUrlSourceToolFilter] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun serverToolResults(
            serverToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilter>
        ) = apply { this.serverToolResults = serverToolResults }

        /**
         * Alias for calling [serverToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(all)`.
         */
        fun serverToolResults(all: BetaManagedAgentsWebFetchUrlSourceAll) =
            serverToolResults(BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(all))

        /**
         * Alias for calling [serverToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilter.ofNone(none)`.
         */
        fun serverToolResults(none: BetaManagedAgentsWebFetchUrlSourceNone) =
            serverToolResults(BetaManagedAgentsWebFetchUrlSourceToolFilter.ofNone(none))

        /**
         * Alias for calling [serverToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilter.ofOnly(only)`.
         */
        fun serverToolResults(only: BetaManagedAgentsWebFetchUrlSourceOnly) =
            serverToolResults(BetaManagedAgentsWebFetchUrlSourceToolFilter.ofOnly(only))

        /**
         * Alias for calling [serverToolResults] with the following:
         * ```java
         * BetaManagedAgentsWebFetchUrlSourceOnly.builder()
         *     .tools(tools)
         *     .build()
         * ```
         */
        fun onlyServerToolResults(tools: List<BetaManagedAgentsWebFetchUrlSourceToolReference>) =
            serverToolResults(BetaManagedAgentsWebFetchUrlSourceOnly.builder().tools(tools).build())

        /**
         * Alias for calling [serverToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilter.ofExcept(except)`.
         */
        fun serverToolResults(except: BetaManagedAgentsWebFetchUrlSourceExcept) =
            serverToolResults(BetaManagedAgentsWebFetchUrlSourceToolFilter.ofExcept(except))

        /**
         * Alias for calling [serverToolResults] with the following:
         * ```java
         * BetaManagedAgentsWebFetchUrlSourceExcept.builder()
         *     .tools(tools)
         *     .build()
         * ```
         */
        fun exceptServerToolResults(tools: List<BetaManagedAgentsWebFetchUrlSourceToolReference>) =
            serverToolResults(
                BetaManagedAgentsWebFetchUrlSourceExcept.builder().tools(tools).build()
            )

        /**
         * Whether URLs in the text of user messages may be fetched. Null when not set, which allows
         * them.
         */
        fun userInput(userInput: BetaManagedAgentsWebFetchUrlSourceUserInput?) =
            userInput(JsonField.ofNullable(userInput))

        /** Alias for calling [Builder.userInput] with `userInput.orElse(null)`. */
        fun userInput(userInput: Optional<BetaManagedAgentsWebFetchUrlSourceUserInput>) =
            userInput(userInput.getOrNull())

        /**
         * Sets [Builder.userInput] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userInput] with a well-typed
         * [BetaManagedAgentsWebFetchUrlSourceUserInput] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun userInput(userInput: JsonField<BetaManagedAgentsWebFetchUrlSourceUserInput>) = apply {
            this.userInput = userInput
        }

        /**
         * Alias for calling [userInput] with
         * `BetaManagedAgentsWebFetchUrlSourceUserInput.ofAll(all)`.
         */
        fun userInput(all: BetaManagedAgentsWebFetchUrlSourceAll) =
            userInput(BetaManagedAgentsWebFetchUrlSourceUserInput.ofAll(all))

        /**
         * Alias for calling [userInput] with
         * `BetaManagedAgentsWebFetchUrlSourceUserInput.ofNone(none)`.
         */
        fun userInput(none: BetaManagedAgentsWebFetchUrlSourceNone) =
            userInput(BetaManagedAgentsWebFetchUrlSourceUserInput.ofNone(none))

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
         * Returns an immutable instance of [BetaManagedAgentsWebFetchUrlSources].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .clientToolResults()
         * .serverToolResults()
         * .userInput()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsWebFetchUrlSources =
            BetaManagedAgentsWebFetchUrlSources(
                checkRequired("clientToolResults", clientToolResults),
                checkRequired("serverToolResults", serverToolResults),
                checkRequired("userInput", userInput),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaManagedAgentsWebFetchUrlSources = apply {
        if (validated) {
            return@apply
        }

        clientToolResults().ifPresent { it.validate() }
        serverToolResults().ifPresent { it.validate() }
        userInput().ifPresent { it.validate() }
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
        (clientToolResults.asKnown().getOrNull()?.validity() ?: 0) +
            (serverToolResults.asKnown().getOrNull()?.validity() ?: 0) +
            (userInput.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWebFetchUrlSources &&
            clientToolResults == other.clientToolResults &&
            serverToolResults == other.serverToolResults &&
            userInput == other.userInput &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(clientToolResults, serverToolResults, userInput, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsWebFetchUrlSources{clientToolResults=$clientToolResults, serverToolResults=$serverToolResults, userInput=$userInput, additionalProperties=$additionalProperties}"
}
