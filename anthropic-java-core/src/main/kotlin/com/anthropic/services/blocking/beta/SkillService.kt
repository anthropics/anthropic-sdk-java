// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.skills.BetaDeletedSkill
import com.anthropic.models.beta.skills.BetaSkill
import com.anthropic.models.beta.skills.SkillCreateParams
import com.anthropic.models.beta.skills.SkillDeleteParams
import com.anthropic.models.beta.skills.SkillListPage
import com.anthropic.models.beta.skills.SkillListParams
import com.anthropic.models.beta.skills.SkillRetrieveParams
import com.anthropic.services.blocking.beta.skills.VersionService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface SkillService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SkillService

    fun versions(): VersionService

    /** Create Skill */
    fun create(params: SkillCreateParams): BetaSkill = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: SkillCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSkill

    /** Get Skill */
    fun retrieve(skillId: String): BetaSkill = retrieve(skillId, SkillRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSkill = retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
    ): BetaSkill = retrieve(skillId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSkill

    /** @see retrieve */
    fun retrieve(params: SkillRetrieveParams): BetaSkill = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(skillId: String, requestOptions: RequestOptions): BetaSkill =
        retrieve(skillId, SkillRetrieveParams.none(), requestOptions)

    /** List Skills */
    fun list(): SkillListPage = list(SkillListParams.none())

    /** @see list */
    fun list(
        params: SkillListParams = SkillListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillListPage

    /** @see list */
    fun list(params: SkillListParams = SkillListParams.none()): SkillListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): SkillListPage =
        list(SkillListParams.none(), requestOptions)

    /** Delete Skill */
    fun delete(skillId: String): BetaDeletedSkill = delete(skillId, SkillDeleteParams.none())

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedSkill = delete(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
    ): BetaDeletedSkill = delete(skillId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedSkill

    /** @see delete */
    fun delete(params: SkillDeleteParams): BetaDeletedSkill = delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(skillId: String, requestOptions: RequestOptions): BetaDeletedSkill =
        delete(skillId, SkillDeleteParams.none(), requestOptions)

    /** A view of [SkillService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): SkillService.WithRawResponse

        fun versions(): VersionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/skills?beta=true`, but is otherwise the same as
         * [SkillService.create].
         */
        @MustBeClosed
        fun create(params: SkillCreateParams): HttpResponseFor<BetaSkill> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: SkillCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSkill>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}?beta=true`, but is otherwise
         * the same as [SkillService.retrieve].
         */
        @MustBeClosed
        fun retrieve(skillId: String): HttpResponseFor<BetaSkill> =
            retrieve(skillId, SkillRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSkill> =
            retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
        ): HttpResponseFor<BetaSkill> = retrieve(skillId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: SkillRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSkill>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: SkillRetrieveParams): HttpResponseFor<BetaSkill> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(skillId: String, requestOptions: RequestOptions): HttpResponseFor<BetaSkill> =
            retrieve(skillId, SkillRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills?beta=true`, but is otherwise the same as
         * [SkillService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<SkillListPage> = list(SkillListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: SkillListParams = SkillListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: SkillListParams = SkillListParams.none()): HttpResponseFor<SkillListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<SkillListPage> =
            list(SkillListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/skills/{skill_id}?beta=true`, but is
         * otherwise the same as [SkillService.delete].
         */
        @MustBeClosed
        fun delete(skillId: String): HttpResponseFor<BetaDeletedSkill> =
            delete(skillId, SkillDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedSkill> =
            delete(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedSkill> = delete(skillId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: SkillDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedSkill>

        /** @see delete */
        @MustBeClosed
        fun delete(params: SkillDeleteParams): HttpResponseFor<BetaDeletedSkill> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            skillId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedSkill> =
            delete(skillId, SkillDeleteParams.none(), requestOptions)
    }
}
