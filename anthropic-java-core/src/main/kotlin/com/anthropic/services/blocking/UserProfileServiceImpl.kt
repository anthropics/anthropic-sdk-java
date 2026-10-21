package com.anthropic.services.blocking

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.userprofiles.DeletedUserProfile
import com.anthropic.models.userprofiles.UserProfile
import com.anthropic.models.userprofiles.UserProfileCreateParams
import com.anthropic.models.userprofiles.UserProfileDeleteParams
import com.anthropic.models.userprofiles.UserProfileListPage
import com.anthropic.models.userprofiles.UserProfileListPageResponse
import com.anthropic.models.userprofiles.UserProfileListParams
import com.anthropic.models.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.userprofiles.UserProfileUpdateParams
import com.anthropic.services.blocking.userprofiles.ActionUrlService
import com.anthropic.services.blocking.userprofiles.ActionUrlServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class UserProfileServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    UserProfileService {

    private val withRawResponse: UserProfileService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val actionUrls: ActionUrlService by lazy { ActionUrlServiceImpl(clientOptions) }

    override fun withRawResponse(): UserProfileService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserProfileService =
        UserProfileServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun actionUrls(): ActionUrlService = actionUrls

    override fun create(
        params: UserProfileCreateParams,
        requestOptions: RequestOptions,
    ): UserProfile =
        // post /v1/user_profiles
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: UserProfileRetrieveParams,
        requestOptions: RequestOptions,
    ): UserProfile =
        // get /v1/user_profiles/{user_profile_id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: UserProfileUpdateParams,
        requestOptions: RequestOptions,
    ): UserProfile =
        // post /v1/user_profiles/{user_profile_id}
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: UserProfileListParams,
        requestOptions: RequestOptions,
    ): UserProfileListPage =
        // get /v1/user_profiles
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: UserProfileDeleteParams,
        requestOptions: RequestOptions,
    ): DeletedUserProfile =
        // delete /v1/user_profiles/{user_profile_id}
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        UserProfileService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val actionUrls: ActionUrlService.WithRawResponse by lazy {
            ActionUrlServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UserProfileService.WithRawResponse =
            UserProfileServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun actionUrls(): ActionUrlService.WithRawResponse = actionUrls

        private val createHandler: Handler<UserProfile> =
            jsonHandler<UserProfile>(clientOptions.jsonMapper)

        override fun create(
            params: UserProfileCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserProfile> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { createHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                        .also { client.recordCreatedId("anthropic.user_profile.id", it._id()) }
                }
            }
        }

        private val retrieveHandler: Handler<UserProfile> =
            jsonHandler<UserProfile>(clientOptions.jsonMapper)

        override fun retrieve(
            params: UserProfileRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserProfile> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userProfileId", params.userProfileId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles")
                    .addPathParam("userProfileId", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { retrieveHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }

        private val updateHandler: Handler<UserProfile> =
            jsonHandler<UserProfile>(clientOptions.jsonMapper)

        override fun update(
            params: UserProfileUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserProfile> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userProfileId", params.userProfileId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles")
                    .addPathParam("userProfileId", params._pathParam(0))
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { updateHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
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
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { listHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
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
        }

        private val deleteHandler: Handler<DeletedUserProfile> =
            jsonHandler<DeletedUserProfile>(clientOptions.jsonMapper)

        override fun delete(
            params: UserProfileDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DeletedUserProfile> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userProfileId", params.userProfileId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles")
                    .addPathParam("userProfileId", params._pathParam(0))
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { deleteHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation().get()) {
                                it.validate()
                            }
                        }
                }
            }
        }
    }
}
