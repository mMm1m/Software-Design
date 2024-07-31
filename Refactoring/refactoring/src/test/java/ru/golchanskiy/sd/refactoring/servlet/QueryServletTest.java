package test.java.ru.golchanskiy.sd.refactoring.servlet;

import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.servlet.QueryServlet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.java.ru.golchanskiy.sd.refactoring.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class QueryServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private QueryServlet servlet;

    private final ProductDAO productDAO = new ProductDAO();

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        servlet = new QueryServlet();
        productDAO.initTable();
        Util.addSampleData();
    }

    @AfterEach
    void tearDown() throws SQLException {
        productDAO.clearProducts();
    }

    @Test
    void testMin() throws IOException {
        StringWriter respWriter = new StringWriter();
        when(request.getParameter("command")).thenReturn("min");
        when(response.getWriter()).thenReturn(new PrintWriter(respWriter));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(respWriter.toString()).isEqualToIgnoringNewLines(
                "<html><body>" +
                        "<h1>Product with min price: </h1>" +
                        "min\t10</br>" +
                        "</body></html>");
    }

    @Test
    void testMax() throws IOException {
        StringWriter respWriter = new StringWriter();
        when(request.getParameter("command")).thenReturn("max");
        when(response.getWriter()).thenReturn(new PrintWriter(respWriter));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(respWriter.toString()).isEqualToIgnoringNewLines(
                "<html><body>" +
                        "<h1>Product with max price: </h1>" +
                        "max\t50</br>" +
                        "</body></html>");
    }

    @Test
    void testCount() throws IOException {
        StringWriter respWriter = new StringWriter();
        when(request.getParameter("command")).thenReturn("count");
        when(response.getWriter()).thenReturn(new PrintWriter(respWriter));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(respWriter.toString()).isEqualToIgnoringNewLines(
                "<html><body>" +
                        "Number of products: 3" +
                        "</body></html>");
    }

    @Test
    void testSum() throws IOException {
        StringWriter respWriter = new StringWriter();
        when(request.getParameter("command")).thenReturn("sum");
        when(response.getWriter()).thenReturn(new PrintWriter(respWriter));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(respWriter.toString()).isEqualToIgnoringNewLines(
                "<html><body>" +
                        "Summary price: 85" +
                        "</body></html>");
    }

    @Test
    void testUnknownCommand() throws IOException {
        StringWriter respWriter = new StringWriter();
        when(request.getParameter("command")).thenReturn("boo");
        when(response.getWriter()).thenReturn(new PrintWriter(respWriter));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(respWriter.toString()).isEqualToIgnoringNewLines("Unknown command: boo");
    }
}
