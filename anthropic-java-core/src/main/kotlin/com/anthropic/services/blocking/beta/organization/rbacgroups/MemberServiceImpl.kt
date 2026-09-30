package com.anthropic.services.blocking.beta.organization.rbacgroups

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
import com.anthropic.models.beta.organization.rbacgroups.members.BetaRbacGroupMember
import com.anthropic.models.beta.organization.rbacgroups.members.MemberAddParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberListPage
import com.anthropic.models.beta.organization.rbacgroups.members.MemberListPageResponse
import com.anthropic.models.beta.organization.rbacgroups.members.MemberListParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberRemoveParams
import com.anthropic.models.beta.organization.rbacgroups.members.MemberRemoveResponse
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class MemberServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    MemberService {

    private val withRawResponse: MemberService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): MemberService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemberService =
        MemberServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(params: MemberListParams, requestOptions: RequestOptions): MemberListPage =
        // get /v1/organizations/rbac_groups/{rbac_group_id}/members?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun add(params: MemberAddParams, requestOptions: RequestOptions): BetaRbacGroupMember =
        // post /v1/organizations/rbac_groups/{rbac_group_id}/members?beta=true
        withRawResponse().add(params, requestOptions).parse()

    override fun remove(
        params: MemberRemoveParams,
        requestOptions: RequestOptions,
    ): MemberRemoveResponse =
        // delete /v1/organizations/rbac_groups/{rbac_group_id}/members/{user_id}?beta=true
        withRawResponse().remove(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        MemberService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemberService.WithRawResponse =
            MemberServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<MemberListPageResponse> =
            jsonHandler<MemberListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: MemberListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MemberListPage> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "rbac_groups",
                        params._pathParam(0),
                        "members",
                    )
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
                        MemberListPage.builder()
                            .service(MemberServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val addHandler: Handler<BetaRbacGroupMember> =
            jsonHandler<BetaRbacGroupMember>(clientOptions.jsonMapper)

        override fun add(
            params: MemberAddParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaRbacGroupMember> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "rbac_groups",
                        params._pathParam(0),
                        "members",
                    )
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { addHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val removeHandler: Handler<MemberRemoveResponse> =
            jsonHandler<MemberRemoveResponse>(clientOptions.jsonMapper)

        override fun remove(
            params: MemberRemoveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MemberRemoveResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userId", params.userId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "rbac_groups",
                        params._pathParam(0),
                        "members",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { removeHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
