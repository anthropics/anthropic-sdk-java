// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
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

/** A configured session resource. Echoes the input minus write-only credentials. */
@JsonDeserialize(using = BetaManagedAgentsSessionResourceConfig.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsSessionResourceConfig.Serializer::class)
class BetaManagedAgentsSessionResourceConfig
private constructor(
    private val githubRepository: BetaManagedAgentsGitHubRepositoryResourceConfig? = null,
    private val file: BetaManagedAgentsFileResourceConfig? = null,
    private val memoryStore: BetaManagedAgentsMemoryStoreResourceConfig? = null,
    private val _json: JsonValue? = null,
) {

    /**
     * A GitHub repository mounted into each session's container. The authorization token is
     * write-only and never returned.
     */
    fun githubRepository(): Optional<BetaManagedAgentsGitHubRepositoryResourceConfig> =
        Optional.ofNullable(githubRepository)

    /** A file mounted into each session's container. */
    fun file(): Optional<BetaManagedAgentsFileResourceConfig> = Optional.ofNullable(file)

    /** A memory store attached to each session created from this deployment. */
    fun memoryStore(): Optional<BetaManagedAgentsMemoryStoreResourceConfig> =
        Optional.ofNullable(memoryStore)

    fun isGitHubRepository(): Boolean = githubRepository != null

    fun isFile(): Boolean = file != null

    fun isMemoryStore(): Boolean = memoryStore != null

    /**
     * A GitHub repository mounted into each session's container. The authorization token is
     * write-only and never returned.
     */
    fun asGitHubRepository(): BetaManagedAgentsGitHubRepositoryResourceConfig =
        githubRepository.getOrThrow("githubRepository")

    /** A file mounted into each session's container. */
    fun asFile(): BetaManagedAgentsFileResourceConfig = file.getOrThrow("file")

    /** A memory store attached to each session created from this deployment. */
    fun asMemoryStore(): BetaManagedAgentsMemoryStoreResourceConfig =
        memoryStore.getOrThrow("memoryStore")

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
     * Optional<String> result = betaManagedAgentsSessionResourceConfig.accept(new BetaManagedAgentsSessionResourceConfig.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitGitHubRepository(BetaManagedAgentsGitHubRepositoryResourceConfig githubRepository) {
     *         return Optional.of(githubRepository.toString());
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
            githubRepository != null -> visitor.visitGitHubRepository(githubRepository)
            file != null -> visitor.visitFile(file)
            memoryStore != null -> visitor.visitMemoryStore(memoryStore)
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
    fun validate(): BetaManagedAgentsSessionResourceConfig = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitGitHubRepository(
                    githubRepository: BetaManagedAgentsGitHubRepositoryResourceConfig
                ) {
                    githubRepository.validate()
                }

                override fun visitFile(file: BetaManagedAgentsFileResourceConfig) {
                    file.validate()
                }

                override fun visitMemoryStore(
                    memoryStore: BetaManagedAgentsMemoryStoreResourceConfig
                ) {
                    memoryStore.validate()
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
                override fun visitGitHubRepository(
                    githubRepository: BetaManagedAgentsGitHubRepositoryResourceConfig
                ) = githubRepository.validity()

                override fun visitFile(file: BetaManagedAgentsFileResourceConfig) = file.validity()

                override fun visitMemoryStore(
                    memoryStore: BetaManagedAgentsMemoryStoreResourceConfig
                ) = memoryStore.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionResourceConfig &&
            githubRepository == other.githubRepository &&
            file == other.file &&
            memoryStore == other.memoryStore
    }

    override fun hashCode(): Int = Objects.hash(githubRepository, file, memoryStore)

    override fun toString(): String =
        when {
            githubRepository != null ->
                "BetaManagedAgentsSessionResourceConfig{githubRepository=$githubRepository}"
            file != null -> "BetaManagedAgentsSessionResourceConfig{file=$file}"
            memoryStore != null ->
                "BetaManagedAgentsSessionResourceConfig{memoryStore=$memoryStore}"
            _json != null -> "BetaManagedAgentsSessionResourceConfig{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsSessionResourceConfig")
        }

    companion object {

        /**
         * A GitHub repository mounted into each session's container. The authorization token is
         * write-only and never returned.
         */
        @JvmStatic
        fun ofGitHubRepository(githubRepository: BetaManagedAgentsGitHubRepositoryResourceConfig) =
            BetaManagedAgentsSessionResourceConfig(githubRepository = githubRepository)

        /** A file mounted into each session's container. */
        @JvmStatic
        fun ofFile(file: BetaManagedAgentsFileResourceConfig) =
            BetaManagedAgentsSessionResourceConfig(file = file)

        /** A memory store attached to each session created from this deployment. */
        @JvmStatic
        fun ofMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResourceConfig) =
            BetaManagedAgentsSessionResourceConfig(memoryStore = memoryStore)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsSessionResourceConfig]
     * to a value of type [T].
     */
    interface Visitor<out T> {

        /**
         * A GitHub repository mounted into each session's container. The authorization token is
         * write-only and never returned.
         */
        fun visitGitHubRepository(
            githubRepository: BetaManagedAgentsGitHubRepositoryResourceConfig
        ): T

        /** A file mounted into each session's container. */
        fun visitFile(file: BetaManagedAgentsFileResourceConfig): T

        /** A memory store attached to each session created from this deployment. */
        fun visitMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResourceConfig): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsSessionResourceConfig] to a value of type
         * [T].
         *
         * An instance of [BetaManagedAgentsSessionResourceConfig] can contain an unknown variant if
         * it was deserialized from data that doesn't match any known variant. For example, if the
         * SDK is on an older version than the API, then the API may respond with new variants that
         * the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsSessionResourceConfig: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsSessionResourceConfig>(
            BetaManagedAgentsSessionResourceConfig::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsSessionResourceConfig {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "github_repository" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsGitHubRepositoryResourceConfig>(),
                        )
                        ?.let {
                            BetaManagedAgentsSessionResourceConfig(
                                githubRepository = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsSessionResourceConfig(_json = json)
                }
                "file" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsFileResourceConfig>(),
                        )
                        ?.let { BetaManagedAgentsSessionResourceConfig(file = it, _json = json) }
                        ?: BetaManagedAgentsSessionResourceConfig(_json = json)
                }
                "memory_store" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMemoryStoreResourceConfig>(),
                        )
                        ?.let {
                            BetaManagedAgentsSessionResourceConfig(memoryStore = it, _json = json)
                        } ?: BetaManagedAgentsSessionResourceConfig(_json = json)
                }
            }

            return BetaManagedAgentsSessionResourceConfig(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsSessionResourceConfig>(
            BetaManagedAgentsSessionResourceConfig::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsSessionResourceConfig,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.githubRepository != null -> generator.writeObject(value.githubRepository)
                value.file != null -> generator.writeObject(value.file)
                value.memoryStore != null -> generator.writeObject(value.memoryStore)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaManagedAgentsSessionResourceConfig")
            }
        }
    }
}
