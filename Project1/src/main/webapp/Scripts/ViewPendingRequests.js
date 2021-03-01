function viewPending ()
{
	const id = document.getElementById ("employeeId").value;
	
	const url = "/Project1/api/employeepending?employeeId="+id;
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			
			console.log(reimbursements);
			  
			    let div = document.getElementById("divContent");
			    
	for(let r of reimbursements){
				let newReimbursement=document.createElement('div')
				let requestId=document.createElement('p')
				let employeeId=document.createElement('p')
				let managerId=document.createElement('p')
				let status=document.createElement('p')
				let amount=document.createElement('p')
				let date=document.createElement('p')
				
				requestId.innerText = r.requestId
				employeeId.innerText = r.employeeId
				managerId.innerText = r.managerId
				status.innerText = r.status
				amount.innerText = r.amount
				date.innerText = r.date
				
				newReimbursement.append(requestId)
				newReimbursement.append(employeeId)
				newReimbursement.append(managerId)
				newReimbursement.append(status)
				newReimbursement.append(amount)
				newReimbursement.append(date)
		
				div.append(newReimbursement)
	}
		
			
		}
	}
	xhr.open ("GET", url);
	
	xhr.send();
}

