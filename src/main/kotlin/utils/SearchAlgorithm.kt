package utils

interface SearchAlgorithm {
    fun search(fullText: String, pattern: String): Boolean
}