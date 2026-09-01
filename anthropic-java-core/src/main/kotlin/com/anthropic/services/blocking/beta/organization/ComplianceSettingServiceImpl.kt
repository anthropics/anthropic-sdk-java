// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

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
import com.anthropic.core.http.json
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.compliancesettings.BetaComplianceSettings
import com.anthropic.models.beta.organization.compliancesettings.ComplianceSettingRetrieveParams
import com.anthropic.models.beta.organization.compliancesettings.ComplianceSettingUpdateParams
import java.util.function.Consumer

class ComplianceSettingServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ComplianceSettingService {

    private val withRawResponse: ComplianceSettingService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ComplianceSettingService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ComplianceSettingService =
        ComplianceSettingServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: ComplianceSettingRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaComplianceSettings =
        // get /v1/organizations/compliance_settings?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: ComplianceSettingUpdateParams,
        requestOptions: RequestOptions,
    ): BetaComplianceSettings =
        // post /v1/organizations/compliance_settings?beta=true
        withRawResponse().update(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ComplianceSettingService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ComplianceSettingService.WithRawResponse =
            ComplianceSettingServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<BetaComplianceSettings> =
            jsonHandler<BetaComplianceSettings>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ComplianceSettingRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaComplianceSettings> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "compliance_settings")
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

        private val updateHandler: Handler<BetaComplianceSettings> =
            jsonHandler<BetaComplianceSettings>(clientOptions.jsonMapper)

        override fun update(
            params: ComplianceSettingUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaComplianceSettings> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "compliance_settings")
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
    }
}
