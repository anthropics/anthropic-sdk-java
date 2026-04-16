// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.userprofiles.BetaUserProfile
import com.anthropic.models.beta.userprofiles.BetaUserProfileEnrollmentUrl
import com.anthropic.models.beta.userprofiles.UserProfileCreateEnrollmentUrlParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateParams
import com.anthropic.models.beta.userprofiles.UserProfileListPage
import com.anthropic.models.beta.userprofiles.UserProfileListPageResponse
import com.anthropic.models.beta.userprofiles.UserProfileListParams
import com.anthropic.models.beta.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.beta.userprofiles.UserProfileUpdateParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class UserProfileServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    UserProfileService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "user-profiles-2026-03-24").build()
    }

    private val withRawResponse: UserProfileService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): UserProfileService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserProfileService =
        UserProfileServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: UserProfileCreateParams,
        requestOptions: RequestOptions,
    ): BetaUserProfile =
        // post /v1/user_profiles?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: UserProfileRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaUserProfile =
        // get /v1/user_profiles/{user_profile_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: UserProfileUpdateParams,
        requestOptions: RequestOptions,
    ): BetaUserProfile =
        // post /v1/user_profiles/{user_profile_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: UserProfileListParams,
        requestOptions: RequestOptions,
    ): UserProfileListPage =
        // get /v1/user_profiles?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun createEnrollmentUrl(
        params: UserProfileCreateEnrollmentUrlParams,
        requestOptions: RequestOptions,
    ): BetaUserProfileEnrollmentUrl =
        // post /v1/user_profiles/{user_profile_id}/enrollment_url?beta=true
        withRawResponse().createEnrollmentUrl(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        UserProfileService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UserProfileService.WithRawResponse =
            UserProfileServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaUserProfile> =
            jsonHandler<BetaUserProfile>(clientOptions.jsonMapper)

        override fun create(
            params: UserProfileCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfile> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { createHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<BetaUserProfile> =
            jsonHandler<BetaUserProfile>(clientOptions.jsonMapper)

        override fun retrieve(
            params: UserProfileRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfile> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userProfileId", params.userProfileId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val updateHandler: Handler<BetaUserProfile> =
            jsonHandler<BetaUserProfile>(clientOptions.jsonMapper)

        override fun update(
            params: UserProfileUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfile> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userProfileId", params.userProfileId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<UserProfileListPageResponse> =
            jsonHandler<UserProfileListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: UserProfileListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserProfileListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
                    .let {
                        UserProfileListPage.builder()
                            .service(UserProfileServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val createEnrollmentUrlHandler: Handler<BetaUserProfileEnrollmentUrl> =
            jsonHandler<BetaUserProfileEnrollmentUrl>(clientOptions.jsonMapper)

        override fun createEnrollmentUrl(
            params: UserProfileCreateEnrollmentUrlParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userProfileId", params.userProfileId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles", params._pathParam(0), "enrollment_url")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { createEnrollmentUrlHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
