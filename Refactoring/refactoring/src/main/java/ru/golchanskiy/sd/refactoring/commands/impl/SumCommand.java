package main.java.ru.golchanskiy.sd.refactoring.commands.impl;

import main.java.ru.golchanskiy.sd.refactoring.commands.ProductCommand;
import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.html.formatting.ProductHtmlFormatting;

import java.io.IOException;
import java.sql.SQLException;

public class SumCommand implements ProductCommand {
    ProductHtmlFormatting formatting;
    ProductDAO productDAO;
    public SumCommand(ProductHtmlFormatting formatting, ProductDAO dao){
        this.productDAO = dao;
        this.formatting = formatting;
    }
    @Override
    public String toHtml() throws SQLException, IOException {
        return formatting.valueToHtml(productDAO.getSumPrice().get(),"Summary price: ");
    }
}
