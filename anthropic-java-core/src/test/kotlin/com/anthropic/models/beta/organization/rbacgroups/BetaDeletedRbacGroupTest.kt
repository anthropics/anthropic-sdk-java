package com.anthropic.models.beta.organization.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedRbacGroupTest {

    @Test
    fun create() {
        val betaDeletedRbacGroup = BetaDeletedRbacGroup.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        assertThat(betaDeletedRbacGroup.id()).isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedRbacGroup = BetaDeletedRbacGroup.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        val roundtrippedBetaDeletedRbacGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedRbacGroup),
                jacksonTypeRef<BetaDeletedRbacGroup>(),
            )

        assertThat(roundtrippedBetaDeletedRbacGroup).isEqualTo(betaDeletedRbacGroup)
    }
}
