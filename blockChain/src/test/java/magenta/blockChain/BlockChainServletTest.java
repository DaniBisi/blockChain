package magenta.blockChain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockChainServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private RequestDispatcher requestDispatcher;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws ServletException, IOException {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);
		when(request.getContextPath()).thenReturn("/");
		new ProvaServlet().doPost(request, response);
//		assertEquals("Served at more than a simple script: /", stringWriter.toString());
		assertEquals("qualcosa", request.getAttribute("records"));
	}

	@Test
	public void doPostWithName() throws Exception {
		when(request.getParameter("name")).thenReturn("Dolly");
		when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);
		new ProvaServlet().doPost(request, response);
		verify(request).setAttribute("user", "Dolly");
		verify(requestDispatcher).forward(request, response);
	}

}
