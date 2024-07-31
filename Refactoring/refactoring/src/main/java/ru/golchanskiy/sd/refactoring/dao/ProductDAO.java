package main.java.ru.golchanskiy.sd.refactoring.dao;

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
    public void initTable() throws SQLException {
        executeAndInsert("CREATE TABLE IF NOT EXISTS PRODUCT" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " PRICE          INT     NOT NULL)");
    }

    public void clearProducts() throws SQLException {
        executeAndInsert("DELETE FROM PRODUCT");
    }

    public void executeAndInsert(Product product) throws SQLException {
            executeAndInsert("INSERT INTO PRODUCT " +
                    "(NAME, PRICE) VALUES (\"" + product.getName() + "\"," + product.getPrice() + ")");
    }
    private void executeAndInsert(String sql) throws SQLException {
            try (Connection c = getConnection()) {
                Statement stmt = c.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            }
    }

    public List<Product> getAllProducts() throws SQLException, IOException {
        return executeQuery("SELECT * FROM PRODUCT", this::toProduct);
    }
    public Optional<Product> getMaxProduct() throws SQLException, IOException {
        List<Product> list = executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1",this::toProduct);
        return varFromList(list);
    }

    public Optional<Product> getMinProduct() throws SQLException, IOException {
        List<Product> list = executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1",this::toProduct);
        return varFromList(list);
    }

    public Optional<Long> getSumPrice() throws SQLException, IOException {
        List<Long> list = executeQuery("SELECT SUM(price) FROM PRODUCT",this::toLong);
        return varFromList(list);
    }

    public Optional<Long> getCount() throws SQLException, IOException {
        List<Long> list = executeQuery("SELECT COUNT(*) FROM PRODUCT",this::toLong);
        return varFromList(list);
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

    private <T> Optional<T> varFromList(List<T> tList){
        if (tList.isEmpty()) return Optional.empty();
        return Optional.of(tList.get(0));
    }

    private Product toProduct(ResultSet set) throws SQLException {
        String name = set.getString("name");
        long price = set.getLong("price");
        return new Product(name, price);
    }
    private long toLong(ResultSet set) throws SQLException {
        return set.getLong("price");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:test.db");
    }
}
