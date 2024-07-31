package main.java.ru.golchanskiy.sd.refactoring.commands.impl;

import main.java.ru.golchanskiy.sd.refactoring.commands.ProductCommand;

import java.io.IOException;
import java.sql.SQLException;

public class UnknownCommand implements ProductCommand {
    @Override
    public String toHtml() throws SQLException, IOException {
        return "Unknown command";
    }
}
