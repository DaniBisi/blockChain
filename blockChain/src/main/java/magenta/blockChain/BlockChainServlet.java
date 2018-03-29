package magenta.blockChain;

import java.io.IOException;
import java.util.LinkedList;

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
 * Servlet implementation class BlockChainServlet
 */
public class BlockChainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(BlockChainServlet.class);


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlockChainServlet() {
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
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String query = request.getParameter("query");
		String args = request.getParameter("args");
		
		if (StringUtils.isEmpty(name)) {
			name = "admin";
			password = "adminpw";
		}
		if(StringUtils.isEmpty(query)) {
			query = "queryAllCars";
		}
		CryptoSuite cryptoSuite;
		try {
			cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
			HFCAClient caClient = HFCAClient.createNewInstance("http://localhost:7054", null);
			caClient.setCryptoSuite(cryptoSuite);
			HFClient client = HFClient.createNewInstance();
			client.setCryptoSuite(cryptoSuite);
			SessionWrapper u1 = new SessionWrapper(caClient, client, new AppUser(name, "org1", "Org1MSP"),
					ChaincodeID.newBuilder().setName("fabcar").build());
			u1.login(password);
			commandFactory f1 = new commandFactory(query,args);
			String queryAnsware = u1.queryDB(f1.getFormattedQuery());
			logger.info(queryAnsware);
			logger.info(response);
			LinkedList<Car> record;
			record = f1.getCommand().execute(queryAnsware);
			
			request.setAttribute("txId", u1.getTransactionId());
			request.setAttribute("user", name);
			request.setAttribute("records", record);
			request.setAttribute("queryAnsware", queryAnsware);
			request.setAttribute("module", "response.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}