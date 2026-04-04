package com.anthropic.core

internal actual fun closeWhenPhantomReachable(observed: Any, close: () -> Unit) {}
