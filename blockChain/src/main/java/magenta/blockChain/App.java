package magenta.blockChain;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

/**
 * Hello world!
 *
 */
public class App {
	private static final Logger logger = LogManager.getLogger(App.class);
	public static void main(String[] args) throws Exception {
	CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
		HFCAClient caClient = HFCAClient.createNewInstance("http://localhost:7054", null);
		caClient.setCryptoSuite(cryptoSuite);
		HFClient client = HFClient.createNewInstance();
		client.setCryptoSuite(cryptoSuite);
		
		
		SessionWrapper u1 = new SessionWrapper(caClient, client, new AppUser("admin", "org1", "Org1MSP"), ChaincodeID.newBuilder().setName("fabcar").build());
		u1.login("adminpw");
		//logger.info(u1.queryDB(new String[] {"queryAllCars"}));
		String pswd = u1.userRegister("user7", "org1");
		logger.info(pswd);
		
//		
//		UserWrapper u2 = new UserWrapper(caClient, client,  new AppUser("user7", "org1", "Org1MSP"), ChaincodeID.newBuilder().setName("fabcar").build());
//		u2.login("blTWsEnjjJqI");
//		logger.info(u2.queryDB(new String[] {"queryAllCars"}));
//		String pswd = u2.userRegister("user9", "org1");
		
		}
}
