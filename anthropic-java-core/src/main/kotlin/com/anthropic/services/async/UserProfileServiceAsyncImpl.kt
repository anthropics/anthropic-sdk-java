package com.anthropic.services.async

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
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.userprofiles.DeletedUserProfile
import com.anthropic.models.userprofiles.UserProfile
import com.anthropic.models.userprofiles.UserProfileCreateParams
import com.anthropic.models.userprofiles.UserProfileDeleteParams
import com.anthropic.models.userprofiles.UserProfileListPageAsync
import com.anthropic.models.userprofiles.UserProfileListPageResponse
import com.anthropic.models.userprofiles.UserProfileListParams
import com.anthropic.models.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.userprofiles.UserProfileUpdateParams
import com.anthropic.services.async.userprofiles.ActionUrlServiceAsync
import com.anthropic.services.async.userprofiles.ActionUrlServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class UserProfileServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    UserProfileServiceAsync {

    private val withRawResponse: UserProfileServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val actionUrls: ActionUrlServiceAsync by lazy {
        ActionUrlServiceAsyncImpl(clientOptions)
    }

    override fun withRawResponse(): UserProfileServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserProfileServiceAsync =
        UserProfileServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun actionUrls(): ActionUrlServiceAsync = actionUrls

    override fun create(
        params: UserProfileCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<UserProfile> =
        // post /v1/user_profiles
        withRawResponse().create(params, requestOptions).thenParse()

    override fun retrieve(
        params: UserProfileRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<UserProfile> =
        // get /v1/user_profiles/{user_profile_id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun update(
        params: UserProfileUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<UserProfile> =
        // post /v1/user_profiles/{user_profile_id}
        withRawResponse().update(params, requestOptions).thenParse()

    override fun list(
        params: UserProfileListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<UserProfileListPageAsync> =
        // get /v1/user_profiles
        withRawResponse().list(params, requestOptions).thenParse()

    override fun delete(
        params: UserProfileDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<DeletedUserProfile> =
        // delete /v1/user_profiles/{user_profile_id}
        withRawResponse().delete(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        UserProfileServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val actionUrls: ActionUrlServiceAsync.WithRawResponse by lazy {
            ActionUrlServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UserProfileServiceAsync.WithRawResponse =
            UserProfileServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun actionUrls(): ActionUrlServiceAsync.WithRawResponse = actionUrls

        private val createHandler: Handler<UserProfile> =
            jsonHandler<UserProfile>(clientOptions.jsonMapper)

        override fun create(
            params: UserProfileCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<UserProfile>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { createHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation!!) {
                                        it.validate()
                                    }
                                }
                                .also {
                                    client.recordCreatedId("anthropic.user_profile.id", it._id())
                                }
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<UserProfile> =
            jsonHandler<UserProfile>(clientOptions.jsonMapper)

        override fun retrieve(
            params: UserProfileRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<UserProfile>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { retrieveHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation!!) {
                                        it.validate()
                                    }
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
        ): CompletableFuture<HttpResponseFor<UserProfile>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { updateHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation!!) {
                                        it.validate()
                                    }
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
        ): CompletableFuture<HttpResponseFor<UserProfileListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "user_profiles")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { listHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation!!) {
                                        it.validate()
                                    }
                                }
                                .let {
                                    UserProfileListPageAsync.builder()
                                        .service(UserProfileServiceAsyncImpl(clientOptions))
                                        .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                        .params(params)
                                        .response(it)
                                        .build()
                                }
                        }
                    }
            }
        }

        private val deleteHandler: Handler<DeletedUserProfile> =
            jsonHandler<DeletedUserProfile>(clientOptions.jsonMapper)

        override fun delete(
            params: UserProfileDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<DeletedUserProfile>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { deleteHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation!!) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }
    }
}
