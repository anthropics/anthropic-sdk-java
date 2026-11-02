package com.anthropic.models.organization.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeletedRbacGroupTest {

    @Test
    fun create() {
        val deletedRbacGroup = DeletedRbacGroup.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        assertThat(deletedRbacGroup.id()).isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deletedRbacGroup = DeletedRbacGroup.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        val roundtrippedDeletedRbacGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deletedRbacGroup),
                jacksonTypeRef<DeletedRbacGroup>(),
            )

        assertThat(roundtrippedDeletedRbacGroup).isEqualTo(deletedRbacGroup)
    }
}
