package com.anthropic.models.beta.agents

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
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
 * Which sources contribute URLs the web_fetch tool may fetch. When web_fetch is limited to URLs the
 * conversation has already shown the model (in a user message, a custom tool's result, or an
 * earlier web_search or web_fetch result), each key narrows one of those sources and defaults to
 * "all". Setting all three keys to "none" is rejected.
 */
class BetaManagedAgentsWebFetchUrlSourcesParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val clientToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>,
    private val serverToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>,
    private val userInput: JsonField<BetaManagedAgentsWebFetchUrlSourceUserInputParams>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("client_tool_results")
        @ExcludeMissing
        clientToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams> =
            JsonMissing.of(),
        @JsonProperty("server_tool_results")
        @ExcludeMissing
        serverToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams> =
            JsonMissing.of(),
        @JsonProperty("user_input")
        @ExcludeMissing
        userInput: JsonField<BetaManagedAgentsWebFetchUrlSourceUserInputParams> = JsonMissing.of(),
    ) : this(clientToolResults, serverToolResults, userInput, mutableMapOf())

    /**
     * Which custom tools' results contribute URLs that may be fetched: "all" (the default), "none",
     * or an only or except list. Each name in a list must be a custom tool in the same tools array.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clientToolResults(): Optional<BetaManagedAgentsWebFetchUrlSourceToolFilterParams> =
        clientToolResults.getOptional("client_tool_results")

    /**
     * Which of the web_search and web_fetch tools' results contribute URLs that may be fetched:
     * "all" (the default), "none", or an only or except list. Each name in a list must be
     * "web_search" or "web_fetch".
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun serverToolResults(): Optional<BetaManagedAgentsWebFetchUrlSourceToolFilterParams> =
        serverToolResults.getOptional("server_tool_results")

    /**
     * Whether URLs in the text of user messages may be fetched: "all" (the default) or "none".
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userInput(): Optional<BetaManagedAgentsWebFetchUrlSourceUserInputParams> =
        userInput.getOptional("user_input")

    /**
     * Returns the raw JSON value of [clientToolResults].
     *
     * Unlike [clientToolResults], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("client_tool_results")
    @ExcludeMissing
    fun _clientToolResults(): JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams> =
        clientToolResults

    /**
     * Returns the raw JSON value of [serverToolResults].
     *
     * Unlike [serverToolResults], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("server_tool_results")
    @ExcludeMissing
    fun _serverToolResults(): JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams> =
        serverToolResults

    /**
     * Returns the raw JSON value of [userInput].
     *
     * Unlike [userInput], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_input")
    @ExcludeMissing
    fun _userInput(): JsonField<BetaManagedAgentsWebFetchUrlSourceUserInputParams> = userInput

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
         * [BetaManagedAgentsWebFetchUrlSourcesParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsWebFetchUrlSourcesParams]. */
    class Builder internal constructor() {

        private var clientToolResults:
            JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams> =
            JsonMissing.of()
        private var serverToolResults:
            JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams> =
            JsonMissing.of()
        private var userInput: JsonField<BetaManagedAgentsWebFetchUrlSourceUserInputParams> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWebFetchUrlSourcesParams: BetaManagedAgentsWebFetchUrlSourcesParams
        ) = apply {
            clientToolResults = betaManagedAgentsWebFetchUrlSourcesParams.clientToolResults
            serverToolResults = betaManagedAgentsWebFetchUrlSourcesParams.serverToolResults
            userInput = betaManagedAgentsWebFetchUrlSourcesParams.userInput
            additionalProperties =
                betaManagedAgentsWebFetchUrlSourcesParams.additionalProperties.toMutableMap()
        }

        /**
         * Which custom tools' results contribute URLs that may be fetched: "all" (the default),
         * "none", or an only or except list. Each name in a list must be a custom tool in the same
         * tools array.
         */
        fun clientToolResults(
            clientToolResults: BetaManagedAgentsWebFetchUrlSourceToolFilterParams?
        ) = clientToolResults(JsonField.ofNullable(clientToolResults))

        /** Alias for calling [Builder.clientToolResults] with `clientToolResults.orElse(null)`. */
        fun clientToolResults(
            clientToolResults: Optional<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>
        ) = clientToolResults(clientToolResults.getOrNull())

        /**
         * Sets [Builder.clientToolResults] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clientToolResults] with a well-typed
         * [BetaManagedAgentsWebFetchUrlSourceToolFilterParams] value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun clientToolResults(
            clientToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>
        ) = apply { this.clientToolResults = clientToolResults }

        /**
         * Alias for calling [clientToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofShorthand(shorthand)`.
         */
        fun clientToolResults(shorthand: BetaManagedAgentsWebFetchUrlSourceShorthand) =
            clientToolResults(
                BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofShorthand(shorthand)
            )

        /**
         * Alias for calling [clientToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofBetaManagedAgentsWebFetchUrlSourceToolFilter(betaManagedAgentsWebFetchUrlSourceToolFilter)`.
         */
        fun clientToolResults(
            betaManagedAgentsWebFetchUrlSourceToolFilter:
                BetaManagedAgentsWebFetchUrlSourceToolFilter
        ) =
            clientToolResults(
                BetaManagedAgentsWebFetchUrlSourceToolFilterParams
                    .ofBetaManagedAgentsWebFetchUrlSourceToolFilter(
                        betaManagedAgentsWebFetchUrlSourceToolFilter
                    )
            )

        /**
         * Which of the web_search and web_fetch tools' results contribute URLs that may be fetched:
         * "all" (the default), "none", or an only or except list. Each name in a list must be
         * "web_search" or "web_fetch".
         */
        fun serverToolResults(
            serverToolResults: BetaManagedAgentsWebFetchUrlSourceToolFilterParams?
        ) = serverToolResults(JsonField.ofNullable(serverToolResults))

        /** Alias for calling [Builder.serverToolResults] with `serverToolResults.orElse(null)`. */
        fun serverToolResults(
            serverToolResults: Optional<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>
        ) = serverToolResults(serverToolResults.getOrNull())

        /**
         * Sets [Builder.serverToolResults] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serverToolResults] with a well-typed
         * [BetaManagedAgentsWebFetchUrlSourceToolFilterParams] value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun serverToolResults(
            serverToolResults: JsonField<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>
        ) = apply { this.serverToolResults = serverToolResults }

        /**
         * Alias for calling [serverToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofShorthand(shorthand)`.
         */
        fun serverToolResults(shorthand: BetaManagedAgentsWebFetchUrlSourceShorthand) =
            serverToolResults(
                BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofShorthand(shorthand)
            )

        /**
         * Alias for calling [serverToolResults] with
         * `BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofBetaManagedAgentsWebFetchUrlSourceToolFilter(betaManagedAgentsWebFetchUrlSourceToolFilter)`.
         */
        fun serverToolResults(
            betaManagedAgentsWebFetchUrlSourceToolFilter:
                BetaManagedAgentsWebFetchUrlSourceToolFilter
        ) =
            serverToolResults(
                BetaManagedAgentsWebFetchUrlSourceToolFilterParams
                    .ofBetaManagedAgentsWebFetchUrlSourceToolFilter(
                        betaManagedAgentsWebFetchUrlSourceToolFilter
                    )
            )

        /**
         * Whether URLs in the text of user messages may be fetched: "all" (the default) or "none".
         */
        fun userInput(userInput: BetaManagedAgentsWebFetchUrlSourceUserInputParams?) =
            userInput(JsonField.ofNullable(userInput))

        /** Alias for calling [Builder.userInput] with `userInput.orElse(null)`. */
        fun userInput(userInput: Optional<BetaManagedAgentsWebFetchUrlSourceUserInputParams>) =
            userInput(userInput.getOrNull())

        /**
         * Sets [Builder.userInput] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userInput] with a well-typed
         * [BetaManagedAgentsWebFetchUrlSourceUserInputParams] value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun userInput(userInput: JsonField<BetaManagedAgentsWebFetchUrlSourceUserInputParams>) =
            apply {
                this.userInput = userInput
            }

        /**
         * Alias for calling [userInput] with
         * `BetaManagedAgentsWebFetchUrlSourceUserInputParams.ofShorthand(shorthand)`.
         */
        fun userInput(shorthand: BetaManagedAgentsWebFetchUrlSourceShorthand) =
            userInput(BetaManagedAgentsWebFetchUrlSourceUserInputParams.ofShorthand(shorthand))

        /**
         * Alias for calling [userInput] with
         * `BetaManagedAgentsWebFetchUrlSourceUserInputParams.ofBetaManagedAgentsWebFetchUrlSourceUserInput(betaManagedAgentsWebFetchUrlSourceUserInput)`.
         */
        fun userInput(
            betaManagedAgentsWebFetchUrlSourceUserInput: BetaManagedAgentsWebFetchUrlSourceUserInput
        ) =
            userInput(
                BetaManagedAgentsWebFetchUrlSourceUserInputParams
                    .ofBetaManagedAgentsWebFetchUrlSourceUserInput(
                        betaManagedAgentsWebFetchUrlSourceUserInput
                    )
            )

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
         * Returns an immutable instance of [BetaManagedAgentsWebFetchUrlSourcesParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsWebFetchUrlSourcesParams =
            BetaManagedAgentsWebFetchUrlSourcesParams(
                clientToolResults,
                serverToolResults,
                userInput,
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
    fun validate(): BetaManagedAgentsWebFetchUrlSourcesParams = apply {
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

        return other is BetaManagedAgentsWebFetchUrlSourcesParams &&
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
        "BetaManagedAgentsWebFetchUrlSourcesParams{clientToolResults=$clientToolResults, serverToolResults=$serverToolResults, userInput=$userInput, additionalProperties=$additionalProperties}"
}
