package com.test.spring_security_module_test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Kiselev Oleg
 * @param firstName - имя
 * @param secondName - фамилия
 */
public record DeveloperDTO(
    @JsonProperty("first_name") String firstName,
    @JsonProperty("second_name") String secondName
) {
}
