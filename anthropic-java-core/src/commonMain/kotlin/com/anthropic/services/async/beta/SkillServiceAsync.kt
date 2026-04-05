// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import kotlinx.kmp.util.core.ClientOptions
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.http.HttpResponseFor
import com.anthropic.models.beta.skills.SkillCreateParams
import com.anthropic.models.beta.skills.SkillCreateResponse
import com.anthropic.models.beta.skills.SkillDeleteParams
import com.anthropic.models.beta.skills.SkillDeleteResponse
import com.anthropic.models.beta.skills.SkillListPageAsync
import com.anthropic.models.beta.skills.SkillListParams
import com.anthropic.models.beta.skills.SkillRetrieveParams
import com.anthropic.models.beta.skills.SkillRetrieveResponse
import com.anthropic.services.async.beta.skills.VersionServiceAsync
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
    suspend fun create(): SkillCreateResponse = create(SkillCreateParams.none())

    /** @see create */
    suspend fun create(
        params: SkillCreateParams = SkillCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillCreateResponse

    /** @see create */
    suspend fun create(
        params: SkillCreateParams = SkillCreateParams.none()
    ): SkillCreateResponse = create(params, RequestOptions.none())

    /** @see create */
    suspend fun create(requestOptions: RequestOptions): SkillCreateResponse =
        create(SkillCreateParams.none(), requestOptions)

    /** Get Skill */
    suspend fun retrieve(skillId: String): SkillRetrieveResponse =
        retrieve(skillId, SkillRetrieveParams.none())

    /** @see retrieve */
    suspend fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillRetrieveResponse =
        retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
    ): SkillRetrieveResponse = retrieve(skillId, params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(params: SkillRetrieveParams): SkillRetrieveResponse =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(
        skillId: String,
        requestOptions: RequestOptions,
    ): SkillRetrieveResponse =
        retrieve(skillId, SkillRetrieveParams.none(), requestOptions)

    /** List Skills */
    suspend fun list(): SkillListPageAsync = list(SkillListParams.none())

    /** @see list */
    suspend fun list(
        params: SkillListParams = SkillListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillListPageAsync

    /** @see list */
    suspend fun list(
        params: SkillListParams = SkillListParams.none()
    ): SkillListPageAsync = list(params, RequestOptions.none())

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): SkillListPageAsync =
        list(SkillListParams.none(), requestOptions)

    /** Delete Skill */
    suspend fun delete(skillId: String): SkillDeleteResponse =
        delete(skillId, SkillDeleteParams.none())

    /** @see delete */
    suspend fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillDeleteResponse =
        delete(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
    ): SkillDeleteResponse = delete(skillId, params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillDeleteResponse

    /** @see delete */
    suspend fun delete(params: SkillDeleteParams): SkillDeleteResponse =
        delete(params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(
        skillId: String,
        requestOptions: RequestOptions,
    ): SkillDeleteResponse =
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
        suspend fun create(): HttpResponseFor<SkillCreateResponse> =
            create(SkillCreateParams.none())

        /** @see create */
        suspend fun create(
            params: SkillCreateParams = SkillCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillCreateResponse>

        /** @see create */
        suspend fun create(
            params: SkillCreateParams = SkillCreateParams.none()
        ): HttpResponseFor<SkillCreateResponse> =
            create(params, RequestOptions.none())

        /** @see create */
        suspend fun create(
            requestOptions: RequestOptions
        ): HttpResponseFor<SkillCreateResponse> =
            create(SkillCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}?beta=true`, but is otherwise
         * the same as [SkillServiceAsync.retrieve].
         */
        suspend fun retrieve(skillId: String): HttpResponseFor<SkillRetrieveResponse> =
            retrieve(skillId, SkillRetrieveParams.none())

        /** @see retrieve */
        suspend fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillRetrieveResponse> =
            retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see retrieve */
        suspend fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
        ): HttpResponseFor<SkillRetrieveResponse> =
            retrieve(skillId, params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            params: SkillRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillRetrieveResponse>

        /** @see retrieve */
        suspend fun retrieve(
            params: SkillRetrieveParams
        ): HttpResponseFor<SkillRetrieveResponse> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            skillId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SkillRetrieveResponse> =
            retrieve(skillId, SkillRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills?beta=true`, but is otherwise the same as
         * [SkillServiceAsync.list].
         */
        suspend fun list(): HttpResponseFor<SkillListPageAsync> =
            list(SkillListParams.none())

        /** @see list */
        suspend fun list(
            params: SkillListParams = SkillListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillListPageAsync>

        /** @see list */
        suspend fun list(
            params: SkillListParams = SkillListParams.none()
        ): HttpResponseFor<SkillListPageAsync> =
            list(params, RequestOptions.none())

        /** @see list */
        suspend fun list(
            requestOptions: RequestOptions
        ): HttpResponseFor<SkillListPageAsync> =
            list(SkillListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/skills/{skill_id}?beta=true`, but is
         * otherwise the same as [SkillServiceAsync.delete].
         */
        suspend fun delete(skillId: String): HttpResponseFor<SkillDeleteResponse> =
            delete(skillId, SkillDeleteParams.none())

        /** @see delete */
        suspend fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillDeleteResponse> =
            delete(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see delete */
        suspend fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
        ): HttpResponseFor<SkillDeleteResponse> =
            delete(skillId, params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(
            params: SkillDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillDeleteResponse>

        /** @see delete */
        suspend fun delete(
            params: SkillDeleteParams
        ): HttpResponseFor<SkillDeleteResponse> =
            delete(params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(
            skillId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SkillDeleteResponse> =
            delete(skillId, SkillDeleteParams.none(), requestOptions)
    }
}
