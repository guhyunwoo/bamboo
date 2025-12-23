package bsm.insert.bamboo.global.security.exception;

import bsm.insert.bamboo.global.error.BusinessBaseException;
import bsm.insert.bamboo.global.error.ErrorCode;

public class InvalidTokenException extends BusinessBaseException {
    public static final BusinessBaseException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
