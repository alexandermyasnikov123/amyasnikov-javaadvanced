package net.dunice.advancedjavaprojectacademy.tasks.block2;

import lombok.val;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Block2 implements Block2Interface {
    @Override
    public <T> Collection<T> getWithoutDuplicates(Collection<T> collection) {
        return Set.copyOf(collection);
    }

    @Override
    public <T> T[] arrayIterator(T[] array) {
        val iterator = Arrays.stream(array).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        return array;
    }

    @Override
    public Integer countNumber(Integer number) {
        val required = 2;
        var result = 0;
        for (int i = 0; i <= number; ++i) {
            result += countNumbers(i, required);
        }
        return result;
    }

    @SuppressWarnings("SameParameterValue")
    private int countNumbers(int decimalValue, int required) {
        val boxedNumber = Integer.valueOf(decimalValue).toString();
        return (int) boxedNumber.chars()
                .filter(symbol -> Character.getNumericValue(symbol) == required)
                .count();
    }

    @Override
    public boolean isPermutationStrings(String str1, String str2) {
        if (str1.isEmpty() && str2.isEmpty()) {
            return false;
        }

        val firstLetters = new HashMap<Character, Integer>();
        val secondLetters = new HashMap<Character, Integer>();

        str1.chars().forEach(letter -> countKeys(firstLetters, (char) letter));
        str2.chars().forEach(letter -> countKeys(secondLetters, (char) letter));

        return firstLetters.equals(secondLetters);
    }

    private <K> void countKeys(Map<K, Integer> map, K key) {
        val oldValue = map.getOrDefault(key, 0);
        map.put(key, oldValue + 1);
    }

    @Override
    public String getCompressedString(String noCompressedString) {
        if (noCompressedString.isEmpty()) {
            return noCompressedString;
        }

        val stringBuilder = new StringBuilder(noCompressedString.length());

        var repeats = 1;
        var previous = noCompressedString.charAt(0);

        for (int i = 1; i < noCompressedString.length(); ++i) {
            val current = noCompressedString.charAt(i);
            if (current == previous) {
                ++repeats;
            } else {
                stringBuilder.append(previous);
                stringBuilder.append(repeats);
                repeats = 1;
            }
            previous = current;
        }
        stringBuilder.append(previous);
        stringBuilder.append(repeats);

        val compressedString = stringBuilder.toString();
        if (compressedString.length() > noCompressedString.length()) {
            return noCompressedString;
        }
        return compressedString;
    }

    @Override
    public Character getFrequencyCharacter(String source) {
        val symbols = new LinkedHashMap<Character, Integer>();

        source.chars().forEach(value -> countKeys(symbols, (char) value));
        return symbols.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .orElseThrow()
                .getKey();
    }

    @Override
    public boolean isStringValid(String givenString) {
        val openedBrackets = "({[";
        val closedBrackets = ")}]";

        val brackets = new Stack<Character>();
        for (int i = 0; i < givenString.length(); ++i) {
            val letter = givenString.charAt(i);
            val stringLetter = Character.toString(letter);

            val isOpenedBracket = openedBrackets.contains(stringLetter);
            val isClosedBracket = closedBrackets.contains(stringLetter);

            if (isOpenedBracket) {
                brackets.push(letter);
            } else if (isClosedBracket) {
                val oppositeBracket = getOppositeBrace(letter);
                if (brackets.isEmpty() || !brackets.pop().equals(oppositeBracket)) {
                    return false;
                }
            }
        }

        return brackets.isEmpty();
    }

    private char getOppositeBrace(char brace) {
        return switch (brace) {
            case '(' -> ')';
            case '{' -> '}';
            case '[' -> ']';
            case ']' -> '[';
            case '}' -> '{';
            case ')' -> '(';
            default -> throw new IllegalArgumentException("Unknown brace type.");
        };
    }

    @Override
    public String getModifyingString(String noBracketsString) {
        val length = noBracketsString.length();
        val isOdd = length % 2 == 0;
        val stringBuilder = new StringBuilder(length * 2);
        val middle = length / 2;

        for (int i = 0; i <= length; ++i) {

            val brace = i <= middle ? '(' : ')';
            stringBuilder.append(brace);

            if (i == middle && isOdd) {
                stringBuilder.append(')');
            }

            if (i < length) {
                stringBuilder.append(noBracketsString.charAt(i));
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public String getValidStringNoSpaces(String string) {
        var whitespaceAppeared = false;
        val length = string.length();
        val builder = new StringBuilder(length);

        for (int i = 0; i < length; ++i) {
            val symbol = string.charAt(i);
            val isWhitespace = Character.isWhitespace(symbol);

            if (isWhitespace && whitespaceAppeared) {
                continue;
            } else {
                whitespaceAppeared = isWhitespace;
            }

            builder.append(symbol);
        }

        return builder.toString().trim();
    }

    @Override
    public int numberOfIdenticalPairs(int[] nums) {
        var pairs = 0;

        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] == nums[j]) {
                    ++pairs;
                }
            }
        }

        return pairs;
    }
}
