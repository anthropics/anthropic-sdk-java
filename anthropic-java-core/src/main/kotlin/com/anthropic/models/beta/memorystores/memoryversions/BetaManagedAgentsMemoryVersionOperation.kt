package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/**
 * The kind of mutation a `memory_version` records. Every non-no-op mutation to a memory appends
 * exactly one version row with one of these values.
 */
class BetaManagedAgentsMemoryVersionOperation
@JsonCreator
private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        /** The memory was created. The first version in any memory's lineage. */
        @JvmField val CREATED = of("created")

        /**
         * The memory's `content`, `path`, or both were changed via update. Writes the agent makes
         * through the filesystem mount also appear as `modified`.
         */
        @JvmField val MODIFIED = of("modified")

        /**
         * The memory was deleted. The `content`, `content_size_bytes`, and `content_sha256` fields
         * are `null` on this version. The preceding version, while it is retained, records the
         * deleted content's size and hash.
         */
        @JvmField val DELETED = of("deleted")

        @JvmStatic
        fun of(value: String) = BetaManagedAgentsMemoryVersionOperation(JsonField.of(value))

        @JvmSynthetic
        internal fun of(value: JsonField<String>): BetaManagedAgentsMemoryVersionOperation =
            value.asString().getOrNull()?.let { of(it) }
                ?: BetaManagedAgentsMemoryVersionOperation(value)
    }

    /** An enum containing [BetaManagedAgentsMemoryVersionOperation]'s known values. */
    enum class Known {
        /** The memory was created. The first version in any memory's lineage. */
        CREATED,
        /**
         * The memory's `content`, `path`, or both were changed via update. Writes the agent makes
         * through the filesystem mount also appear as `modified`.
         */
        MODIFIED,
        /**
         * The memory was deleted. The `content`, `content_size_bytes`, and `content_sha256` fields
         * are `null` on this version. The preceding version, while it is retained, records the
         * deleted content's size and hash.
         */
        DELETED,
    }

    /**
     * An enum containing [BetaManagedAgentsMemoryVersionOperation]'s known values, as well as an
     * [_UNKNOWN] member.
     *
     * An instance of [BetaManagedAgentsMemoryVersionOperation] can contain an unknown value in a
     * couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        /** The memory was created. The first version in any memory's lineage. */
        CREATED,
        /**
         * The memory's `content`, `path`, or both were changed via update. Writes the agent makes
         * through the filesystem mount also appear as `modified`.
         */
        MODIFIED,
        /**
         * The memory was deleted. The `content`, `content_size_bytes`, and `content_sha256` fields
         * are `null` on this version. The preceding version, while it is retained, records the
         * deleted content's size and hash.
         */
        DELETED,
        /**
         * An enum member indicating that [BetaManagedAgentsMemoryVersionOperation] was instantiated
         * with an unknown value.
         */
        _UNKNOWN,
    }

    /**
     * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN] if
     * the class was instantiated with an unknown value.
     *
     * Use the [known] method instead if you're certain the value is always known or if you want to
     * throw for the unknown case.
     */
    fun value(): Value =
        when (this) {
            CREATED -> Value.CREATED
            MODIFIED -> Value.MODIFIED
            DELETED -> Value.DELETED
            else -> Value._UNKNOWN
        }

    /**
     * Returns an enum member corresponding to this class instance's value.
     *
     * Use the [value] method instead if you're uncertain the value is always known and don't want
     * to throw for the unknown case.
     *
     * @throws AnthropicInvalidDataException if this class instance's value is a not a known member.
     */
    fun known(): Known =
        when (this) {
            CREATED -> Known.CREATED
            MODIFIED -> Known.MODIFIED
            DELETED -> Known.DELETED
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsMemoryVersionOperation: $value"
                )
        }

    /**
     * Returns this class instance's primitive wire representation.
     *
     * This differs from the [toString] method because that method is primarily for debugging and
     * generally doesn't throw.
     *
     * @throws AnthropicInvalidDataException if this class instance's value does not have the
     *   expected primitive type.
     */
    fun asString(): String =
        _value().asString().orElseThrow { AnthropicInvalidDataException("Value is not a String") }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaManagedAgentsMemoryVersionOperation = apply {
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMemoryVersionOperation && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
