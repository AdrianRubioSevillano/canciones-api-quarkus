package es.upsa.dasi.quarkuscanciones.domain.exceptions;

public class CancionesRunTimeException extends RuntimeException {
    public CancionesRunTimeException() {
    }

    public CancionesRunTimeException(String message) {
        super(message);
    }

    public CancionesRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CancionesRunTimeException(Throwable cause) {
        super(cause);
    }

    public CancionesRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
