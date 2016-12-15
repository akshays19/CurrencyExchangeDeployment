<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

</head>

<script>
var errMessage = "";
function validateDOB()
{
    var dob = document.forms["userForm"]["dob"].value;
    var pattern = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
    var reg = /^[0-9.\/-]+$/;
    if (dob == null || dob == "" || !pattern.test(dob.val()) || !reg.test(dob.val())) {
        errMessage += "Invalid date of birth\n";
        return false;
    }
    else {
        return true
    }
}

function validateMail(){
	
	var email = document.forms["userForm"]["email"].value;
	var re = /^[a-zA-Z0-9.!#$%&*+=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	if(email==null || email == "" || !re.test(email.val())){
		 errMessage += "Invalid Mail\n";
	     return false;
	 }
	 else {
	     return true;
	 }
}

function validateForm(){

	errMessage="";
	var name = document.forms["userForm"]["name"].value;
	if(name==null || name == ""){
		 errMessage += "Name can not be null\n";
		 alert(errMessage);
		 return false;
	}
	var password = document.forms["userForm"]["password"].value;
	if(password==null || password == "" || password.length<6){
		 errMessage += "Password must be six characters\n";
		 alert(errMessage);
		 return false;
	}
	validateMail();
	validateDOB();
	
    if (errMessage == "") {
    } else {
        alert(errMessage);
        return false;
    }

}
</script>

<body>

<div class="container">

    <form:form method="POST" id="userForm" modelAttribute="userForm" class="form-signin" onSubmit="return validateForm(this)">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input id="name" name="Name" type="text" path="name" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input id="email" name="Email" type="email" path="email" class="form-control" placeholder="Email"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>
        
        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input id="password" name="Password" type="password" path="password" class="form-control" placeholder="Password(min 6 characters)"></form:input>
            </div>
        </spring:bind>
        
        <spring:bind path="dob">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input name="DOB" type="dob" path="dob" class="form-control" placeholder="DOB(DD/MM/YYYY)"></form:input>
            </div>
        </spring:bind>
        
        <spring:bind path="street">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input name="Street" type="street" path="street" class="form-control" placeholder="Street"></form:input>
            </div>
        </spring:bind>
        
        <spring:bind path="zipCode">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input name="ZipCode" type="zipCode" path="zipCode" class="form-control" placeholder="ZipCode"></form:input>
            </div>
        </spring:bind>
        
        <spring:bind path="city">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input name="City" type="city" path="city" class="form-control" placeholder="City"></form:input>
            </div>
        </spring:bind>
        
        <spring:bind path="country">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select id="country" name="Country" type="country" path="country" class="form-control" placeholder="Country"></form:select>
            </div>
        </spring:bind>


        <button id="submitButton" class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src = "${contextPath}/resources/js/countries.js"></script>
<script language="javascript">
	populateCountries("country");
</script>
</body>
</html>