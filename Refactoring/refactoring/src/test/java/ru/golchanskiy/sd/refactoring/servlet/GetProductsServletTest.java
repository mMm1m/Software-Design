package test.java.ru.golchanskiy.sd.refactoring.servlet;

import main.java.ru.golchanskiy.sd.refactoring.dao.ProductDAO;
import main.java.ru.golchanskiy.sd.refactoring.html.formatting.ProductHtmlFormatting;
import main.java.ru.golchanskiy.sd.refactoring.servlet.GetProductsServlet;
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

public class GetProductsServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private GetProductsServlet servlet;

    private final ProductDAO productDAO = new ProductDAO();

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        servlet = new GetProductsServlet(productDAO, new ProductHtmlFormatting());
        productDAO.initTable();
    }

    @AfterEach
    void tearDown() throws SQLException {
        productDAO.clearProducts();
    }

    @Test
    void testEmptyResponse() throws IOException {
        StringWriter respWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(respWriter));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(respWriter.toString().trim()).isEqualTo(
                "<html><body>\n" +
                        "</body></html>");
    }

    @Test
    void testAddedDataIsReturned() throws IOException, SQLException {
        Util.addSampleData();
        StringWriter respWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(respWriter));

        servlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(respWriter.toString()).isEqualToIgnoringNewLines(
                "<html><body>" +
                        "min\t10</br>" +
                        "middle\t25</br>" +
                        "max\t50</br>" +
                        "</body></html>");
    }
}
