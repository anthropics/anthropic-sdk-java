// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AgentRetrieveParamsTest {

    @Test
    fun create() {
        AgentRetrieveParams.builder()
            .agentId("agent_011CZkYpogX7uDKUyvBTophP")
            .version(0)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params = AgentRetrieveParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").build()

        assertThat(params._pathParam(0)).isEqualTo("agent_011CZkYpogX7uDKUyvBTophP")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            AgentRetrieveParams.builder()
                .agentId("agent_011CZkYpogX7uDKUyvBTophP")
                .version(0)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = AgentRetrieveParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            AgentRetrieveParams.builder()
                .agentId("agent_011CZkYpogX7uDKUyvBTophP")
                .version(0)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().put("version", "0").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = AgentRetrieveParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
