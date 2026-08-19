// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.skills.DeletedSkill
import com.anthropic.models.skills.Skill
import com.anthropic.models.skills.SkillCreateParams
import com.anthropic.models.skills.SkillDeleteParams
import com.anthropic.models.skills.SkillListPageAsync
import com.anthropic.models.skills.SkillListParams
import com.anthropic.models.skills.SkillRetrieveParams
import com.anthropic.services.async.skills.VersionServiceAsync
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
    fun create(params: SkillCreateParams): CompletableFuture<Skill> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: SkillCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Skill>

    /** Get Skill */
    fun retrieve(skillId: String): CompletableFuture<Skill> =
        retrieve(skillId, SkillRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Skill> =
        retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
    ): CompletableFuture<Skill> = retrieve(skillId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Skill>

    /** @see retrieve */
    fun retrieve(params: SkillRetrieveParams): CompletableFuture<Skill> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(skillId: String, requestOptions: RequestOptions): CompletableFuture<Skill> =
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
    fun delete(skillId: String): CompletableFuture<DeletedSkill> =
        delete(skillId, SkillDeleteParams.none())

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedSkill> =
        delete(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
    ): CompletableFuture<DeletedSkill> = delete(skillId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedSkill>

    /** @see delete */
    fun delete(params: SkillDeleteParams): CompletableFuture<DeletedSkill> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(skillId: String, requestOptions: RequestOptions): CompletableFuture<DeletedSkill> =
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
         * Returns a raw HTTP response for `post /v1/skills`, but is otherwise the same as
         * [SkillServiceAsync.create].
         */
        fun create(params: SkillCreateParams): CompletableFuture<HttpResponseFor<Skill>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: SkillCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Skill>>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}`, but is otherwise the same as
         * [SkillServiceAsync.retrieve].
         */
        fun retrieve(skillId: String): CompletableFuture<HttpResponseFor<Skill>> =
            retrieve(skillId, SkillRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Skill>> =
            retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<Skill>> =
            retrieve(skillId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: SkillRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Skill>>

        /** @see retrieve */
        fun retrieve(params: SkillRetrieveParams): CompletableFuture<HttpResponseFor<Skill>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            skillId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Skill>> =
            retrieve(skillId, SkillRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills`, but is otherwise the same as
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
         * Returns a raw HTTP response for `delete /v1/skills/{skill_id}`, but is otherwise the same
         * as [SkillServiceAsync.delete].
         */
        fun delete(skillId: String): CompletableFuture<HttpResponseFor<DeletedSkill>> =
            delete(skillId, SkillDeleteParams.none())

        /** @see delete */
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedSkill>> =
            delete(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see delete */
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<DeletedSkill>> =
            delete(skillId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: SkillDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedSkill>>

        /** @see delete */
        fun delete(params: SkillDeleteParams): CompletableFuture<HttpResponseFor<DeletedSkill>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            skillId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<DeletedSkill>> =
            delete(skillId, SkillDeleteParams.none(), requestOptions)
    }
}
