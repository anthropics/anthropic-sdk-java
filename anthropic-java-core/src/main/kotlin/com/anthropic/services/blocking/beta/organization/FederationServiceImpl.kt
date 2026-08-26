// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.blocking.beta.organization.federation.IssuerService
import com.anthropic.services.blocking.beta.organization.federation.IssuerServiceImpl
import com.anthropic.services.blocking.beta.organization.federation.RuleService
import com.anthropic.services.blocking.beta.organization.federation.RuleServiceImpl
import java.util.function.Consumer

class FederationServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    FederationService {

    private val withRawResponse: FederationService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val issuers: IssuerService by lazy { IssuerServiceImpl(clientOptions) }

    private val rules: RuleService by lazy { RuleServiceImpl(clientOptions) }

    override fun withRawResponse(): FederationService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): FederationService =
        FederationServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun issuers(): IssuerService = issuers

    override fun rules(): RuleService = rules

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        FederationService.WithRawResponse {

        private val issuers: IssuerService.WithRawResponse by lazy {
            IssuerServiceImpl.WithRawResponseImpl(clientOptions)
        }

        private val rules: RuleService.WithRawResponse by lazy {
            RuleServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): FederationService.WithRawResponse =
            FederationServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun issuers(): IssuerService.WithRawResponse = issuers

        override fun rules(): RuleService.WithRawResponse = rules
    }
}
