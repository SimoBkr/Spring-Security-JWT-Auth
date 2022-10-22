package com.peaqock.aml.utils;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PasswordGeneratorUtils {

    private static final String ERROR_CODE = "ERRONEOUS_SPECIAL_CHARS";
    /**
     * Special characters allowed in password.
     */
    private static final String ALLOWED_SPL_CHARACTERS = "!@#$%^&*()_+";
    private static final Random random = new SecureRandom();

    public static String generatePassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);
        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);
        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);
        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return ALLOWED_SPL_CHARACTERS;
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);
        return gen.generatePassword(10, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
    }

    public static String generateRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(getRandomNumbers(),
                Stream.concat(getRandomSpecialChars(),
                        Stream.concat(getRandomAlphabets(2, true),
                                getRandomAlphabets(4, false))
                )
        );
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);
        return charList.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static Stream<Character> getRandomAlphabets(int count, boolean upperCase) {
        IntStream characters;
        if (upperCase) {
            characters = random.ints(count, 65, 90);
        } else {
            characters = random.ints(count, 97, 122);
        }
        return characters.mapToObj(data -> (char) data);
    }

    private static Stream<Character> getRandomNumbers() {
        IntStream numbers = random.ints(2, 48, 57);
        return numbers.mapToObj(data -> (char) data);
    }

    private static Stream<Character> getRandomSpecialChars() {
        IntStream specialChars = random.ints(2, 33, 45);
        return specialChars.mapToObj(data -> (char) data);
    }
}
