package com.anthropic.models.beta.organization.analytics.usage

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsActor
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsActorType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsApiActor
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsFederatedActor
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsReportUserActor
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServiceAccountActor
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServiceTier
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSpeed
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageCostInferenceGeo
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * The dimension values one usage row is broken out by; each is null unless its dimension is in
 * `group_by[]`.
 */
class BetaAnalyticsUsageGroup
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val actor: JsonField<BetaAnalyticsActor>,
    private val actorType: JsonField<BetaAnalyticsActorType>,
    private val agentId: JsonField<String>,
    private val apiKeyId: JsonField<String>,
    private val claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory>,
    private val claudeTagUserId: JsonField<String>,
    private val contextWindow: JsonField<BetaAnalyticsContextWindow>,
    private val deploymentId: JsonField<String>,
    private val environmentId: JsonField<String>,
    private val inferenceGeo: JsonField<BetaAnalyticsUsageCostInferenceGeo>,
    private val model: JsonField<String>,
    private val product: JsonField<String>,
    private val rbacGroupId: JsonField<String>,
    private val serviceTier: JsonField<BetaAnalyticsServiceTier>,
    private val sessionId: JsonField<String>,
    private val slackChannelId: JsonField<String>,
    private val speed: JsonField<BetaAnalyticsSpeed>,
    private val userProfileId: JsonField<String>,
    private val workspaceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("actor")
        @ExcludeMissing
        actor: JsonField<BetaAnalyticsActor> = JsonMissing.of(),
        @JsonProperty("actor_type")
        @ExcludeMissing
        actorType: JsonField<BetaAnalyticsActorType> = JsonMissing.of(),
        @JsonProperty("agent_id") @ExcludeMissing agentId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("api_key_id") @ExcludeMissing apiKeyId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("claude_tag_category")
        @ExcludeMissing
        claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory> = JsonMissing.of(),
        @JsonProperty("claude_tag_user_id")
        @ExcludeMissing
        claudeTagUserId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("context_window")
        @ExcludeMissing
        contextWindow: JsonField<BetaAnalyticsContextWindow> = JsonMissing.of(),
        @JsonProperty("deployment_id")
        @ExcludeMissing
        deploymentId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("environment_id")
        @ExcludeMissing
        environmentId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("inference_geo")
        @ExcludeMissing
        inferenceGeo: JsonField<BetaAnalyticsUsageCostInferenceGeo> = JsonMissing.of(),
        @JsonProperty("model") @ExcludeMissing model: JsonField<String> = JsonMissing.of(),
        @JsonProperty("product") @ExcludeMissing product: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("service_tier")
        @ExcludeMissing
        serviceTier: JsonField<BetaAnalyticsServiceTier> = JsonMissing.of(),
        @JsonProperty("session_id") @ExcludeMissing sessionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("slack_channel_id")
        @ExcludeMissing
        slackChannelId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("speed")
        @ExcludeMissing
        speed: JsonField<BetaAnalyticsSpeed> = JsonMissing.of(),
        @JsonProperty("user_profile_id")
        @ExcludeMissing
        userProfileId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
    ) : this(
        actor,
        actorType,
        agentId,
        apiKeyId,
        claudeTagCategory,
        claudeTagUserId,
        contextWindow,
        deploymentId,
        environmentId,
        inferenceGeo,
        model,
        product,
        rbacGroupId,
        serviceTier,
        sessionId,
        slackChannelId,
        speed,
        userProfileId,
        workspaceId,
        mutableMapOf(),
    )

    /**
     * The identity the usage is attributed to: the user if one is attributed, else the service
     * account, else the federated (cloud-platform) identity, else the API key. Populated only when
     * `actor` is in `group_by[]`; null for usage with no attributable identity, and for users' and
     * federated identities' usage when the reader is a member who can see only some workspaces.
     * Distinct identities the server cannot render also read null, each as its own row.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun actor(): Optional<BetaAnalyticsActor> = actor.getOptional("actor")

    /**
     * Kind of identity the usage is attributed to; matches `actor.type` when `actor` is set.
     * Populated only when `actor_type` is in `group_by[]`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun actorType(): Optional<BetaAnalyticsActorType> = actorType.getOptional("actor_type")

    /**
     * Claude Managed Agents agent whose session incurred the usage. Populated only when `agent_id`
     * is in `group_by[]`; null for usage outside a managed session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun agentId(): Optional<String> = agentId.getOptional("agent_id")

    /**
     * API key that authenticated the requests, as a tagged API key ID, whichever identity `actor`
     * resolves to. Populated only when `api_key_id` is in `group_by[]`; null for usage not
     * authenticated by an API key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun apiKeyId(): Optional<String> = apiKeyId.getOptional("api_key_id")

    /**
     * Claude Tag (Claude in Slack) spend category: `engaged` (a person addressed Claude in a
     * channel or thread), `proactive` (Claude responded without being addressed), `scheduled` (a
     * scheduled routine ran), `monitoring` (Claude watching a channel it was asked to monitor), or
     * `dm` (direct messages with Claude). Populated only when `claude_tag_category` is in
     * `group_by[]`; null for usage that is not Claude Tag. Direct-message usage is billed to the
     * individual user and is reported under that user's product, not under `claude_tag`. New
     * categories may be added over time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeTagCategory(): Optional<BetaAnalyticsClaudeTagCategory> =
        claudeTagCategory.getOptional("claude_tag_category")

    /**
     * Slack user ID (for example `U0123ABCDEF`) of the member the Claude Tag (Claude in Slack)
     * usage is attributed to, not a claude.ai user ID. Populated only when `claude_tag_user_id` is
     * in `group_by[]`; null for usage that is not Claude Tag and for Claude Tag usage that is not
     * attributed to a single user (for example `monitoring`, and `proactive` usage Claude
     * initiated), so per-user rows can sum to less than the Claude Tag total. Cannot be combined
     * with `group_by[]=rbac_group_id` or the `rbac_group_ids[]` filter.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeTagUserId(): Optional<String> = claudeTagUserId.getOptional("claude_tag_user_id")

    /**
     * Context-window pricing tier of the usage or cost. Null unless `context_window` is in
     * `group_by[]`; it can also be null on grouped rows with no context-window tier, such as code
     * execution.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contextWindow(): Optional<BetaAnalyticsContextWindow> =
        contextWindow.getOptional("context_window")

    /**
     * Deployment that started the Claude Managed Agents session. Populated only when
     * `deployment_id` is in `group_by[]`; null for usage outside a managed session and for sessions
     * no deployment started.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun deploymentId(): Optional<String> = deploymentId.getOptional("deployment_id")

    /**
     * Claude Managed Agents environment the session ran in. Populated only when `environment_id` is
     * in `group_by[]`; null for usage outside a managed session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun environmentId(): Optional<String> = environmentId.getOptional("environment_id")

    /**
     * Inference region of the usage or cost. Null unless `inference_geo` is in `group_by[]`; it can
     * also be null on grouped rows where the region is not set (the rows that
     * `inference_geos[]=not_available` matches).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inferenceGeo(): Optional<BetaAnalyticsUsageCostInferenceGeo> =
        inferenceGeo.getOptional("inference_geo")

    /**
     * Model that produced the usage or cost, as a model name in the form the `models[]` filter
     * accepts (for example, `claude-opus-5-5`). Null unless `model` is in `group_by[]`; it can also
     * be null on grouped rows whose usage or cost is not attributed to a specific model, such as
     * code execution.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun model(): Optional<String> = model.getOptional("model")

    /**
     * Product surface that produced the usage or cost. Null unless product is in `group_by[]`; it
     * can also be null on grouped rows whose usage cannot be attributed to a known surface. Values
     * include `chat`, `claude_code`, `cowork`, `office_agent`, `claude_in_chrome`, `claude_design`,
     * `claude_tag`, and `chat_cowork_unified`. `claude_tag` is Claude Tag, the Claude product in
     * Slack. `chat_cowork_unified` is Chat and Cowork unified, Cowork's features inside claude.ai
     * chat: chat and Cowork usage by a member who has it turned on is reported under this value
     * instead of `chat` or `cowork`. It is accepted as a filter only on deployments that offer Chat
     * and Cowork unified. Some unattributed usage is reported as "other".
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun product(): Optional<String> = product.getOptional("product")

    /**
     * RBAC group (team) the usage is attributed to, in the public tagged `rbac_group_...` spelling
     * — the same spelling the activity resources use for this key, so the same team has one id
     * across resources and it round-trips as an `rbac_group_ids[]` filter value. Populated only
     * when `rbac_group_id` is in `group_by[]`. Any-membership semantics: a user in several groups
     * contributes their full usage to each of those groups' rows, so the named-group rows overlap
     * and their sum can exceed the org total. A null value is the single unassigned row: users in
     * no group on that (UTC) day. For the true org total, run the same query without `group_by[]`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rbacGroupId(): Optional<String> = rbacGroupId.getOptional("rbac_group_id")

    /**
     * Service tier the usage was served at. Populated only when `service_tier` is in `group_by[]`;
     * null for usage with no service tier (for example, code execution) and for Claude Enterprise
     * usage.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun serviceTier(): Optional<BetaAnalyticsServiceTier> = serviceTier.getOptional("service_tier")

    /**
     * Claude Managed Agents session that incurred the usage. Populated only when `session_id` is in
     * `group_by[]`; null for usage outside a managed session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun sessionId(): Optional<String> = sessionId.getOptional("session_id")

    /**
     * Slack channel the usage originated from. Populated only when `slack_channel_id` is in
     * `group_by[]`; null for usage outside Slack (and for rows recorded before channel attribution
     * was enabled).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun slackChannelId(): Optional<String> = slackChannelId.getOptional("slack_channel_id")

    /**
     * Inference speed mode of the usage or cost: `fast` or `standard`. Null unless `speed` is in
     * `group_by[]`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun speed(): Optional<BetaAnalyticsSpeed> = speed.getOptional("speed")

    /**
     * User profile the usage was attributed to, as a tagged `uprof_` ID. Populated only when
     * `user_profile_id` is in `group_by[]`; null for usage that named no profile and for Claude
     * Enterprise usage.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userProfileId(): Optional<String> = userProfileId.getOptional("user_profile_id")

    /**
     * Workspace the usage occurred in, as a tagged workspace ID, including the organization's
     * default workspace. Populated only when `workspace_id` is in `group_by[]`; null for Claude
     * Enterprise usage.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workspaceId(): Optional<String> = workspaceId.getOptional("workspace_id")

    /**
     * Returns the raw JSON value of [actor].
     *
     * Unlike [actor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("actor") @ExcludeMissing fun _actor(): JsonField<BetaAnalyticsActor> = actor

    /**
     * Returns the raw JSON value of [actorType].
     *
     * Unlike [actorType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("actor_type")
    @ExcludeMissing
    fun _actorType(): JsonField<BetaAnalyticsActorType> = actorType

    /**
     * Returns the raw JSON value of [agentId].
     *
     * Unlike [agentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agent_id") @ExcludeMissing fun _agentId(): JsonField<String> = agentId

    /**
     * Returns the raw JSON value of [apiKeyId].
     *
     * Unlike [apiKeyId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("api_key_id") @ExcludeMissing fun _apiKeyId(): JsonField<String> = apiKeyId

    /**
     * Returns the raw JSON value of [claudeTagCategory].
     *
     * Unlike [claudeTagCategory], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("claude_tag_category")
    @ExcludeMissing
    fun _claudeTagCategory(): JsonField<BetaAnalyticsClaudeTagCategory> = claudeTagCategory

    /**
     * Returns the raw JSON value of [claudeTagUserId].
     *
     * Unlike [claudeTagUserId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("claude_tag_user_id")
    @ExcludeMissing
    fun _claudeTagUserId(): JsonField<String> = claudeTagUserId

    /**
     * Returns the raw JSON value of [contextWindow].
     *
     * Unlike [contextWindow], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("context_window")
    @ExcludeMissing
    fun _contextWindow(): JsonField<BetaAnalyticsContextWindow> = contextWindow

    /**
     * Returns the raw JSON value of [deploymentId].
     *
     * Unlike [deploymentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("deployment_id")
    @ExcludeMissing
    fun _deploymentId(): JsonField<String> = deploymentId

    /**
     * Returns the raw JSON value of [environmentId].
     *
     * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("environment_id")
    @ExcludeMissing
    fun _environmentId(): JsonField<String> = environmentId

    /**
     * Returns the raw JSON value of [inferenceGeo].
     *
     * Unlike [inferenceGeo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("inference_geo")
    @ExcludeMissing
    fun _inferenceGeo(): JsonField<BetaAnalyticsUsageCostInferenceGeo> = inferenceGeo

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<String> = model

    /**
     * Returns the raw JSON value of [product].
     *
     * Unlike [product], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("product") @ExcludeMissing fun _product(): JsonField<String> = product

    /**
     * Returns the raw JSON value of [rbacGroupId].
     *
     * Unlike [rbacGroupId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rbac_group_id")
    @ExcludeMissing
    fun _rbacGroupId(): JsonField<String> = rbacGroupId

    /**
     * Returns the raw JSON value of [serviceTier].
     *
     * Unlike [serviceTier], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("service_tier")
    @ExcludeMissing
    fun _serviceTier(): JsonField<BetaAnalyticsServiceTier> = serviceTier

    /**
     * Returns the raw JSON value of [sessionId].
     *
     * Unlike [sessionId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("session_id") @ExcludeMissing fun _sessionId(): JsonField<String> = sessionId

    /**
     * Returns the raw JSON value of [slackChannelId].
     *
     * Unlike [slackChannelId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("slack_channel_id")
    @ExcludeMissing
    fun _slackChannelId(): JsonField<String> = slackChannelId

    /**
     * Returns the raw JSON value of [speed].
     *
     * Unlike [speed], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("speed") @ExcludeMissing fun _speed(): JsonField<BetaAnalyticsSpeed> = speed

    /**
     * Returns the raw JSON value of [userProfileId].
     *
     * Unlike [userProfileId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_profile_id")
    @ExcludeMissing
    fun _userProfileId(): JsonField<String> = userProfileId

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_id")
    @ExcludeMissing
    fun _workspaceId(): JsonField<String> = workspaceId

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsUsageGroup].
         *
         * The following fields are required:
         * ```java
         * .actor()
         * .actorType()
         * .agentId()
         * .apiKeyId()
         * .claudeTagCategory()
         * .claudeTagUserId()
         * .contextWindow()
         * .deploymentId()
         * .environmentId()
         * .inferenceGeo()
         * .model()
         * .product()
         * .rbacGroupId()
         * .serviceTier()
         * .sessionId()
         * .slackChannelId()
         * .speed()
         * .userProfileId()
         * .workspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsUsageGroup]. */
    class Builder internal constructor() {

        private var actor: JsonField<BetaAnalyticsActor>? = null
        private var actorType: JsonField<BetaAnalyticsActorType>? = null
        private var agentId: JsonField<String>? = null
        private var apiKeyId: JsonField<String>? = null
        private var claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory>? = null
        private var claudeTagUserId: JsonField<String>? = null
        private var contextWindow: JsonField<BetaAnalyticsContextWindow>? = null
        private var deploymentId: JsonField<String>? = null
        private var environmentId: JsonField<String>? = null
        private var inferenceGeo: JsonField<BetaAnalyticsUsageCostInferenceGeo>? = null
        private var model: JsonField<String>? = null
        private var product: JsonField<String>? = null
        private var rbacGroupId: JsonField<String>? = null
        private var serviceTier: JsonField<BetaAnalyticsServiceTier>? = null
        private var sessionId: JsonField<String>? = null
        private var slackChannelId: JsonField<String>? = null
        private var speed: JsonField<BetaAnalyticsSpeed>? = null
        private var userProfileId: JsonField<String>? = null
        private var workspaceId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsUsageGroup: BetaAnalyticsUsageGroup) = apply {
            actor = betaAnalyticsUsageGroup.actor
            actorType = betaAnalyticsUsageGroup.actorType
            agentId = betaAnalyticsUsageGroup.agentId
            apiKeyId = betaAnalyticsUsageGroup.apiKeyId
            claudeTagCategory = betaAnalyticsUsageGroup.claudeTagCategory
            claudeTagUserId = betaAnalyticsUsageGroup.claudeTagUserId
            contextWindow = betaAnalyticsUsageGroup.contextWindow
            deploymentId = betaAnalyticsUsageGroup.deploymentId
            environmentId = betaAnalyticsUsageGroup.environmentId
            inferenceGeo = betaAnalyticsUsageGroup.inferenceGeo
            model = betaAnalyticsUsageGroup.model
            product = betaAnalyticsUsageGroup.product
            rbacGroupId = betaAnalyticsUsageGroup.rbacGroupId
            serviceTier = betaAnalyticsUsageGroup.serviceTier
            sessionId = betaAnalyticsUsageGroup.sessionId
            slackChannelId = betaAnalyticsUsageGroup.slackChannelId
            speed = betaAnalyticsUsageGroup.speed
            userProfileId = betaAnalyticsUsageGroup.userProfileId
            workspaceId = betaAnalyticsUsageGroup.workspaceId
            additionalProperties = betaAnalyticsUsageGroup.additionalProperties.toMutableMap()
        }

        /**
         * The identity the usage is attributed to: the user if one is attributed, else the service
         * account, else the federated (cloud-platform) identity, else the API key. Populated only
         * when `actor` is in `group_by[]`; null for usage with no attributable identity, and for
         * users' and federated identities' usage when the reader is a member who can see only some
         * workspaces. Distinct identities the server cannot render also read null, each as its own
         * row.
         */
        fun actor(actor: BetaAnalyticsActor?) = actor(JsonField.ofNullable(actor))

        /** Alias for calling [Builder.actor] with `actor.orElse(null)`. */
        fun actor(actor: Optional<BetaAnalyticsActor>) = actor(actor.getOrNull())

        /**
         * Sets [Builder.actor] to an arbitrary JSON value.
         *
         * You should usually call [Builder.actor] with a well-typed [BetaAnalyticsActor] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun actor(actor: JsonField<BetaAnalyticsActor>) = apply { this.actor = actor }

        /** Alias for calling [actor] with `BetaAnalyticsActor.ofUser(user)`. */
        fun actor(user: BetaAnalyticsReportUserActor) = actor(BetaAnalyticsActor.ofUser(user))

        /** Alias for calling [actor] with `BetaAnalyticsActor.ofServiceAccount(serviceAccount)`. */
        fun actor(serviceAccount: BetaAnalyticsServiceAccountActor) =
            actor(BetaAnalyticsActor.ofServiceAccount(serviceAccount))

        /**
         * Alias for calling [actor] with the following:
         * ```java
         * BetaAnalyticsServiceAccountActor.builder()
         *     .serviceAccountId(serviceAccountId)
         *     .build()
         * ```
         */
        fun serviceAccountActor(serviceAccountId: String) =
            actor(
                BetaAnalyticsServiceAccountActor.builder()
                    .serviceAccountId(serviceAccountId)
                    .build()
            )

        /** Alias for calling [actor] with `BetaAnalyticsActor.ofFederated(federated)`. */
        fun actor(federated: BetaAnalyticsFederatedActor) =
            actor(BetaAnalyticsActor.ofFederated(federated))

        /** Alias for calling [actor] with `BetaAnalyticsActor.ofApi(api)`. */
        fun actor(api: BetaAnalyticsApiActor) = actor(BetaAnalyticsActor.ofApi(api))

        /**
         * Alias for calling [actor] with the following:
         * ```java
         * BetaAnalyticsApiActor.builder()
         *     .apiKeyId(apiKeyId)
         *     .build()
         * ```
         */
        fun apiActor(apiKeyId: String) =
            actor(BetaAnalyticsApiActor.builder().apiKeyId(apiKeyId).build())

        /**
         * Kind of identity the usage is attributed to; matches `actor.type` when `actor` is set.
         * Populated only when `actor_type` is in `group_by[]`.
         */
        fun actorType(actorType: BetaAnalyticsActorType?) =
            actorType(JsonField.ofNullable(actorType))

        /** Alias for calling [Builder.actorType] with `actorType.orElse(null)`. */
        fun actorType(actorType: Optional<BetaAnalyticsActorType>) =
            actorType(actorType.getOrNull())

        /**
         * Sets [Builder.actorType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.actorType] with a well-typed [BetaAnalyticsActorType]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun actorType(actorType: JsonField<BetaAnalyticsActorType>) = apply {
            this.actorType = actorType
        }

        /**
         * Claude Managed Agents agent whose session incurred the usage. Populated only when
         * `agent_id` is in `group_by[]`; null for usage outside a managed session.
         */
        fun agentId(agentId: String?) = agentId(JsonField.ofNullable(agentId))

        /** Alias for calling [Builder.agentId] with `agentId.orElse(null)`. */
        fun agentId(agentId: Optional<String>) = agentId(agentId.getOrNull())

        /**
         * Sets [Builder.agentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agentId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun agentId(agentId: JsonField<String>) = apply { this.agentId = agentId }

        /**
         * API key that authenticated the requests, as a tagged API key ID, whichever identity
         * `actor` resolves to. Populated only when `api_key_id` is in `group_by[]`; null for usage
         * not authenticated by an API key.
         */
        fun apiKeyId(apiKeyId: String?) = apiKeyId(JsonField.ofNullable(apiKeyId))

        /** Alias for calling [Builder.apiKeyId] with `apiKeyId.orElse(null)`. */
        fun apiKeyId(apiKeyId: Optional<String>) = apiKeyId(apiKeyId.getOrNull())

        /**
         * Sets [Builder.apiKeyId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.apiKeyId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun apiKeyId(apiKeyId: JsonField<String>) = apply { this.apiKeyId = apiKeyId }

        /**
         * Claude Tag (Claude in Slack) spend category: `engaged` (a person addressed Claude in a
         * channel or thread), `proactive` (Claude responded without being addressed), `scheduled`
         * (a scheduled routine ran), `monitoring` (Claude watching a channel it was asked to
         * monitor), or `dm` (direct messages with Claude). Populated only when
         * `claude_tag_category` is in `group_by[]`; null for usage that is not Claude Tag.
         * Direct-message usage is billed to the individual user and is reported under that user's
         * product, not under `claude_tag`. New categories may be added over time.
         */
        fun claudeTagCategory(claudeTagCategory: BetaAnalyticsClaudeTagCategory?) =
            claudeTagCategory(JsonField.ofNullable(claudeTagCategory))

        /** Alias for calling [Builder.claudeTagCategory] with `claudeTagCategory.orElse(null)`. */
        fun claudeTagCategory(claudeTagCategory: Optional<BetaAnalyticsClaudeTagCategory>) =
            claudeTagCategory(claudeTagCategory.getOrNull())

        /**
         * Sets [Builder.claudeTagCategory] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeTagCategory] with a well-typed
         * [BetaAnalyticsClaudeTagCategory] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun claudeTagCategory(claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory>) =
            apply {
                this.claudeTagCategory = claudeTagCategory
            }

        /**
         * Slack user ID (for example `U0123ABCDEF`) of the member the Claude Tag (Claude in Slack)
         * usage is attributed to, not a claude.ai user ID. Populated only when `claude_tag_user_id`
         * is in `group_by[]`; null for usage that is not Claude Tag and for Claude Tag usage that
         * is not attributed to a single user (for example `monitoring`, and `proactive` usage
         * Claude initiated), so per-user rows can sum to less than the Claude Tag total. Cannot be
         * combined with `group_by[]=rbac_group_id` or the `rbac_group_ids[]` filter.
         */
        fun claudeTagUserId(claudeTagUserId: String?) =
            claudeTagUserId(JsonField.ofNullable(claudeTagUserId))

        /** Alias for calling [Builder.claudeTagUserId] with `claudeTagUserId.orElse(null)`. */
        fun claudeTagUserId(claudeTagUserId: Optional<String>) =
            claudeTagUserId(claudeTagUserId.getOrNull())

        /**
         * Sets [Builder.claudeTagUserId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeTagUserId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun claudeTagUserId(claudeTagUserId: JsonField<String>) = apply {
            this.claudeTagUserId = claudeTagUserId
        }

        /**
         * Context-window pricing tier of the usage or cost. Null unless `context_window` is in
         * `group_by[]`; it can also be null on grouped rows with no context-window tier, such as
         * code execution.
         */
        fun contextWindow(contextWindow: BetaAnalyticsContextWindow?) =
            contextWindow(JsonField.ofNullable(contextWindow))

        /** Alias for calling [Builder.contextWindow] with `contextWindow.orElse(null)`. */
        fun contextWindow(contextWindow: Optional<BetaAnalyticsContextWindow>) =
            contextWindow(contextWindow.getOrNull())

        /**
         * Sets [Builder.contextWindow] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contextWindow] with a well-typed
         * [BetaAnalyticsContextWindow] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun contextWindow(contextWindow: JsonField<BetaAnalyticsContextWindow>) = apply {
            this.contextWindow = contextWindow
        }

        /**
         * Deployment that started the Claude Managed Agents session. Populated only when
         * `deployment_id` is in `group_by[]`; null for usage outside a managed session and for
         * sessions no deployment started.
         */
        fun deploymentId(deploymentId: String?) = deploymentId(JsonField.ofNullable(deploymentId))

        /** Alias for calling [Builder.deploymentId] with `deploymentId.orElse(null)`. */
        fun deploymentId(deploymentId: Optional<String>) = deploymentId(deploymentId.getOrNull())

        /**
         * Sets [Builder.deploymentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.deploymentId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun deploymentId(deploymentId: JsonField<String>) = apply {
            this.deploymentId = deploymentId
        }

        /**
         * Claude Managed Agents environment the session ran in. Populated only when
         * `environment_id` is in `group_by[]`; null for usage outside a managed session.
         */
        fun environmentId(environmentId: String?) =
            environmentId(JsonField.ofNullable(environmentId))

        /** Alias for calling [Builder.environmentId] with `environmentId.orElse(null)`. */
        fun environmentId(environmentId: Optional<String>) =
            environmentId(environmentId.getOrNull())

        /**
         * Sets [Builder.environmentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.environmentId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun environmentId(environmentId: JsonField<String>) = apply {
            this.environmentId = environmentId
        }

        /**
         * Inference region of the usage or cost. Null unless `inference_geo` is in `group_by[]`; it
         * can also be null on grouped rows where the region is not set (the rows that
         * `inference_geos[]=not_available` matches).
         */
        fun inferenceGeo(inferenceGeo: BetaAnalyticsUsageCostInferenceGeo?) =
            inferenceGeo(JsonField.ofNullable(inferenceGeo))

        /** Alias for calling [Builder.inferenceGeo] with `inferenceGeo.orElse(null)`. */
        fun inferenceGeo(inferenceGeo: Optional<BetaAnalyticsUsageCostInferenceGeo>) =
            inferenceGeo(inferenceGeo.getOrNull())

        /**
         * Sets [Builder.inferenceGeo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inferenceGeo] with a well-typed
         * [BetaAnalyticsUsageCostInferenceGeo] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun inferenceGeo(inferenceGeo: JsonField<BetaAnalyticsUsageCostInferenceGeo>) = apply {
            this.inferenceGeo = inferenceGeo
        }

        /**
         * Model that produced the usage or cost, as a model name in the form the `models[]` filter
         * accepts (for example, `claude-opus-5-5`). Null unless `model` is in `group_by[]`; it can
         * also be null on grouped rows whose usage or cost is not attributed to a specific model,
         * such as code execution.
         */
        fun model(model: String?) = model(JsonField.ofNullable(model))

        /** Alias for calling [Builder.model] with `model.orElse(null)`. */
        fun model(model: Optional<String>) = model(model.getOrNull())

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<String>) = apply { this.model = model }

        /**
         * Product surface that produced the usage or cost. Null unless product is in `group_by[]`;
         * it can also be null on grouped rows whose usage cannot be attributed to a known surface.
         * Values include `chat`, `claude_code`, `cowork`, `office_agent`, `claude_in_chrome`,
         * `claude_design`, `claude_tag`, and `chat_cowork_unified`. `claude_tag` is Claude Tag, the
         * Claude product in Slack. `chat_cowork_unified` is Chat and Cowork unified, Cowork's
         * features inside claude.ai chat: chat and Cowork usage by a member who has it turned on is
         * reported under this value instead of `chat` or `cowork`. It is accepted as a filter only
         * on deployments that offer Chat and Cowork unified. Some unattributed usage is reported as
         * "other".
         */
        fun product(product: String?) = product(JsonField.ofNullable(product))

        /** Alias for calling [Builder.product] with `product.orElse(null)`. */
        fun product(product: Optional<String>) = product(product.getOrNull())

        /**
         * Sets [Builder.product] to an arbitrary JSON value.
         *
         * You should usually call [Builder.product] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun product(product: JsonField<String>) = apply { this.product = product }

        /**
         * RBAC group (team) the usage is attributed to, in the public tagged `rbac_group_...`
         * spelling — the same spelling the activity resources use for this key, so the same team
         * has one id across resources and it round-trips as an `rbac_group_ids[]` filter value.
         * Populated only when `rbac_group_id` is in `group_by[]`. Any-membership semantics: a user
         * in several groups contributes their full usage to each of those groups' rows, so the
         * named-group rows overlap and their sum can exceed the org total. A null value is the
         * single unassigned row: users in no group on that (UTC) day. For the true org total, run
         * the same query without `group_by[]`.
         */
        fun rbacGroupId(rbacGroupId: String?) = rbacGroupId(JsonField.ofNullable(rbacGroupId))

        /** Alias for calling [Builder.rbacGroupId] with `rbacGroupId.orElse(null)`. */
        fun rbacGroupId(rbacGroupId: Optional<String>) = rbacGroupId(rbacGroupId.getOrNull())

        /**
         * Sets [Builder.rbacGroupId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun rbacGroupId(rbacGroupId: JsonField<String>) = apply { this.rbacGroupId = rbacGroupId }

        /**
         * Service tier the usage was served at. Populated only when `service_tier` is in
         * `group_by[]`; null for usage with no service tier (for example, code execution) and for
         * Claude Enterprise usage.
         */
        fun serviceTier(serviceTier: BetaAnalyticsServiceTier?) =
            serviceTier(JsonField.ofNullable(serviceTier))

        /** Alias for calling [Builder.serviceTier] with `serviceTier.orElse(null)`. */
        fun serviceTier(serviceTier: Optional<BetaAnalyticsServiceTier>) =
            serviceTier(serviceTier.getOrNull())

        /**
         * Sets [Builder.serviceTier] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serviceTier] with a well-typed
         * [BetaAnalyticsServiceTier] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun serviceTier(serviceTier: JsonField<BetaAnalyticsServiceTier>) = apply {
            this.serviceTier = serviceTier
        }

        /**
         * Claude Managed Agents session that incurred the usage. Populated only when `session_id`
         * is in `group_by[]`; null for usage outside a managed session.
         */
        fun sessionId(sessionId: String?) = sessionId(JsonField.ofNullable(sessionId))

        /** Alias for calling [Builder.sessionId] with `sessionId.orElse(null)`. */
        fun sessionId(sessionId: Optional<String>) = sessionId(sessionId.getOrNull())

        /**
         * Sets [Builder.sessionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sessionId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun sessionId(sessionId: JsonField<String>) = apply { this.sessionId = sessionId }

        /**
         * Slack channel the usage originated from. Populated only when `slack_channel_id` is in
         * `group_by[]`; null for usage outside Slack (and for rows recorded before channel
         * attribution was enabled).
         */
        fun slackChannelId(slackChannelId: String?) =
            slackChannelId(JsonField.ofNullable(slackChannelId))

        /** Alias for calling [Builder.slackChannelId] with `slackChannelId.orElse(null)`. */
        fun slackChannelId(slackChannelId: Optional<String>) =
            slackChannelId(slackChannelId.getOrNull())

        /**
         * Sets [Builder.slackChannelId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.slackChannelId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun slackChannelId(slackChannelId: JsonField<String>) = apply {
            this.slackChannelId = slackChannelId
        }

        /**
         * Inference speed mode of the usage or cost: `fast` or `standard`. Null unless `speed` is
         * in `group_by[]`.
         */
        fun speed(speed: BetaAnalyticsSpeed?) = speed(JsonField.ofNullable(speed))

        /** Alias for calling [Builder.speed] with `speed.orElse(null)`. */
        fun speed(speed: Optional<BetaAnalyticsSpeed>) = speed(speed.getOrNull())

        /**
         * Sets [Builder.speed] to an arbitrary JSON value.
         *
         * You should usually call [Builder.speed] with a well-typed [BetaAnalyticsSpeed] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun speed(speed: JsonField<BetaAnalyticsSpeed>) = apply { this.speed = speed }

        /**
         * User profile the usage was attributed to, as a tagged `uprof_` ID. Populated only when
         * `user_profile_id` is in `group_by[]`; null for usage that named no profile and for Claude
         * Enterprise usage.
         */
        fun userProfileId(userProfileId: String?) =
            userProfileId(JsonField.ofNullable(userProfileId))

        /** Alias for calling [Builder.userProfileId] with `userProfileId.orElse(null)`. */
        fun userProfileId(userProfileId: Optional<String>) =
            userProfileId(userProfileId.getOrNull())

        /**
         * Sets [Builder.userProfileId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userProfileId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun userProfileId(userProfileId: JsonField<String>) = apply {
            this.userProfileId = userProfileId
        }

        /**
         * Workspace the usage occurred in, as a tagged workspace ID, including the organization's
         * default workspace. Populated only when `workspace_id` is in `group_by[]`; null for Claude
         * Enterprise usage.
         */
        fun workspaceId(workspaceId: String?) = workspaceId(JsonField.ofNullable(workspaceId))

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceId(workspaceId: JsonField<String>) = apply { this.workspaceId = workspaceId }

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
         * Returns an immutable instance of [BetaAnalyticsUsageGroup].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .actor()
         * .actorType()
         * .agentId()
         * .apiKeyId()
         * .claudeTagCategory()
         * .claudeTagUserId()
         * .contextWindow()
         * .deploymentId()
         * .environmentId()
         * .inferenceGeo()
         * .model()
         * .product()
         * .rbacGroupId()
         * .serviceTier()
         * .sessionId()
         * .slackChannelId()
         * .speed()
         * .userProfileId()
         * .workspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsUsageGroup =
            BetaAnalyticsUsageGroup(
                checkRequired("actor", actor),
                checkRequired("actorType", actorType),
                checkRequired("agentId", agentId),
                checkRequired("apiKeyId", apiKeyId),
                checkRequired("claudeTagCategory", claudeTagCategory),
                checkRequired("claudeTagUserId", claudeTagUserId),
                checkRequired("contextWindow", contextWindow),
                checkRequired("deploymentId", deploymentId),
                checkRequired("environmentId", environmentId),
                checkRequired("inferenceGeo", inferenceGeo),
                checkRequired("model", model),
                checkRequired("product", product),
                checkRequired("rbacGroupId", rbacGroupId),
                checkRequired("serviceTier", serviceTier),
                checkRequired("sessionId", sessionId),
                checkRequired("slackChannelId", slackChannelId),
                checkRequired("speed", speed),
                checkRequired("userProfileId", userProfileId),
                checkRequired("workspaceId", workspaceId),
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
    fun validate(): BetaAnalyticsUsageGroup = apply {
        if (validated) {
            return@apply
        }

        actor().ifPresent { it.validate() }
        actorType().ifPresent { it.validate() }
        agentId()
        apiKeyId()
        claudeTagCategory().ifPresent { it.validate() }
        claudeTagUserId()
        contextWindow().ifPresent { it.validate() }
        deploymentId()
        environmentId()
        inferenceGeo().ifPresent { it.validate() }
        model()
        product()
        rbacGroupId()
        serviceTier().ifPresent { it.validate() }
        sessionId()
        slackChannelId()
        speed().ifPresent { it.validate() }
        userProfileId()
        workspaceId()
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
        (actor.asKnown().getOrNull()?.validity() ?: 0) +
            (actorType.asKnown().getOrNull()?.validity() ?: 0) +
            (if (agentId.asKnown().isPresent) 1 else 0) +
            (if (apiKeyId.asKnown().isPresent) 1 else 0) +
            (claudeTagCategory.asKnown().getOrNull()?.validity() ?: 0) +
            (if (claudeTagUserId.asKnown().isPresent) 1 else 0) +
            (contextWindow.asKnown().getOrNull()?.validity() ?: 0) +
            (if (deploymentId.asKnown().isPresent) 1 else 0) +
            (if (environmentId.asKnown().isPresent) 1 else 0) +
            (inferenceGeo.asKnown().getOrNull()?.validity() ?: 0) +
            (if (model.asKnown().isPresent) 1 else 0) +
            (if (product.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (serviceTier.asKnown().getOrNull()?.validity() ?: 0) +
            (if (sessionId.asKnown().isPresent) 1 else 0) +
            (if (slackChannelId.asKnown().isPresent) 1 else 0) +
            (speed.asKnown().getOrNull()?.validity() ?: 0) +
            (if (userProfileId.asKnown().isPresent) 1 else 0) +
            (if (workspaceId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsUsageGroup &&
            actor == other.actor &&
            actorType == other.actorType &&
            agentId == other.agentId &&
            apiKeyId == other.apiKeyId &&
            claudeTagCategory == other.claudeTagCategory &&
            claudeTagUserId == other.claudeTagUserId &&
            contextWindow == other.contextWindow &&
            deploymentId == other.deploymentId &&
            environmentId == other.environmentId &&
            inferenceGeo == other.inferenceGeo &&
            model == other.model &&
            product == other.product &&
            rbacGroupId == other.rbacGroupId &&
            serviceTier == other.serviceTier &&
            sessionId == other.sessionId &&
            slackChannelId == other.slackChannelId &&
            speed == other.speed &&
            userProfileId == other.userProfileId &&
            workspaceId == other.workspaceId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            actor,
            actorType,
            agentId,
            apiKeyId,
            claudeTagCategory,
            claudeTagUserId,
            contextWindow,
            deploymentId,
            environmentId,
            inferenceGeo,
            model,
            product,
            rbacGroupId,
            serviceTier,
            sessionId,
            slackChannelId,
            speed,
            userProfileId,
            workspaceId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsUsageGroup{actor=$actor, actorType=$actorType, agentId=$agentId, apiKeyId=$apiKeyId, claudeTagCategory=$claudeTagCategory, claudeTagUserId=$claudeTagUserId, contextWindow=$contextWindow, deploymentId=$deploymentId, environmentId=$environmentId, inferenceGeo=$inferenceGeo, model=$model, product=$product, rbacGroupId=$rbacGroupId, serviceTier=$serviceTier, sessionId=$sessionId, slackChannelId=$slackChannelId, speed=$speed, userProfileId=$userProfileId, workspaceId=$workspaceId, additionalProperties=$additionalProperties}"
}
