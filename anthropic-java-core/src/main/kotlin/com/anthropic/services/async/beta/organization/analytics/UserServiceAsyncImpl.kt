package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.parseable
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.analytics.users.UserListPageAsync
import com.anthropic.models.beta.organization.analytics.users.UserListPageResponse
import com.anthropic.models.beta.organization.analytics.users.UserListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class UserServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    UserServiceAsync {

    private val withRawResponse: UserServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): UserServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserServiceAsync =
        UserServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(
        params: UserListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<UserListPageAsync> =
        // get /v1/organizations/analytics/users?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        UserServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UserServiceAsync.WithRawResponse =
            UserServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<UserListPageResponse> =
            jsonHandler<UserListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: UserListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<UserListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "analytics", "users")
                    .putQueryParam("beta", "true")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
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
                                UserListPageAsync.builder()
                                    .service(UserServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }
    }
}
