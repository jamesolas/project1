package managerServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

import com.app.dao.impl.ManagerDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.model.Manager;
import com.app.model.Reimbursement;
import com.app.service.impl.ManagerServiceImpl;

class ManagerServiceTest {
	
	@InjectMocks
	private static ManagerServiceImpl managerServiceImpl;
	
	@Mock
	private static ManagerDAOImpl managerDAOImpl;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		managerServiceImpl = new ManagerServiceImpl();
	}
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testViewManagerPendingRequests() {
		List<Reimbursement> reimbursementList = new ArrayList<>();
		int reimbursementId = 1;
		int employeeId = 1;
		int managerId = 1;
		String status = "pending";
		float amount = 55;
		Date date = new java.sql.Date(2020-01-01);
		
		reimbursementList.add(new Reimbursement(reimbursementId, employeeId, managerId, status, amount, date));
		
		try {
			Mockito.when(managerDAOImpl.viewManagerPendingRequests(managerId)).thenReturn(reimbursementList);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(reimbursementList, managerDAOImpl.viewManagerPendingRequests(managerId));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testViewOneEmployeeRequests() {
		List<Reimbursement> reimbursementList = new ArrayList<>();
		int reimbursementId = 1;
		int employeeId = 3;
		int managerId = 2;
		String status = "accepted";
		float amount = 45;
		Date date = new java.sql.Date(2020-01-01);
		
		reimbursementList.add(new Reimbursement(reimbursementId, employeeId, managerId, status, amount, date));
		
		try {
			Mockito.when(managerDAOImpl.viewManagerPendingRequests(employeeId)).thenReturn(reimbursementList);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(reimbursementList, managerDAOImpl.viewManagerPendingRequests(employeeId));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testApproveRequest() {
		int requestId = 1;
		
		try {
			Mockito.when(managerDAOImpl.approveRequest(requestId)).thenReturn(1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(1, managerDAOImpl.approveRequest(requestId));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDenyRequest() {
int requestId = 1;
		
		try {
			Mockito.when(managerDAOImpl.denyRequest(requestId)).thenReturn(1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(1, managerDAOImpl.denyRequest(requestId));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
//	void testViewReceipts() {
//		fail("Not yet implemented");
//	}

	@Test
	void testViewResolvedRequests() {
		List<Reimbursement> reimbursementList = new ArrayList<>();
		int reimbursementId = 1;
		int employeeId = 3;
		int managerId = 2;
		String status = "denied";
		float amount = 45;
		Date date = new java.sql.Date(2020-01-01);
		
		reimbursementList.add(new Reimbursement(reimbursementId, employeeId, managerId, status, amount, date));
		
		try {
			Mockito.when(managerDAOImpl.viewResolvedRequests()).thenReturn(reimbursementList);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(reimbursementList, managerDAOImpl.viewResolvedRequests());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testViewEmployees() {
		List<Employee> employeeList = new ArrayList<>();
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
		employeeList.add(employee);
		
		try {
			Mockito.when(managerDAOImpl.viewEmployees()).thenReturn(employeeList);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(employeeList, managerDAOImpl.viewEmployees());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
//	void testViewAllReceipts() {
//		fail("Not yet implemented");
//	}

}
