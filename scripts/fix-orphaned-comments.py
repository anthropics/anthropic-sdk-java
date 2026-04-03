#!/usr/bin/env python3
"""Fix orphaned Alias comments and @Deprecated annotations left after Optional overload deletion."""

import os
import re
import glob

BASE = "/home/user/anthropic-sdk-java"

def find_kt_files():
    patterns = [
        os.path.join(BASE, "anthropic-java-core/src/commonMain/**/*.kt"),
        os.path.join(BASE, "anthropic-java-core/src/test/**/*.kt"),
        os.path.join(BASE, "anthropic-java-client-okhttp/src/main/**/*.kt"),
        os.path.join(BASE, "anthropic-java-aws/src/main/**/*.kt"),
    ]
    files = set()
    for p in patterns:
        files.update(glob.glob(p, recursive=True))
    return sorted(files)

def fix_orphaned_comments(content):
    """Remove orphaned Alias comments followed by @Deprecated that no longer precede a function."""
    lines = content.split('\n')
    new_lines = []
    i = 0
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()

        # Pattern 1: /** Alias for ... */ followed by @Deprecated, then either a doc comment or not a fun line
        if stripped.startswith('/** Alias for') and stripped.endswith('*/'):
            # Check next non-blank line
            j = i + 1
            while j < len(lines) and lines[j].strip() == '':
                j += 1
            if j < len(lines) and lines[j].strip().startswith('@Deprecated'):
                # Check what comes after @Deprecated
                k = j + 1
                while k < len(lines) and lines[k].strip() == '':
                    k += 1
                if k < len(lines) and (lines[k].strip().startswith('/**') or lines[k].strip().startswith('@')):
                    # This is an orphaned comment+annotation - skip both
                    i = j + 1  # skip past @Deprecated
                    continue
                elif k < len(lines) and not lines[k].strip().startswith('fun '):
                    # Also orphaned
                    i = j + 1
                    continue
            elif j < len(lines) and not lines[j].strip().startswith('fun ') and not lines[j].strip().startswith('@'):
                # Orphaned comment without @Deprecated
                # But only if the Alias is about Optional (contains just a param name, no real overload info)
                # Be safe - only remove if the Alias text looks like it was about Optional
                pass

        # Pattern 2: /** Alias for ... */ immediately followed by another /** comment (orphan)
        if stripped.startswith('/** Alias for') and stripped.endswith('*/'):
            j = i + 1
            while j < len(lines) and lines[j].strip() == '':
                j += 1
            if j < len(lines) and lines[j].strip().startswith('/**'):
                # orphaned Alias - skip it
                i += 1
                continue

        new_lines.append(line)
        i += 1

    return '\n'.join(new_lines)

def main():
    files = find_kt_files()
    modified = 0
    for filepath in files:
        with open(filepath, 'r') as f:
            content = f.read()
        new_content = fix_orphaned_comments(content)
        if new_content != content:
            with open(filepath, 'w') as f:
                f.write(new_content)
            modified += 1
    print(f"Fixed orphaned comments in {modified} files")

if __name__ == '__main__':
    main()
