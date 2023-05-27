<%@ include file="common/header.jspf"%>
<c:if test="${not empty message}">
    <div class="alert alert-success">
        <strong>Success!</strong> ${message}
    </div>
</c:if>
<c:if test="${not empty error_message}">
    <div class="alert alert-success">
        <strong>Success!</strong> ${message}
    </div>
</c:if>
<c:if test="${action == 'Add'}">
<!-- Tab content -->
<div id="Add" class="tabcontentShow">
    <%@ include file="addUser.jsp"%>
</div>
</c:if>
<c:if test="${action == 'Search'}">
    <div id="SearchObj" class="tabcontentShow">
        <%@ include file="searchUser.jsp"%>
    </div>
</c:if>

<c:if test="${action == 'upload'}">
    <div id="UploadObj" class="tabcontentShow">
        <%@ include file="uploadFile.jsp"%>
    </div>
</c:if>

<%@ include file="common/footer.jspf"%>