#!/bin/bash
set -e

cd /home/user/anthropic-sdk-java

echo "=== Running main transformation script ==="
python3 scripts/remove-all-optional.py

echo "=== Removing OptionalExtensions.kt ==="
rm -f anthropic-java-core/src/commonMain/kotlin/com/anthropic/core/OptionalExtensions.kt

echo "=== Fixing StructuredContentBlock files ==="
sed -i 's/rawContentBlock.text().map { StructuredTextBlock(outputType, it) }/rawContentBlock.text()?.let { StructuredTextBlock(outputType, it) }/g' \
    anthropic-java-core/src/commonMain/kotlin/com/anthropic/models/messages/StructuredContentBlock.kt \
    anthropic-java-core/src/commonMain/kotlin/com/anthropic/models/beta/messages/StructuredContentBlock.kt

echo "=== Fixing accumulator .get() calls ==="
sed -i 's/\.stopDetails()\.get()/\.stopDetails()!!/g; s/\.stopSequence()\.get()/\.stopSequence()!!/g; s/\.container()\.get()/\.container()!!/g; s/\.contextManagement()\.get()/\.contextManagement()!!/g' \
    anthropic-java-core/src/commonMain/kotlin/com/anthropic/helpers/BetaMessageAccumulator.kt \
    anthropic-java-core/src/commonMain/kotlin/com/anthropic/helpers/MessageAccumulator.kt

echo "=== Fixing != null() ==="
sed -i 's/!= null()/!= null/g' anthropic-java-core/src/commonMain/kotlin/com/anthropic/helpers/BetaMessageAccumulator.kt

echo "=== Fixing BetaToolRunner ==="
sed -i "s|val toolUseBlockParams = contentBlockParams.flatMap { it.toolUse().asSequence() }|val toolUseBlockParams = contentBlockParams.mapNotNull { it.toolUse() }|g" \
    anthropic-java-core/src/commonMain/kotlin/com/anthropic/helpers/BetaToolRunner.kt
sed -i "s|val handler = params.betaMemoryToolHandler().get()|val handler = params.betaMemoryToolHandler()!!|g" \
    anthropic-java-core/src/commonMain/kotlin/com/anthropic/helpers/BetaToolRunner.kt

echo "=== Fixing ValuesJvm.kt ==="
sed -i "s|fun <T : Any> MultipartField<T>.filenameOptional(): String? = java.util.filename()|fun <T : Any> MultipartField<T>.filenameOptional(): String? = filename()|g" \
    anthropic-java-core/src/commonMain/kotlin/com/anthropic/core/ValuesJvm.kt

echo "=== Fixing orphaned comments ==="
python3 scripts/fix-orphaned-comments.py

echo "=== Verifying ==="
remaining=$(grep -rl "java.util.Optional" anthropic-java-core/src/commonMain/ --include="*.kt" | grep -v OptionalExtensions | wc -l)
echo "Files still with java.util.Optional in commonMain: $remaining"

echo "=== Compiling ==="
./gradlew :anthropic-java-core:compileKotlinJvm --no-configuration-cache 2>&1 | grep "^e:" | wc -l
echo "=== Done ==="
