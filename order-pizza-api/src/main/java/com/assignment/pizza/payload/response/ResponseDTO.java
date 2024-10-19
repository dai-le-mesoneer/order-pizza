package com.assignment.pizza.payload.response;

import com.assignment.pizza.common.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder(setterPrefix = "set", builderMethodName = "newBuilder")
public class ResponseDTO<T> implements Serializable {
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ErrorCode code;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ErrorDTO> errors;
}
