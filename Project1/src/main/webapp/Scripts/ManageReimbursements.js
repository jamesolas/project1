function viewRequests()
{
	const id = document.getElementById ("employeeId").value;
	
	const url = "/Project1/api/viewoneemployeerequests?employeeId="+id;
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			
			console.log(reimbursements);
			   
			    let tbody = document.getElementById("requestData");
			    
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


var GC_REMIMBURSEMENTS=null;
function showImage (idx)
{
	const fn = GC_REMIMBURSEMENTS[idx].filename;
	//alert(fn);
	window.open ("../uploadFiles/"+fn);
}


function viewPending()
{
	// const id = document.getElementById ("managerId").value;
	//alert("viewPending");
	// const url = "/Project1/api/viewoneemployeerequests?employeeId="+id;
	
	const url = "/Project1/api/managerviewpending";
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (this.readyState==4 && this.status==200){
			const reimbursements =JSON.parse(this.responseText);
			GC_REMIMBURSEMENTS=reimbursements;
			console.log(reimbursements);
			   
			    let tbody = document.getElementById("requestData");
	let idx=0;
	for(let r of reimbursements){
				let tr=document.createElement('tr')
				let requestId=document.createElement('td')
				let employeeId=document.createElement('td')
				let managerId=document.createElement('td')
				let status=document.createElement('td')
				let amount=document.createElement('td')
				let date=document.createElement('td')
				let fileP=document.createElement('td')

				let parsedDate = new Date(r.date)
				r.date.textContext = parsedDate.getFullYear() + "-" + parsedDate.getMonth() + "-" + parsedDate.getDay();
				
				requestId.innerText = r.requestId
				employeeId.innerText = r.employeeId
				managerId.innerText = r.managerId
				status.innerText = r.status
				amount.innerText = r.amount
				date.innerText = parsedDate
				fileP.innerHTML = "<a href='javascript:showImage("+idx+")'>"+ r.requestId+"</a>";
				
				tr.append(requestId)
				tr.append(employeeId)
				tr.append(managerId)
				tr.append(status)
				tr.append(amount)
				tr.append(date)
				tr.append(fileP)

				tbody.append(tr)
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
			   
			    let tbody = document.getElementById("requestData");
			    
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