package main.java.ru.golchanskiy.sd.refactoring.servlet;

import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.entity.Product;
import main.java.ru.golchanskiy.sd.refactoring.html.formatting.ProductHtmlFormatting;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class GetProductsServlet extends HttpServlet {
    ProductDAO productDAO;
    ProductHtmlFormatting htmlFormatting;
    public GetProductsServlet(ProductDAO productDAO, ProductHtmlFormatting formatting){
        this.productDAO = productDAO;
        this.htmlFormatting = formatting;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ans;
        try {
            ans = htmlFormatting.productsToHtml(productDAO.getAllProducts());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.getWriter().println(ans);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
