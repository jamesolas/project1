
function viewPending ()
{
	const id = document.getElementById ("employeeId").value;
	
	const url = "/Project1/api/employeepending?employeeId="+id;
	//alert(url);
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			
			console.log(reimbursements);
			    //let div = document.querySelector('.container reimbursements')
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
	//xhr.responseType="json";
	xhr.send();
}

/*
function viewPending1(){
	let xhr = XMLHttpRequest(); //readyState 0
	
	xhttp.onreadystatechange = function(){
		if(xhr.readyState === 4 & xhr.status === 200){
			let reimbursements = JSON.parse(xhr.response)
			
			for(let r of reimbursements){
				let newReimbursement.createElement('div')
				let requestId.createElement('p')
				let employeeId.createElement('p')
				let managerId.createElement('p')
				let status.createElement('p')
				let amount.createElement('p')
				let date.createElement('p')
				
				requestId.innerText = r.id
				employeeId.innerText = r.name
				managerId.innerText = r.mname
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

xhr.open('GET', "http://localhost:8080/Project1/api/employeepending")
xhr.send() //readyState 2
*/
