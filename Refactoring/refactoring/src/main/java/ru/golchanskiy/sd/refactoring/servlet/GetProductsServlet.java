package main.java.ru.golchanskiy.sd.refactoring.servlet;

import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.entity.Product;

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
    public GetProductsServlet(ProductDAO productDAO){
        this.productDAO = productDAO;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder ans = new StringBuilder();
        try {
             List<Product> list = productDAO.getAllProducts();
             for(Product a : list){
                 ans.append(a.getName());
             }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.getWriter().println(ans);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
