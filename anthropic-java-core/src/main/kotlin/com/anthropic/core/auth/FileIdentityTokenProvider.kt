package com.anthropic.core.auth

import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

internal class FileIdentityTokenProvider(private val path: String) : IdentityTokenProvider {
    override fun get(): String {
        return String(Files.readAllBytes(Paths.get(path))).trim()
    }

    override fun getAsync(executor: Executor): CompletableFuture<String> =
        CompletableFuture.supplyAsync({ get() }, executor)
}
