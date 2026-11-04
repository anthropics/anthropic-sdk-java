package com.anthropic.models.organization.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupGrantsOwnerAccessErrorDetailsTest {

    @Test
    fun create() {
        val rbacGroupGrantsOwnerAccessErrorDetails =
            RbacGroupGrantsOwnerAccessErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacGroupGrantsOwnerAccessErrorDetails =
            RbacGroupGrantsOwnerAccessErrorDetails.builder().build()

        val roundtrippedRbacGroupGrantsOwnerAccessErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacGroupGrantsOwnerAccessErrorDetails),
                jacksonTypeRef<RbacGroupGrantsOwnerAccessErrorDetails>(),
            )

        assertThat(roundtrippedRbacGroupGrantsOwnerAccessErrorDetails)
            .isEqualTo(rbacGroupGrantsOwnerAccessErrorDetails)
    }
}
