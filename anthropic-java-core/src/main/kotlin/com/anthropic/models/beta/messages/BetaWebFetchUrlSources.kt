package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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

/**
 * Which sources contribute to the set of URLs web fetch may fetch.
 *
 * Each key is a tagged variant: ``user_input`` is ``all`` or ``none``; the two tool filters are
 * ``all``, ``none``, ``only`` (only the named tools' results) or ``except`` (every result but the
 * named tools'). A named tool must be declared in this request's ``tools[]``.
 */
class BetaWebFetchUrlSources
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val clientToolResults: JsonField<ClientToolResults>,
    private val serverToolResults: JsonField<ServerToolResults>,
    private val userInput: JsonField<UserInput>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("client_tool_results")
        @ExcludeMissing
        clientToolResults: JsonField<ClientToolResults> = JsonMissing.of(),
        @JsonProperty("server_tool_results")
        @ExcludeMissing
        serverToolResults: JsonField<ServerToolResults> = JsonMissing.of(),
        @JsonProperty("user_input")
        @ExcludeMissing
        userInput: JsonField<UserInput> = JsonMissing.of(),
    ) : this(clientToolResults, serverToolResults, userInput, mutableMapOf())

    /**
     * Which client tools' results contribute fetchable URLs: "all", "none", or an only or except
     * list of client tool names from tools[].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clientToolResults(): Optional<ClientToolResults> =
        clientToolResults.getOptional("client_tool_results")

    /**
     * Which server tools' results contribute fetchable URLs: "all", "none", or an only or except
     * list of server tool names from tools[]; only web_search and web_fetch results ever
     * contribute.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun serverToolResults(): Optional<ServerToolResults> =
        serverToolResults.getOptional("server_tool_results")

    /**
     * Whether URLs in user messages are fetchable: "all" or "none".
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userInput(): Optional<UserInput> = userInput.getOptional("user_input")

    /**
     * Returns the raw JSON value of [clientToolResults].
     *
     * Unlike [clientToolResults], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("client_tool_results")
    @ExcludeMissing
    fun _clientToolResults(): JsonField<ClientToolResults> = clientToolResults

    /**
     * Returns the raw JSON value of [serverToolResults].
     *
     * Unlike [serverToolResults], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("server_tool_results")
    @ExcludeMissing
    fun _serverToolResults(): JsonField<ServerToolResults> = serverToolResults

    /**
     * Returns the raw JSON value of [userInput].
     *
     * Unlike [userInput], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_input") @ExcludeMissing fun _userInput(): JsonField<UserInput> = userInput

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

        /** Returns a mutable builder for constructing an instance of [BetaWebFetchUrlSources]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWebFetchUrlSources]. */
    class Builder internal constructor() {

        private var clientToolResults: JsonField<ClientToolResults> = JsonMissing.of()
        private var serverToolResults: JsonField<ServerToolResults> = JsonMissing.of()
        private var userInput: JsonField<UserInput> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWebFetchUrlSources: BetaWebFetchUrlSources) = apply {
            clientToolResults = betaWebFetchUrlSources.clientToolResults
            serverToolResults = betaWebFetchUrlSources.serverToolResults
            userInput = betaWebFetchUrlSources.userInput
            additionalProperties = betaWebFetchUrlSources.additionalProperties.toMutableMap()
        }

        /**
         * Which client tools' results contribute fetchable URLs: "all", "none", or an only or
         * except list of client tool names from tools[].
         */
        fun clientToolResults(clientToolResults: ClientToolResults) =
            clientToolResults(JsonField.of(clientToolResults))

        /**
         * Sets [Builder.clientToolResults] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clientToolResults] with a well-typed [ClientToolResults]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun clientToolResults(clientToolResults: JsonField<ClientToolResults>) = apply {
            this.clientToolResults = clientToolResults
        }

        /** Alias for calling [clientToolResults] with `ClientToolResults.ofAll(all)`. */
        fun clientToolResults(all: BetaWebFetchUrlSourceAll) =
            clientToolResults(ClientToolResults.ofAll(all))

        /** Alias for calling [clientToolResults] with `ClientToolResults.ofNone(none)`. */
        fun clientToolResults(none: BetaWebFetchUrlSourceNone) =
            clientToolResults(ClientToolResults.ofNone(none))

        /** Alias for calling [clientToolResults] with `ClientToolResults.ofOnly(only)`. */
        fun clientToolResults(only: BetaWebFetchUrlSourceOnly) =
            clientToolResults(ClientToolResults.ofOnly(only))

        /**
         * Alias for calling [clientToolResults] with the following:
         * ```java
         * BetaWebFetchUrlSourceOnly.builder()
         *     .tools(tools)
         *     .build()
         * ```
         */
        fun onlyClientToolResults(tools: List<BetaWebFetchUrlSourceToolReference>) =
            clientToolResults(BetaWebFetchUrlSourceOnly.builder().tools(tools).build())

        /** Alias for calling [clientToolResults] with `ClientToolResults.ofExcept(except)`. */
        fun clientToolResults(except: BetaWebFetchUrlSourceExcept) =
            clientToolResults(ClientToolResults.ofExcept(except))

        /**
         * Alias for calling [clientToolResults] with the following:
         * ```java
         * BetaWebFetchUrlSourceExcept.builder()
         *     .tools(tools)
         *     .build()
         * ```
         */
        fun exceptClientToolResults(tools: List<BetaWebFetchUrlSourceToolReference>) =
            clientToolResults(BetaWebFetchUrlSourceExcept.builder().tools(tools).build())

        /**
         * Which server tools' results contribute fetchable URLs: "all", "none", or an only or
         * except list of server tool names from tools[]; only web_search and web_fetch results ever
         * contribute.
         */
        fun serverToolResults(serverToolResults: ServerToolResults) =
            serverToolResults(JsonField.of(serverToolResults))

        /**
         * Sets [Builder.serverToolResults] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serverToolResults] with a well-typed [ServerToolResults]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun serverToolResults(serverToolResults: JsonField<ServerToolResults>) = apply {
            this.serverToolResults = serverToolResults
        }

        /** Alias for calling [serverToolResults] with `ServerToolResults.ofAll(all)`. */
        fun serverToolResults(all: BetaWebFetchUrlSourceAll) =
            serverToolResults(ServerToolResults.ofAll(all))

        /** Alias for calling [serverToolResults] with `ServerToolResults.ofNone(none)`. */
        fun serverToolResults(none: BetaWebFetchUrlSourceNone) =
            serverToolResults(ServerToolResults.ofNone(none))

        /** Alias for calling [serverToolResults] with `ServerToolResults.ofOnly(only)`. */
        fun serverToolResults(only: BetaWebFetchUrlSourceOnly) =
            serverToolResults(ServerToolResults.ofOnly(only))

        /**
         * Alias for calling [serverToolResults] with the following:
         * ```java
         * BetaWebFetchUrlSourceOnly.builder()
         *     .tools(tools)
         *     .build()
         * ```
         */
        fun onlyServerToolResults(tools: List<BetaWebFetchUrlSourceToolReference>) =
            serverToolResults(BetaWebFetchUrlSourceOnly.builder().tools(tools).build())

        /** Alias for calling [serverToolResults] with `ServerToolResults.ofExcept(except)`. */
        fun serverToolResults(except: BetaWebFetchUrlSourceExcept) =
            serverToolResults(ServerToolResults.ofExcept(except))

        /**
         * Alias for calling [serverToolResults] with the following:
         * ```java
         * BetaWebFetchUrlSourceExcept.builder()
         *     .tools(tools)
         *     .build()
         * ```
         */
        fun exceptServerToolResults(tools: List<BetaWebFetchUrlSourceToolReference>) =
            serverToolResults(BetaWebFetchUrlSourceExcept.builder().tools(tools).build())

        /** Whether URLs in user messages are fetchable: "all" or "none". */
        fun userInput(userInput: UserInput) = userInput(JsonField.of(userInput))

        /**
         * Sets [Builder.userInput] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userInput] with a well-typed [UserInput] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun userInput(userInput: JsonField<UserInput>) = apply { this.userInput = userInput }

        /** Alias for calling [userInput] with `UserInput.ofAll(all)`. */
        fun userInput(all: BetaWebFetchUrlSourceAll) = userInput(UserInput.ofAll(all))

        /** Alias for calling [userInput] with `UserInput.ofNone(none)`. */
        fun userInput(none: BetaWebFetchUrlSourceNone) = userInput(UserInput.ofNone(none))

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
         * Returns an immutable instance of [BetaWebFetchUrlSources].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaWebFetchUrlSources =
            BetaWebFetchUrlSources(
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
    fun validate(): BetaWebFetchUrlSources = apply {
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

    /**
     * Which client tools' results contribute fetchable URLs: "all", "none", or an only or except
     * list of client tool names from tools[].
     */
    @JsonDeserialize(using = ClientToolResults.Deserializer::class)
    @JsonSerialize(using = ClientToolResults.Serializer::class)
    class ClientToolResults
    private constructor(
        private val all: BetaWebFetchUrlSourceAll? = null,
        private val none: BetaWebFetchUrlSourceNone? = null,
        private val only: BetaWebFetchUrlSourceOnly? = null,
        private val except: BetaWebFetchUrlSourceExcept? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitAll(all: BetaWebFetchUrlSourceAll): Type = Type.ALL

                    override fun visitNone(none: BetaWebFetchUrlSourceNone): Type = Type.NONE

                    override fun visitOnly(only: BetaWebFetchUrlSourceOnly): Type = Type.ONLY

                    override fun visitExcept(except: BetaWebFetchUrlSourceExcept): Type =
                        Type.EXCEPT

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun tools(): Optional<List<BetaWebFetchUrlSourceToolReference>> =
            accept(
                object : Visitor<Optional<List<BetaWebFetchUrlSourceToolReference>>> {
                    override fun visitAll(
                        all: BetaWebFetchUrlSourceAll
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> = Optional.empty()

                    override fun visitNone(
                        none: BetaWebFetchUrlSourceNone
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> = Optional.empty()

                    override fun visitOnly(
                        only: BetaWebFetchUrlSourceOnly
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> =
                        Optional.of(only.tools())

                    override fun visitExcept(
                        except: BetaWebFetchUrlSourceExcept
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> =
                        Optional.of(except.tools())

                    override fun unknown(
                        json: JsonValue?
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> =
                        json
                            .getProperty<List<BetaWebFetchUrlSourceToolReference>>("tools")
                            .asKnown()
                }
            )

        /**
         * The ``url_sources`` variant under which a source contributes in full: every result of the
         * tool filter's source, or all user input.
         */
        fun all(): Optional<BetaWebFetchUrlSourceAll> = Optional.ofNullable(all)

        /**
         * The ``url_sources`` variant under which a source contributes nothing: no result of the
         * tool filter's source, or no user input.
         */
        fun none(): Optional<BetaWebFetchUrlSourceNone> = Optional.ofNullable(none)

        /** The tool filter variant under which only the named tools' results contribute. */
        fun only(): Optional<BetaWebFetchUrlSourceOnly> = Optional.ofNullable(only)

        /** The tool filter variant under which every result but the named tools' contributes. */
        fun except(): Optional<BetaWebFetchUrlSourceExcept> = Optional.ofNullable(except)

        fun isAll(): Boolean = all != null

        fun isNone(): Boolean = none != null

        fun isOnly(): Boolean = only != null

        fun isExcept(): Boolean = except != null

        /**
         * The ``url_sources`` variant under which a source contributes in full: every result of the
         * tool filter's source, or all user input.
         */
        fun asAll(): BetaWebFetchUrlSourceAll = all.getOrThrow("all")

        /**
         * The ``url_sources`` variant under which a source contributes nothing: no result of the
         * tool filter's source, or no user input.
         */
        fun asNone(): BetaWebFetchUrlSourceNone = none.getOrThrow("none")

        /** The tool filter variant under which only the named tools' results contribute. */
        fun asOnly(): BetaWebFetchUrlSourceOnly = only.getOrThrow("only")

        /** The tool filter variant under which every result but the named tools' contributes. */
        fun asExcept(): BetaWebFetchUrlSourceExcept = except.getOrThrow("except")

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
         * Optional<String> result = clientToolResults.accept(new ClientToolResults.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAll(BetaWebFetchUrlSourceAll all) {
         *         return Optional.of(all.toString());
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
                all != null -> visitor.visitAll(all)
                none != null -> visitor.visitNone(none)
                only != null -> visitor.visitOnly(only)
                except != null -> visitor.visitExcept(except)
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
        fun validate(): ClientToolResults = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAll(all: BetaWebFetchUrlSourceAll) {
                        all.validate()
                    }

                    override fun visitNone(none: BetaWebFetchUrlSourceNone) {
                        none.validate()
                    }

                    override fun visitOnly(only: BetaWebFetchUrlSourceOnly) {
                        only.validate()
                    }

                    override fun visitExcept(except: BetaWebFetchUrlSourceExcept) {
                        except.validate()
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
                    override fun visitAll(all: BetaWebFetchUrlSourceAll) = all.validity()

                    override fun visitNone(none: BetaWebFetchUrlSourceNone) = none.validity()

                    override fun visitOnly(only: BetaWebFetchUrlSourceOnly) = only.validity()

                    override fun visitExcept(except: BetaWebFetchUrlSourceExcept) =
                        except.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ClientToolResults &&
                all == other.all &&
                none == other.none &&
                only == other.only &&
                except == other.except
        }

        override fun hashCode(): Int = Objects.hash(all, none, only, except)

        override fun toString(): String =
            when {
                all != null -> "ClientToolResults{all=$all}"
                none != null -> "ClientToolResults{none=$none}"
                only != null -> "ClientToolResults{only=$only}"
                except != null -> "ClientToolResults{except=$except}"
                _json != null -> "ClientToolResults{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ClientToolResults")
            }

        companion object {

            /**
             * The ``url_sources`` variant under which a source contributes in full: every result of
             * the tool filter's source, or all user input.
             */
            @JvmStatic fun ofAll(all: BetaWebFetchUrlSourceAll) = ClientToolResults(all = all)

            /**
             * The ``url_sources`` variant under which a source contributes nothing: no result of
             * the tool filter's source, or no user input.
             */
            @JvmStatic fun ofNone(none: BetaWebFetchUrlSourceNone) = ClientToolResults(none = none)

            /** The tool filter variant under which only the named tools' results contribute. */
            @JvmStatic fun ofOnly(only: BetaWebFetchUrlSourceOnly) = ClientToolResults(only = only)

            /**
             * Returns an immutable instance of [ClientToolResults] whose [ofOnly] variant is built
             * from the given required [tools].
             */
            @JvmStatic
            fun ofOnly(tools: List<BetaWebFetchUrlSourceToolReference>) =
                ofOnly(BetaWebFetchUrlSourceOnly.of(tools))

            /**
             * The tool filter variant under which every result but the named tools' contributes.
             */
            @JvmStatic
            fun ofExcept(except: BetaWebFetchUrlSourceExcept) = ClientToolResults(except = except)

            /**
             * Returns an immutable instance of [ClientToolResults] whose [ofExcept] variant is
             * built from the given required [tools].
             */
            @JvmStatic
            fun ofExcept(tools: List<BetaWebFetchUrlSourceToolReference>) =
                ofExcept(BetaWebFetchUrlSourceExcept.of(tools))
        }

        /**
         * An interface that defines how to map each variant of [ClientToolResults] to a value of
         * type [T].
         */
        interface Visitor<out T> {

            /**
             * The ``url_sources`` variant under which a source contributes in full: every result of
             * the tool filter's source, or all user input.
             */
            fun visitAll(all: BetaWebFetchUrlSourceAll): T

            /**
             * The ``url_sources`` variant under which a source contributes nothing: no result of
             * the tool filter's source, or no user input.
             */
            fun visitNone(none: BetaWebFetchUrlSourceNone): T

            /** The tool filter variant under which only the named tools' results contribute. */
            fun visitOnly(only: BetaWebFetchUrlSourceOnly): T

            /**
             * The tool filter variant under which every result but the named tools' contributes.
             */
            fun visitExcept(except: BetaWebFetchUrlSourceExcept): T

            /**
             * Maps an unknown variant of [ClientToolResults] to a value of type [T].
             *
             * An instance of [ClientToolResults] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown ClientToolResults: $json")
            }
        }

        internal class Deserializer :
            BaseDeserializer<ClientToolResults>(ClientToolResults::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ClientToolResults {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "all" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceAll>())
                            ?.let { ClientToolResults(all = it, _json = json) }
                            ?: ClientToolResults(_json = json)
                    }
                    "none" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceNone>())
                            ?.let { ClientToolResults(none = it, _json = json) }
                            ?: ClientToolResults(_json = json)
                    }
                    "only" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceOnly>())
                            ?.let { ClientToolResults(only = it, _json = json) }
                            ?: ClientToolResults(_json = json)
                    }
                    "except" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceExcept>())
                            ?.let { ClientToolResults(except = it, _json = json) }
                            ?: ClientToolResults(_json = json)
                    }
                }

                return ClientToolResults(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<ClientToolResults>(ClientToolResults::class) {

            override fun serialize(
                value: ClientToolResults,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.all != null -> generator.writeObject(value.all)
                    value.none != null -> generator.writeObject(value.none)
                    value.only != null -> generator.writeObject(value.only)
                    value.except != null -> generator.writeObject(value.except)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid ClientToolResults")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val ALL = of("all")

                @JvmField val NONE = of("none")

                @JvmField val ONLY = of("only")

                @JvmField val EXCEPT = of("except")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                ALL,
                NONE,
                ONLY,
                EXCEPT,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                ALL,
                NONE,
                ONLY,
                EXCEPT,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    ALL -> Value.ALL
                    NONE -> Value.NONE
                    ONLY -> Value.ONLY
                    EXCEPT -> Value.EXCEPT
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    ALL -> Known.ALL
                    NONE -> Known.NONE
                    ONLY -> Known.ONLY
                    EXCEPT -> Known.EXCEPT
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
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

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    /**
     * Which server tools' results contribute fetchable URLs: "all", "none", or an only or except
     * list of server tool names from tools[]; only web_search and web_fetch results ever
     * contribute.
     */
    @JsonDeserialize(using = ServerToolResults.Deserializer::class)
    @JsonSerialize(using = ServerToolResults.Serializer::class)
    class ServerToolResults
    private constructor(
        private val all: BetaWebFetchUrlSourceAll? = null,
        private val none: BetaWebFetchUrlSourceNone? = null,
        private val only: BetaWebFetchUrlSourceOnly? = null,
        private val except: BetaWebFetchUrlSourceExcept? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitAll(all: BetaWebFetchUrlSourceAll): Type = Type.ALL

                    override fun visitNone(none: BetaWebFetchUrlSourceNone): Type = Type.NONE

                    override fun visitOnly(only: BetaWebFetchUrlSourceOnly): Type = Type.ONLY

                    override fun visitExcept(except: BetaWebFetchUrlSourceExcept): Type =
                        Type.EXCEPT

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun tools(): Optional<List<BetaWebFetchUrlSourceToolReference>> =
            accept(
                object : Visitor<Optional<List<BetaWebFetchUrlSourceToolReference>>> {
                    override fun visitAll(
                        all: BetaWebFetchUrlSourceAll
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> = Optional.empty()

                    override fun visitNone(
                        none: BetaWebFetchUrlSourceNone
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> = Optional.empty()

                    override fun visitOnly(
                        only: BetaWebFetchUrlSourceOnly
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> =
                        Optional.of(only.tools())

                    override fun visitExcept(
                        except: BetaWebFetchUrlSourceExcept
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> =
                        Optional.of(except.tools())

                    override fun unknown(
                        json: JsonValue?
                    ): Optional<List<BetaWebFetchUrlSourceToolReference>> =
                        json
                            .getProperty<List<BetaWebFetchUrlSourceToolReference>>("tools")
                            .asKnown()
                }
            )

        /**
         * The ``url_sources`` variant under which a source contributes in full: every result of the
         * tool filter's source, or all user input.
         */
        fun all(): Optional<BetaWebFetchUrlSourceAll> = Optional.ofNullable(all)

        /**
         * The ``url_sources`` variant under which a source contributes nothing: no result of the
         * tool filter's source, or no user input.
         */
        fun none(): Optional<BetaWebFetchUrlSourceNone> = Optional.ofNullable(none)

        /** The tool filter variant under which only the named tools' results contribute. */
        fun only(): Optional<BetaWebFetchUrlSourceOnly> = Optional.ofNullable(only)

        /** The tool filter variant under which every result but the named tools' contributes. */
        fun except(): Optional<BetaWebFetchUrlSourceExcept> = Optional.ofNullable(except)

        fun isAll(): Boolean = all != null

        fun isNone(): Boolean = none != null

        fun isOnly(): Boolean = only != null

        fun isExcept(): Boolean = except != null

        /**
         * The ``url_sources`` variant under which a source contributes in full: every result of the
         * tool filter's source, or all user input.
         */
        fun asAll(): BetaWebFetchUrlSourceAll = all.getOrThrow("all")

        /**
         * The ``url_sources`` variant under which a source contributes nothing: no result of the
         * tool filter's source, or no user input.
         */
        fun asNone(): BetaWebFetchUrlSourceNone = none.getOrThrow("none")

        /** The tool filter variant under which only the named tools' results contribute. */
        fun asOnly(): BetaWebFetchUrlSourceOnly = only.getOrThrow("only")

        /** The tool filter variant under which every result but the named tools' contributes. */
        fun asExcept(): BetaWebFetchUrlSourceExcept = except.getOrThrow("except")

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
         * Optional<String> result = serverToolResults.accept(new ServerToolResults.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAll(BetaWebFetchUrlSourceAll all) {
         *         return Optional.of(all.toString());
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
                all != null -> visitor.visitAll(all)
                none != null -> visitor.visitNone(none)
                only != null -> visitor.visitOnly(only)
                except != null -> visitor.visitExcept(except)
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
        fun validate(): ServerToolResults = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAll(all: BetaWebFetchUrlSourceAll) {
                        all.validate()
                    }

                    override fun visitNone(none: BetaWebFetchUrlSourceNone) {
                        none.validate()
                    }

                    override fun visitOnly(only: BetaWebFetchUrlSourceOnly) {
                        only.validate()
                    }

                    override fun visitExcept(except: BetaWebFetchUrlSourceExcept) {
                        except.validate()
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
                    override fun visitAll(all: BetaWebFetchUrlSourceAll) = all.validity()

                    override fun visitNone(none: BetaWebFetchUrlSourceNone) = none.validity()

                    override fun visitOnly(only: BetaWebFetchUrlSourceOnly) = only.validity()

                    override fun visitExcept(except: BetaWebFetchUrlSourceExcept) =
                        except.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ServerToolResults &&
                all == other.all &&
                none == other.none &&
                only == other.only &&
                except == other.except
        }

        override fun hashCode(): Int = Objects.hash(all, none, only, except)

        override fun toString(): String =
            when {
                all != null -> "ServerToolResults{all=$all}"
                none != null -> "ServerToolResults{none=$none}"
                only != null -> "ServerToolResults{only=$only}"
                except != null -> "ServerToolResults{except=$except}"
                _json != null -> "ServerToolResults{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ServerToolResults")
            }

        companion object {

            /**
             * The ``url_sources`` variant under which a source contributes in full: every result of
             * the tool filter's source, or all user input.
             */
            @JvmStatic fun ofAll(all: BetaWebFetchUrlSourceAll) = ServerToolResults(all = all)

            /**
             * The ``url_sources`` variant under which a source contributes nothing: no result of
             * the tool filter's source, or no user input.
             */
            @JvmStatic fun ofNone(none: BetaWebFetchUrlSourceNone) = ServerToolResults(none = none)

            /** The tool filter variant under which only the named tools' results contribute. */
            @JvmStatic fun ofOnly(only: BetaWebFetchUrlSourceOnly) = ServerToolResults(only = only)

            /**
             * Returns an immutable instance of [ServerToolResults] whose [ofOnly] variant is built
             * from the given required [tools].
             */
            @JvmStatic
            fun ofOnly(tools: List<BetaWebFetchUrlSourceToolReference>) =
                ofOnly(BetaWebFetchUrlSourceOnly.of(tools))

            /**
             * The tool filter variant under which every result but the named tools' contributes.
             */
            @JvmStatic
            fun ofExcept(except: BetaWebFetchUrlSourceExcept) = ServerToolResults(except = except)

            /**
             * Returns an immutable instance of [ServerToolResults] whose [ofExcept] variant is
             * built from the given required [tools].
             */
            @JvmStatic
            fun ofExcept(tools: List<BetaWebFetchUrlSourceToolReference>) =
                ofExcept(BetaWebFetchUrlSourceExcept.of(tools))
        }

        /**
         * An interface that defines how to map each variant of [ServerToolResults] to a value of
         * type [T].
         */
        interface Visitor<out T> {

            /**
             * The ``url_sources`` variant under which a source contributes in full: every result of
             * the tool filter's source, or all user input.
             */
            fun visitAll(all: BetaWebFetchUrlSourceAll): T

            /**
             * The ``url_sources`` variant under which a source contributes nothing: no result of
             * the tool filter's source, or no user input.
             */
            fun visitNone(none: BetaWebFetchUrlSourceNone): T

            /** The tool filter variant under which only the named tools' results contribute. */
            fun visitOnly(only: BetaWebFetchUrlSourceOnly): T

            /**
             * The tool filter variant under which every result but the named tools' contributes.
             */
            fun visitExcept(except: BetaWebFetchUrlSourceExcept): T

            /**
             * Maps an unknown variant of [ServerToolResults] to a value of type [T].
             *
             * An instance of [ServerToolResults] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown ServerToolResults: $json")
            }
        }

        internal class Deserializer :
            BaseDeserializer<ServerToolResults>(ServerToolResults::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ServerToolResults {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "all" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceAll>())
                            ?.let { ServerToolResults(all = it, _json = json) }
                            ?: ServerToolResults(_json = json)
                    }
                    "none" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceNone>())
                            ?.let { ServerToolResults(none = it, _json = json) }
                            ?: ServerToolResults(_json = json)
                    }
                    "only" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceOnly>())
                            ?.let { ServerToolResults(only = it, _json = json) }
                            ?: ServerToolResults(_json = json)
                    }
                    "except" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceExcept>())
                            ?.let { ServerToolResults(except = it, _json = json) }
                            ?: ServerToolResults(_json = json)
                    }
                }

                return ServerToolResults(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<ServerToolResults>(ServerToolResults::class) {

            override fun serialize(
                value: ServerToolResults,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.all != null -> generator.writeObject(value.all)
                    value.none != null -> generator.writeObject(value.none)
                    value.only != null -> generator.writeObject(value.only)
                    value.except != null -> generator.writeObject(value.except)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid ServerToolResults")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val ALL = of("all")

                @JvmField val NONE = of("none")

                @JvmField val ONLY = of("only")

                @JvmField val EXCEPT = of("except")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                ALL,
                NONE,
                ONLY,
                EXCEPT,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                ALL,
                NONE,
                ONLY,
                EXCEPT,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    ALL -> Value.ALL
                    NONE -> Value.NONE
                    ONLY -> Value.ONLY
                    EXCEPT -> Value.EXCEPT
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    ALL -> Known.ALL
                    NONE -> Known.NONE
                    ONLY -> Known.ONLY
                    EXCEPT -> Known.EXCEPT
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
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

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    /** Whether URLs in user messages are fetchable: "all" or "none". */
    @JsonDeserialize(using = UserInput.Deserializer::class)
    @JsonSerialize(using = UserInput.Serializer::class)
    class UserInput
    private constructor(
        private val all: BetaWebFetchUrlSourceAll? = null,
        private val none: BetaWebFetchUrlSourceNone? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitAll(all: BetaWebFetchUrlSourceAll): Type = Type.ALL

                    override fun visitNone(none: BetaWebFetchUrlSourceNone): Type = Type.NONE

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        /**
         * The ``url_sources`` variant under which a source contributes in full: every result of the
         * tool filter's source, or all user input.
         */
        fun all(): Optional<BetaWebFetchUrlSourceAll> = Optional.ofNullable(all)

        /**
         * The ``url_sources`` variant under which a source contributes nothing: no result of the
         * tool filter's source, or no user input.
         */
        fun none(): Optional<BetaWebFetchUrlSourceNone> = Optional.ofNullable(none)

        fun isAll(): Boolean = all != null

        fun isNone(): Boolean = none != null

        /**
         * The ``url_sources`` variant under which a source contributes in full: every result of the
         * tool filter's source, or all user input.
         */
        fun asAll(): BetaWebFetchUrlSourceAll = all.getOrThrow("all")

        /**
         * The ``url_sources`` variant under which a source contributes nothing: no result of the
         * tool filter's source, or no user input.
         */
        fun asNone(): BetaWebFetchUrlSourceNone = none.getOrThrow("none")

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
         * Optional<String> result = userInput.accept(new UserInput.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAll(BetaWebFetchUrlSourceAll all) {
         *         return Optional.of(all.toString());
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
                all != null -> visitor.visitAll(all)
                none != null -> visitor.visitNone(none)
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
        fun validate(): UserInput = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAll(all: BetaWebFetchUrlSourceAll) {
                        all.validate()
                    }

                    override fun visitNone(none: BetaWebFetchUrlSourceNone) {
                        none.validate()
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
                    override fun visitAll(all: BetaWebFetchUrlSourceAll) = all.validity()

                    override fun visitNone(none: BetaWebFetchUrlSourceNone) = none.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is UserInput && all == other.all && none == other.none
        }

        override fun hashCode(): Int = Objects.hash(all, none)

        override fun toString(): String =
            when {
                all != null -> "UserInput{all=$all}"
                none != null -> "UserInput{none=$none}"
                _json != null -> "UserInput{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid UserInput")
            }

        companion object {

            /**
             * The ``url_sources`` variant under which a source contributes in full: every result of
             * the tool filter's source, or all user input.
             */
            @JvmStatic fun ofAll(all: BetaWebFetchUrlSourceAll) = UserInput(all = all)

            /**
             * The ``url_sources`` variant under which a source contributes nothing: no result of
             * the tool filter's source, or no user input.
             */
            @JvmStatic fun ofNone(none: BetaWebFetchUrlSourceNone) = UserInput(none = none)
        }

        /**
         * An interface that defines how to map each variant of [UserInput] to a value of type [T].
         */
        interface Visitor<out T> {

            /**
             * The ``url_sources`` variant under which a source contributes in full: every result of
             * the tool filter's source, or all user input.
             */
            fun visitAll(all: BetaWebFetchUrlSourceAll): T

            /**
             * The ``url_sources`` variant under which a source contributes nothing: no result of
             * the tool filter's source, or no user input.
             */
            fun visitNone(none: BetaWebFetchUrlSourceNone): T

            /**
             * Maps an unknown variant of [UserInput] to a value of type [T].
             *
             * An instance of [UserInput] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown UserInput: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<UserInput>(UserInput::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): UserInput {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "all" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceAll>())
                            ?.let { UserInput(all = it, _json = json) } ?: UserInput(_json = json)
                    }
                    "none" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaWebFetchUrlSourceNone>())
                            ?.let { UserInput(none = it, _json = json) } ?: UserInput(_json = json)
                    }
                }

                return UserInput(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<UserInput>(UserInput::class) {

            override fun serialize(
                value: UserInput,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.all != null -> generator.writeObject(value.all)
                    value.none != null -> generator.writeObject(value.none)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid UserInput")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val ALL = of("all")

                @JvmField val NONE = of("none")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                ALL,
                NONE,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                ALL,
                NONE,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    ALL -> Value.ALL
                    NONE -> Value.NONE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    ALL -> Known.ALL
                    NONE -> Known.NONE
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
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

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWebFetchUrlSources &&
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
        "BetaWebFetchUrlSources{clientToolResults=$clientToolResults, serverToolResults=$serverToolResults, userInput=$userInput, additionalProperties=$additionalProperties}"
}
