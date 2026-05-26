package com.acropolis.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationCodeResponse {
    
    @JsonProperty("operation_code")
    private int operationCode;
}
