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
			const reimbursements =JSON.parse(this.responseText);
			
			console.log(reimbursements);
			   
			    let div = document.getElementById("divContent2");
			    
	for(let r of reimbursements){
			    let newReimbursement=document.createElement('div')
				let employeeId=document.createElement('p')
				let firstName=document.createElement('p')
				let lastName=document.createElement('p')
				let phone=document.createElement('p')
				//let manager=document.createElement('p')
				let managerId=document.createElement('p')
				let mFirstName=document.createElement('p')
				let mLastName=document.createElement('p')
				let type=document.createElement('p')
				
				employeeId.innerText = r.employeeId
				firstName.innerText = r.firstName
				lastName.innerText = r.lastName
				phone.innerText = r.phone
				//manager.innerText = r.manager
				managerId.innerText = r.manager.managerId
				mFirstName.innerText = r.manager.firstName
				mLastName.innerText = r.manager.lasttName
				type.innerText = r.type
				
				newReimbursement.append(employeeId)
				newReimbursement.append(firstName)
				newReimbursement.append(lastName)
				newReimbursement.append(phone)
				//newReimbursement.append(manager)
				newReimbursement.append(managerId)
				newReimbursement.append(mFirstName)
				newReimbursement.append(mLastName)
				newReimbursement.append(type) 
		
				div.append(newReimbursement)
	       }		
     	}
	}
	xhr.open ("GET", url);
	//xhr.responseType="json";
	xhr.send();
	}