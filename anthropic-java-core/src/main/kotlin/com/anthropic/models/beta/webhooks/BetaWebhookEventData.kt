// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

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

@JsonDeserialize(using = BetaWebhookEventData.Deserializer::class)
@JsonSerialize(using = BetaWebhookEventData.Serializer::class)
class BetaWebhookEventData
private constructor(
    private val sessionCreated: BetaWebhookSessionCreatedEventData? = null,
    private val sessionPending: BetaWebhookSessionPendingEventData? = null,
    private val sessionRunning: BetaWebhookSessionRunningEventData? = null,
    private val sessionIdled: BetaWebhookSessionIdledEventData? = null,
    private val sessionRequiresAction: BetaWebhookSessionRequiresActionEventData? = null,
    private val sessionArchived: BetaWebhookSessionArchivedEventData? = null,
    private val sessionDeleted: BetaWebhookSessionDeletedEventData? = null,
    private val sessionStatusScheduled: BetaWebhookSessionStatusScheduledEventData? = null,
    private val sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData? = null,
    private val sessionStatusIdled: BetaWebhookSessionStatusIdledEventData? = null,
    private val sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData? = null,
    private val sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData? = null,
    private val sessionThreadIdled: BetaWebhookSessionThreadIdledEventData? = null,
    private val sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData? = null,
    private val sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData? =
        null,
    private val vaultCreated: BetaWebhookVaultCreatedEventData? = null,
    private val vaultArchived: BetaWebhookVaultArchivedEventData? = null,
    private val vaultDeleted: BetaWebhookVaultDeletedEventData? = null,
    private val vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData? = null,
    private val vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData? = null,
    private val vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData? = null,
    private val vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData? =
        null,
    private val _json: JsonValue? = null,
) {

    fun sessionCreated(): Optional<BetaWebhookSessionCreatedEventData> =
        Optional.ofNullable(sessionCreated)

    fun sessionPending(): Optional<BetaWebhookSessionPendingEventData> =
        Optional.ofNullable(sessionPending)

    fun sessionRunning(): Optional<BetaWebhookSessionRunningEventData> =
        Optional.ofNullable(sessionRunning)

    fun sessionIdled(): Optional<BetaWebhookSessionIdledEventData> =
        Optional.ofNullable(sessionIdled)

    fun sessionRequiresAction(): Optional<BetaWebhookSessionRequiresActionEventData> =
        Optional.ofNullable(sessionRequiresAction)

    fun sessionArchived(): Optional<BetaWebhookSessionArchivedEventData> =
        Optional.ofNullable(sessionArchived)

    fun sessionDeleted(): Optional<BetaWebhookSessionDeletedEventData> =
        Optional.ofNullable(sessionDeleted)

    fun sessionStatusScheduled(): Optional<BetaWebhookSessionStatusScheduledEventData> =
        Optional.ofNullable(sessionStatusScheduled)

    fun sessionStatusRunStarted(): Optional<BetaWebhookSessionStatusRunStartedEventData> =
        Optional.ofNullable(sessionStatusRunStarted)

    fun sessionStatusIdled(): Optional<BetaWebhookSessionStatusIdledEventData> =
        Optional.ofNullable(sessionStatusIdled)

    fun sessionStatusTerminated(): Optional<BetaWebhookSessionStatusTerminatedEventData> =
        Optional.ofNullable(sessionStatusTerminated)

    fun sessionThreadCreated(): Optional<BetaWebhookSessionThreadCreatedEventData> =
        Optional.ofNullable(sessionThreadCreated)

    fun sessionThreadIdled(): Optional<BetaWebhookSessionThreadIdledEventData> =
        Optional.ofNullable(sessionThreadIdled)

    fun sessionThreadTerminated(): Optional<BetaWebhookSessionThreadTerminatedEventData> =
        Optional.ofNullable(sessionThreadTerminated)

    fun sessionOutcomeEvaluationEnded():
        Optional<BetaWebhookSessionOutcomeEvaluationEndedEventData> =
        Optional.ofNullable(sessionOutcomeEvaluationEnded)

    fun vaultCreated(): Optional<BetaWebhookVaultCreatedEventData> =
        Optional.ofNullable(vaultCreated)

    fun vaultArchived(): Optional<BetaWebhookVaultArchivedEventData> =
        Optional.ofNullable(vaultArchived)

    fun vaultDeleted(): Optional<BetaWebhookVaultDeletedEventData> =
        Optional.ofNullable(vaultDeleted)

    fun vaultCredentialCreated(): Optional<BetaWebhookVaultCredentialCreatedEventData> =
        Optional.ofNullable(vaultCredentialCreated)

    fun vaultCredentialArchived(): Optional<BetaWebhookVaultCredentialArchivedEventData> =
        Optional.ofNullable(vaultCredentialArchived)

    fun vaultCredentialDeleted(): Optional<BetaWebhookVaultCredentialDeletedEventData> =
        Optional.ofNullable(vaultCredentialDeleted)

    fun vaultCredentialRefreshFailed(): Optional<BetaWebhookVaultCredentialRefreshFailedEventData> =
        Optional.ofNullable(vaultCredentialRefreshFailed)

    fun isSessionCreated(): Boolean = sessionCreated != null

    fun isSessionPending(): Boolean = sessionPending != null

    fun isSessionRunning(): Boolean = sessionRunning != null

    fun isSessionIdled(): Boolean = sessionIdled != null

    fun isSessionRequiresAction(): Boolean = sessionRequiresAction != null

    fun isSessionArchived(): Boolean = sessionArchived != null

    fun isSessionDeleted(): Boolean = sessionDeleted != null

    fun isSessionStatusScheduled(): Boolean = sessionStatusScheduled != null

    fun isSessionStatusRunStarted(): Boolean = sessionStatusRunStarted != null

    fun isSessionStatusIdled(): Boolean = sessionStatusIdled != null

    fun isSessionStatusTerminated(): Boolean = sessionStatusTerminated != null

    fun isSessionThreadCreated(): Boolean = sessionThreadCreated != null

    fun isSessionThreadIdled(): Boolean = sessionThreadIdled != null

    fun isSessionThreadTerminated(): Boolean = sessionThreadTerminated != null

    fun isSessionOutcomeEvaluationEnded(): Boolean = sessionOutcomeEvaluationEnded != null

    fun isVaultCreated(): Boolean = vaultCreated != null

    fun isVaultArchived(): Boolean = vaultArchived != null

    fun isVaultDeleted(): Boolean = vaultDeleted != null

    fun isVaultCredentialCreated(): Boolean = vaultCredentialCreated != null

    fun isVaultCredentialArchived(): Boolean = vaultCredentialArchived != null

    fun isVaultCredentialDeleted(): Boolean = vaultCredentialDeleted != null

    fun isVaultCredentialRefreshFailed(): Boolean = vaultCredentialRefreshFailed != null

    fun asSessionCreated(): BetaWebhookSessionCreatedEventData =
        sessionCreated.getOrThrow("sessionCreated")

    fun asSessionPending(): BetaWebhookSessionPendingEventData =
        sessionPending.getOrThrow("sessionPending")

    fun asSessionRunning(): BetaWebhookSessionRunningEventData =
        sessionRunning.getOrThrow("sessionRunning")

    fun asSessionIdled(): BetaWebhookSessionIdledEventData = sessionIdled.getOrThrow("sessionIdled")

    fun asSessionRequiresAction(): BetaWebhookSessionRequiresActionEventData =
        sessionRequiresAction.getOrThrow("sessionRequiresAction")

    fun asSessionArchived(): BetaWebhookSessionArchivedEventData =
        sessionArchived.getOrThrow("sessionArchived")

    fun asSessionDeleted(): BetaWebhookSessionDeletedEventData =
        sessionDeleted.getOrThrow("sessionDeleted")

    fun asSessionStatusScheduled(): BetaWebhookSessionStatusScheduledEventData =
        sessionStatusScheduled.getOrThrow("sessionStatusScheduled")

    fun asSessionStatusRunStarted(): BetaWebhookSessionStatusRunStartedEventData =
        sessionStatusRunStarted.getOrThrow("sessionStatusRunStarted")

    fun asSessionStatusIdled(): BetaWebhookSessionStatusIdledEventData =
        sessionStatusIdled.getOrThrow("sessionStatusIdled")

    fun asSessionStatusTerminated(): BetaWebhookSessionStatusTerminatedEventData =
        sessionStatusTerminated.getOrThrow("sessionStatusTerminated")

    fun asSessionThreadCreated(): BetaWebhookSessionThreadCreatedEventData =
        sessionThreadCreated.getOrThrow("sessionThreadCreated")

    fun asSessionThreadIdled(): BetaWebhookSessionThreadIdledEventData =
        sessionThreadIdled.getOrThrow("sessionThreadIdled")

    fun asSessionThreadTerminated(): BetaWebhookSessionThreadTerminatedEventData =
        sessionThreadTerminated.getOrThrow("sessionThreadTerminated")

    fun asSessionOutcomeEvaluationEnded(): BetaWebhookSessionOutcomeEvaluationEndedEventData =
        sessionOutcomeEvaluationEnded.getOrThrow("sessionOutcomeEvaluationEnded")

    fun asVaultCreated(): BetaWebhookVaultCreatedEventData = vaultCreated.getOrThrow("vaultCreated")

    fun asVaultArchived(): BetaWebhookVaultArchivedEventData =
        vaultArchived.getOrThrow("vaultArchived")

    fun asVaultDeleted(): BetaWebhookVaultDeletedEventData = vaultDeleted.getOrThrow("vaultDeleted")

    fun asVaultCredentialCreated(): BetaWebhookVaultCredentialCreatedEventData =
        vaultCredentialCreated.getOrThrow("vaultCredentialCreated")

    fun asVaultCredentialArchived(): BetaWebhookVaultCredentialArchivedEventData =
        vaultCredentialArchived.getOrThrow("vaultCredentialArchived")

    fun asVaultCredentialDeleted(): BetaWebhookVaultCredentialDeletedEventData =
        vaultCredentialDeleted.getOrThrow("vaultCredentialDeleted")

    fun asVaultCredentialRefreshFailed(): BetaWebhookVaultCredentialRefreshFailedEventData =
        vaultCredentialRefreshFailed.getOrThrow("vaultCredentialRefreshFailed")

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
     * Optional<String> result = betaWebhookEventData.accept(new BetaWebhookEventData.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitSessionCreated(BetaWebhookSessionCreatedEventData sessionCreated) {
     *         return Optional.of(sessionCreated.toString());
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
            sessionCreated != null -> visitor.visitSessionCreated(sessionCreated)
            sessionPending != null -> visitor.visitSessionPending(sessionPending)
            sessionRunning != null -> visitor.visitSessionRunning(sessionRunning)
            sessionIdled != null -> visitor.visitSessionIdled(sessionIdled)
            sessionRequiresAction != null ->
                visitor.visitSessionRequiresAction(sessionRequiresAction)
            sessionArchived != null -> visitor.visitSessionArchived(sessionArchived)
            sessionDeleted != null -> visitor.visitSessionDeleted(sessionDeleted)
            sessionStatusScheduled != null ->
                visitor.visitSessionStatusScheduled(sessionStatusScheduled)
            sessionStatusRunStarted != null ->
                visitor.visitSessionStatusRunStarted(sessionStatusRunStarted)
            sessionStatusIdled != null -> visitor.visitSessionStatusIdled(sessionStatusIdled)
            sessionStatusTerminated != null ->
                visitor.visitSessionStatusTerminated(sessionStatusTerminated)
            sessionThreadCreated != null -> visitor.visitSessionThreadCreated(sessionThreadCreated)
            sessionThreadIdled != null -> visitor.visitSessionThreadIdled(sessionThreadIdled)
            sessionThreadTerminated != null ->
                visitor.visitSessionThreadTerminated(sessionThreadTerminated)
            sessionOutcomeEvaluationEnded != null ->
                visitor.visitSessionOutcomeEvaluationEnded(sessionOutcomeEvaluationEnded)
            vaultCreated != null -> visitor.visitVaultCreated(vaultCreated)
            vaultArchived != null -> visitor.visitVaultArchived(vaultArchived)
            vaultDeleted != null -> visitor.visitVaultDeleted(vaultDeleted)
            vaultCredentialCreated != null ->
                visitor.visitVaultCredentialCreated(vaultCredentialCreated)
            vaultCredentialArchived != null ->
                visitor.visitVaultCredentialArchived(vaultCredentialArchived)
            vaultCredentialDeleted != null ->
                visitor.visitVaultCredentialDeleted(vaultCredentialDeleted)
            vaultCredentialRefreshFailed != null ->
                visitor.visitVaultCredentialRefreshFailed(vaultCredentialRefreshFailed)
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
    fun validate(): BetaWebhookEventData = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ) {
                    sessionCreated.validate()
                }

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ) {
                    sessionPending.validate()
                }

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ) {
                    sessionRunning.validate()
                }

                override fun visitSessionIdled(sessionIdled: BetaWebhookSessionIdledEventData) {
                    sessionIdled.validate()
                }

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ) {
                    sessionRequiresAction.validate()
                }

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ) {
                    sessionArchived.validate()
                }

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ) {
                    sessionDeleted.validate()
                }

                override fun visitSessionStatusScheduled(
                    sessionStatusScheduled: BetaWebhookSessionStatusScheduledEventData
                ) {
                    sessionStatusScheduled.validate()
                }

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ) {
                    sessionStatusRunStarted.validate()
                }

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ) {
                    sessionStatusIdled.validate()
                }

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ) {
                    sessionStatusTerminated.validate()
                }

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ) {
                    sessionThreadCreated.validate()
                }

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ) {
                    sessionThreadIdled.validate()
                }

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ) {
                    sessionThreadTerminated.validate()
                }

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ) {
                    sessionOutcomeEvaluationEnded.validate()
                }

                override fun visitVaultCreated(vaultCreated: BetaWebhookVaultCreatedEventData) {
                    vaultCreated.validate()
                }

                override fun visitVaultArchived(vaultArchived: BetaWebhookVaultArchivedEventData) {
                    vaultArchived.validate()
                }

                override fun visitVaultDeleted(vaultDeleted: BetaWebhookVaultDeletedEventData) {
                    vaultDeleted.validate()
                }

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ) {
                    vaultCredentialCreated.validate()
                }

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ) {
                    vaultCredentialArchived.validate()
                }

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ) {
                    vaultCredentialDeleted.validate()
                }

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ) {
                    vaultCredentialRefreshFailed.validate()
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
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ) = sessionCreated.validity()

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ) = sessionPending.validity()

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ) = sessionRunning.validity()

                override fun visitSessionIdled(sessionIdled: BetaWebhookSessionIdledEventData) =
                    sessionIdled.validity()

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ) = sessionRequiresAction.validity()

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ) = sessionArchived.validity()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ) = sessionDeleted.validity()

                override fun visitSessionStatusScheduled(
                    sessionStatusScheduled: BetaWebhookSessionStatusScheduledEventData
                ) = sessionStatusScheduled.validity()

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ) = sessionStatusRunStarted.validity()

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ) = sessionStatusIdled.validity()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ) = sessionStatusTerminated.validity()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ) = sessionThreadCreated.validity()

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ) = sessionThreadIdled.validity()

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ) = sessionThreadTerminated.validity()

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ) = sessionOutcomeEvaluationEnded.validity()

                override fun visitVaultCreated(vaultCreated: BetaWebhookVaultCreatedEventData) =
                    vaultCreated.validity()

                override fun visitVaultArchived(vaultArchived: BetaWebhookVaultArchivedEventData) =
                    vaultArchived.validity()

                override fun visitVaultDeleted(vaultDeleted: BetaWebhookVaultDeletedEventData) =
                    vaultDeleted.validity()

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ) = vaultCredentialCreated.validity()

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ) = vaultCredentialArchived.validity()

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ) = vaultCredentialDeleted.validity()

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ) = vaultCredentialRefreshFailed.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWebhookEventData &&
            sessionCreated == other.sessionCreated &&
            sessionPending == other.sessionPending &&
            sessionRunning == other.sessionRunning &&
            sessionIdled == other.sessionIdled &&
            sessionRequiresAction == other.sessionRequiresAction &&
            sessionArchived == other.sessionArchived &&
            sessionDeleted == other.sessionDeleted &&
            sessionStatusScheduled == other.sessionStatusScheduled &&
            sessionStatusRunStarted == other.sessionStatusRunStarted &&
            sessionStatusIdled == other.sessionStatusIdled &&
            sessionStatusTerminated == other.sessionStatusTerminated &&
            sessionThreadCreated == other.sessionThreadCreated &&
            sessionThreadIdled == other.sessionThreadIdled &&
            sessionThreadTerminated == other.sessionThreadTerminated &&
            sessionOutcomeEvaluationEnded == other.sessionOutcomeEvaluationEnded &&
            vaultCreated == other.vaultCreated &&
            vaultArchived == other.vaultArchived &&
            vaultDeleted == other.vaultDeleted &&
            vaultCredentialCreated == other.vaultCredentialCreated &&
            vaultCredentialArchived == other.vaultCredentialArchived &&
            vaultCredentialDeleted == other.vaultCredentialDeleted &&
            vaultCredentialRefreshFailed == other.vaultCredentialRefreshFailed
    }

    override fun hashCode(): Int =
        Objects.hash(
            sessionCreated,
            sessionPending,
            sessionRunning,
            sessionIdled,
            sessionRequiresAction,
            sessionArchived,
            sessionDeleted,
            sessionStatusScheduled,
            sessionStatusRunStarted,
            sessionStatusIdled,
            sessionStatusTerminated,
            sessionThreadCreated,
            sessionThreadIdled,
            sessionThreadTerminated,
            sessionOutcomeEvaluationEnded,
            vaultCreated,
            vaultArchived,
            vaultDeleted,
            vaultCredentialCreated,
            vaultCredentialArchived,
            vaultCredentialDeleted,
            vaultCredentialRefreshFailed,
        )

    override fun toString(): String =
        when {
            sessionCreated != null -> "BetaWebhookEventData{sessionCreated=$sessionCreated}"
            sessionPending != null -> "BetaWebhookEventData{sessionPending=$sessionPending}"
            sessionRunning != null -> "BetaWebhookEventData{sessionRunning=$sessionRunning}"
            sessionIdled != null -> "BetaWebhookEventData{sessionIdled=$sessionIdled}"
            sessionRequiresAction != null ->
                "BetaWebhookEventData{sessionRequiresAction=$sessionRequiresAction}"
            sessionArchived != null -> "BetaWebhookEventData{sessionArchived=$sessionArchived}"
            sessionDeleted != null -> "BetaWebhookEventData{sessionDeleted=$sessionDeleted}"
            sessionStatusScheduled != null ->
                "BetaWebhookEventData{sessionStatusScheduled=$sessionStatusScheduled}"
            sessionStatusRunStarted != null ->
                "BetaWebhookEventData{sessionStatusRunStarted=$sessionStatusRunStarted}"
            sessionStatusIdled != null ->
                "BetaWebhookEventData{sessionStatusIdled=$sessionStatusIdled}"
            sessionStatusTerminated != null ->
                "BetaWebhookEventData{sessionStatusTerminated=$sessionStatusTerminated}"
            sessionThreadCreated != null ->
                "BetaWebhookEventData{sessionThreadCreated=$sessionThreadCreated}"
            sessionThreadIdled != null ->
                "BetaWebhookEventData{sessionThreadIdled=$sessionThreadIdled}"
            sessionThreadTerminated != null ->
                "BetaWebhookEventData{sessionThreadTerminated=$sessionThreadTerminated}"
            sessionOutcomeEvaluationEnded != null ->
                "BetaWebhookEventData{sessionOutcomeEvaluationEnded=$sessionOutcomeEvaluationEnded}"
            vaultCreated != null -> "BetaWebhookEventData{vaultCreated=$vaultCreated}"
            vaultArchived != null -> "BetaWebhookEventData{vaultArchived=$vaultArchived}"
            vaultDeleted != null -> "BetaWebhookEventData{vaultDeleted=$vaultDeleted}"
            vaultCredentialCreated != null ->
                "BetaWebhookEventData{vaultCredentialCreated=$vaultCredentialCreated}"
            vaultCredentialArchived != null ->
                "BetaWebhookEventData{vaultCredentialArchived=$vaultCredentialArchived}"
            vaultCredentialDeleted != null ->
                "BetaWebhookEventData{vaultCredentialDeleted=$vaultCredentialDeleted}"
            vaultCredentialRefreshFailed != null ->
                "BetaWebhookEventData{vaultCredentialRefreshFailed=$vaultCredentialRefreshFailed}"
            _json != null -> "BetaWebhookEventData{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaWebhookEventData")
        }

    companion object {

        @JvmStatic
        fun ofSessionCreated(sessionCreated: BetaWebhookSessionCreatedEventData) =
            BetaWebhookEventData(sessionCreated = sessionCreated)

        @JvmStatic
        fun ofSessionPending(sessionPending: BetaWebhookSessionPendingEventData) =
            BetaWebhookEventData(sessionPending = sessionPending)

        @JvmStatic
        fun ofSessionRunning(sessionRunning: BetaWebhookSessionRunningEventData) =
            BetaWebhookEventData(sessionRunning = sessionRunning)

        @JvmStatic
        fun ofSessionIdled(sessionIdled: BetaWebhookSessionIdledEventData) =
            BetaWebhookEventData(sessionIdled = sessionIdled)

        @JvmStatic
        fun ofSessionRequiresAction(
            sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
        ) = BetaWebhookEventData(sessionRequiresAction = sessionRequiresAction)

        @JvmStatic
        fun ofSessionArchived(sessionArchived: BetaWebhookSessionArchivedEventData) =
            BetaWebhookEventData(sessionArchived = sessionArchived)

        @JvmStatic
        fun ofSessionDeleted(sessionDeleted: BetaWebhookSessionDeletedEventData) =
            BetaWebhookEventData(sessionDeleted = sessionDeleted)

        @JvmStatic
        fun ofSessionStatusScheduled(
            sessionStatusScheduled: BetaWebhookSessionStatusScheduledEventData
        ) = BetaWebhookEventData(sessionStatusScheduled = sessionStatusScheduled)

        @JvmStatic
        fun ofSessionStatusRunStarted(
            sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
        ) = BetaWebhookEventData(sessionStatusRunStarted = sessionStatusRunStarted)

        @JvmStatic
        fun ofSessionStatusIdled(sessionStatusIdled: BetaWebhookSessionStatusIdledEventData) =
            BetaWebhookEventData(sessionStatusIdled = sessionStatusIdled)

        @JvmStatic
        fun ofSessionStatusTerminated(
            sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
        ) = BetaWebhookEventData(sessionStatusTerminated = sessionStatusTerminated)

        @JvmStatic
        fun ofSessionThreadCreated(sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData) =
            BetaWebhookEventData(sessionThreadCreated = sessionThreadCreated)

        @JvmStatic
        fun ofSessionThreadIdled(sessionThreadIdled: BetaWebhookSessionThreadIdledEventData) =
            BetaWebhookEventData(sessionThreadIdled = sessionThreadIdled)

        @JvmStatic
        fun ofSessionThreadTerminated(
            sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
        ) = BetaWebhookEventData(sessionThreadTerminated = sessionThreadTerminated)

        @JvmStatic
        fun ofSessionOutcomeEvaluationEnded(
            sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
        ) = BetaWebhookEventData(sessionOutcomeEvaluationEnded = sessionOutcomeEvaluationEnded)

        @JvmStatic
        fun ofVaultCreated(vaultCreated: BetaWebhookVaultCreatedEventData) =
            BetaWebhookEventData(vaultCreated = vaultCreated)

        @JvmStatic
        fun ofVaultArchived(vaultArchived: BetaWebhookVaultArchivedEventData) =
            BetaWebhookEventData(vaultArchived = vaultArchived)

        @JvmStatic
        fun ofVaultDeleted(vaultDeleted: BetaWebhookVaultDeletedEventData) =
            BetaWebhookEventData(vaultDeleted = vaultDeleted)

        @JvmStatic
        fun ofVaultCredentialCreated(
            vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
        ) = BetaWebhookEventData(vaultCredentialCreated = vaultCredentialCreated)

        @JvmStatic
        fun ofVaultCredentialArchived(
            vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
        ) = BetaWebhookEventData(vaultCredentialArchived = vaultCredentialArchived)

        @JvmStatic
        fun ofVaultCredentialDeleted(
            vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
        ) = BetaWebhookEventData(vaultCredentialDeleted = vaultCredentialDeleted)

        @JvmStatic
        fun ofVaultCredentialRefreshFailed(
            vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
        ) = BetaWebhookEventData(vaultCredentialRefreshFailed = vaultCredentialRefreshFailed)
    }

    /**
     * An interface that defines how to map each variant of [BetaWebhookEventData] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitSessionCreated(sessionCreated: BetaWebhookSessionCreatedEventData): T

        fun visitSessionPending(sessionPending: BetaWebhookSessionPendingEventData): T

        fun visitSessionRunning(sessionRunning: BetaWebhookSessionRunningEventData): T

        fun visitSessionIdled(sessionIdled: BetaWebhookSessionIdledEventData): T

        fun visitSessionRequiresAction(
            sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
        ): T

        fun visitSessionArchived(sessionArchived: BetaWebhookSessionArchivedEventData): T

        fun visitSessionDeleted(sessionDeleted: BetaWebhookSessionDeletedEventData): T

        fun visitSessionStatusScheduled(
            sessionStatusScheduled: BetaWebhookSessionStatusScheduledEventData
        ): T

        fun visitSessionStatusRunStarted(
            sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
        ): T

        fun visitSessionStatusIdled(sessionStatusIdled: BetaWebhookSessionStatusIdledEventData): T

        fun visitSessionStatusTerminated(
            sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
        ): T

        fun visitSessionThreadCreated(
            sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
        ): T

        fun visitSessionThreadIdled(sessionThreadIdled: BetaWebhookSessionThreadIdledEventData): T

        fun visitSessionThreadTerminated(
            sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
        ): T

        fun visitSessionOutcomeEvaluationEnded(
            sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
        ): T

        fun visitVaultCreated(vaultCreated: BetaWebhookVaultCreatedEventData): T

        fun visitVaultArchived(vaultArchived: BetaWebhookVaultArchivedEventData): T

        fun visitVaultDeleted(vaultDeleted: BetaWebhookVaultDeletedEventData): T

        fun visitVaultCredentialCreated(
            vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
        ): T

        fun visitVaultCredentialArchived(
            vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
        ): T

        fun visitVaultCredentialDeleted(
            vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
        ): T

        fun visitVaultCredentialRefreshFailed(
            vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
        ): T

        /**
         * Maps an unknown variant of [BetaWebhookEventData] to a value of type [T].
         *
         * An instance of [BetaWebhookEventData] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaWebhookEventData: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaWebhookEventData>(BetaWebhookEventData::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaWebhookEventData {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "session.created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionCreatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.pending" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionPendingEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionPending = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.running" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionRunningEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionRunning = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.idled" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookSessionIdledEventData>())
                        ?.let { BetaWebhookEventData(sessionIdled = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.requires_action" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionRequiresActionEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionRequiresAction = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.archived" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionArchivedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionDeletedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.status_scheduled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionStatusScheduledEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionStatusScheduled = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.status_run_started" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionStatusRunStartedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionStatusRunStarted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.status_idled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionStatusIdledEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionStatusIdled = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.status_terminated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionStatusTerminatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionStatusTerminated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.thread_created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionThreadCreatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionThreadCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.thread_idled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionThreadIdledEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionThreadIdled = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.thread_terminated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionThreadTerminatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionThreadTerminated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.outcome_evaluation_ended" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionOutcomeEvaluationEndedEventData>(),
                        )
                        ?.let {
                            BetaWebhookEventData(sessionOutcomeEvaluationEnded = it, _json = json)
                        } ?: BetaWebhookEventData(_json = json)
                }
                "vault.created" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookVaultCreatedEventData>())
                        ?.let { BetaWebhookEventData(vaultCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault.archived" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookVaultArchivedEventData>())
                        ?.let { BetaWebhookEventData(vaultArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault.deleted" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookVaultDeletedEventData>())
                        ?.let { BetaWebhookEventData(vaultDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault_credential.created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookVaultCredentialCreatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(vaultCredentialCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault_credential.archived" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookVaultCredentialArchivedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(vaultCredentialArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault_credential.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookVaultCredentialDeletedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(vaultCredentialDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault_credential.refresh_failed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookVaultCredentialRefreshFailedEventData>(),
                        )
                        ?.let {
                            BetaWebhookEventData(vaultCredentialRefreshFailed = it, _json = json)
                        } ?: BetaWebhookEventData(_json = json)
                }
            }

            return BetaWebhookEventData(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaWebhookEventData>(BetaWebhookEventData::class) {

        override fun serialize(
            value: BetaWebhookEventData,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.sessionCreated != null -> generator.writeObject(value.sessionCreated)
                value.sessionPending != null -> generator.writeObject(value.sessionPending)
                value.sessionRunning != null -> generator.writeObject(value.sessionRunning)
                value.sessionIdled != null -> generator.writeObject(value.sessionIdled)
                value.sessionRequiresAction != null ->
                    generator.writeObject(value.sessionRequiresAction)
                value.sessionArchived != null -> generator.writeObject(value.sessionArchived)
                value.sessionDeleted != null -> generator.writeObject(value.sessionDeleted)
                value.sessionStatusScheduled != null ->
                    generator.writeObject(value.sessionStatusScheduled)
                value.sessionStatusRunStarted != null ->
                    generator.writeObject(value.sessionStatusRunStarted)
                value.sessionStatusIdled != null -> generator.writeObject(value.sessionStatusIdled)
                value.sessionStatusTerminated != null ->
                    generator.writeObject(value.sessionStatusTerminated)
                value.sessionThreadCreated != null ->
                    generator.writeObject(value.sessionThreadCreated)
                value.sessionThreadIdled != null -> generator.writeObject(value.sessionThreadIdled)
                value.sessionThreadTerminated != null ->
                    generator.writeObject(value.sessionThreadTerminated)
                value.sessionOutcomeEvaluationEnded != null ->
                    generator.writeObject(value.sessionOutcomeEvaluationEnded)
                value.vaultCreated != null -> generator.writeObject(value.vaultCreated)
                value.vaultArchived != null -> generator.writeObject(value.vaultArchived)
                value.vaultDeleted != null -> generator.writeObject(value.vaultDeleted)
                value.vaultCredentialCreated != null ->
                    generator.writeObject(value.vaultCredentialCreated)
                value.vaultCredentialArchived != null ->
                    generator.writeObject(value.vaultCredentialArchived)
                value.vaultCredentialDeleted != null ->
                    generator.writeObject(value.vaultCredentialDeleted)
                value.vaultCredentialRefreshFailed != null ->
                    generator.writeObject(value.vaultCredentialRefreshFailed)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaWebhookEventData")
            }
        }
    }
}
