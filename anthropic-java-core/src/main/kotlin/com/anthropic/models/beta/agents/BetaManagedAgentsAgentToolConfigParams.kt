// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Configuration override for a specific tool within a toolset. */
@JsonDeserialize(using = BetaManagedAgentsAgentToolConfigParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsAgentToolConfigParams.Serializer::class)
class BetaManagedAgentsAgentToolConfigParams
private constructor(
    private val bash: BetaManagedAgentsBashToolConfigParams? = null,
    private val edit: BetaManagedAgentsEditToolConfigParams? = null,
    private val read: BetaManagedAgentsReadToolConfigParams? = null,
    private val write: BetaManagedAgentsWriteToolConfigParams? = null,
    private val glob: BetaManagedAgentsGlobToolConfigParams? = null,
    private val grep: BetaManagedAgentsGrepToolConfigParams? = null,
    private val webFetch: BetaManagedAgentsWebFetchToolConfigParams? = null,
    private val webSearch: BetaManagedAgentsWebSearchToolConfigParams? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitBash(bash: BetaManagedAgentsBashToolConfigParams): Type =
                    Type.BASH

                override fun visitEdit(edit: BetaManagedAgentsEditToolConfigParams): Type =
                    Type.EDIT

                override fun visitRead(read: BetaManagedAgentsReadToolConfigParams): Type =
                    Type.READ

                override fun visitWrite(write: BetaManagedAgentsWriteToolConfigParams): Type =
                    Type.WRITE

                override fun visitGlob(glob: BetaManagedAgentsGlobToolConfigParams): Type =
                    Type.GLOB

                override fun visitGrep(grep: BetaManagedAgentsGrepToolConfigParams): Type =
                    Type.GREP

                override fun visitWebFetch(
                    webFetch: BetaManagedAgentsWebFetchToolConfigParams
                ): Type = Type.WEB_FETCH

                override fun visitWebSearch(
                    webSearch: BetaManagedAgentsWebSearchToolConfigParams
                ): Type = Type.WEB_SEARCH

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun enabled(): Optional<Boolean> =
        accept(
            object : Visitor<Optional<Boolean>> {
                override fun visitBash(
                    bash: BetaManagedAgentsBashToolConfigParams
                ): Optional<Boolean> = bash.enabled()

                override fun visitEdit(
                    edit: BetaManagedAgentsEditToolConfigParams
                ): Optional<Boolean> = edit.enabled()

                override fun visitRead(
                    read: BetaManagedAgentsReadToolConfigParams
                ): Optional<Boolean> = read.enabled()

                override fun visitWrite(
                    write: BetaManagedAgentsWriteToolConfigParams
                ): Optional<Boolean> = write.enabled()

                override fun visitGlob(
                    glob: BetaManagedAgentsGlobToolConfigParams
                ): Optional<Boolean> = glob.enabled()

                override fun visitGrep(
                    grep: BetaManagedAgentsGrepToolConfigParams
                ): Optional<Boolean> = grep.enabled()

                override fun visitWebFetch(
                    webFetch: BetaManagedAgentsWebFetchToolConfigParams
                ): Optional<Boolean> = webFetch.enabled()

                override fun visitWebSearch(
                    webSearch: BetaManagedAgentsWebSearchToolConfigParams
                ): Optional<Boolean> = webSearch.enabled()

                override fun unknown(json: JsonValue?): Optional<Boolean> =
                    json.getProperty<Boolean>("enabled").asKnown()
            }
        )

    fun allowedDomains(): Optional<List<String>> =
        accept(
            object : Visitor<Optional<List<String>>> {
                override fun visitBash(
                    bash: BetaManagedAgentsBashToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitEdit(
                    edit: BetaManagedAgentsEditToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitRead(
                    read: BetaManagedAgentsReadToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitWrite(
                    write: BetaManagedAgentsWriteToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitGlob(
                    glob: BetaManagedAgentsGlobToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitGrep(
                    grep: BetaManagedAgentsGrepToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitWebFetch(
                    webFetch: BetaManagedAgentsWebFetchToolConfigParams
                ): Optional<List<String>> = webFetch.allowedDomains()

                override fun visitWebSearch(
                    webSearch: BetaManagedAgentsWebSearchToolConfigParams
                ): Optional<List<String>> = webSearch.allowedDomains()

                override fun unknown(json: JsonValue?): Optional<List<String>> =
                    json.getProperty<List<String>>("allowed_domains").asKnown()
            }
        )

    fun blockedDomains(): Optional<List<String>> =
        accept(
            object : Visitor<Optional<List<String>>> {
                override fun visitBash(
                    bash: BetaManagedAgentsBashToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitEdit(
                    edit: BetaManagedAgentsEditToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitRead(
                    read: BetaManagedAgentsReadToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitWrite(
                    write: BetaManagedAgentsWriteToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitGlob(
                    glob: BetaManagedAgentsGlobToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitGrep(
                    grep: BetaManagedAgentsGrepToolConfigParams
                ): Optional<List<String>> = Optional.empty()

                override fun visitWebFetch(
                    webFetch: BetaManagedAgentsWebFetchToolConfigParams
                ): Optional<List<String>> = webFetch.blockedDomains()

                override fun visitWebSearch(
                    webSearch: BetaManagedAgentsWebSearchToolConfigParams
                ): Optional<List<String>> = webSearch.blockedDomains()

                override fun unknown(json: JsonValue?): Optional<List<String>> =
                    json.getProperty<List<String>>("blocked_domains").asKnown()
            }
        )

    /** Configuration override for the bash tool. */
    fun bash(): Optional<BetaManagedAgentsBashToolConfigParams> = Optional.ofNullable(bash)

    /** Configuration override for the edit tool. */
    fun edit(): Optional<BetaManagedAgentsEditToolConfigParams> = Optional.ofNullable(edit)

    /** Configuration override for the read tool. */
    fun read(): Optional<BetaManagedAgentsReadToolConfigParams> = Optional.ofNullable(read)

    /** Configuration override for the write tool. */
    fun write(): Optional<BetaManagedAgentsWriteToolConfigParams> = Optional.ofNullable(write)

    /** Configuration override for the glob tool. */
    fun glob(): Optional<BetaManagedAgentsGlobToolConfigParams> = Optional.ofNullable(glob)

    /** Configuration override for the grep tool. */
    fun grep(): Optional<BetaManagedAgentsGrepToolConfigParams> = Optional.ofNullable(grep)

    /** Configuration override for the web_fetch tool. */
    fun webFetch(): Optional<BetaManagedAgentsWebFetchToolConfigParams> =
        Optional.ofNullable(webFetch)

    /** Configuration override for the web_search tool. */
    fun webSearch(): Optional<BetaManagedAgentsWebSearchToolConfigParams> =
        Optional.ofNullable(webSearch)

    fun isBash(): Boolean = bash != null

    fun isEdit(): Boolean = edit != null

    fun isRead(): Boolean = read != null

    fun isWrite(): Boolean = write != null

    fun isGlob(): Boolean = glob != null

    fun isGrep(): Boolean = grep != null

    fun isWebFetch(): Boolean = webFetch != null

    fun isWebSearch(): Boolean = webSearch != null

    /** Configuration override for the bash tool. */
    fun asBash(): BetaManagedAgentsBashToolConfigParams = bash.getOrThrow("bash")

    /** Configuration override for the edit tool. */
    fun asEdit(): BetaManagedAgentsEditToolConfigParams = edit.getOrThrow("edit")

    /** Configuration override for the read tool. */
    fun asRead(): BetaManagedAgentsReadToolConfigParams = read.getOrThrow("read")

    /** Configuration override for the write tool. */
    fun asWrite(): BetaManagedAgentsWriteToolConfigParams = write.getOrThrow("write")

    /** Configuration override for the glob tool. */
    fun asGlob(): BetaManagedAgentsGlobToolConfigParams = glob.getOrThrow("glob")

    /** Configuration override for the grep tool. */
    fun asGrep(): BetaManagedAgentsGrepToolConfigParams = grep.getOrThrow("grep")

    /** Configuration override for the web_fetch tool. */
    fun asWebFetch(): BetaManagedAgentsWebFetchToolConfigParams = webFetch.getOrThrow("webFetch")

    /** Configuration override for the web_search tool. */
    fun asWebSearch(): BetaManagedAgentsWebSearchToolConfigParams =
        webSearch.getOrThrow("webSearch")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaManagedAgentsAgentToolConfigParams.accept(new BetaManagedAgentsAgentToolConfigParams.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitBash(BetaManagedAgentsBashToolConfigParams bash) {
     *         return Optional.of(bash.toString());
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
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            bash != null -> visitor.visitBash(bash)
            edit != null -> visitor.visitEdit(edit)
            read != null -> visitor.visitRead(read)
            write != null -> visitor.visitWrite(write)
            glob != null -> visitor.visitGlob(glob)
            grep != null -> visitor.visitGrep(grep)
            webFetch != null -> visitor.visitWebFetch(webFetch)
            webSearch != null -> visitor.visitWebSearch(webSearch)
            else -> visitor.unknown(_json)
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
    fun validate(): BetaManagedAgentsAgentToolConfigParams = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitBash(bash: BetaManagedAgentsBashToolConfigParams) {
                    bash.validate()
                }

                override fun visitEdit(edit: BetaManagedAgentsEditToolConfigParams) {
                    edit.validate()
                }

                override fun visitRead(read: BetaManagedAgentsReadToolConfigParams) {
                    read.validate()
                }

                override fun visitWrite(write: BetaManagedAgentsWriteToolConfigParams) {
                    write.validate()
                }

                override fun visitGlob(glob: BetaManagedAgentsGlobToolConfigParams) {
                    glob.validate()
                }

                override fun visitGrep(grep: BetaManagedAgentsGrepToolConfigParams) {
                    grep.validate()
                }

                override fun visitWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfigParams) {
                    webFetch.validate()
                }

                override fun visitWebSearch(webSearch: BetaManagedAgentsWebSearchToolConfigParams) {
                    webSearch.validate()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitBash(bash: BetaManagedAgentsBashToolConfigParams) =
                    bash.validity()

                override fun visitEdit(edit: BetaManagedAgentsEditToolConfigParams) =
                    edit.validity()

                override fun visitRead(read: BetaManagedAgentsReadToolConfigParams) =
                    read.validity()

                override fun visitWrite(write: BetaManagedAgentsWriteToolConfigParams) =
                    write.validity()

                override fun visitGlob(glob: BetaManagedAgentsGlobToolConfigParams) =
                    glob.validity()

                override fun visitGrep(grep: BetaManagedAgentsGrepToolConfigParams) =
                    grep.validity()

                override fun visitWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfigParams) =
                    webFetch.validity()

                override fun visitWebSearch(webSearch: BetaManagedAgentsWebSearchToolConfigParams) =
                    webSearch.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolConfigParams &&
            bash == other.bash &&
            edit == other.edit &&
            read == other.read &&
            write == other.write &&
            glob == other.glob &&
            grep == other.grep &&
            webFetch == other.webFetch &&
            webSearch == other.webSearch
    }

    override fun hashCode(): Int =
        Objects.hash(bash, edit, read, write, glob, grep, webFetch, webSearch)

    override fun toString(): String =
        when {
            bash != null -> "BetaManagedAgentsAgentToolConfigParams{bash=$bash}"
            edit != null -> "BetaManagedAgentsAgentToolConfigParams{edit=$edit}"
            read != null -> "BetaManagedAgentsAgentToolConfigParams{read=$read}"
            write != null -> "BetaManagedAgentsAgentToolConfigParams{write=$write}"
            glob != null -> "BetaManagedAgentsAgentToolConfigParams{glob=$glob}"
            grep != null -> "BetaManagedAgentsAgentToolConfigParams{grep=$grep}"
            webFetch != null -> "BetaManagedAgentsAgentToolConfigParams{webFetch=$webFetch}"
            webSearch != null -> "BetaManagedAgentsAgentToolConfigParams{webSearch=$webSearch}"
            _json != null -> "BetaManagedAgentsAgentToolConfigParams{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsAgentToolConfigParams")
        }

    companion object {

        /** Configuration override for the bash tool. */
        @JvmStatic
        fun ofBash(bash: BetaManagedAgentsBashToolConfigParams) =
            BetaManagedAgentsAgentToolConfigParams(bash = bash)

        /** Configuration override for the edit tool. */
        @JvmStatic
        fun ofEdit(edit: BetaManagedAgentsEditToolConfigParams) =
            BetaManagedAgentsAgentToolConfigParams(edit = edit)

        /** Configuration override for the read tool. */
        @JvmStatic
        fun ofRead(read: BetaManagedAgentsReadToolConfigParams) =
            BetaManagedAgentsAgentToolConfigParams(read = read)

        /** Configuration override for the write tool. */
        @JvmStatic
        fun ofWrite(write: BetaManagedAgentsWriteToolConfigParams) =
            BetaManagedAgentsAgentToolConfigParams(write = write)

        /** Configuration override for the glob tool. */
        @JvmStatic
        fun ofGlob(glob: BetaManagedAgentsGlobToolConfigParams) =
            BetaManagedAgentsAgentToolConfigParams(glob = glob)

        /** Configuration override for the grep tool. */
        @JvmStatic
        fun ofGrep(grep: BetaManagedAgentsGrepToolConfigParams) =
            BetaManagedAgentsAgentToolConfigParams(grep = grep)

        /** Configuration override for the web_fetch tool. */
        @JvmStatic
        fun ofWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfigParams) =
            BetaManagedAgentsAgentToolConfigParams(webFetch = webFetch)

        /** Configuration override for the web_search tool. */
        @JvmStatic
        fun ofWebSearch(webSearch: BetaManagedAgentsWebSearchToolConfigParams) =
            BetaManagedAgentsAgentToolConfigParams(webSearch = webSearch)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsAgentToolConfigParams]
     * to a value of type [T].
     */
    interface Visitor<out T> {

        /** Configuration override for the bash tool. */
        fun visitBash(bash: BetaManagedAgentsBashToolConfigParams): T

        /** Configuration override for the edit tool. */
        fun visitEdit(edit: BetaManagedAgentsEditToolConfigParams): T

        /** Configuration override for the read tool. */
        fun visitRead(read: BetaManagedAgentsReadToolConfigParams): T

        /** Configuration override for the write tool. */
        fun visitWrite(write: BetaManagedAgentsWriteToolConfigParams): T

        /** Configuration override for the glob tool. */
        fun visitGlob(glob: BetaManagedAgentsGlobToolConfigParams): T

        /** Configuration override for the grep tool. */
        fun visitGrep(grep: BetaManagedAgentsGrepToolConfigParams): T

        /** Configuration override for the web_fetch tool. */
        fun visitWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfigParams): T

        /** Configuration override for the web_search tool. */
        fun visitWebSearch(webSearch: BetaManagedAgentsWebSearchToolConfigParams): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsAgentToolConfigParams] to a value of type
         * [T].
         *
         * An instance of [BetaManagedAgentsAgentToolConfigParams] can contain an unknown variant if
         * it was deserialized from data that doesn't match any known variant. For example, if the
         * SDK is on an older version than the API, then the API may respond with new variants that
         * the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsAgentToolConfigParams: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsAgentToolConfigParams>(
            BetaManagedAgentsAgentToolConfigParams::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsAgentToolConfigParams {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "bash" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsBashToolConfigParams>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolConfigParams(bash = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfigParams(_json = json)
                }
                "edit" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsEditToolConfigParams>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolConfigParams(edit = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfigParams(_json = json)
                }
                "read" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsReadToolConfigParams>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolConfigParams(read = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfigParams(_json = json)
                }
                "write" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWriteToolConfigParams>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolConfigParams(write = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfigParams(_json = json)
                }
                "glob" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsGlobToolConfigParams>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolConfigParams(glob = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfigParams(_json = json)
                }
                "grep" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsGrepToolConfigParams>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolConfigParams(grep = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfigParams(_json = json)
                }
                "web_fetch" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWebFetchToolConfigParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsAgentToolConfigParams(webFetch = it, _json = json)
                        } ?: BetaManagedAgentsAgentToolConfigParams(_json = json)
                }
                "web_search" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWebSearchToolConfigParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsAgentToolConfigParams(webSearch = it, _json = json)
                        } ?: BetaManagedAgentsAgentToolConfigParams(_json = json)
                }
            }

            return BetaManagedAgentsAgentToolConfigParams(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsAgentToolConfigParams>(
            BetaManagedAgentsAgentToolConfigParams::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsAgentToolConfigParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.bash != null -> generator.writeObject(value.bash)
                value.edit != null -> generator.writeObject(value.edit)
                value.read != null -> generator.writeObject(value.read)
                value.write != null -> generator.writeObject(value.write)
                value.glob != null -> generator.writeObject(value.glob)
                value.grep != null -> generator.writeObject(value.grep)
                value.webFetch != null -> generator.writeObject(value.webFetch)
                value.webSearch != null -> generator.writeObject(value.webSearch)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaManagedAgentsAgentToolConfigParams")
            }
        }
    }

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val BASH = of("bash")

            @JvmField val EDIT = of("edit")

            @JvmField val READ = of("read")

            @JvmField val WRITE = of("write")

            @JvmField val GLOB = of("glob")

            @JvmField val GREP = of("grep")

            @JvmField val WEB_FETCH = of("web_fetch")

            @JvmField val WEB_SEARCH = of("web_search")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            BASH,
            EDIT,
            READ,
            WRITE,
            GLOB,
            GREP,
            WEB_FETCH,
            WEB_SEARCH,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            BASH,
            EDIT,
            READ,
            WRITE,
            GLOB,
            GREP,
            WEB_FETCH,
            WEB_SEARCH,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                BASH -> Value.BASH
                EDIT -> Value.EDIT
                READ -> Value.READ
                WRITE -> Value.WRITE
                GLOB -> Value.GLOB
                GREP -> Value.GREP
                WEB_FETCH -> Value.WEB_FETCH
                WEB_SEARCH -> Value.WEB_SEARCH
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
                BASH -> Known.BASH
                EDIT -> Known.EDIT
                READ -> Known.READ
                WRITE -> Known.WRITE
                GLOB -> Known.GLOB
                GREP -> Known.GREP
                WEB_FETCH -> Known.WEB_FETCH
                WEB_SEARCH -> Known.WEB_SEARCH
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
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
