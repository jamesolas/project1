

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


