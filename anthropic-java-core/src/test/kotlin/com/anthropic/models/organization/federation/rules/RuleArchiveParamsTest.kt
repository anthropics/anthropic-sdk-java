package com.anthropic.models.organization.federation.rules

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RuleArchiveParamsTest {

    @Test
    fun create() {
        RuleArchiveParams.builder().federationRuleId("federation_rule_id").build()
    }

    @Test
    fun pathParams() {
        val params = RuleArchiveParams.builder().federationRuleId("federation_rule_id").build()

        assertThat(params._pathParam(0)).isEqualTo("federation_rule_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
