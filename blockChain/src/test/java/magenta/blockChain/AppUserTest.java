package magenta.blockChain;

import static org.junit.Assert.*;

import org.hyperledger.fabric.sdk.Enrollment;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.LinkedHashSet;

public class AppUserTest {

	private AppUser AppUser;
	@Before
	public void setup() {
		this.AppUser = new AppUser("daniele", "Org1", "Org1MSP");
	}
	@Test
	public void TestgetAffiliation() {
		assertEquals("Org1MSP", AppUser.getAffiliation());
	}
	@Test
	public void TestgetOrganization() {
		assertEquals("Org1", AppUser.getOrganization());
	}

	@Test
	public void TestgetEnrollment() {
		Enrollment e1 = mock(Enrollment.class);
		AppUser.setEnrollment(e1);
		assertEquals(e1, AppUser.getEnrollment());
	}
	@Test
	public void TestgetEmptyRole() {
		assertEquals(0, AppUser.getRoles().size());
	}
	@Test
	public void TestaddRole() {
		LinkedHashSet<String> role = new LinkedHashSet<String>();
		String r1 = "Manager";
		role.add(r1);
		AppUser.addRoles(r1);
		assertEquals(role, AppUser.getRoles());
	}

}
