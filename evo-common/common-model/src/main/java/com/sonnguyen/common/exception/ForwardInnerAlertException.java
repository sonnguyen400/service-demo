package com.sonnguyen.common.exception;

import com.sonnguyen.common.dto.error.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForwardInnerAlertException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ErrorResponse<Void> response;

    public ForwardInnerAlertException(ErrorResponse<Void> response) {
        this.response = response;
    }
}
