package net.dunice.advancedjavaprojectacademy.tasks.block2;

import lombok.val;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

    //TODO: Not right implemented
    //FIXME: Not right impl
    @Override
    public String getCompressedString(String noCompressedString) {
        if (noCompressedString.isEmpty()) {
            return noCompressedString;
        }

        val stringBuilder = new StringBuilder(noCompressedString.length());

        var repeats = 1;
        var previous = noCompressedString.charAt(0);

        for (int i = 0; i < noCompressedString.length(); ++i) {
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

        return stringBuilder.toString();
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
        val allowedBraces = "({[]})";
        val braces = new HashMap<Character, Integer>();

        givenString.chars()
                .filter(letter -> allowedBraces.contains(Character.toString(letter)))
                .forEach(brace -> countKeys(braces, (char) brace));

        for (var entry : braces.entrySet()) {
            val brace = entry.getKey();
            val count = entry.getValue();
            val opposite = getOppositeBrace(brace);

            if (!braces.getOrDefault(opposite, 0).equals(count)) {
                return false;
            }
        }
        return true;
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
        if (length % 2 == 0) {
            throw new UnsupportedOperationException("Odd strings are not supported.");
        }

        val stringBuilder = new StringBuilder(length * 2);
        val middle = length / 2;

        for (int i = 0; i <= length; ++i) {
            val brace = i <= middle ? '(' : ')';
            stringBuilder.append(brace);

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

        return builder.toString();
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
