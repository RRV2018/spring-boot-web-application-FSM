<h3 xmlns:c="http://www.w3.org/1999/html">Search User</h3>
<form:form method="post" action="./searchUser" modelAttribute="userForm" id="myform" >
        <caption align="left"><h4>Search User</h4></caption>
        <div class="form-group">
            <label for="userName"><b>Enter User Name</b></label>
            <input name="userName" type="text" placeholder="Enter User Name" id="userName">
        </div>
        <div class="form-group">
                <button type="submit" class="btn btn-info" style="width:100px; margin-left: 120px;">
                    <span class="glyphicon glyphicon-search"></span> Search
                </button>
        </div>
</form:form>
<c:if test="${userList != null}">
    <table class="table table-striped table-hover table-condensed table-sm">
        <thead style="background-color: coral;">
            <td width="13%">User ID</td>
            <td width="29%">User Name</td>
            <td width="29%">Password</td>
            <td width="29%">Status</td>
            <td width="29%">Action</td>
        </thead>
        <c:forEach items="${userList}" var="userData">
            <tr>
            <td align="center">${userData.getUserId()}</td>
            <td>${userData.getUserName()}</td>
            <td>${userData.getUserPassword()}</td>
                <td>${userData.getStatus()}</td>
            <td>
<!--                data-toggle="modal" data-target="#showRoles"-->
                <button type="button" class="btn btn-info btn-sm showRole" data-value="${userData.getUserId()}"><span class="glyphicon glyphicon-th-list"></span>Roles</button>
                <form:form method="post" action="./deleteUser" modelAttribute="loginForm" class="form" autocomplete="nope" >
                    <input type="image" src="./image/delete-button.png" alt="Submit" style="float:left" width="45" height="25">
                    <span style="width:4px;"><input type="hidden" name="userId" value="${userData.getUserId()}" size="1"></span>
                </form:form>
            </td>
        </tr>
        </c:forEach>
    </table>
</c:if>

<!-- Success Modal -->
<div class="modal fade" id="showRoles" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">User Roles</h4>
            </div>
            <div class="modal-body">
                    <table class="table table-striped table-hover table-condensed table-sm" id="roleTab">
                        <thead style="background-color: coral;">
                        <td width="13%">Role ID</td>
                        <td width="29%">Role Name</td>
                        <td width="29%">User Name</td>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Failure Modal -->
<div class="modal fade" id="showError" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Error Box</h4>
            </div>
            <div class="modal-body">
                <p>Error while fetching roles for user.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
