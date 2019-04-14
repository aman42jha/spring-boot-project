/**
 * 
 */

var currentPage = 0;
var nextPageSize = 5;

$('documnet').ready(function(){
	$('#nextbtn').hide();
	$('#logoutbtn').hide();
	let token = getCookie('token');
	if(token){
		verifyToken(token);
	}else{
		$('#usertable').hide();
	}
});

function setCookie(key, value, exdays) {
	let expires="";
	if(exdays){
		let d = new Date();
		d.setTime(d.getTime() + (exdays*24*60*60*1000));
		expires = "expires="+ d.toUTCString();
	}
	document.cookie = key + "=" + value + ";" + expires + ";path=/";
}

function getCookie(key) {
	  var name = key + "=";
	  var decodedCookie = decodeURIComponent(document.cookie);
	  var ca = decodedCookie.split(';');
	  for(var i = 0; i <ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
}

function eraseCookie(key){
	document.cookie= key+'=;Max-Age=-99999999;';
}

function register(){
	let userName = $( "input[name=registername]" ).val();
	let password = $( "input[name=registerpassword]" ).val();
	let gender = $("#gender option:selected").val();
	let requestData = {
			userName : userName,
			password : password,
			gender : gender
	};
	let requestUrl = 'http://localhost:8080/server/user';

	$.ajax({
		type : 'POST',
		url : requestUrl,
		data : JSON.stringify(requestData),
		dataType : "text",
		contentType : 'application/json; charset=utf-8',
		timeout : 10000,
		success : function(response) {
			let responseData = jQuery.parseJSON(response);
			$( "input[name=registername]" ).val('');
			$( "input[name=registerpassword]" ).val('');
			if(responseData.ack.status){
				$('#registerMsg').text("Registred Successfully.");
			}else{
				$('#registerMsg').text("Registration Failed.");
			}
		},
		error : function(xhr, status, error) {
			$('#registerMsg').text("Registration Failed. Try again");
		},
		async : true
	});
}

function submit(){
	let userName = $( "input[name=username]" ).val();
	let password = $( "input[name=password]" ).val();
	let requestData = {
			userName : userName,
			password : password
	};
	let requestUrl = 'http://localhost:8080/server/login';

	$.ajax({
		type : 'POST',
		url : requestUrl,
		data : JSON.stringify(requestData),
		dataType : "text",
		contentType : 'application/json; charset=utf-8',
		timeout : 10000,
		success : function(response) {
			let responseData = jQuery.parseJSON(response);
			$( "input[name=username]" ).val('');
			$( "input[name=password]" ).val('');
			var userName = JSON.stringify(responseData.data['user']['userName']);
			if(responseData.ack.status){
				let data = responseData.data;
				let token = (data != undefined && data != null)? data.token: '';
				setCookie('token', token, 7);
				$('#tableForm').empty();
				$('#logoutbtn').show();
				createTableHead();
				getUsers();
			}else{
				$('#loginMsg').text("Wrong login credentials!!");
			}
		},
		error : function(xhr, status, error) {
			$('#loginMsg').text("Wrong login credentials!!");
		},
		async : true
	});
}

function verifyToken(token){
	let requestUrl = 'http://localhost:8080/server/verify';
	$.ajax({
		type : 'GET',
		url : requestUrl,
		headers: { 'X-Auth-Token': token},
		dataType : "text",
		contentType : 'application/json; charset=utf-8',
		timeout : 10000,
		success : function(response) {
			let responseData = jQuery.parseJSON(response);
			if(responseData.ack.status){
				alert("verify Successfully");
				$('#tableForm').empty();
				$('#logoutbtn').show();
				createTableHead();
				getUsers();
			}
		},
		error : function(xhr, status, error) {
			alert("server error");
		},
		async : true
	});
}

function getUsers(){
	let token = getCookie('token');
	if(token == null || token == undefined){
		alert("You are not logged in.");
		return;
	}
	let requestUrl = 'http://localhost:8080/server/users?page='+currentPage+'&size='+nextPageSize+'';
	
	$.ajax({
		type : 'GET',
		url : requestUrl,
		headers: { 'X-Auth-Token': token},
		dataType : "text",
		contentType : 'application/json; charset=utf-8',
		timeout : 10000,
		success : function(response) {
			let responseData = jQuery.parseJSON(response);
			if(responseData.ack.status){
				let data = responseData.data;
				currentPage = data.currentPage;
				let totalPages = data.totalPages;
				if(currentPage < (totalPages-1)){
					currentPage += 1;
					$('#nextbtn').show();
				}else{
					$('#nextbtn').hide();
				}
				let content = data.content;
				$('#userregister').hide();
				$('#userlogin').hide();
				$('#usertable').show();
				showTable(content);
			}
		},
		error : function(xhr, status, error) {
			alert("server error");
		},
		async : true
	});
}

var tableHead;
function createTableHead() {
	tableHeadArray = [ 'ID', 'User Name', 'Password', 'Gender'];
	let table = document.createElement('table');
	table.setAttribute('id', 'table');
	let header = table.createTHead();
	let tableRow = header.insertRow(0);
	for (let head = 0; head < tableHeadArray.length; head++) {
		let tableHead = document.createElement('th');
		tableHead.innerHTML = tableHeadArray[head];
		$(tableHead).css({
			"text-align" : "center"
		});
		tableRow.appendChild(tableHead);
	}
	let div = document.getElementById('tableForm');
	div.appendChild(table);
}

function showTable(content) {
	let contentLength = content.length;
	let table = document.getElementById('table');
	let tbody = table.createTBody();
	tbody.setAttribute('id', 'tableHead');
	for (let row = 0; row < contentLength; row++) {
		let tableRow = tbody.insertRow(row); // TABLE ROW.
		let id = content[row].id;
		let userName = content[row].userName;
		let password = content[row].password;
		let gender = content[row].gender;
		let contentData = [ id, userName, password, gender];
		for (let cell = 0; cell < tableHeadArray.length; cell++) {
			let tableData = document.createElement('td'); // TABLE DEFINITION.
			tableData = tableRow.insertCell(cell);
			if (cell == 3) {
				let selectTag = $('<select id="genderDiv'
						+ row
						+ '" style="width: 90px;"> <option value="MALE">M</option><option value="FEMALE">F</option>');
				selectTag.appendTo(tableData);
				$('#genderDiv' + row).val(contentData[cell]);
			} else {
				// CREATE AND ADD TEXTBOX IN EACH CELL.
				let inputText = document.createElement('input');
				inputText.setAttribute('type', 'text');
				inputText.setAttribute('placeholder', 'Enter '
						+ tableHeadArray[cell]);
				inputText.setAttribute('class', tableHeadArray[cell].split(' ')
						.slice(0, 1));
				inputText.setAttribute('value', contentData[cell]);
				inputText.setAttribute('title', tableHeadArray[cell]);
				tableData.appendChild(inputText);
			}
		}
	}
}

function logout() {
	eraseCookie('token')
	currentPage = 0;
	nextPageSize = 5;
	$('#userregister').show();
	$('#userlogin').show();
	$('#usertable').hide();
}
