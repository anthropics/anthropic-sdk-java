package com.anthropic.services.blocking.beta.organization.analytics.apps.chat

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
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.analytics.apps.chat.projects.ProjectListPage
import com.anthropic.models.beta.organization.analytics.apps.chat.projects.ProjectListPageResponse
import com.anthropic.models.beta.organization.analytics.apps.chat.projects.ProjectListParams
import java.util.function.Consumer

class ProjectServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ProjectService {

    private val withRawResponse: ProjectService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ProjectService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ProjectService =
        ProjectServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(params: ProjectListParams, requestOptions: RequestOptions): ProjectListPage =
        // get /v1/organizations/analytics/apps/chat/projects?beta=true
        withRawResponse().list(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ProjectService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ProjectService.WithRawResponse =
            ProjectServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<ProjectListPageResponse> =
            jsonHandler<ProjectListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ProjectListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ProjectListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "analytics", "apps", "chat", "projects")
                    .putQueryParam("beta", "true")
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
                        ProjectListPage.builder()
                            .service(ProjectServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }
    }
}
