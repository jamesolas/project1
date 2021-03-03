


function viewPending ()
{
	const id = document.getElementById ("employeeId").value;
	
	const url = "/Project1/api/employeepending?employeeId="+id;
	
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
				
				requestId.innerText = r.requestId
				employeeId.innerText = r.employeeId
				managerId.innerText = r.managerId
				status.innerText = r.status
				amount.innerText = r.amount
				date.innerText = r.date
				
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

// function viewPending ()
// {
// 	const id = document.getElementById ("employeeId").value;
	
// 	const url = "/Project1/api/employeepending?employeeId="+id;
	
// 	let xhr = new XMLHttpRequest();
// 	xhr.onreadystatechange = function (){
// 		if (this.readyState==4 && this.status==200){
// 			const reimbursements =JSON.parse(this.responseText);
			
// 			console.log(reimbursements);
			    
// 			    let div = document.getElementById("requestsHere");
			    
// 	for(let r of reimbursements){
// 				let tr=document.createElement('div')
// 				let requestId=document.createElement('p')
// 				let employeeId=document.createElement('p')
// 				let managerId=document.createElement('p')
// 				let status=document.createElement('p')
// 				let amount=document.createElement('p')
// 				let date=document.createElement('p')
				
// 				requestId.innerText = r.requestId
// 				employeeId.innerText = r.employeeId
// 				managerId.innerText = r.managerId
// 				status.innerText = r.status
// 				amount.innerText = r.amount
// 				date.innerText = r.date
				
// 				tr.append(requestId)
// 				tr.append(employeeId)
// 				tr.append(managerId)
// 				tr.append(status)
// 				tr.append(amount)
// 				tr.append(date)
		
// 				div.append(tr)
// 	}
		
			
// 		}
// 	}
// 	xhr.open ("GET", url);

// 	xhr.send();
// }

function viewResolved () {
 	const id = document.getElementById ("employeeId2").value;
 	
 	let div = document.getElementById("divResolved");
	
	const url = "/Project1/api/employeeresolved?employeeId="+id;
	//alert(url);
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			
			console.log(reimbursements);
			    //let div = document.querySelector('.container reimbursements')
			    let div = document.getElementById("divContent");
	for(let r of reimbursements){
				let tr=document.createElement('div')
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
				
				tr.append(requestId)
				tr.append(employeeId)
				tr.append(managerId)
				tr.append(status)
				tr.append(amount)
				tr.append(date)
				
				div.append(tr)
	}
		
			
		}
	}
	xhr.open ("GET", url);
	//xhr.responseType="json";
	xhr.send();
}

