package main.java.ru.golchanskiy.sd.refactoring.commands;

import java.io.IOException;
import java.sql.SQLException;

public interface ProductCommand {
    String toHtml() throws SQLException, IOException;
}
