package domain.exceptions;

public class MachiKoroException extends RuntimeException {
    public MachiKoroException(String message) {
        super(message);
    }

    public MachiKoroException(String message, Throwable cause) {
        super(message, cause);
    }

    public MachiKoroException(Throwable cause) {
        super(cause);
    }

    public MachiKoroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
