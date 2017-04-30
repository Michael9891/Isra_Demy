<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/upload-page-css.css" rel="stylesheet">
    <title>Upload for free</title>
</head>
<body>
<header>
    <div class="logo">
        <h1>IsraDemy Document Uploader</h1>
    </div>
</header>

</body>
<jsp:include page="head.jsp" />
<div class="container">
    <div class="contr"><h3>You can select the file (PDF / DOC) and click Upload button</h3></div>

    <div class="upload_form_cont">
        <form action="uploadfile" method="post"enctype="multipart/form-data">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div><label for="image_file">Please select file</label></div>
                <div><input type="file"  name="filename" /></div>
            </div>

            <div id="fileinfo">
                <div id="filename"></div>
                <div id="filesize"></div>
                <div id="filetype"></div>
                <div id="filedim"></div>
            </div>
            <div class="collegeSelector">
                <h4 class="title">Select your College</h4>
			   <select name="college">
					<option value="hit"> HIT</option>
					<option value="tau"> TAU</option>
					<option value="technion">TECHNION</option>
				</select>
            </div>
            <input type="hidden" name="token" value="${token}"/>
            <div class="col-lg-12 col-md-12 col-sm-12">
                <input type="submit" value="Upload" />
            </div>
            <div id="error">You should select valid image files only!</div>
            <div id="error2">An error occurred while uploading the file</div>
            <div id="abort">The upload has been canceled by the user or the browser dropped the connection</div>
            <div id="warnsize">Your file is very big. We can't accept it. Please select more small file</div>

            <div id="progress_info">
                <div id="progress"></div>
                <div id="progress_percent">&nbsp;</div>
                <div class="clear_both"></div>
                <div>
                    <div id="speed">&nbsp;</div>
                    <div id="remaining">&nbsp;</div>
                    <div id="b_transfered">&nbsp;</div>
                    <div class="clear_both"></div>
                </div>
                <div id="upload_response"></div>
            </div>
        </form>

        <img id="preview" />
    </div>

    <!-- Files Table -->
    <div class="table">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>


            </tbody>
        </table>

    </div>
    <!-- Files Table -->
</div>

</body>
</html>