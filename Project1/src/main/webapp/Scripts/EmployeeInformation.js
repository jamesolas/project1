function viewInfo ()
{
	// const id = document.getElementById ("employeeId1").value; 
	
	//const url = "/Project1/api/viewinfo?employeeId1="+id;
	const url = "/Project1/api/viewinfo?employeeId1=";

	//alert(url);
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const info =JSON.parse(this.responseText);
						   
			    let div = document.getElementById("divContent");

				
				let employeeInfo=document.createElement('div')
				let employeeId=document.createElement('p')
				let firstName=document.createElement('p')
				let lastName=document.createElement('p')
				let phone=document.createElement('p')
				let managerId=document.createElement('p')
				let type=document.createElement('p')
				
				employeeId.innerText = "Employee ID: "+info.employeeId
				firstName.innerText = "First Name: "+info.firstName
				lastName.innerText = "Last Name: "+info.lastName
				phone.innerText = "Phone: "+info.phone
				managerId.innerText = "Manager ID: "+info.manager.managerId
				type.innerText = "Type: "+info.type
				
				employeeInfo.append(employeeId)
				employeeInfo.append(firstName)
				employeeInfo.append(lastName)
				employeeInfo.append(phone)
				employeeInfo.append(managerId)
				employeeInfo.append(type)
		
				div.append(employeeInfo)
	
		
			
		}
	}
	xhr.open ("GET", url);
	xhr.send();
	}