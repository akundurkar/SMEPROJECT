function methodCall(){
	var buttonValue = document.getElementById("subButton").value;
	if(buttonValue=="Register User"){
		create();
	}else if(buttonValue=="Update"){
		update();
	}
	return false;
}
function create(){
	
	var email = $("#email").val();
	
	var password = $("#password").val();
	
	var organisationName = $("#organisationName").val();
	
	var organisationType = $("#organisationType").val();
	
	var userType = $("#userType").val();
	
	var formData={"email":email,"password":password,"organisationName":organisationName,"organisationType":organisationType,"userType":userType};
	
	$.ajax({
		
		url : "/services/registration/create",
		
		type: "POST",
		
		data : JSON.stringify(formData),
		
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			//document.getElementById("productname").value="";
			//document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			//document.getElementById("productname").value="";
			alert("Error Status Create:"+textStatus);
		}
	});
	return false;
}
function update(){
	var name = $("#productname").val();
	var id = +$("#productid").val();
	var formData={"id":id,"name":name};
	$.ajax({
		url : "/example/registration/edit",
		type: "POST",
		data : JSON.stringify(formData),
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(data, textStatus, jqXHR)
		{
			document.getElementById("productname").value="";
			document.getElementById("subButton").value="Create";
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			document.getElementById("productname").value="";
			alert("Error Status Update:"+textStatus);
		}
	});
	return false;
}
function loadObjects(){
	$.ajax({
		url : "/services/registration/findall",
		type: "GET",
		data : {},
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			processResponseData(data);
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			//document.getElementById("productname").value="";
			alert("Error Status Registration Objects:"+textStatus);
		}
	});
	return false;
}
function deleteObject(productid){
	var productForm={id:productid};
	delurl="/services/registration/remove/"+productid;
	$.ajax({
		url : delurl,
		type: "POST",
		data : productForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			loadObjects();
		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Delete:"+textStatus);
		}
	});
}
function editObject(productid){
	editurl="/example/standalone/findById/"+productid;
	var productForm={id:productid};
	$.ajax({
		url : editurl,
		type: "GET",
		data : productForm,
		dataType: "json",
		success: function(data, textStatus, jqXHR)
		{
			viewObject(data);
			document.getElementById("subButton").value="Update";

		},
		error: function (jqXHR, textStatus, errorThrown)
		{
			alert("Error Status Find Object:"+textStatus);
		}
	});
}
function viewObject(data){
	document.getElementById("productname").value=data.name;
	document.getElementById("productid").value=data.id;
}
function generateTableData(itemvalue){
	
	console.log(itemvalue.organisation);
	
	var dataRow="<tr>" +
	
	"<td>" +itemvalue.id+"</td>"+
	
	"<td>" +itemvalue.email+"</td>"+
	
	"<td>" +itemvalue.password+"</td>"+
	
	"<td>" +itemvalue.organisationName+"</td>"+
	
	"<td>" +
	
	"<a href=# onclick=deleteObject("+itemvalue.id+")>Delete</a>"+
	
	"|<a href=# onclick=editObject("+itemvalue.id+")>Edit</a>"+
	
	"</td>"+
	
	"</tr>";
	
	return dataRow;
}
function processResponseData(responsedata){
	
	var dyanamicTableRow="<table border=1>"+
	
	"<tr>" +
	
	"<td>Id</td>"+
	
	"<td>Email</td>"+
	
	"<td>Password</td>"+
	
	"<td>Organisation</td>"+
	
	"<td>Actions</td>"+
	
	"</tr>";
	
	var dataRow="";
	
	$.each(responsedata, function(itemno, itemvalue){
		dataRow=dataRow+generateTableData(itemvalue);
	});
	
	dyanamicTableRow=dyanamicTableRow+dataRow+"</table>";
	
	document.getElementById("productFormResponse").innerHTML=dyanamicTableRow;
}