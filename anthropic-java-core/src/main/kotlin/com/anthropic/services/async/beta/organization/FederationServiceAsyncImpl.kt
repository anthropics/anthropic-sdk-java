// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.services.async.beta.organization.federation.IssuerServiceAsync
import com.anthropic.services.async.beta.organization.federation.IssuerServiceAsyncImpl
import com.anthropic.services.async.beta.organization.federation.RuleServiceAsync
import com.anthropic.services.async.beta.organization.federation.RuleServiceAsyncImpl
import java.util.function.Consumer

class FederationServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    FederationServiceAsync {

    private val withRawResponse: FederationServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val issuers: IssuerServiceAsync by lazy { IssuerServiceAsyncImpl(clientOptions) }

    private val rules: RuleServiceAsync by lazy { RuleServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): FederationServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): FederationServiceAsync =
        FederationServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun issuers(): IssuerServiceAsync = issuers

    override fun rules(): RuleServiceAsync = rules

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        FederationServiceAsync.WithRawResponse {

        private val issuers: IssuerServiceAsync.WithRawResponse by lazy {
            IssuerServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val rules: RuleServiceAsync.WithRawResponse by lazy {
            RuleServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): FederationServiceAsync.WithRawResponse =
            FederationServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun issuers(): IssuerServiceAsync.WithRawResponse = issuers

        override fun rules(): RuleServiceAsync.WithRawResponse = rules
    }
}
