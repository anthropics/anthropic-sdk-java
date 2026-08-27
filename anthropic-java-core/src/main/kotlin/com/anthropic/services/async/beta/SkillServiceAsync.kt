// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.skills.BetaDeletedSkill
import com.anthropic.models.beta.skills.BetaSkill
import com.anthropic.models.beta.skills.SkillCreateParams
import com.anthropic.models.beta.skills.SkillDeleteParams
import com.anthropic.models.beta.skills.SkillListPageAsync
import com.anthropic.models.beta.skills.SkillListParams
import com.anthropic.models.beta.skills.SkillRetrieveParams
import com.anthropic.services.async.beta.skills.VersionServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface SkillServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SkillServiceAsync

    fun versions(): VersionServiceAsync

    /** Create Skill */
    fun create(params: SkillCreateParams): CompletableFuture<BetaSkill> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: SkillCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSkill>

    /** Get Skill */
    fun retrieve(skillId: String): CompletableFuture<BetaSkill> =
        retrieve(skillId, SkillRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSkill> =
        retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
    ): CompletableFuture<BetaSkill> = retrieve(skillId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSkill>

    /** @see retrieve */
    fun retrieve(params: SkillRetrieveParams): CompletableFuture<BetaSkill> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(skillId: String, requestOptions: RequestOptions): CompletableFuture<BetaSkill> =
        retrieve(skillId, SkillRetrieveParams.none(), requestOptions)

    /** List Skills */
    fun list(): CompletableFuture<SkillListPageAsync> = list(SkillListParams.none())

    /** @see list */
    fun list(
        params: SkillListParams = SkillListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillListPageAsync>

    /** @see list */
    fun list(
        params: SkillListParams = SkillListParams.none()
    ): CompletableFuture<SkillListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<SkillListPageAsync> =
        list(SkillListParams.none(), requestOptions)

    /** Delete Skill */
    fun delete(skillId: String): CompletableFuture<BetaDeletedSkill> =
        delete(skillId, SkillDeleteParams.none())

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedSkill> =
        delete(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
    ): CompletableFuture<BetaDeletedSkill> = delete(skillId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedSkill>

    /** @see delete */
    fun delete(params: SkillDeleteParams): CompletableFuture<BetaDeletedSkill> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        skillId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedSkill> =
        delete(skillId, SkillDeleteParams.none(), requestOptions)

    /** A view of [SkillServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SkillServiceAsync.WithRawResponse

        fun versions(): VersionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/skills?beta=true`, but is otherwise the same as
         * [SkillServiceAsync.create].
         */
        fun create(params: SkillCreateParams): CompletableFuture<HttpResponseFor<BetaSkill>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: SkillCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSkill>>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}?beta=true`, but is otherwise
         * the same as [SkillServiceAsync.retrieve].
         */
        fun retrieve(skillId: String): CompletableFuture<HttpResponseFor<BetaSkill>> =
            retrieve(skillId, SkillRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSkill>> =
            retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaSkill>> =
            retrieve(skillId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: SkillRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSkill>>

        /** @see retrieve */
        fun retrieve(params: SkillRetrieveParams): CompletableFuture<HttpResponseFor<BetaSkill>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            skillId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSkill>> =
            retrieve(skillId, SkillRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills?beta=true`, but is otherwise the same as
         * [SkillServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<SkillListPageAsync>> =
            list(SkillListParams.none())

        /** @see list */
        fun list(
            params: SkillListParams = SkillListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillListPageAsync>>

        /** @see list */
        fun list(
            params: SkillListParams = SkillListParams.none()
        ): CompletableFuture<HttpResponseFor<SkillListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<SkillListPageAsync>> =
            list(SkillListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/skills/{skill_id}?beta=true`, but is
         * otherwise the same as [SkillServiceAsync.delete].
         */
        fun delete(skillId: String): CompletableFuture<HttpResponseFor<BetaDeletedSkill>> =
            delete(skillId, SkillDeleteParams.none())

        /** @see delete */
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedSkill>> =
            delete(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see delete */
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedSkill>> =
            delete(skillId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: SkillDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedSkill>>

        /** @see delete */
        fun delete(
            params: SkillDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaDeletedSkill>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            skillId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDeletedSkill>> =
            delete(skillId, SkillDeleteParams.none(), requestOptions)
    }
}
