package org.hbrs.se2.process.control.exceptions;

/**
 *
 * @author Felix
 */
public class DatabaseException extends Exception {

    private String reason = null;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DatabaseException(String reason) {
        this.reason = reason;
    }
}
