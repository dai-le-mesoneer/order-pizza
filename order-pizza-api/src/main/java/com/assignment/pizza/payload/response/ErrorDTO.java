package com.assignment.pizza.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    private String key;

    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;

    public static <T extends Enum<T>> ErrorDTO of(String key, T code){
        ErrorDTO inst = new ErrorDTO();
        inst.setKey(key);
        inst.setCode(code.name());
        return inst;
    }

    public static <T extends Enum<T>> ErrorDTO of(String key, T code, String value){
        ErrorDTO inst = of(key, code);
        inst.setValue(value);
        return inst;
    }
}
