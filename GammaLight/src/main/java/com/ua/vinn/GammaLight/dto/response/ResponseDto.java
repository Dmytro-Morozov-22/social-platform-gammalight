package com.ua.vinn.GammaLight.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ua.vinn.GammaLight.trashFiles.ResponseFindMe;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> implements Serializable {

    private static final long serialVersionUID = 1882067957239979192L;

    private T data = null;
    private String message;
    private ResponseDto.Status status = ResponseDto.Status.OK;

    public ResponseDto(T data) {
        this.data = data;
    }

    public ResponseDto(ResponseDto.Status status, String message) {
        this.status = status;
        this.message = message;
    }

    @ToString
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Status {
        OK(0, "OK"),
        OK_SUCCESS(200, "SUCCESS"),
        FAIL(500, "Fail"),
        BAD_REQUEST(400, "BAD_REQUEST"),
        UNAUTHORIZED(401, "UNAUTHORIZED"),
        CUSTOM(null, null),
        NOT_FOUND(404, "NOT_FOUND"),
        USER_NOT_FOUND(409, "user not found"),
        USER_NOT_UNIQUE(420, "user not unique"),
        REQUIRED_FIELDS(425, "Required field empty");

        @Accessors(fluent = true)
        private Integer id;
        @Getter
        @Accessors(fluent = true)
        private String message;

        @JsonValue
        public Integer id() {
            return id;
        }

    }

}
