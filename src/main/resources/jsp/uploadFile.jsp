<h2>File Upload</h2>
<form:form method="post" action="./upload" modelAttribute="userForm" id="uploadFileForm" enctype="multipart/form-data">
    <div class="form-group">
        <label for="file"><b>Select File</b><span class="req">*</span></label>
        <input type="file" placeholder="Enter Username" name="file" id="file" required>
    </div>
    <div class="form-group" style="margin-left: 120px;">
        <input type="submit" value="Upload">
    </div>
</form:form>