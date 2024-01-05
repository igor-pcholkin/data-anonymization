package com.discreet.datamasking.anonymizer.name;

import com.discreet.datamasking.anonymizer.AbstractAnonymizer;

import java.util.Random;

/**
 * Anonymizer working with names/full names.
 * Substitutes each letter with a random latin letter except starting ones
 * Letters converted to appropriate case: starting ones to upper case, following ones to lower case.
 * Respects number of original letters in each name.
 *
 * Example: "John Paul Smith" -> "Jzir Pyre Snpex"
 */
public class FullNameMildAnonymizer extends AbstractAnonymizer {
    static final int LATIN_CHAR_RANGE = 26;
    static final int LATIN_BASE_CHAR_LOWER = 'a';
    static final int LATIN_BASE_CHAR_UPPER = 'A';

    @Override
    protected boolean isTranslationNeeded(int origCodePoint, String input, int i) {
        return !Character.isSpaceChar(origCodePoint);
    }

    @Override
    protected Character doTranslateChar(int origCodePoint, Random random, String input, int i) {
        int maskedCodePoint = isValidFirstLetterInWord(origCodePoint, input, i) ? Character.toTitleCase(origCodePoint) :
                LATIN_BASE_CHAR_LOWER + ( (origCodePoint + Math.abs(random.nextInt())) % LATIN_CHAR_RANGE );
        return Character.valueOf((char) maskedCodePoint);
    }

    protected boolean isValidFirstLetterInWord(int origCodePoint, String input, int i) {
        return (i == 0 || Character.isSpaceChar(input.codePointAt(i - 1))) &&
                Character.isAlphabetic(origCodePoint);
    }

}
