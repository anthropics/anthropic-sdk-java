#!/bin/bash
# Strip JVM-specific annotations and imports from model files
# Run AFTER error classes are migrated to commonMain

DIR="anthropic-java-core/src/main/kotlin/com/anthropic/models"
COUNT=0

for f in $(find "$DIR" -name "*.kt"); do
    # Remove Jackson imports
    sed -i '/^import com\.fasterxml\.jackson/d' "$f"
    # Remove java.util imports
    sed -i '/^import java\.util\.Collections$/d' "$f"
    sed -i '/^import java\.util\.Objects$/d' "$f"
    sed -i '/^import java\.util\.Optional$/d' "$f"
    sed -i '/^import kotlin\.jvm\.optionals/d' "$f"
    # Remove Jackson annotations (on their own lines)
    sed -i '/@JsonCreator/d' "$f"
    sed -i '/@JsonProperty/d' "$f"
    sed -i '/@JsonAnySetter/d' "$f"
    sed -i '/@JsonAnyGetter/d' "$f"
    sed -i '/@ExcludeMissing/d' "$f"
    # Remove JVM annotations
    sed -i '/@JvmStatic/d' "$f"
    sed -i '/@JvmSynthetic/d' "$f"
    sed -i '/@JvmField/d' "$f"
    sed -i '/@JvmName/d' "$f"
    sed -i '/@JvmOverloads/d' "$f"
    sed -i '/@file:JvmName/d' "$f"
    # Replace Collections.unmodifiableMap with toMap()
    sed -i 's/Collections\.unmodifiableMap(\(.*\))/\1.toMap()/g' "$f"
    # Replace Objects.hash with contentHash
    sed -i 's/Objects\.hash(\(.*\))/contentHash(\1)/g' "$f"

    COUNT=$((COUNT + 1))
done

echo "Processed $COUNT model files"
