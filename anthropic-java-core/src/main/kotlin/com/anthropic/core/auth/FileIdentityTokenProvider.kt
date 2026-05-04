package com.anthropic.core.auth

import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture

internal class FileIdentityTokenProvider(private val path: String) : IdentityTokenProvider {
    override fun get(): String {
        return String(Files.readAllBytes(Paths.get(path))).trim()
    }

    override fun getAsync(): CompletableFuture<String> {
        return CompletableFuture.supplyAsync { get() }
    }
}
