// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
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

/** Configuration for a specific agent tool. */
@JsonDeserialize(using = BetaManagedAgentsAgentToolConfig.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsAgentToolConfig.Serializer::class)
class BetaManagedAgentsAgentToolConfig
private constructor(
    private val bash: BetaManagedAgentsBashToolConfig? = null,
    private val edit: BetaManagedAgentsEditToolConfig? = null,
    private val read: BetaManagedAgentsReadToolConfig? = null,
    private val write: BetaManagedAgentsWriteToolConfig? = null,
    private val glob: BetaManagedAgentsGlobToolConfig? = null,
    private val grep: BetaManagedAgentsGrepToolConfig? = null,
    private val webFetch: BetaManagedAgentsWebFetchToolConfig? = null,
    private val webSearch: BetaManagedAgentsWebSearchToolConfig? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitBash(bash: BetaManagedAgentsBashToolConfig): Type = Type.BASH

                override fun visitEdit(edit: BetaManagedAgentsEditToolConfig): Type = Type.EDIT

                override fun visitRead(read: BetaManagedAgentsReadToolConfig): Type = Type.READ

                override fun visitWrite(write: BetaManagedAgentsWriteToolConfig): Type = Type.WRITE

                override fun visitGlob(glob: BetaManagedAgentsGlobToolConfig): Type = Type.GLOB

                override fun visitGrep(grep: BetaManagedAgentsGrepToolConfig): Type = Type.GREP

                override fun visitWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfig): Type =
                    Type.WEB_FETCH

                override fun visitWebSearch(webSearch: BetaManagedAgentsWebSearchToolConfig): Type =
                    Type.WEB_SEARCH

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun enabled(): Boolean =
        accept(
            object : Visitor<Boolean> {
                override fun visitBash(bash: BetaManagedAgentsBashToolConfig): Boolean =
                    bash.enabled()

                override fun visitEdit(edit: BetaManagedAgentsEditToolConfig): Boolean =
                    edit.enabled()

                override fun visitRead(read: BetaManagedAgentsReadToolConfig): Boolean =
                    read.enabled()

                override fun visitWrite(write: BetaManagedAgentsWriteToolConfig): Boolean =
                    write.enabled()

                override fun visitGlob(glob: BetaManagedAgentsGlobToolConfig): Boolean =
                    glob.enabled()

                override fun visitGrep(grep: BetaManagedAgentsGrepToolConfig): Boolean =
                    grep.enabled()

                override fun visitWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfig): Boolean =
                    webFetch.enabled()

                override fun visitWebSearch(
                    webSearch: BetaManagedAgentsWebSearchToolConfig
                ): Boolean = webSearch.enabled()
            }
        )

    fun allowedDomains(): Optional<List<String>> =
        accept(
            object : Visitor<Optional<List<String>>> {
                override fun visitBash(
                    bash: BetaManagedAgentsBashToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitEdit(
                    edit: BetaManagedAgentsEditToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitRead(
                    read: BetaManagedAgentsReadToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitWrite(
                    write: BetaManagedAgentsWriteToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitGlob(
                    glob: BetaManagedAgentsGlobToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitGrep(
                    grep: BetaManagedAgentsGrepToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitWebFetch(
                    webFetch: BetaManagedAgentsWebFetchToolConfig
                ): Optional<List<String>> = webFetch.allowedDomains()

                override fun visitWebSearch(
                    webSearch: BetaManagedAgentsWebSearchToolConfig
                ): Optional<List<String>> = webSearch.allowedDomains()
            }
        )

    fun blockedDomains(): Optional<List<String>> =
        accept(
            object : Visitor<Optional<List<String>>> {
                override fun visitBash(
                    bash: BetaManagedAgentsBashToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitEdit(
                    edit: BetaManagedAgentsEditToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitRead(
                    read: BetaManagedAgentsReadToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitWrite(
                    write: BetaManagedAgentsWriteToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitGlob(
                    glob: BetaManagedAgentsGlobToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitGrep(
                    grep: BetaManagedAgentsGrepToolConfig
                ): Optional<List<String>> = Optional.empty()

                override fun visitWebFetch(
                    webFetch: BetaManagedAgentsWebFetchToolConfig
                ): Optional<List<String>> = webFetch.blockedDomains()

                override fun visitWebSearch(
                    webSearch: BetaManagedAgentsWebSearchToolConfig
                ): Optional<List<String>> = webSearch.blockedDomains()
            }
        )

    /** Configuration for the bash tool. */
    fun bash(): Optional<BetaManagedAgentsBashToolConfig> = Optional.ofNullable(bash)

    /** Configuration for the edit tool. */
    fun edit(): Optional<BetaManagedAgentsEditToolConfig> = Optional.ofNullable(edit)

    /** Configuration for the read tool. */
    fun read(): Optional<BetaManagedAgentsReadToolConfig> = Optional.ofNullable(read)

    /** Configuration for the write tool. */
    fun write(): Optional<BetaManagedAgentsWriteToolConfig> = Optional.ofNullable(write)

    /** Configuration for the glob tool. */
    fun glob(): Optional<BetaManagedAgentsGlobToolConfig> = Optional.ofNullable(glob)

    /** Configuration for the grep tool. */
    fun grep(): Optional<BetaManagedAgentsGrepToolConfig> = Optional.ofNullable(grep)

    /** Configuration for the web_fetch tool. */
    fun webFetch(): Optional<BetaManagedAgentsWebFetchToolConfig> = Optional.ofNullable(webFetch)

    /** Configuration for the web_search tool. */
    fun webSearch(): Optional<BetaManagedAgentsWebSearchToolConfig> = Optional.ofNullable(webSearch)

    fun isBash(): Boolean = bash != null

    fun isEdit(): Boolean = edit != null

    fun isRead(): Boolean = read != null

    fun isWrite(): Boolean = write != null

    fun isGlob(): Boolean = glob != null

    fun isGrep(): Boolean = grep != null

    fun isWebFetch(): Boolean = webFetch != null

    fun isWebSearch(): Boolean = webSearch != null

    /** Configuration for the bash tool. */
    fun asBash(): BetaManagedAgentsBashToolConfig = bash.getOrThrow("bash")

    /** Configuration for the edit tool. */
    fun asEdit(): BetaManagedAgentsEditToolConfig = edit.getOrThrow("edit")

    /** Configuration for the read tool. */
    fun asRead(): BetaManagedAgentsReadToolConfig = read.getOrThrow("read")

    /** Configuration for the write tool. */
    fun asWrite(): BetaManagedAgentsWriteToolConfig = write.getOrThrow("write")

    /** Configuration for the glob tool. */
    fun asGlob(): BetaManagedAgentsGlobToolConfig = glob.getOrThrow("glob")

    /** Configuration for the grep tool. */
    fun asGrep(): BetaManagedAgentsGrepToolConfig = grep.getOrThrow("grep")

    /** Configuration for the web_fetch tool. */
    fun asWebFetch(): BetaManagedAgentsWebFetchToolConfig = webFetch.getOrThrow("webFetch")

    /** Configuration for the web_search tool. */
    fun asWebSearch(): BetaManagedAgentsWebSearchToolConfig = webSearch.getOrThrow("webSearch")

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
     * Optional<String> result = betaManagedAgentsAgentToolConfig.accept(new BetaManagedAgentsAgentToolConfig.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitBash(BetaManagedAgentsBashToolConfig bash) {
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
    fun validate(): BetaManagedAgentsAgentToolConfig = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitBash(bash: BetaManagedAgentsBashToolConfig) {
                    bash.validate()
                }

                override fun visitEdit(edit: BetaManagedAgentsEditToolConfig) {
                    edit.validate()
                }

                override fun visitRead(read: BetaManagedAgentsReadToolConfig) {
                    read.validate()
                }

                override fun visitWrite(write: BetaManagedAgentsWriteToolConfig) {
                    write.validate()
                }

                override fun visitGlob(glob: BetaManagedAgentsGlobToolConfig) {
                    glob.validate()
                }

                override fun visitGrep(grep: BetaManagedAgentsGrepToolConfig) {
                    grep.validate()
                }

                override fun visitWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfig) {
                    webFetch.validate()
                }

                override fun visitWebSearch(webSearch: BetaManagedAgentsWebSearchToolConfig) {
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
                override fun visitBash(bash: BetaManagedAgentsBashToolConfig) = bash.validity()

                override fun visitEdit(edit: BetaManagedAgentsEditToolConfig) = edit.validity()

                override fun visitRead(read: BetaManagedAgentsReadToolConfig) = read.validity()

                override fun visitWrite(write: BetaManagedAgentsWriteToolConfig) = write.validity()

                override fun visitGlob(glob: BetaManagedAgentsGlobToolConfig) = glob.validity()

                override fun visitGrep(grep: BetaManagedAgentsGrepToolConfig) = grep.validity()

                override fun visitWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfig) =
                    webFetch.validity()

                override fun visitWebSearch(webSearch: BetaManagedAgentsWebSearchToolConfig) =
                    webSearch.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolConfig &&
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
            bash != null -> "BetaManagedAgentsAgentToolConfig{bash=$bash}"
            edit != null -> "BetaManagedAgentsAgentToolConfig{edit=$edit}"
            read != null -> "BetaManagedAgentsAgentToolConfig{read=$read}"
            write != null -> "BetaManagedAgentsAgentToolConfig{write=$write}"
            glob != null -> "BetaManagedAgentsAgentToolConfig{glob=$glob}"
            grep != null -> "BetaManagedAgentsAgentToolConfig{grep=$grep}"
            webFetch != null -> "BetaManagedAgentsAgentToolConfig{webFetch=$webFetch}"
            webSearch != null -> "BetaManagedAgentsAgentToolConfig{webSearch=$webSearch}"
            _json != null -> "BetaManagedAgentsAgentToolConfig{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsAgentToolConfig")
        }

    companion object {

        /** Configuration for the bash tool. */
        @JvmStatic
        fun ofBash(bash: BetaManagedAgentsBashToolConfig) =
            BetaManagedAgentsAgentToolConfig(bash = bash)

        /** Configuration for the edit tool. */
        @JvmStatic
        fun ofEdit(edit: BetaManagedAgentsEditToolConfig) =
            BetaManagedAgentsAgentToolConfig(edit = edit)

        /** Configuration for the read tool. */
        @JvmStatic
        fun ofRead(read: BetaManagedAgentsReadToolConfig) =
            BetaManagedAgentsAgentToolConfig(read = read)

        /** Configuration for the write tool. */
        @JvmStatic
        fun ofWrite(write: BetaManagedAgentsWriteToolConfig) =
            BetaManagedAgentsAgentToolConfig(write = write)

        /** Configuration for the glob tool. */
        @JvmStatic
        fun ofGlob(glob: BetaManagedAgentsGlobToolConfig) =
            BetaManagedAgentsAgentToolConfig(glob = glob)

        /** Configuration for the grep tool. */
        @JvmStatic
        fun ofGrep(grep: BetaManagedAgentsGrepToolConfig) =
            BetaManagedAgentsAgentToolConfig(grep = grep)

        /** Configuration for the web_fetch tool. */
        @JvmStatic
        fun ofWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfig) =
            BetaManagedAgentsAgentToolConfig(webFetch = webFetch)

        /** Configuration for the web_search tool. */
        @JvmStatic
        fun ofWebSearch(webSearch: BetaManagedAgentsWebSearchToolConfig) =
            BetaManagedAgentsAgentToolConfig(webSearch = webSearch)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsAgentToolConfig] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** Configuration for the bash tool. */
        fun visitBash(bash: BetaManagedAgentsBashToolConfig): T

        /** Configuration for the edit tool. */
        fun visitEdit(edit: BetaManagedAgentsEditToolConfig): T

        /** Configuration for the read tool. */
        fun visitRead(read: BetaManagedAgentsReadToolConfig): T

        /** Configuration for the write tool. */
        fun visitWrite(write: BetaManagedAgentsWriteToolConfig): T

        /** Configuration for the glob tool. */
        fun visitGlob(glob: BetaManagedAgentsGlobToolConfig): T

        /** Configuration for the grep tool. */
        fun visitGrep(grep: BetaManagedAgentsGrepToolConfig): T

        /** Configuration for the web_fetch tool. */
        fun visitWebFetch(webFetch: BetaManagedAgentsWebFetchToolConfig): T

        /** Configuration for the web_search tool. */
        fun visitWebSearch(webSearch: BetaManagedAgentsWebSearchToolConfig): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsAgentToolConfig] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsAgentToolConfig] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsAgentToolConfig: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsAgentToolConfig>(
            BetaManagedAgentsAgentToolConfig::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsAgentToolConfig {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "bash" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsBashToolConfig>())
                        ?.let { BetaManagedAgentsAgentToolConfig(bash = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfig(_json = json)
                }
                "edit" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsEditToolConfig>())
                        ?.let { BetaManagedAgentsAgentToolConfig(edit = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfig(_json = json)
                }
                "read" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsReadToolConfig>())
                        ?.let { BetaManagedAgentsAgentToolConfig(read = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfig(_json = json)
                }
                "write" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsWriteToolConfig>())
                        ?.let { BetaManagedAgentsAgentToolConfig(write = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfig(_json = json)
                }
                "glob" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsGlobToolConfig>())
                        ?.let { BetaManagedAgentsAgentToolConfig(glob = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfig(_json = json)
                }
                "grep" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsGrepToolConfig>())
                        ?.let { BetaManagedAgentsAgentToolConfig(grep = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfig(_json = json)
                }
                "web_fetch" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWebFetchToolConfig>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolConfig(webFetch = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfig(_json = json)
                }
                "web_search" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsWebSearchToolConfig>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolConfig(webSearch = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolConfig(_json = json)
                }
            }

            return BetaManagedAgentsAgentToolConfig(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsAgentToolConfig>(BetaManagedAgentsAgentToolConfig::class) {

        override fun serialize(
            value: BetaManagedAgentsAgentToolConfig,
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
                else -> throw IllegalStateException("Invalid BetaManagedAgentsAgentToolConfig")
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
