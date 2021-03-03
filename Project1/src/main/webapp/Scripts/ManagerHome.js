var GC_REMIMBURSEMENTS=null;
function showImage (idx)
{
	const fn = GC_REMIMBURSEMENTS[idx].filename;
	//alert(fn);
	window.open ("../uploadFiles/"+fn);
}

function viewReceipts()
{
	//const id = document.getElementById ("employeeId").value;
	
	const url = "/Project1/api/viewimages";
	//alert(url);
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			GC_REIMBURSEMENTS = reimbursements;
			console.log(reimbursements);
			   
			    let div = document.getElementById("divContent1");
	let idx=0;		    
	for(let r of reimbursements){
				let newReimbursement=document.createElement('div');
				//var requestId;
				let fileP=document.createElement('p');			
				fileP.innerHTML = "<a href='javascript:showImage("+idx+")'>"+r.requestId+"</a>";
				newReimbursement.append(fileP)
						
				div.append(newReimbursement)
				++idx;
	       }		
     	}
	}
	xhr.open ("GET", url);
	//xhr.responseType="json";
	xhr.send();
}



	
	
	
	function viewAllEmployees(){
	//const id = document.getElementById ("employeeId").value;
	
	const url = "/Project1/api/viewemployees";
	//alert(url);
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const employees =JSON.parse(this.responseText);
			
			console.log(employees);
			   
			    let tbody = document.getElementById("employeesHere");
			    
	for(let r of employees){
			    let tr=document.createElement('tr')
				let employeeId=document.createElement('td')
				let firstName=document.createElement('td')
				let lastName=document.createElement('td')
				let phone=document.createElement('td')
				let managerId=document.createElement('td')
				let mFirstName=document.createElement('td')
				let mLastName=document.createElement('td')
				let type=document.createElement('td')
				
				employeeId.innerText = r.employeeId
				firstName.innerText = r.firstName
				lastName.innerText = r.lastName
				phone.innerText = r.phone
				managerId.innerText = r.manager.managerId
				mFirstName.innerText = r.manager.firstName
				mLastName.innerText = r.manager.lastName
				type.innerText = r.type
				
				tr.append(employeeId)
				tr.append(firstName)
				tr.append(lastName)
				tr.append(phone)
				tr.append(managerId)
				tr.append(mFirstName)
				tr.append(mLastName)
				tr.append(type) 
		
				tbody.append(tr)
	       }		
     	}
	}
	xhr.open ("GET", url);
	//xhr.responseType="json";
	xhr.send();
	}