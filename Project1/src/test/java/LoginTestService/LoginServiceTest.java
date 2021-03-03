package LoginTestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.app.dao.impl.LoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.service.impl.LoginServiceImpl;

class LoginServiceTest {
	
	@InjectMocks
	private static LoginServiceImpl loginServiceImpl;
	
	@Mock
	private static LoginDAOImpl loginDAOImpl;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		loginServiceImpl = new LoginServiceImpl();
	}
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testLogin() {
		String email="jim@gmail.com";
		String password="pass";
		
		try {
			Mockito.when(loginDAOImpl.login(email, password)).thenReturn("true");
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		try {
			assertEquals("true", loginServiceImpl.login(email,password));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
