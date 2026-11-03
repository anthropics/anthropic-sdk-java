package com.anthropic.models.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupOwnerAccessUnconfirmedErrorDetailsTest {

    @Test
    fun create() {
        val rbacGroupOwnerAccessUnconfirmedErrorDetails =
            RbacGroupOwnerAccessUnconfirmedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacGroupOwnerAccessUnconfirmedErrorDetails =
            RbacGroupOwnerAccessUnconfirmedErrorDetails.builder().build()

        val roundtrippedRbacGroupOwnerAccessUnconfirmedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacGroupOwnerAccessUnconfirmedErrorDetails),
                jacksonTypeRef<RbacGroupOwnerAccessUnconfirmedErrorDetails>(),
            )

        assertThat(roundtrippedRbacGroupOwnerAccessUnconfirmedErrorDetails)
            .isEqualTo(rbacGroupOwnerAccessUnconfirmedErrorDetails)
    }
}
