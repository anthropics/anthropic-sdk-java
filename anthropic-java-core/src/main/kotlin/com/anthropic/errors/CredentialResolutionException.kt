package com.anthropic.errors

/**
 * Thrown when resolving credentials for a specific profile fails — e.g. the profile's configuration
 * references an identity token file that cannot be read, or its credentials cache is malformed.
 *
 * Unlike [NoCredentialsException], which means no source was configured at all, this indicates a
 * configured source was found but could not be turned into usable credentials.
 */
class CredentialResolutionException
internal constructor(
    @get:JvmName("profileName") val profileName: String,
    message: String,
    cause: Throwable? = null,
) : AnthropicException("Failed to resolve credentials for profile '$profileName': $message", cause)
