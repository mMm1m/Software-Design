package test.java.ru.golchanskiy.sd.refactoring.servlet;

import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.servlet.AddProductServlet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

public class AddProductServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private AddProductServlet servlet;

    private final ProductDAO productDAO = new ProductDAO();

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        servlet = new AddProductServlet(productDAO);
        productDAO.initTable();
    }

    @AfterEach
    void tearDown() throws SQLException {
        productDAO.clearProducts();
    }

    @Test
    void testResponseOk() throws IOException, IOException {
        when(request.getParameter("name")).thenReturn("hello");
        when(request.getParameter("price")).thenReturn("200");
        StringWriter out = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(out));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(out.toString()).isEqualToIgnoringNewLines("OK");
    }

    @Test
    void testEntityIsAdded() throws IOException, SQLException {
        when(request.getParameter("name")).thenReturn("hello");
        when(request.getParameter("price")).thenReturn("20");
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        servlet.doGet(request, response);

        assertThat(productDAO.getCount()).contains(1);
    }
}
