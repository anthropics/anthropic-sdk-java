// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.skills.SkillCreateParams
import com.anthropic.models.beta.skills.SkillCreateResponse
import com.anthropic.models.beta.skills.SkillDeleteParams
import com.anthropic.models.beta.skills.SkillDeleteResponse
import com.anthropic.models.beta.skills.SkillListPage
import com.anthropic.models.beta.skills.SkillListParams
import com.anthropic.models.beta.skills.SkillRetrieveParams
import com.anthropic.models.beta.skills.SkillRetrieveResponse
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
    fun create(): SkillCreateResponse = create(SkillCreateParams.none())

    /** @see create */
    fun create(
        params: SkillCreateParams = SkillCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillCreateResponse

    /** @see create */
    fun create(params: SkillCreateParams = SkillCreateParams.none()): SkillCreateResponse =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): SkillCreateResponse =
        create(SkillCreateParams.none(), requestOptions)

    /** Get Skill */
    fun retrieve(skillId: String): SkillRetrieveResponse =
        retrieve(skillId, SkillRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillRetrieveResponse = retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        skillId: String,
        params: SkillRetrieveParams = SkillRetrieveParams.none(),
    ): SkillRetrieveResponse = retrieve(skillId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillRetrieveResponse

    /** @see retrieve */
    fun retrieve(params: SkillRetrieveParams): SkillRetrieveResponse =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(skillId: String, requestOptions: RequestOptions): SkillRetrieveResponse =
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
    fun delete(skillId: String): SkillDeleteResponse = delete(skillId, SkillDeleteParams.none())

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillDeleteResponse = delete(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see delete */
    fun delete(
        skillId: String,
        params: SkillDeleteParams = SkillDeleteParams.none(),
    ): SkillDeleteResponse = delete(skillId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillDeleteResponse

    /** @see delete */
    fun delete(params: SkillDeleteParams): SkillDeleteResponse =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(skillId: String, requestOptions: RequestOptions): SkillDeleteResponse =
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
        fun create(): HttpResponseFor<SkillCreateResponse> = create(SkillCreateParams.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: SkillCreateParams = SkillCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillCreateResponse>

        /** @see create */
        @MustBeClosed
        fun create(
            params: SkillCreateParams = SkillCreateParams.none()
        ): HttpResponseFor<SkillCreateResponse> = create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(requestOptions: RequestOptions): HttpResponseFor<SkillCreateResponse> =
            create(SkillCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}?beta=true`, but is otherwise
         * the same as [SkillService.retrieve].
         */
        @MustBeClosed
        fun retrieve(skillId: String): HttpResponseFor<SkillRetrieveResponse> =
            retrieve(skillId, SkillRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillRetrieveResponse> =
            retrieve(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            skillId: String,
            params: SkillRetrieveParams = SkillRetrieveParams.none(),
        ): HttpResponseFor<SkillRetrieveResponse> = retrieve(skillId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: SkillRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: SkillRetrieveParams): HttpResponseFor<SkillRetrieveResponse> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            skillId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SkillRetrieveResponse> =
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
        fun delete(skillId: String): HttpResponseFor<SkillDeleteResponse> =
            delete(skillId, SkillDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillDeleteResponse> =
            delete(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            skillId: String,
            params: SkillDeleteParams = SkillDeleteParams.none(),
        ): HttpResponseFor<SkillDeleteResponse> = delete(skillId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: SkillDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillDeleteResponse>

        /** @see delete */
        @MustBeClosed
        fun delete(params: SkillDeleteParams): HttpResponseFor<SkillDeleteResponse> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            skillId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SkillDeleteResponse> =
            delete(skillId, SkillDeleteParams.none(), requestOptions)
    }
}
