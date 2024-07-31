package main.java.ru.golchanskiy.sd.refactoring;

import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.entity.Product;
import main.java.ru.golchanskiy.sd.refactoring.html.formatting.ProductHtmlFormatting;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import main.java.ru.golchanskiy.sd.refactoring.servlet.AddProductServlet;
import main.java.ru.golchanskiy.sd.refactoring.servlet.GetProductsServlet;
import main.java.ru.golchanskiy.sd.refactoring.servlet.QueryServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        try{
            productDAO.initTable();
        }
        catch (SQLException exception){
            log.error(exception.getMessage());
        }

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet(productDAO)), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(productDAO, new ProductHtmlFormatting())),"/get-products");
        context.addServlet(new ServletHolder(new QueryServlet(productDAO)),"/query");

        server.start();
        server.join();
    }
}
