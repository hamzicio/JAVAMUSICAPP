package com.employeeportal.demo.utilities.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorDetails {
    private Date TimeStamp;
    private String message;
    private String errorDetails;
}
