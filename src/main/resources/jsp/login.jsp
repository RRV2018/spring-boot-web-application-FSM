<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1; }

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
.center {
  margin: auto;
  width: 40%;
  border: 0px solid #73AD21;
  padding: 5px;
}
.error-message {
  background-color: #fce4e4;
  float: center;
  padding: 10px 20px;
}
</style>
</head>
<body>
<div class="center">
    <center><h2>Login Form</h2></center>
    <form:form method="post" action="./login" modelAttribute="loginForm" class="form" autocomplete="nope" >
        <center>
        <img src="./image/avatar-images.png" alt="Login" width="120" height="120">
        </center>
        <div class="form-group">
        <label for="userName"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="userName" required>
        </div>
        <div class="form-group">
        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>
        </div>
        <div class="form-group">
        <c:if test="${not empty message}">
            <p style="color:tomato;"> ${message}</p>
        </c:if>
        <button type="submit">Login</button>
        </div>

        <div class="form-group">
        <button type="button" class="cancelbtn">Cancel</button>
        <span class="psw">Forgot <a href="#">password?</a></span>
        </div>
    </form:form>
</div>
<%@ include file="common/footer.jspf"%>