<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Profile</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">
    <link href="css/profile.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<!-- Navigation -->
<jsp:include page="head.jsp" />

<!-- Page Content -->
<div class="container">
${pageContext.request.contextPath}
    <h1 class="page-header">Edit Profile</h1>
    <div class="row">
        <!-- left column -->
			<div class="col-md-4 col-sm-6 col-xs-12">
				<div class="text-center">
					<img
						src="img-profile/${user.imageName}"
						class="avatar img-circle img-thumbnail" alt="avatar">
					<h6>Upload a different photo...</h6>
				</div>
			</div>
			<!-- edit form column -->
        <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
            <div class="alert alert-info alert-dismissable">
                <a class="panel-close close" data-dismiss="alert">×</a>
                <i class="fa fa-coffee"></i>
                This is an <strong>.alert</strong>. Use this to show important messages to the user.
            </div>
            <h3>Personal info</h3>
            <form class="form-horizontal" action="updateprofile" method="post"  enctype="multipart/form-data" role="form" >
					<input type="file" name="filename" class="text-center center-block well well-sm">
                <div class="form-group">
                    <label class="col-lg-3 control-label">First name:</label>
                    <div class="col-lg-8">
                        <input class="form-control" value="${user.firstName}" type="text" name="firstname">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Last name:</label>
                    <div class="col-lg-8">
                        <input class="form-control" value="${user.lastName}" type="text" name="lastname">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Phone:</label>
                    <div class="col-lg-8">
                        <input class="form-control" value="${user.phone}" type="number" name="phone">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Email:</label>
                    <div class="col-lg-8">
                        <input class="form-control" value="${user.email}" type="text" name="email">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-8">
                        <input class="btn btn-primary" value="Save Changes" type="submit">
                        <span></span>
                        <input class="btn btn-default" value="Cancel" type="reset">
                    </div>
                    <input type="hidden" name="token" value="${token}"/>
                </div>
            </form>
        </div>
    </div>
        <!-- Files Table -->
        <div class="table">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <th>File Name</th>
                    <th>College</th>
                    <th>User Name</th>
                </tr>
                <c:forEach items="${files}" var="file">
					<tr >
	   					<td class="row" height="40px">${file.fileName}</td>
	   					<td class="row" height="40px">${file.collegeName}</td>
	   					<td class="row" height="40px">${file.userName.userName}</td>
	   			 	</tr>
				</c:forEach>
                 </tbody>
            </table>

        </div>
        <!-- Files Table -->

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <footer class="footer">
            <a href='#'>Terms of use</a> | 
            <a href='#'>Privacy Policy</a> | 
            <a href='contact-page.html'>Contact us</a> | 
            <a href='#'>Feedback</a>
            <p>©2016 IsraDemy </p>
        </footer> <!--footer Section ends-->

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>
</html>