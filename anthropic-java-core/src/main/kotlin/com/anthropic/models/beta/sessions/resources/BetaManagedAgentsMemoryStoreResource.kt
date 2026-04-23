// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.Enum
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

/** A memory store attached to an agent session. */
class BetaManagedAgentsMemoryStoreResource
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val memoryStoreId: JsonField<String>,
    private val type: JsonField<Type>,
    private val access: JsonField<Access>,
    private val description: JsonField<String>,
    private val instructions: JsonField<String>,
    private val mountPath: JsonField<String>,
    private val name: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("memory_store_id")
        @ExcludeMissing
        memoryStoreId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("access") @ExcludeMissing access: JsonField<Access> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("instructions")
        @ExcludeMissing
        instructions: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mount_path") @ExcludeMissing mountPath: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
    ) : this(
        memoryStoreId,
        type,
        access,
        description,
        instructions,
        mountPath,
        name,
        mutableMapOf(),
    )

    /**
     * The memory store ID (memstore_...). Must belong to the caller's organization and workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun memoryStoreId(): String = memoryStoreId.getRequired("memory_store_id")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Access mode for an attached memory store.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun access(): Optional<Access> = access.getOptional("access")

    /**
     * Description of the memory store, snapshotted at attach time. Rendered into the agent's system
     * prompt. Empty string when the store has no description.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = description.getOptional("description")

    /**
     * Per-attachment guidance for the agent on how to use this store. Rendered into the memory
     * section of the system prompt. Max 4096 chars.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun instructions(): Optional<String> = instructions.getOptional("instructions")

    /**
     * Filesystem path where the store is mounted in the session container, e.g.
     * /mnt/memory/user-preferences. Derived from the store's name. Output-only.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mountPath(): Optional<String> = mountPath.getOptional("mount_path")

    /**
     * Display name of the memory store, snapshotted at attach time. Later edits to the store's name
     * do not propagate to this resource.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun name(): Optional<String> = name.getOptional("name")

    /**
     * Returns the raw JSON value of [memoryStoreId].
     *
     * Unlike [memoryStoreId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("memory_store_id")
    @ExcludeMissing
    fun _memoryStoreId(): JsonField<String> = memoryStoreId

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [access].
     *
     * Unlike [access], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("access") @ExcludeMissing fun _access(): JsonField<Access> = access

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [instructions].
     *
     * Unlike [instructions], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("instructions")
    @ExcludeMissing
    fun _instructions(): JsonField<String> = instructions

    /**
     * Returns the raw JSON value of [mountPath].
     *
     * Unlike [mountPath], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mount_path") @ExcludeMissing fun _mountPath(): JsonField<String> = mountPath

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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
         * [BetaManagedAgentsMemoryStoreResource].
         *
         * The following fields are required:
         * ```java
         * .memoryStoreId()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMemoryStoreResource]. */
    class Builder internal constructor() {

        private var memoryStoreId: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var access: JsonField<Access> = JsonMissing.of()
        private var description: JsonField<String> = JsonMissing.of()
        private var instructions: JsonField<String> = JsonMissing.of()
        private var mountPath: JsonField<String> = JsonMissing.of()
        private var name: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMemoryStoreResource: BetaManagedAgentsMemoryStoreResource
        ) = apply {
            memoryStoreId = betaManagedAgentsMemoryStoreResource.memoryStoreId
            type = betaManagedAgentsMemoryStoreResource.type
            access = betaManagedAgentsMemoryStoreResource.access
            description = betaManagedAgentsMemoryStoreResource.description
            instructions = betaManagedAgentsMemoryStoreResource.instructions
            mountPath = betaManagedAgentsMemoryStoreResource.mountPath
            name = betaManagedAgentsMemoryStoreResource.name
            additionalProperties =
                betaManagedAgentsMemoryStoreResource.additionalProperties.toMutableMap()
        }

        /**
         * The memory store ID (memstore_...). Must belong to the caller's organization and
         * workspace.
         */
        fun memoryStoreId(memoryStoreId: String) = memoryStoreId(JsonField.of(memoryStoreId))

        /**
         * Sets [Builder.memoryStoreId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.memoryStoreId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun memoryStoreId(memoryStoreId: JsonField<String>) = apply {
            this.memoryStoreId = memoryStoreId
        }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** Access mode for an attached memory store. */
        fun access(access: Access?) = access(JsonField.ofNullable(access))

        /** Alias for calling [Builder.access] with `access.orElse(null)`. */
        fun access(access: Optional<Access>) = access(access.getOrNull())

        /**
         * Sets [Builder.access] to an arbitrary JSON value.
         *
         * You should usually call [Builder.access] with a well-typed [Access] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun access(access: JsonField<Access>) = apply { this.access = access }

        /**
         * Description of the memory store, snapshotted at attach time. Rendered into the agent's
         * system prompt. Empty string when the store has no description.
         */
        fun description(description: String) = description(JsonField.of(description))

        /**
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { this.description = description }

        /**
         * Per-attachment guidance for the agent on how to use this store. Rendered into the memory
         * section of the system prompt. Max 4096 chars.
         */
        fun instructions(instructions: String?) = instructions(JsonField.ofNullable(instructions))

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
            this.instructions = instructions
        }

        /**
         * Filesystem path where the store is mounted in the session container, e.g.
         * /mnt/memory/user-preferences. Derived from the store's name. Output-only.
         */
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

        /**
         * Display name of the memory store, snapshotted at attach time. Later edits to the store's
         * name do not propagate to this resource.
         */
        fun name(name: String?) = name(JsonField.ofNullable(name))

        /** Alias for calling [Builder.name] with `name.orElse(null)`. */
        fun name(name: Optional<String>) = name(name.getOrNull())

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

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
         * Returns an immutable instance of [BetaManagedAgentsMemoryStoreResource].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .memoryStoreId()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMemoryStoreResource =
            BetaManagedAgentsMemoryStoreResource(
                checkRequired("memoryStoreId", memoryStoreId),
                checkRequired("type", type),
                access,
                description,
                instructions,
                mountPath,
                name,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsMemoryStoreResource = apply {
        if (validated) {
            return@apply
        }

        memoryStoreId()
        type().validate()
        access().ifPresent { it.validate() }
        description()
        instructions()
        mountPath()
        name()
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
        (if (memoryStoreId.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (access.asKnown().getOrNull()?.validity() ?: 0) +
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (instructions.asKnown().isPresent) 1 else 0) +
            (if (mountPath.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0)

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

            @JvmField val MEMORY_STORE = of("memory_store")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MEMORY_STORE
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
            MEMORY_STORE,
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
                MEMORY_STORE -> Value.MEMORY_STORE
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
                MEMORY_STORE -> Known.MEMORY_STORE
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

    /** Access mode for an attached memory store. */
    class Access @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val READ_WRITE = of("read_write")

            @JvmField val READ_ONLY = of("read_only")

            @JvmStatic fun of(value: String) = Access(JsonField.of(value))
        }

        /** An enum containing [Access]'s known values. */
        enum class Known {
            READ_WRITE,
            READ_ONLY,
        }

        /**
         * An enum containing [Access]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Access] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            READ_WRITE,
            READ_ONLY,
            /** An enum member indicating that [Access] was instantiated with an unknown value. */
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
                READ_WRITE -> Value.READ_WRITE
                READ_ONLY -> Value.READ_ONLY
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
                READ_WRITE -> Known.READ_WRITE
                READ_ONLY -> Known.READ_ONLY
                else -> throw AnthropicInvalidDataException("Unknown Access: $value")
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

        fun validate(): Access = apply {
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

            return other is Access && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMemoryStoreResource &&
            memoryStoreId == other.memoryStoreId &&
            type == other.type &&
            access == other.access &&
            description == other.description &&
            instructions == other.instructions &&
            mountPath == other.mountPath &&
            name == other.name &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            memoryStoreId,
            type,
            access,
            description,
            instructions,
            mountPath,
            name,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMemoryStoreResource{memoryStoreId=$memoryStoreId, type=$type, access=$access, description=$description, instructions=$instructions, mountPath=$mountPath, name=$name, additionalProperties=$additionalProperties}"
}
