package com.acropolis.bfhl.service;

import com.acropolis.bfhl.dto.BfhlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BfhlServiceImplTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    // 1. Basic mixed input — Example A
    @Test
    void givenMixedInput_whenProcessed_thenAllFieldsCorrect() {
        BfhlResponse r = service.processData(List.of("a", "1", "334", "4", "R", "$"));
        assertThat(r.isSuccess()).isTrue();
        assertThat(r.getOddNumbers()).containsExactly("1");
        assertThat(r.getEvenNumbers()).containsExactly("334", "4");
        assertThat(r.getAlphabets()).containsExactly("A", "R");
        assertThat(r.getSpecialCharacters()).containsExactly("$");
        assertThat(r.getSum()).isEqualTo("339");
        assertThat(r.getConcatString()).isEqualTo("Ra");
    }

    // 2. Larger mixed input — Example B
    @Test
    void givenLargerMixedInput_whenProcessed_thenConcatStringCorrect() {
        BfhlResponse r = service.processData(List.of("a", "y", "b"));
        assertThat(r.getConcatString()).isEqualTo("ByA");
        assertThat(r.getSum()).isEqualTo("0");
        assertThat(r.getOddNumbers()).isEmpty();
        assertThat(r.getEvenNumbers()).isEmpty();
    }

    // 3. Letters only — Example C
    @Test
    void givenLettersOnly_whenProcessed_thenOddEvenEmpty() {
        BfhlResponse r = service.processData(List.of("A", "ABCD", "DOE"));
        assertThat(r.getOddNumbers()).isEmpty();
        assertThat(r.getEvenNumbers()).isEmpty();
        assertThat(r.getSpecialCharacters()).isEmpty();
        assertThat(r.getConcatString()).isEqualTo("EoDdCbAa");
    }

    // 4. Numbers only
    @Test
    void givenNumbersOnly_whenProcessed_thenAlphabetsEmpty() {
        BfhlResponse r = service.processData(List.of("2", "3", "10"));
        assertThat(r.getAlphabets()).isEmpty();
        assertThat(r.getSpecialCharacters()).isEmpty();
        assertThat(r.getEvenNumbers()).containsExactly("2", "10");
        assertThat(r.getOddNumbers()).containsExactly("3");
        assertThat(r.getSum()).isEqualTo("15");
    }

    // 5. Special characters only
    @Test
    void givenSpecialCharsOnly_whenProcessed_thenNumbersAndAlphabetsEmpty() {
        BfhlResponse r = service.processData(List.of("$", "&", "-"));
        assertThat(r.getAlphabets()).isEmpty();
        assertThat(r.getOddNumbers()).isEmpty();
        assertThat(r.getEvenNumbers()).isEmpty();
        assertThat(r.getSpecialCharacters()).containsExactly("$", "&", "-");
    }

    // 6. Empty concat_string when no alphabets
    @Test
    void givenNoAlphabets_whenProcessed_thenConcatStringEmpty() {
        BfhlResponse r = service.processData(List.of("1", "2", "$"));
        assertThat(r.getConcatString()).isEqualTo("");
    }

    // 7. Sum = "0" when no numbers
    @Test
    void givenNoNumbers_whenProcessed_thenSumIsZero() {
        BfhlResponse r = service.processData(List.of("a", "$"));
        assertThat(r.getSum()).isEqualTo("0");
    }

    // 8. Odd/Even separation
    @Test
    void givenOddAndEvenNumbers_whenProcessed_thenCorrectlySeparated() {
        BfhlResponse r = service.processData(List.of("1", "2"));
        assertThat(r.getOddNumbers()).containsExactly("1");
        assertThat(r.getEvenNumbers()).containsExactly("2");
    }

    // 9. Multi-char alphabet treated as alphabetic
    @Test
    void givenMultiCharAlphabet_whenProcessed_thenTreatedAsAlphabetic() {
        BfhlResponse r = service.processData(List.of("ABCD"));
        assertThat(r.getAlphabets()).containsExactly("ABCD");
        assertThat(r.getSpecialCharacters()).isEmpty();
    }

    // 10. All alphabets uppercased
    @Test
    void givenLowercaseAlphabet_whenProcessed_thenAlphabetsUppercased() {
        BfhlResponse r = service.processData(List.of("a"));
        assertThat(r.getAlphabets()).containsExactly("A");
    }

    // 11. concat_string alternating case — all three examples
    @Test
    void givenExampleA_whenBuildConcatString_thenReturnsRa() {
        // alphabeticElems already uppercased by processData
        String result = service.buildConcatString(List.of("A", "R"));
        assertThat(result).isEqualTo("Ra");
    }

    @Test
    void givenExampleB_whenBuildConcatString_thenReturnsByA() {
        String result = service.buildConcatString(List.of("A", "Y", "B"));
        assertThat(result).isEqualTo("ByA");
    }

    @Test
    void givenExampleC_whenBuildConcatString_thenReturnsEoDdCbAa() {
        String result = service.buildConcatString(List.of("A", "ABCD", "DOE"));
        assertThat(result).isEqualTo("EoDdCbAa");
    }
}
