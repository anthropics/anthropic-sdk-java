// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.skills.SkillCreateParams
import com.anthropic.models.beta.skills.SkillCreateResponse
import com.anthropic.models.beta.skills.SkillDeleteParams
import com.anthropic.models.beta.skills.SkillDeleteResponse
import com.anthropic.models.beta.skills.SkillListPageAsync
import com.anthropic.models.beta.skills.SkillListParams
import com.anthropic.models.beta.skills.SkillRetrieveParams
import com.anthropic.models.beta.skills.SkillRetrieveResponse
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
    fun create(): CompletableFuture<SkillCreateResponse> = create(SkillCreateParams.none())

    /** @see create */
    fun create(
        params: SkillCreateParams = SkillCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillCreateResponse>

    /** @see create */
    fun create(
        params: SkillCreateParams = SkillCreateParams.none()
    ): CompletableFuture<SkillCreateResponse> = create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): CompletableFuture<SkillCreateResponse> =
        create(SkillCreateParams.none(), requestOptions)

    /** Get Skill */
    fun retrieve(skillId: String): CompletableFuture<SkillRetrieveResponse> =
        retrieve(skillId, SkillRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillRetrieveResponse> =
        retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
    ): CompletableFuture<SkillRetrieveResponse> = retrieve(skillId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillRetrieveResponse>

    /** @see retrieve */
    fun retrieve(params: SkillRetrieveParams): CompletableFuture<SkillRetrieveResponse> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SkillRetrieveResponse> =
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
    fun delete(skillId: String): CompletableFuture<SkillDeleteResponse> =
        delete(skillId, SkillDeleteParams.none())

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillDeleteResponse> =
        delete(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
    ): CompletableFuture<SkillDeleteResponse> = delete(skillId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillDeleteResponse>

    /** @see delete */
    fun delete(params: SkillDeleteParams): CompletableFuture<SkillDeleteResponse> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        skillId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SkillDeleteResponse> =
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
        fun create(): CompletableFuture<HttpResponseFor<SkillCreateResponse>> =
            create(SkillCreateParams.none())

        /** @see create */
        fun create(
            params: SkillCreateParams = SkillCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillCreateResponse>>

        /** @see create */
        fun create(
            params: SkillCreateParams = SkillCreateParams.none()
        ): CompletableFuture<HttpResponseFor<SkillCreateResponse>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<SkillCreateResponse>> =
            create(SkillCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}?beta=true`, but is otherwise
         * the same as [SkillServiceAsync.retrieve].
         */
        fun retrieve(skillId: String): CompletableFuture<HttpResponseFor<SkillRetrieveResponse>> =
            retrieve(skillId, SkillRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillRetrieveResponse>> =
            retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<SkillRetrieveResponse>> =
            retrieve(skillId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: SkillRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillRetrieveResponse>>

        /** @see retrieve */
        fun retrieve(
            params: SkillRetrieveParams
        ): CompletableFuture<HttpResponseFor<SkillRetrieveResponse>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            skillId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SkillRetrieveResponse>> =
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
        fun delete(skillId: String): CompletableFuture<HttpResponseFor<SkillDeleteResponse>> =
            delete(skillId, SkillDeleteParams.none())

        /** @see delete */
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillDeleteResponse>> =
            delete(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see delete */
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<SkillDeleteResponse>> =
            delete(skillId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: SkillDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillDeleteResponse>>

        /** @see delete */
        fun delete(
            params: SkillDeleteParams
        ): CompletableFuture<HttpResponseFor<SkillDeleteResponse>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            skillId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SkillDeleteResponse>> =
            delete(skillId, SkillDeleteParams.none(), requestOptions)
    }
}
