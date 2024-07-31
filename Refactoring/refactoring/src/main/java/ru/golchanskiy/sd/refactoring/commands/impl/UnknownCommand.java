package main.java.ru.golchanskiy.sd.refactoring.commands.impl;

import main.java.ru.golchanskiy.sd.refactoring.commands.ProductCommand;
import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.html.formatting.ProductHtmlFormatting;

import java.io.IOException;
import java.sql.SQLException;

public class UnknownCommand implements ProductCommand {
    @Override
    public String toHtml() throws SQLException, IOException {
        return "Unknown command";
    }
}
