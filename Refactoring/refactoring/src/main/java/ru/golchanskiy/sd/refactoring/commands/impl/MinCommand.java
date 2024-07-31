package main.java.ru.golchanskiy.sd.refactoring.commands.impl;

import main.java.ru.golchanskiy.sd.refactoring.commands.ProductCommand;
import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.html.formatting.ProductHtmlFormatting;

import java.io.IOException;
import java.sql.SQLException;

public class MinCommand implements ProductCommand {
    ProductHtmlFormatting formatting;
    ProductDAO productDAO;
    public MinCommand(ProductHtmlFormatting formatting, ProductDAO dao){
        this.productDAO = dao;
        this.formatting = formatting;
    }
    @Override
    public String toHtml() throws SQLException, IOException {
        return formatting.productToHtml(productDAO.getMaxProduct().get(),"Product with min price: ");
    }
}
