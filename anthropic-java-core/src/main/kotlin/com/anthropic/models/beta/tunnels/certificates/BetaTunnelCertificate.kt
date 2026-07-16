// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels.certificates

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

/** A CA certificate attached to a tunnel. */
class BetaTunnelCertificate
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val fingerprint: JsonField<String>,
    private val tunnelId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("fingerprint")
        @ExcludeMissing
        fingerprint: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tunnel_id") @ExcludeMissing tunnelId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, archivedAt, createdAt, expiresAt, fingerprint, tunnelId, type, mutableMapOf())

    /**
     * Unique identifier for the certificate, prefixed with `tcrt_`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> = archivedAt.getOptional("archived_at")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun expiresAt(): Optional<OffsetDateTime> = expiresAt.getOptional("expires_at")

    /**
     * Lowercase hex SHA-256 fingerprint of the certificate's DER encoding.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun fingerprint(): String = fingerprint.getRequired("fingerprint")

    /**
     * ID of the tunnel the certificate is registered against.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tunnelId(): String = tunnelId.getRequired("tunnel_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tunnel_certificate")
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
     * Returns the raw JSON value of [archivedAt].
     *
     * Unlike [archivedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("archived_at")
    @ExcludeMissing
    fun _archivedAt(): JsonField<OffsetDateTime> = archivedAt

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [expiresAt].
     *
     * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /**
     * Returns the raw JSON value of [fingerprint].
     *
     * Unlike [fingerprint], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("fingerprint") @ExcludeMissing fun _fingerprint(): JsonField<String> = fingerprint

    /**
     * Returns the raw JSON value of [tunnelId].
     *
     * Unlike [tunnelId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tunnel_id") @ExcludeMissing fun _tunnelId(): JsonField<String> = tunnelId

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
         * Returns a mutable builder for constructing an instance of [BetaTunnelCertificate].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .createdAt()
         * .expiresAt()
         * .fingerprint()
         * .tunnelId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTunnelCertificate]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var fingerprint: JsonField<String>? = null
        private var tunnelId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("tunnel_certificate")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTunnelCertificate: BetaTunnelCertificate) = apply {
            id = betaTunnelCertificate.id
            archivedAt = betaTunnelCertificate.archivedAt
            createdAt = betaTunnelCertificate.createdAt
            expiresAt = betaTunnelCertificate.expiresAt
            fingerprint = betaTunnelCertificate.fingerprint
            tunnelId = betaTunnelCertificate.tunnelId
            type = betaTunnelCertificate.type
            additionalProperties = betaTunnelCertificate.additionalProperties.toMutableMap()
        }

        /** Unique identifier for the certificate, prefixed with `tcrt_`. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** A timestamp in RFC 3339 format */
        fun archivedAt(archivedAt: OffsetDateTime?) = archivedAt(JsonField.ofNullable(archivedAt))

        /** Alias for calling [Builder.archivedAt] with `archivedAt.orElse(null)`. */
        fun archivedAt(archivedAt: Optional<OffsetDateTime>) = archivedAt(archivedAt.getOrNull())

        /**
         * Sets [Builder.archivedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun archivedAt(archivedAt: JsonField<OffsetDateTime>) = apply {
            this.archivedAt = archivedAt
        }

        /** A timestamp in RFC 3339 format */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** A timestamp in RFC 3339 format */
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

        /** Lowercase hex SHA-256 fingerprint of the certificate's DER encoding. */
        fun fingerprint(fingerprint: String) = fingerprint(JsonField.of(fingerprint))

        /**
         * Sets [Builder.fingerprint] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fingerprint] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun fingerprint(fingerprint: JsonField<String>) = apply { this.fingerprint = fingerprint }

        /** ID of the tunnel the certificate is registered against. */
        fun tunnelId(tunnelId: String) = tunnelId(JsonField.of(tunnelId))

        /**
         * Sets [Builder.tunnelId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tunnelId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tunnelId(tunnelId: JsonField<String>) = apply { this.tunnelId = tunnelId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tunnel_certificate")
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
         * Returns an immutable instance of [BetaTunnelCertificate].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .createdAt()
         * .expiresAt()
         * .fingerprint()
         * .tunnelId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaTunnelCertificate =
            BetaTunnelCertificate(
                checkRequired("id", id),
                checkRequired("archivedAt", archivedAt),
                checkRequired("createdAt", createdAt),
                checkRequired("expiresAt", expiresAt),
                checkRequired("fingerprint", fingerprint),
                checkRequired("tunnelId", tunnelId),
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
    fun validate(): BetaTunnelCertificate = apply {
        if (validated) {
            return@apply
        }

        id()
        archivedAt()
        createdAt()
        expiresAt()
        fingerprint()
        tunnelId()
        _type().let {
            if (it != JsonValue.from("tunnel_certificate")) {
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
            (if (archivedAt.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (expiresAt.asKnown().isPresent) 1 else 0) +
            (if (fingerprint.asKnown().isPresent) 1 else 0) +
            (if (tunnelId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("tunnel_certificate")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTunnelCertificate &&
            id == other.id &&
            archivedAt == other.archivedAt &&
            createdAt == other.createdAt &&
            expiresAt == other.expiresAt &&
            fingerprint == other.fingerprint &&
            tunnelId == other.tunnelId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            archivedAt,
            createdAt,
            expiresAt,
            fingerprint,
            tunnelId,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTunnelCertificate{id=$id, archivedAt=$archivedAt, createdAt=$createdAt, expiresAt=$expiresAt, fingerprint=$fingerprint, tunnelId=$tunnelId, type=$type, additionalProperties=$additionalProperties}"
}
