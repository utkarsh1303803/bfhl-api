package com.acropolis.bfhl.service;

import com.acropolis.bfhl.dto.BfhlResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BfhlServiceImpl implements BfhlService {

    // ── Replace these with your actual details ──────────────────────────────
    private static final String USER_ID     = "utkarsh_singh_07072005";
    private static final String EMAIL       = "utkarshsingh230400@acropolis.in";
    private static final String ROLL_NUMBER = "0827AL231137";
    // ────────────────────────────────────────────────────────────────────────

    @Override
    public BfhlResponse processData(List<String> data) {
        log.info("Processing {} elements", data.size());

        List<String> oddNumbers       = new ArrayList<>();
        List<String> evenNumbers      = new ArrayList<>();
        List<String> alphabeticElems  = new ArrayList<>();
        List<String> specialChars     = new ArrayList<>();

        for (String element : data) {
            if (isNumber(element)) {
                long value = Long.parseLong(element);
                if (value % 2 == 0) {
                    evenNumbers.add(element);
                } else {
                    oddNumbers.add(element);
                }
            } else if (isAlphabetic(element)) {
                alphabeticElems.add(element.toUpperCase());
            } else {
                specialChars.add(element);
            }
        }

        String sum          = computeSum(data);
        String concatString = buildConcatString(alphabeticElems);

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabeticElems)
                .specialCharacters(specialChars)
                .sum(sum)
                .concatString(concatString)
                .build();
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean isAlphabetic(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    private String computeSum(List<String> data) {
        long total = 0;
        for (String element : data) {
            if (isNumber(element)) {
                total += Long.parseLong(element);
            }
        }
        return String.valueOf(total);
    }

    /**
     * Builds concat_string from already-uppercased alphabetic elements:
     * 1. Flatten all chars from each element (preserving order)
     * 2. Concatenate into one string
     * 3. Reverse
     * 4. Apply alternating caps (index 0 = upper, 1 = lower, ...)
     */
    String buildConcatString(List<String> alphabeticElements) {
        if (alphabeticElements == null || alphabeticElements.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (String elem : alphabeticElements) {
            sb.append(elem);
        }

        String reversed = sb.reverse().toString();
        return applyAlternatingCaps(reversed);
    }

    String applyAlternatingCaps(String input) {
        if (input == null || input.isEmpty()) return "";
        StringBuilder result = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            result.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        return result.toString();
    }
}
