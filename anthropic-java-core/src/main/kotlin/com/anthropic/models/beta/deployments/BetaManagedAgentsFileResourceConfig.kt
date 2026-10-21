package com.anthropic.models.beta.deployments

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

/** A file mounted into each session's container. */
class BetaManagedAgentsFileResourceConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val fileId: JsonField<String>,
    private val type: JsonValue,
    private val mountPath: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("file_id") @ExcludeMissing fileId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("mount_path") @ExcludeMissing mountPath: JsonField<String> = JsonMissing.of(),
    ) : this(fileId, type, mountPath, mutableMapOf())

    /**
     * ID of a previously uploaded file.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun fileId(): String = fileId.getRequired("file_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("file")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Mount path in the container. Defaults to `/mnt/session/uploads/<file_id>`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mountPath(): Optional<String> = mountPath.getOptional("mount_path")

    /**
     * Returns the raw JSON value of [fileId].
     *
     * Unlike [fileId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_id") @ExcludeMissing fun _fileId(): JsonField<String> = fileId

    /**
     * Returns the raw JSON value of [mountPath].
     *
     * Unlike [mountPath], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mount_path") @ExcludeMissing fun _mountPath(): JsonField<String> = mountPath

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
         * [BetaManagedAgentsFileResourceConfig].
         *
         * The following fields are required:
         * ```java
         * .fileId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsFileResourceConfig] with the required
         * [fileId] set to the given value.
         */
        @JvmStatic fun of(fileId: String) = builder().fileId(fileId).build()
    }

    /** A builder for [BetaManagedAgentsFileResourceConfig]. */
    class Builder internal constructor() {

        private var fileId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("file")
        private var mountPath: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsFileResourceConfig: BetaManagedAgentsFileResourceConfig
        ) = apply {
            fileId = betaManagedAgentsFileResourceConfig.fileId
            type = betaManagedAgentsFileResourceConfig.type
            mountPath = betaManagedAgentsFileResourceConfig.mountPath
            additionalProperties =
                betaManagedAgentsFileResourceConfig.additionalProperties.toMutableMap()
        }

        /** ID of a previously uploaded file. */
        fun fileId(fileId: String) = fileId(JsonField.of(fileId))

        /**
         * Sets [Builder.fileId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fileId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun fileId(fileId: JsonField<String>) = apply { this.fileId = fileId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("file")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Mount path in the container. Defaults to `/mnt/session/uploads/<file_id>`. */
        fun mountPath(mountPath: String?) = mountPath(JsonField.ofNullable(mountPath))

        /** Alias for calling [Builder.mountPath] with `mountPath.orElse(null)`. */
        fun mountPath(mountPath: Optional<String>) = mountPath(mountPath.getOrNull())

        /**
         * Sets [Builder.mountPath] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mountPath] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mountPath(mountPath: JsonField<String>) = apply { this.mountPath = mountPath }

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
         * Returns an immutable instance of [BetaManagedAgentsFileResourceConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .fileId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsFileResourceConfig =
            BetaManagedAgentsFileResourceConfig(
                checkRequired("fileId", fileId),
                type,
                mountPath,
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
    fun validate(): BetaManagedAgentsFileResourceConfig = apply {
        if (validated) {
            return@apply
        }

        fileId()
        _type().let {
            if (it != JsonValue.from("file")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        mountPath()
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
        (if (fileId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("file")) 1 else 0 } +
            (if (mountPath.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsFileResourceConfig &&
            fileId == other.fileId &&
            type == other.type &&
            mountPath == other.mountPath &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(fileId, type, mountPath, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsFileResourceConfig{fileId=$fileId, type=$type, mountPath=$mountPath, additionalProperties=$additionalProperties}"
}
