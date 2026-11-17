package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContractSpendLimitReachedErrorDetailsTest {

    @Test
    fun create() {
        val contractSpendLimitReachedErrorDetails =
            ContractSpendLimitReachedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val contractSpendLimitReachedErrorDetails =
            ContractSpendLimitReachedErrorDetails.builder().build()

        val roundtrippedContractSpendLimitReachedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contractSpendLimitReachedErrorDetails),
                jacksonTypeRef<ContractSpendLimitReachedErrorDetails>(),
            )

        assertThat(roundtrippedContractSpendLimitReachedErrorDetails)
            .isEqualTo(contractSpendLimitReachedErrorDetails)
    }
}
