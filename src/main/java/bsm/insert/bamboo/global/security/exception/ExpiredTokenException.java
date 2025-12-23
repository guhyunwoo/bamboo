package bsm.insert.bamboo.global.security.exception;

import bsm.insert.bamboo.global.error.BusinessBaseException;
import bsm.insert.bamboo.global.error.ErrorCode;

public class ExpiredTokenException extends BusinessBaseException {
    public static final BusinessBaseException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
