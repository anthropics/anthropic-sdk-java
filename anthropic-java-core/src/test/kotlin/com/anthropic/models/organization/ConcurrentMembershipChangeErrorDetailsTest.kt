package com.anthropic.models.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ConcurrentMembershipChangeErrorDetailsTest {

    @Test
    fun create() {
        val concurrentMembershipChangeErrorDetails =
            ConcurrentMembershipChangeErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val concurrentMembershipChangeErrorDetails =
            ConcurrentMembershipChangeErrorDetails.builder().build()

        val roundtrippedConcurrentMembershipChangeErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(concurrentMembershipChangeErrorDetails),
                jacksonTypeRef<ConcurrentMembershipChangeErrorDetails>(),
            )

        assertThat(roundtrippedConcurrentMembershipChangeErrorDetails)
            .isEqualTo(concurrentMembershipChangeErrorDetails)
    }
}
