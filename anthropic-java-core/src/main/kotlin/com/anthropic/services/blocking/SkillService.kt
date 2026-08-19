// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.skills.DeletedSkill
import com.anthropic.models.skills.Skill
import com.anthropic.models.skills.SkillCreateParams
import com.anthropic.models.skills.SkillDeleteParams
import com.anthropic.models.skills.SkillListPage
import com.anthropic.models.skills.SkillListParams
import com.anthropic.models.skills.SkillRetrieveParams
import com.anthropic.services.blocking.skills.VersionService
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
    fun create(params: SkillCreateParams): Skill = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: SkillCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Skill

    /** Get Skill */
    fun retrieve(skillId: String): Skill = retrieve(skillId, SkillRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Skill = retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(skillId: String, params: SkillRetrieveParams = SkillRetrieveParams.none()): Skill =
        retrieve(skillId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Skill

    /** @see retrieve */
    fun retrieve(params: SkillRetrieveParams): Skill = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(skillId: String, requestOptions: RequestOptions): Skill =
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
    fun delete(skillId: String): DeletedSkill = delete(skillId, SkillDeleteParams.none())

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedSkill = delete(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
    ): DeletedSkill = delete(skillId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedSkill

    /** @see delete */
    fun delete(params: SkillDeleteParams): DeletedSkill = delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(skillId: String, requestOptions: RequestOptions): DeletedSkill =
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
         * Returns a raw HTTP response for `post /v1/skills`, but is otherwise the same as
         * [SkillService.create].
         */
        @MustBeClosed
        fun create(params: SkillCreateParams): HttpResponseFor<Skill> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: SkillCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Skill>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}`, but is otherwise the same as
         * [SkillService.retrieve].
         */
        @MustBeClosed
        fun retrieve(skillId: String): HttpResponseFor<Skill> =
            retrieve(skillId, SkillRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Skill> =
            retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
        ): HttpResponseFor<Skill> = retrieve(skillId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: SkillRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Skill>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: SkillRetrieveParams): HttpResponseFor<Skill> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(skillId: String, requestOptions: RequestOptions): HttpResponseFor<Skill> =
            retrieve(skillId, SkillRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills`, but is otherwise the same as
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
         * Returns a raw HTTP response for `delete /v1/skills/{skill_id}`, but is otherwise the same
         * as [SkillService.delete].
         */
        @MustBeClosed
        fun delete(skillId: String): HttpResponseFor<DeletedSkill> =
            delete(skillId, SkillDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedSkill> =
            delete(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
        ): HttpResponseFor<DeletedSkill> = delete(skillId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: SkillDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedSkill>

        /** @see delete */
        @MustBeClosed
        fun delete(params: SkillDeleteParams): HttpResponseFor<DeletedSkill> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(skillId: String, requestOptions: RequestOptions): HttpResponseFor<DeletedSkill> =
            delete(skillId, SkillDeleteParams.none(), requestOptions)
    }
}
