package magenta.blockChain;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

/**
 * Servlet implementation class ProvaServlet
 */
public class ProvaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ProvaServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProvaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at more than a simple script: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("name");
		if (StringUtils.isEmpty(name)) {
			name = "admin";
			password = "adminpw";
		}
		CryptoSuite cryptoSuite;
		try {
			cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
			HFCAClient caClient = HFCAClient.createNewInstance("http://localhost:7054", null);
			caClient.setCryptoSuite(cryptoSuite);
			HFClient client = HFClient.createNewInstance();
			client.setCryptoSuite(cryptoSuite);
			SessionWrapper u1 = new SessionWrapper(caClient, client, new AppUser(name, "org1", "Org1MSP"), ChaincodeID.newBuilder().setName("fabcar").build());
			u1.login(password);
			String queryAnsware = u1.queryDB(new String[] {"queryAllCars"});
			logger.info(queryAnsware);
			logger.info(response);
			request.getRequestDispatcher("response.jsp").forward(request, response);
			request.setAttribute("user", name);
			request.setAttribute("queryAnsware", queryAnsware);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
