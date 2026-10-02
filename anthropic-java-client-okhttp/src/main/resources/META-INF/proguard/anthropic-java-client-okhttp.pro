# ProGuard narrows the return types of these methods but leaves the casts to their original return types in place, so
# the class fails verification on the first request. This only stops ProGuard from optimizing the methods.
-keepclassmembers,allowshrinking,allowobfuscation class okio.Okio__JvmOkioKt { *; }