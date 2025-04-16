package data.utilities

fun splitIgnoringQuotedCommas(input: String): List<String> {
    val pattern = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"
    return input.split(pattern.toRegex())
}