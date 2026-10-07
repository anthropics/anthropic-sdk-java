# Jackson, and the Kotlin reflection it uses on our classes, read these attributes at runtime.
-keepattributes RuntimeVisibleAnnotations,RuntimeVisibleParameterAnnotations,AnnotationDefault,Signature,InnerClasses,EnclosingMethod

# Jackson reads the default arguments of our constructors from `@Metadata`, which is what makes an absent property missing
# instead of null. Shrinkers strip the annotation from every class unless the annotation class itself is kept.
-keep class kotlin.Metadata { *; }

# ProGuard, unlike R8, does not see that Kotlin reflection loads these through `java.util.ServiceLoader`.
-keep interface kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader
-keep class * implements kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader { public protected *; }
-keep interface kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
-keep class * implements kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition { public protected *; }
-keep interface kotlin.reflect.jvm.internal.impl.km.internal.extensions.MetadataExtensions
-keep class * implements kotlin.reflect.jvm.internal.impl.km.internal.extensions.MetadataExtensions { public protected *; }

# `java.lang.Class.getEnumConstants()`, which Jackson calls, finds `values()` reflectively. So do `java.util.EnumMap` and
# `java.lang.Enum.valueOf(...)`, which ProGuard, unlike R8, does not see.
-keepclassmembers enum com.fasterxml.jackson.**, kotlin.reflect.jvm.internal.**, com.anthropic.** {
    public static **[] values();
}

# Jackson reads the type argument of a `TypeReference` subclass from its generic signature. R8 drops the signature of a
# class that no rule matches, and the signature of a subclass is unreadable without the one of `TypeReference` itself.
-keep,allowshrinking,allowobfuscation class com.fasterxml.jackson.core.type.TypeReference
-keep,allowshrinking,allowobfuscation class * extends com.fasterxml.jackson.core.type.TypeReference

# Only annotations refer to these, so to a shrinker they look unused. Jackson instantiates the classes reflectively.
-keep,allowobfuscation @interface com.anthropic.core.ExcludeMissing
-keep,allowobfuscation class com.anthropic.core.JsonField$IsMissing { <init>(); }
-keepclassmembers class com.fasterxml.jackson.databind.ser.std.NullSerializer { <init>(); }

# Jackson calls the annotated members of our classes reflectively. Their names are free to change because the annotations
# hold the names of the JSON properties, except for enum constants, which Jackson matches to their fields by name.
-keepclassmembers,allowobfuscation class com.anthropic.** {
    @com.fasterxml.jackson.annotation.* *;
}
-keepclassmembers enum com.anthropic.** {
    @com.fasterxml.jackson.annotation.* <fields>;
}

# Jackson may be all that instantiates one of our classes (e.g. a response). R8 treats a class as instantiated, and keeps
# its annotations, only if a rule keeps the class itself, so `-keepclassmembers` is not enough. The `-if`, which also matches
# a constructor with annotated parameters, leaves unused classes removable (in R8; to ProGuard the condition holds
# whenever the class exists). The constructors include the synthetic one that Kotlin reflection calls to apply default
# arguments.
-if class com.anthropic.** { @com.fasterxml.jackson.annotation.* <init>(...); }
-keep,allowobfuscation class com.anthropic.<1> { <init>(...); }

# Likewise for our classes with a deserializer or serializer, and for those, which only the annotations refer to.
-if @com.fasterxml.jackson.databind.annotation.JsonDeserialize class com.anthropic.**
-keep,allowobfuscation class com.anthropic.<1>
-if @com.fasterxml.jackson.databind.annotation.JsonSerialize class com.anthropic.**
-keep,allowobfuscation class com.anthropic.<1>
-if class com.anthropic.** extends com.fasterxml.jackson.databind.JsonDeserializer
-keep,allowobfuscation class com.anthropic.<1> { <init>(); }
-if class com.anthropic.** extends com.fasterxml.jackson.databind.JsonSerializer
-keep,allowobfuscation class com.anthropic.<1> { <init>(); }