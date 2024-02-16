package org.currytree

object Slugger {
    fun slugify(input: String): String {
        val lower = input.lowercase()
        val ascii = lower.replace(Regex("[^\\p{ASCII}]+"), "-")
        val noPunctOrControl = ascii.replace(Regex("[\\p{Punct}\\p{Cntrl}]+"), "")
        val spacesReplaced = noPunctOrControl.replace(Regex("[\\p{Blank}]+"), "-")
        val trailingRemoved = spacesReplaced.replace(Regex("[-_]+$"), "")
        val leadingRemoved = trailingRemoved.replace(Regex("^[-_]+"), "")
        return leadingRemoved
    }
}