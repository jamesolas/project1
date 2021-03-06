package employeeServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.app.dao.impl.EmployeeDAOImpl;
import com.app.dao.impl.LoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.model.Manager;
import com.app.model.Reimbursement;
import com.app.service.impl.EmployeeServiceImpl;
import com.app.service.impl.LoginServiceImpl;

class EmployeeServiceTest extends EmployeeServiceImpl {
	
	@InjectMocks
	private static EmployeeServiceImpl employeeServiceImpl;
	
	@Mock
	private static EmployeeDAOImpl employeeDAOImpl;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		employeeServiceImpl = new EmployeeServiceImpl();
	}
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.openMocks(this);
	}


	@Test
	void testSubmitRequest() {
		int employeeId = 1;
		int managerId = 1;
		String status = "pending";
		double amount = 25;
		Date date = new java.sql.Date(2020-01-01);
		
		Reimbursement reimbursement = new Reimbursement(1, employeeId, managerId, status, amount, date);
		
		try {
			Mockito.when(employeeDAOImpl.submitRequest(reimbursement)).thenReturn(1);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(1, employeeDAOImpl.submitRequest(reimbursement));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testViewPendingRequests() {
		List<Reimbursement> reimbursement = new ArrayList<>();
		int requestId = 1;
		int employeeId = 1;
		int managerId = 1;
		String status = "pending";
		double amount = 55;
		Date date = new java.sql.Date(2020-01-01);
		reimbursement.add(new Reimbursement(requestId, employeeId, managerId, status, amount, date));
		
	 
		try {
			Mockito.when(employeeDAOImpl.viewPendingRequests(1)).thenReturn(reimbursement);
		} catch (BusinessException e) {
			e.printStackTrace();
			
		}
		try {
			assertEquals(reimbursement, employeeDAOImpl.viewPendingRequests(1));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testViewResolvedRequests() {
		List<Reimbursement> reimbursement = new ArrayList<>();
		int requestId = 1;
		int employeeId = 1;
		int managerId = 1;
		String status = "accepted";
		double amount = 55;
		Date date = new java.sql.Date(2020-01-01);
		reimbursement.add(new Reimbursement(requestId, employeeId, managerId, status, amount, date));
		
	 
		try {
			Mockito.when(employeeDAOImpl.viewPendingRequests(1)).thenReturn(reimbursement);
		} catch (BusinessException e) {
			e.printStackTrace();
			
		}
		try {
			assertEquals(reimbursement, employeeDAOImpl.viewPendingRequests(1));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testViewInfo() {
		int managerId = 1;
		String managerFirstName = "Derek";
		String managerLastName = "Long";
		
		int employeeId = 1;
		String firstName = "Jim";
		String lastName = "Bob";
		String phone = "111-222-3333";
		Manager manager = new Manager(managerId, managerFirstName, managerLastName);
		String type = "employee";
		Employee employee = new Employee(employeeId, firstName, lastName, phone, manager, type);
		
		try {
			Mockito.when(employeeDAOImpl.viewInfo(employeeId)).thenReturn(employee);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(employee, employeeDAOImpl.viewInfo(employeeId));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testChangePhone() {
		int employeeId = 1;
		String phone = "111-222-3333";
				
		try {
			Mockito.when(employeeDAOImpl.changePhone(employeeId, phone)).thenReturn(1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(1, employeeDAOImpl.changePhone(employeeId, phone));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
