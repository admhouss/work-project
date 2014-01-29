package by.premiya.olga.project.util.exceptions;

import org.springframework.security.access.AccessDeniedException;

/**
 * @author Vlad Abramov
 */
public class DoNotDeleteException extends AccessDeniedException {

    private static final long serialVersionUID = -6271970654561450630L;

    /**
     * Constructs an <code>DoNotDeleteException</code> with the specified
     * message.
     *
     * @param msg the detail message
     */
    public DoNotDeleteException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>DoNotDeleteException</code> with the specified
     * message and root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public DoNotDeleteException(String msg, Throwable t) {
        super(msg, t);
    }
}
