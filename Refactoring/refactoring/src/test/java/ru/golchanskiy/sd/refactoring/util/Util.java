package test.java.ru.golchanskiy.sd.refactoring.util;

import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static final List<Product> SAMPLE_DATA = new ArrayList<>();
    public static final ProductDAO productDAO = new ProductDAO();

    static {
        SAMPLE_DATA.add(new Product("min", 10));
        SAMPLE_DATA.add(new Product("middle", 25));
        SAMPLE_DATA.add(new Product("max", 50));
    }

    public static void addSampleData() throws SQLException {
        for (Product product : SAMPLE_DATA) {
            productDAO.executeAndInsert(product);
        }
    }
}
