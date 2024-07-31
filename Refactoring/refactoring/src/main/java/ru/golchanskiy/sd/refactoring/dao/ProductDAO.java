package main.java.ru.golchanskiy.sd.refactoring.dao;

import main.java.ru.golchanskiy.sd.refactoring.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDAO {
    public void executeAndInsert(Product product){
        executeAndInsert("INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + product.getName() + "\"," + product.getPrice() + ")");
    }
    private void executeAndInsert(String sql){
        try {
            try (Connection c = getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
}
