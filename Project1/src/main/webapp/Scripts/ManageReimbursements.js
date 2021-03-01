function viewRequests()
{
	const id = document.getElementById ("employeeId").value;
	
	const url = "/Project1/api/viewoneemployeerequests?employeeId="+id;
	
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








function viewRequests()
{
	const id = document.getElementById ("employeeId").value;
	
	const url = "/Project1/api/viewoneemployeerequests?employeeId="+id;
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			
			console.log(reimbursements);
			   
			    let div = document.getElementById("divContent2");
			    
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








var GC_REMIMBURSEMENTS=null;
function showImage (idx)
{
	const fn = GC_REMIMBURSEMENTS[idx].filename;
	//alert(fn);
	window.open ("../uploadFiles/"+fn);
}


function viewPending()
{
	const id = document.getElementById ("managerId").value;
	//alert("viewPending");
	const url = "/Project1/api/viewoneemployeerequests?employeeId="+id;
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			GC_REMIMBURSEMENTS=reimbursements;
			console.log(reimbursements);
			   
			    let div = document.getElementById("divContent2");
	let idx=0;
	for(let r of reimbursements){
				let newReimbursement=document.createElement('div')
				let requestId=document.createElement('p')
				let employeeId=document.createElement('p')
				let managerId=document.createElement('p')
				let status=document.createElement('p')
				let amount=document.createElement('p')
				let date=document.createElement('p')
				let fileP=document.createElement('p')
				requestId.innerText = r.requestId
				employeeId.innerText = r.employeeId
				managerId.innerText = r.managerId
				status.innerText = r.status
				amount.innerText = r.amount
				date.innerText = r.date
				fileP.innerHTML = "<a href='javascript:showImage("+idx+")'>"+ r.requestId+"</a>";
				newReimbursement.append(requestId)
				newReimbursement.append(employeeId)
				newReimbursement.append(managerId)
				newReimbursement.append(status)
				newReimbursement.append(amount)
				newReimbursement.append(date)
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








function viewAllResolved()
{
	//const id = document.getElementById ("employeeId").value;
	
	const url = "/Project1/api/managerviewresolved";
	//alert(url);
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			
			console.log(reimbursements);
			   
			    let div = document.getElementById("divContent3");
			    
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