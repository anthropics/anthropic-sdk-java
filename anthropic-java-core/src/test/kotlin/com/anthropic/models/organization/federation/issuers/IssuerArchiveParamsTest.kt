package com.anthropic.models.organization.federation.issuers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IssuerArchiveParamsTest {

    @Test
    fun create() {
        IssuerArchiveParams.builder().federationIssuerId("federation_issuer_id").build()
    }

    @Test
    fun pathParams() {
        val params =
            IssuerArchiveParams.builder().federationIssuerId("federation_issuer_id").build()

        assertThat(params._pathParam(0)).isEqualTo("federation_issuer_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
