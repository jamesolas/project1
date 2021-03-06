


function viewPending ()
{
	//const id = document.getElementById ("employeeId").value;
	
	//const url = "/Project1/api/employeepending?employeeId="+id;

	const url = "/Project1/api/employeepending?employeeId=";
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			
			console.log(reimbursements);
			    
			    let tbody = document.getElementById("requestsHere");			
			    
	for(let r of reimbursements){
				let tr=document.createElement('tr')
				let requestId=document.createElement('td')
				let employeeId=document.createElement('td')
				let managerId=document.createElement('td')
				let status=document.createElement('td')
				let amount=document.createElement('td')
				let date=document.createElement('td')

				let parsedDate = new Date(r.date)
				r.date.textContext = parsedDate.getFullYear() + "-" + parsedDate.getMonth() + "-" + parsedDate.getDay();
				
				requestId.innerText = r.requestId
				employeeId.innerText = r.employeeId
				managerId.innerText = r.managerId
				status.innerText = r.status
				amount.innerText = r.amount
				date.innerText = parsedDate
				
				tr.append(requestId)
				tr.append(employeeId)
				tr.append(managerId)
				tr.append(status)
				tr.append(amount)
				tr.append(date)
		
				tbody.append(tr)
			}	
		}
	}
	xhr.open ("GET", url);

	xhr.send();
}



function viewResolved () {
 	//const id = document.getElementById ("employeeId2").value;
 	
 	//let div = document.getElementById("divResolved");
	
	//const url = "/Project1/api/employeeresolved?employeeId="+id;
	const url = "/Project1/api/employeeresolved?employeeId";

	//alert(url);
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			
			console.log(reimbursements);
			    //let div = document.querySelector('.container reimbursements')
			    let tbody = document.getElementById("requestsHere");

	for(let r of reimbursements){
				let tr=document.createElement('tr')
				let requestId=document.createElement('td')
				let employeeId=document.createElement('td')
				let managerId=document.createElement('td')
				let status=document.createElement('td')
				let amount=document.createElement('td')
				let date=document.createElement('td')

				let parsedDate = new Date(r.date)
				r.date.textContext = parsedDate.getFullYear() + "-" + parsedDate.getMonth() + "-" + parsedDate.getDay();
				
				requestId.innerText = r.requestId
				employeeId.innerText = r.employeeId
				managerId.innerText = r.managerId
				status.innerText = r.status
				amount.innerText = r.amount
				date.innerText = parsedDate
				
				tr.append(requestId)
				tr.append(employeeId)
				tr.append(managerId)
				tr.append(status)
				tr.append(amount)
				tr.append(date)
				
				tbody.append(tr)
	}
		
			
		}
	}
	xhr.open ("GET", url);
	//xhr.responseType="json";
	xhr.send();
}

