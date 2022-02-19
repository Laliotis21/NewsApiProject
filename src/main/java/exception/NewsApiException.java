package exception;

@SuppressWarnings("serial")
public class NewsApiException extends Exception {
	public NewsApiException() {
        super();
    }

    public NewsApiException(String message) {
        super(message);
    }

    public NewsApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsApiException(Throwable cause) {
        super(cause);
    }

    protected NewsApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}