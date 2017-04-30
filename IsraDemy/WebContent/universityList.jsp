<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html lang="en">

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

    <h2 class="title">University Courses List</h2>
    <!-- Files Table -->
    <div class="table">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <th>File Name</th>
                <th>College</th>
                <th></th>
            </tr>
            <c:choose>
			    <c:when test="${user.admin=='1'}">
			       <c:forEach items="${list}" var="files">
						<tr >
				   			<td class="row" height="40px">${files.fileName}</td>
				   			<td class="row" height="40px">${files.collegeName}</td>
				   			<td class="row" height="40px">
				   			<input type="hidden" value="${files.fileId}" name="fileid" />
				   			<a href="download?filename=${files.fileName}&collegename=${files.collegeName}&id=${files.fileId}">download</a>
				   			</td>
				   			<td class="row" height="40px">
				   			<input type="hidden" value="${files.fileId}" name="fileid" />
				   			<a href="delete?filename=${files.fileName}&college=${files.collegeName}&id=${files.fileId}">delete</a>
				   			</td>
				   		 </tr>	 
					</c:forEach> 
			        <br />
			    </c:when>    
			    <c:otherwise>
			        <c:forEach items="${list}" var="files">
						<tr >
				   			<td class="row" height="40px">${files.fileName}</td>
				   			<td class="row" height="40px">${files.collegeName}</td>
				   			<td class="row" height="40px">
				   			<input type="hidden" value="${files.fileId}" name="fileid" />
				   			<a href="download?filename=${files.fileName}&collegename=${files.collegeName}&id=${files.fileId}">download</a>
				   			</td>
				   		 </tr>	 
					</c:forEach>
			        <br />
			    </c:otherwise>
			</c:choose>
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
        <a href='#'>Terms of use</a> | 
        <a href='#'>Privacy Policy</a> | 
        <a href='contact-page.html'>Contact us</a> | 
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