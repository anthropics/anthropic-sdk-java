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

/** The updated session resource. */
@JsonDeserialize(using = ResourceUpdateResponse.Deserializer::class)
@JsonSerialize(using = ResourceUpdateResponse.Serializer::class)
class ResourceUpdateResponse
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

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            githubRepository != null -> visitor.visitGitHubRepository(githubRepository)
            file != null -> visitor.visitFile(file)
            memoryStore != null -> visitor.visitMemoryStore(memoryStore)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): ResourceUpdateResponse = apply {
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

        return other is ResourceUpdateResponse &&
            githubRepository == other.githubRepository &&
            file == other.file &&
            memoryStore == other.memoryStore
    }

    override fun hashCode(): Int = Objects.hash(githubRepository, file, memoryStore)

    override fun toString(): String =
        when {
            githubRepository != null -> "ResourceUpdateResponse{githubRepository=$githubRepository}"
            file != null -> "ResourceUpdateResponse{file=$file}"
            memoryStore != null -> "ResourceUpdateResponse{memoryStore=$memoryStore}"
            _json != null -> "ResourceUpdateResponse{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ResourceUpdateResponse")
        }

    companion object {

        @JvmStatic
        fun ofGitHubRepository(githubRepository: BetaManagedAgentsGitHubRepositoryResource) =
            ResourceUpdateResponse(githubRepository = githubRepository)

        @JvmStatic
        fun ofFile(file: BetaManagedAgentsFileResource) = ResourceUpdateResponse(file = file)

        /** A memory store attached to an agent session. */
        @JvmStatic
        fun ofMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResource) =
            ResourceUpdateResponse(memoryStore = memoryStore)
    }

    /**
     * An interface that defines how to map each variant of [ResourceUpdateResponse] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitGitHubRepository(githubRepository: BetaManagedAgentsGitHubRepositoryResource): T

        fun visitFile(file: BetaManagedAgentsFileResource): T

        /** A memory store attached to an agent session. */
        fun visitMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResource): T

        /**
         * Maps an unknown variant of [ResourceUpdateResponse] to a value of type [T].
         *
         * An instance of [ResourceUpdateResponse] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ResourceUpdateResponse: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<ResourceUpdateResponse>(ResourceUpdateResponse::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ResourceUpdateResponse {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "github_repository" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsGitHubRepositoryResource>(),
                        )
                        ?.let { ResourceUpdateResponse(githubRepository = it, _json = json) }
                        ?: ResourceUpdateResponse(_json = json)
                }
                "file" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsFileResource>())
                        ?.let { ResourceUpdateResponse(file = it, _json = json) }
                        ?: ResourceUpdateResponse(_json = json)
                }
                "memory_store" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMemoryStoreResource>(),
                        )
                        ?.let { ResourceUpdateResponse(memoryStore = it, _json = json) }
                        ?: ResourceUpdateResponse(_json = json)
                }
            }

            return ResourceUpdateResponse(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<ResourceUpdateResponse>(ResourceUpdateResponse::class) {

        override fun serialize(
            value: ResourceUpdateResponse,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.githubRepository != null -> generator.writeObject(value.githubRepository)
                value.file != null -> generator.writeObject(value.file)
                value.memoryStore != null -> generator.writeObject(value.memoryStore)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ResourceUpdateResponse")
            }
        }
    }
}
