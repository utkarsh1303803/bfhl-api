package com.acropolis.bfhl.controller;

import com.acropolis.bfhl.dto.BfhlResponse;
import com.acropolis.bfhl.service.BfhlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BfhlController.class)
class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BfhlService bfhlService;

    // 1. Happy path
    @Test
    void givenValidRequest_whenPost_thenReturns200WithCorrectStructure() throws Exception {
        BfhlResponse mockResponse = BfhlResponse.builder()
                .isSuccess(true)
                .userId("john_doe_17091999")
                .email("john@xyz.com")
                .rollNumber("ABCD123")
                .oddNumbers(List.of("1"))
                .evenNumbers(List.of("334", "4"))
                .alphabets(List.of("A", "R"))
                .specialCharacters(List.of("$"))
                .sum("339")
                .concatString("Ra")
                .build();

        when(bfhlService.processData(anyList())).thenReturn(mockResponse);

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":[\"a\",\"1\",\"334\",\"4\",\"R\",\"$\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("john_doe_17091999"))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_string").value("Ra"));
    }

    // 2. Null data field
    @Test
    void givenNullDataField_whenPost_thenReturns400WithIsSuccessFalse() throws Exception {
        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":null}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    // 3. Empty data array
    @Test
    void givenEmptyDataArray_whenPost_thenReturns400WithIsSuccessFalse() throws Exception {
        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":[]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    // 4. Malformed JSON
    @Test
    void givenMalformedJson_whenPost_thenReturns400WithIsSuccessFalse() throws Exception {
        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{bad json}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }
}
