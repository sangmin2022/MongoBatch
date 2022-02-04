package com.example.mongobatch.Job.quartzControllerService.dao;

import lombok.Data;

@Data
//@Document(collection = "test_table")
public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
