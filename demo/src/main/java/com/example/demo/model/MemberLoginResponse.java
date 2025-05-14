package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Schema(name = "LoginSuccessResponse")
public class MemberLoginResponse {

    @Schema(example = "'Operation' success/failed")
    private String message;
    @Schema(example = "Http status code(200, 201, 400, 404, 500)")
    private int status;

    @Schema(description = "MemberId", examples = "1")
    private Long memberId;

    public MemberLoginResponse(String message, HttpStatus status, Member member) {
        this.message = message;
        this.status = status.value();
        this.memberId = member != null ? member.getId() : null;
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

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
