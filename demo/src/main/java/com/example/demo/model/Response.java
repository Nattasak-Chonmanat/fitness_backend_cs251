package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@Schema(description = "Response")
public class Response {
    @Schema(example = "'Operation' success/failed")
    private String message;
    @Schema(example = "Http status code(200, 201, 400, 404, 500)")
    private int status;

    public Response(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }
}
