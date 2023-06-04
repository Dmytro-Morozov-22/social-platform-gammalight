package com.ua.vinn.GammaLight.trashFiles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFindMe<T> implements Serializable {

        private static final long serialVersionUID = 1882067957239979192L;

        private T data = null;
        private Object data2 = null;
        private String message;
        private Status status = Status.OK;

        public ResponseFindMe(T data) {
            this.data = data;
        }

        public ResponseFindMe(Status status, String message) {
            this.status = status;
            this.message = message;
        }

        public boolean isSuccessful() {
            if (getStatus() == Status.OK || getStatus() == Status.OK_SUCCESS) {
                return true;
            }
            return false;
        }

        public Status getStatus() {
            if (status == null) return Status.OK;
            return status;
        }

        @ToString
        @JsonFormat(shape = JsonFormat.Shape.OBJECT)
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
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

            @JsonCreator
            public static Status fromId(int id) {
                for (Status c : Status.values()) {
                    if ((c.id != null) && (c.id == id)) {
                        return c;
                    }
                }
                return CUSTOM;
            }
        }




}
