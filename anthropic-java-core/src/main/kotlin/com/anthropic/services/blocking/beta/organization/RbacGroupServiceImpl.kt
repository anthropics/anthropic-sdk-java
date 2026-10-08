package com.anthropic.services.blocking.beta.organization

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
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.rbacgroups.BetaRbacGroup
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupCreateParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupDeleteParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupDeleteResponse
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupListPage
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupListPageResponse
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupListParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupRetrieveParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupUpdateParams
import com.anthropic.services.blocking.beta.organization.rbacgroups.MemberService
import com.anthropic.services.blocking.beta.organization.rbacgroups.MemberServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RbacGroupServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    RbacGroupService {

    private val withRawResponse: RbacGroupService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val members: MemberService by lazy { MemberServiceImpl(clientOptions) }

    override fun withRawResponse(): RbacGroupService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupService =
        RbacGroupServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun members(): MemberService = members

    override fun create(
        params: RbacGroupCreateParams,
        requestOptions: RequestOptions,
    ): BetaRbacGroup =
        // post /v1/organizations/rbac_groups?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: RbacGroupRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaRbacGroup =
        // get /v1/organizations/rbac_groups/{rbac_group_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: RbacGroupUpdateParams,
        requestOptions: RequestOptions,
    ): BetaRbacGroup =
        // post /v1/organizations/rbac_groups/{rbac_group_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: RbacGroupListParams,
        requestOptions: RequestOptions,
    ): RbacGroupListPage =
        // get /v1/organizations/rbac_groups?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: RbacGroupDeleteParams,
        requestOptions: RequestOptions,
    ): RbacGroupDeleteResponse =
        // delete /v1/organizations/rbac_groups/{rbac_group_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RbacGroupService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val members: MemberService.WithRawResponse by lazy {
            MemberServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacGroupService.WithRawResponse =
            RbacGroupServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun members(): MemberService.WithRawResponse = members

        private val createHandler: Handler<BetaRbacGroup> =
            jsonHandler<BetaRbacGroup>(clientOptions.jsonMapper)

        override fun create(
            params: RbacGroupCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaRbacGroup> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
                    .putQueryParam("beta", "true")
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

        private val retrieveHandler: Handler<BetaRbacGroup> =
            jsonHandler<BetaRbacGroup>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RbacGroupRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaRbacGroup> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
                    .addPathParam("rbacGroupId", params._pathParam(0))
                    .putQueryParam("beta", "true")
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

        private val updateHandler: Handler<BetaRbacGroup> =
            jsonHandler<BetaRbacGroup>(clientOptions.jsonMapper)

        override fun update(
            params: RbacGroupUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaRbacGroup> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
                    .addPathParam("rbacGroupId", params._pathParam(0))
                    .putQueryParam("beta", "true")
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

        private val listHandler: Handler<RbacGroupListPageResponse> =
            jsonHandler<RbacGroupListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RbacGroupListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RbacGroupListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
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
                        RbacGroupListPage.builder()
                            .service(RbacGroupServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<RbacGroupDeleteResponse> =
            jsonHandler<RbacGroupDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: RbacGroupDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RbacGroupDeleteResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
                    .addPathParam("rbacGroupId", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
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
