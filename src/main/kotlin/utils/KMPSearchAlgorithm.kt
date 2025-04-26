package utils

class KMPSearchAlgorithm : SearchAlgorithm {
    override fun search(fullText: String, pattern: String): Boolean {
        val lowerText = fullText.lowercase()
        val lowerPattern = pattern.lowercase()
        val lps = buildLPS(lowerPattern)

        return try {
            performKMPSearch(lowerText, lowerPattern, lps)
        } catch (e: Exception) {
            throw Exception("Add valid meal name please")
        }
    }
}

private fun performKMPSearch(text: String, pattern: String, lps: IntArray): Boolean {
    var textIndex = 0
    var patternIndex = 0

    while (textIndex < text.length) {
        if (pattern[patternIndex] == text[textIndex]) {
            textIndex++
            patternIndex++
        }

        if (patternIndex == pattern.length) {
            return true
        } else if (textIndex < text.length && pattern[patternIndex] != text[textIndex]) {
            patternIndex = updatePatternIndex(patternIndex, lps)
            if (patternIndex == 0) {
                textIndex++
            }
        }
    }

    return false
}

private fun updatePatternIndex(currentIndex: Int, lps: IntArray): Int {
    return if (currentIndex != 0) lps[currentIndex - 1] else 0
}

private fun buildLPS(pattern: String): IntArray {
    val lps = IntArray(pattern.length)
    var length = 0
    var index = 1

    while (index < pattern.length) {
        if (pattern[index] == pattern[length]) {
            length++
            lps[index] = length
            index++
        } else {
            if (length != 0) {
                length = lps[length - 1]
            } else {
                lps[index] = 0
                index++
            }
        }
    }
    return lps
}
