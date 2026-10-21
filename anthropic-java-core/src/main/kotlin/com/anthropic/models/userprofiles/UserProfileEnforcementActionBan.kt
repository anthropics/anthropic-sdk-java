package com.anthropic.models.userprofiles

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Anthropic has stopped serving the end user where the ban applies and does not plan to serve them
 * there again.
 */
class UserProfileEnforcementActionBan
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val action: JsonField<UserProfileEnforcementActionNextStep>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val openedAt: JsonField<OffsetDateTime>,
    private val reason: JsonField<UserProfileEnforcementActionReason>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("action")
        @ExcludeMissing
        action: JsonField<UserProfileEnforcementActionNextStep> = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("opened_at")
        @ExcludeMissing
        openedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("reason")
        @ExcludeMissing
        reason: JsonField<UserProfileEnforcementActionReason> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, action, expiresAt, openedAt, reason, type, mutableMapOf())

    /**
     * The action's ID (`enac_...`), stable while the action is open.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Always `null`: the end user cannot clear a ban.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun action(): Optional<UserProfileEnforcementActionNextStep> = action.getOptional("action")

    /**
     * Always `null`: a ban has no end date.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun expiresAt(): Optional<OffsetDateTime> = expiresAt.getOptional("expires_at")

    /**
     * When Anthropic opened the action, in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun openedAt(): OffsetDateTime = openedAt.getRequired("opened_at")

    /**
     * Why Anthropic placed the limit: `unsupported_region`, `fraud_or_abuse`, `high_harm`,
     * `usage_policy_violation`, `account_compromise` or `unspecified`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun reason(): UserProfileEnforcementActionReason = reason.getRequired("reason")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("ban")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [action].
     *
     * Unlike [action], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("action")
    @ExcludeMissing
    fun _action(): JsonField<UserProfileEnforcementActionNextStep> = action

    /**
     * Returns the raw JSON value of [expiresAt].
     *
     * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /**
     * Returns the raw JSON value of [openedAt].
     *
     * Unlike [openedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("opened_at") @ExcludeMissing fun _openedAt(): JsonField<OffsetDateTime> = openedAt

    /**
     * Returns the raw JSON value of [reason].
     *
     * Unlike [reason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reason")
    @ExcludeMissing
    fun _reason(): JsonField<UserProfileEnforcementActionReason> = reason

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
         * [UserProfileEnforcementActionBan].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .action()
         * .expiresAt()
         * .openedAt()
         * .reason()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfileEnforcementActionBan]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var action: JsonField<UserProfileEnforcementActionNextStep>? = null
        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var openedAt: JsonField<OffsetDateTime>? = null
        private var reason: JsonField<UserProfileEnforcementActionReason>? = null
        private var type: JsonValue = JsonValue.from("ban")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(userProfileEnforcementActionBan: UserProfileEnforcementActionBan) =
            apply {
                id = userProfileEnforcementActionBan.id
                action = userProfileEnforcementActionBan.action
                expiresAt = userProfileEnforcementActionBan.expiresAt
                openedAt = userProfileEnforcementActionBan.openedAt
                reason = userProfileEnforcementActionBan.reason
                type = userProfileEnforcementActionBan.type
                additionalProperties =
                    userProfileEnforcementActionBan.additionalProperties.toMutableMap()
            }

        /** The action's ID (`enac_...`), stable while the action is open. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Always `null`: the end user cannot clear a ban. */
        fun action(action: UserProfileEnforcementActionNextStep?) =
            action(JsonField.ofNullable(action))

        /** Alias for calling [Builder.action] with `action.orElse(null)`. */
        fun action(action: Optional<UserProfileEnforcementActionNextStep>) =
            action(action.getOrNull())

        /**
         * Sets [Builder.action] to an arbitrary JSON value.
         *
         * You should usually call [Builder.action] with a well-typed
         * [UserProfileEnforcementActionNextStep] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun action(action: JsonField<UserProfileEnforcementActionNextStep>) = apply {
            this.action = action
        }

        /** Always `null`: a ban has no end date. */
        fun expiresAt(expiresAt: OffsetDateTime?) = expiresAt(JsonField.ofNullable(expiresAt))

        /** Alias for calling [Builder.expiresAt] with `expiresAt.orElse(null)`. */
        fun expiresAt(expiresAt: Optional<OffsetDateTime>) = expiresAt(expiresAt.getOrNull())

        /**
         * Sets [Builder.expiresAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.expiresAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply { this.expiresAt = expiresAt }

        /** When Anthropic opened the action, in RFC 3339 format. */
        fun openedAt(openedAt: OffsetDateTime) = openedAt(JsonField.of(openedAt))

        /**
         * Sets [Builder.openedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.openedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun openedAt(openedAt: JsonField<OffsetDateTime>) = apply { this.openedAt = openedAt }

        /**
         * Why Anthropic placed the limit: `unsupported_region`, `fraud_or_abuse`, `high_harm`,
         * `usage_policy_violation`, `account_compromise` or `unspecified`.
         */
        fun reason(reason: UserProfileEnforcementActionReason) = reason(JsonField.of(reason))

        /**
         * Sets [Builder.reason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.reason] with a well-typed
         * [UserProfileEnforcementActionReason] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun reason(reason: JsonField<UserProfileEnforcementActionReason>) = apply {
            this.reason = reason
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("ban")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [UserProfileEnforcementActionBan].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .action()
         * .expiresAt()
         * .openedAt()
         * .reason()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserProfileEnforcementActionBan =
            UserProfileEnforcementActionBan(
                checkRequired("id", id),
                checkRequired("action", action),
                checkRequired("expiresAt", expiresAt),
                checkRequired("openedAt", openedAt),
                checkRequired("reason", reason),
                type,
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
    fun validate(): UserProfileEnforcementActionBan = apply {
        if (validated) {
            return@apply
        }

        id()
        action().ifPresent { it.validate() }
        expiresAt()
        openedAt()
        reason().validate()
        _type().let {
            if (it != JsonValue.from("ban")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (action.asKnown().getOrNull()?.validity() ?: 0) +
            (if (expiresAt.asKnown().isPresent) 1 else 0) +
            (if (openedAt.asKnown().isPresent) 1 else 0) +
            (reason.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("ban")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileEnforcementActionBan &&
            id == other.id &&
            action == other.action &&
            expiresAt == other.expiresAt &&
            openedAt == other.openedAt &&
            reason == other.reason &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, action, expiresAt, openedAt, reason, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserProfileEnforcementActionBan{id=$id, action=$action, expiresAt=$expiresAt, openedAt=$openedAt, reason=$reason, type=$type, additionalProperties=$additionalProperties}"
}
