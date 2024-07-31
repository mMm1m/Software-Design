package main.java.ru.golchanskiy.sd.refactoring.sql;

import java.sql.SQLException;

public interface SQLFunction<T, R> {
    R apply(T resultSet) throws SQLException;
}
