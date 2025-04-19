package utils

fun kmpSearch(searchedFullText: String, searchedPattern: String): Boolean {
    val searchedFullTextLowerCase = searchedFullText.lowercase()
    val searchedPatternLowerCase = searchedPattern.lowercase()

    val lps = buildLPS(searchedPatternLowerCase)
    var searchedFullTextIndex = 0
    var patternIndex = 0

    try {
        while (searchedFullTextIndex < searchedFullTextLowerCase.length) {
            if (searchedPatternLowerCase[patternIndex] == searchedFullTextLowerCase[searchedFullTextIndex]) {
                searchedFullTextIndex++
                patternIndex++
            }

            if (patternIndex == searchedPatternLowerCase.length) {
                return true
            } else if (searchedFullTextIndex < searchedFullTextLowerCase.length && searchedPatternLowerCase[patternIndex] != searchedFullTextLowerCase[searchedFullTextIndex]) {
                if (patternIndex != 0) {
                    patternIndex = lps[patternIndex - 1]
                } else {
                    searchedFullTextIndex++
                }
            }
        }
    } catch (exception: Exception) {
        throw Exception("Add valid meal name please")
    }
    return false
}

private fun buildLPS(searchedPattern: String): IntArray {
    val lps = IntArray(searchedPattern.length)
    var length = 0
    var index = 1

    while (index < searchedPattern.length) {
        if (searchedPattern[index] == searchedPattern[length]) {
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