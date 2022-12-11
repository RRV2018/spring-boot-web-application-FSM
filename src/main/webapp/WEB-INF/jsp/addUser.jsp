<div style="width:40%; height:100%;">
<form:form method="post" action="./addUser" modelAttribute="userForm" id="addUserForm">
    <caption align="left"><h4>Add User</h4></caption>
        <div class="form-group">
            <label for="userName"><b>Enter User Name</b><span class="req">*</span></label>
            <input type="text" placeholder="Enter Username" name="userName" id="userName" required>
        </div>
    <div class="form-group">
        <label for="myPassword">Enter Password<span class="req">*</span></label>
        <input name="password" type="password" id="myPassword" placeholder="Enter Password" required style="margin-left: 5px;">
    </div>
    <div class="form-group">
        <label for="confirmPassword">Enter Password<span class="req">*</span></label>
        <input name="confirmPassword" type="password" id="confirmPassword" placeholder="Confirm Password" required onkeyup="validatePassword()" style="margin-left: 5px;">
    </div>
    <div class="form-group">
        <label for="userRole">User Role</label>
        <select name="userRole" id="userRole" style="width: 150px;margin-left: 50px;">
            <option value="">Select</option>
            <c:forEach items="${roles}" var="role">
                <option value="${role.roleName}">${role.roleDesc}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label>
        Permissions :
        </label>
        <label class="checkbox-inline" style="margin-left: 30px;">
            <input type="checkbox" value="*" id="all"> Full
        </label>
        <label class="checkbox-inline">
            <input type="checkbox" value="read" id="read">Read Only
        </label>
        <label class="checkbox-inline">
            <input type="checkbox" value="write" id=write">Write Only
        </label>
    </div>
    <div class="form-group" style="margin-left: 120px;">
        <input type="submit" value="SUBMIT">
    </div>
</form:form>
</div>