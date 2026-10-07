package es.upsa.dasi.quarkuscanciones.domain.exceptions;

public class CancionException extends Exception {
    public CancionException() {
    }

    public CancionException(String message) {
        super(message);
    }

    public CancionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CancionException(Throwable cause) {
        super(cause);
    }

    public CancionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
