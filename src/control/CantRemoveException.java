package control;

import java.sql.SQLException;

public class CantRemoveException extends SQLException {

    public CantRemoveException(String reason) {
        super(reason);
    }
}
