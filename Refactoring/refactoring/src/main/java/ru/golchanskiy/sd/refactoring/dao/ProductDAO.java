package main.java.ru.golchanskiy.sd.refactoring.dao;

import jdk.jfr.internal.consumer.OngoingStream;
import main.java.ru.golchanskiy.sd.refactoring.entity.Product;
import main.java.ru.golchanskiy.sd.refactoring.sql.SQLFunction;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductDAO {
    public void executeAndInsert(Product product){
        executeAndInsert("INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + product.getName() + "\"," + product.getPrice() + ")");
    }
    private void executeAndInsert(String sql) {
        try {
            try (Connection c = getConnection()) {
                Statement stmt = c.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() throws SQLException, IOException {
        return executeQuery("SELECT * FROM PRODUCT", this::toProduct);
    }

    private <T> List<T> executeQuery(String sql, SQLFunction<ResultSet, T> sqlFunction) throws SQLException, IOException {
        try (Connection c = getConnection()) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(sqlFunction.apply(rs));
            }
            rs.close();
            stmt.close();
            return list;
        }
    }

    private Product toProduct(ResultSet set) throws SQLException {
        String name = set.getString("name");
        long price = set.getLong("price");
        return new Product(name, price);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:test.db");
    }
}
