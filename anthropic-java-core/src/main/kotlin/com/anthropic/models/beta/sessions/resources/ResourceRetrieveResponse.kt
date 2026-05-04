// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

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

/** The requested session resource. */
@JsonDeserialize(using = ResourceRetrieveResponse.Deserializer::class)
@JsonSerialize(using = ResourceRetrieveResponse.Serializer::class)
class ResourceRetrieveResponse
private constructor(
    private val githubRepository: BetaManagedAgentsGitHubRepositoryResource? = null,
    private val file: BetaManagedAgentsFileResource? = null,
    private val memoryStore: BetaManagedAgentsMemoryStoreResource? = null,
    private val _json: JsonValue? = null,
) {

    fun githubRepository(): Optional<BetaManagedAgentsGitHubRepositoryResource> =
        Optional.ofNullable(githubRepository)

    fun file(): Optional<BetaManagedAgentsFileResource> = Optional.ofNullable(file)

    /** A memory store attached to an agent session. */
    fun memoryStore(): Optional<BetaManagedAgentsMemoryStoreResource> =
        Optional.ofNullable(memoryStore)

    fun isGitHubRepository(): Boolean = githubRepository != null

    fun isFile(): Boolean = file != null

    fun isMemoryStore(): Boolean = memoryStore != null

    fun asGitHubRepository(): BetaManagedAgentsGitHubRepositoryResource =
        githubRepository.getOrThrow("githubRepository")

    fun asFile(): BetaManagedAgentsFileResource = file.getOrThrow("file")

    /** A memory store attached to an agent session. */
    fun asMemoryStore(): BetaManagedAgentsMemoryStoreResource =
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
     * Optional<String> result = resourceRetrieveResponse.accept(new ResourceRetrieveResponse.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitGitHubRepository(BetaManagedAgentsGitHubRepositoryResource githubRepository) {
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
    fun validate(): ResourceRetrieveResponse = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitGitHubRepository(
                    githubRepository: BetaManagedAgentsGitHubRepositoryResource
                ) {
                    githubRepository.validate()
                }

                override fun visitFile(file: BetaManagedAgentsFileResource) {
                    file.validate()
                }

                override fun visitMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResource) {
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
                    githubRepository: BetaManagedAgentsGitHubRepositoryResource
                ) = githubRepository.validity()

                override fun visitFile(file: BetaManagedAgentsFileResource) = file.validity()

                override fun visitMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResource) =
                    memoryStore.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ResourceRetrieveResponse &&
            githubRepository == other.githubRepository &&
            file == other.file &&
            memoryStore == other.memoryStore
    }

    override fun hashCode(): Int = Objects.hash(githubRepository, file, memoryStore)

    override fun toString(): String =
        when {
            githubRepository != null ->
                "ResourceRetrieveResponse{githubRepository=$githubRepository}"
            file != null -> "ResourceRetrieveResponse{file=$file}"
            memoryStore != null -> "ResourceRetrieveResponse{memoryStore=$memoryStore}"
            _json != null -> "ResourceRetrieveResponse{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ResourceRetrieveResponse")
        }

    companion object {

        @JvmStatic
        fun ofGitHubRepository(githubRepository: BetaManagedAgentsGitHubRepositoryResource) =
            ResourceRetrieveResponse(githubRepository = githubRepository)

        @JvmStatic
        fun ofFile(file: BetaManagedAgentsFileResource) = ResourceRetrieveResponse(file = file)

        /** A memory store attached to an agent session. */
        @JvmStatic
        fun ofMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResource) =
            ResourceRetrieveResponse(memoryStore = memoryStore)
    }

    /**
     * An interface that defines how to map each variant of [ResourceRetrieveResponse] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitGitHubRepository(githubRepository: BetaManagedAgentsGitHubRepositoryResource): T

        fun visitFile(file: BetaManagedAgentsFileResource): T

        /** A memory store attached to an agent session. */
        fun visitMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResource): T

        /**
         * Maps an unknown variant of [ResourceRetrieveResponse] to a value of type [T].
         *
         * An instance of [ResourceRetrieveResponse] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ResourceRetrieveResponse: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<ResourceRetrieveResponse>(ResourceRetrieveResponse::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ResourceRetrieveResponse {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "github_repository" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsGitHubRepositoryResource>(),
                        )
                        ?.let { ResourceRetrieveResponse(githubRepository = it, _json = json) }
                        ?: ResourceRetrieveResponse(_json = json)
                }
                "file" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsFileResource>())
                        ?.let { ResourceRetrieveResponse(file = it, _json = json) }
                        ?: ResourceRetrieveResponse(_json = json)
                }
                "memory_store" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMemoryStoreResource>(),
                        )
                        ?.let { ResourceRetrieveResponse(memoryStore = it, _json = json) }
                        ?: ResourceRetrieveResponse(_json = json)
                }
            }

            return ResourceRetrieveResponse(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<ResourceRetrieveResponse>(ResourceRetrieveResponse::class) {

        override fun serialize(
            value: ResourceRetrieveResponse,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.githubRepository != null -> generator.writeObject(value.githubRepository)
                value.file != null -> generator.writeObject(value.file)
                value.memoryStore != null -> generator.writeObject(value.memoryStore)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ResourceRetrieveResponse")
            }
        }
    }
}
