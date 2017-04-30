<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf" %>
<html lang="en">
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/forum.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/contact-page-css.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Isrademy Forum</title>
</head>
<body>
<jsp:include page="head.jsp" />
<div class="container" style="margin-top: 35px">
    <div class="page-header page-heading">
        <h1 class="pull-left">Forums</h1>
        <ol class="breadcrumb pull-right where-am-i">
            <li><a href="#">Forums</a></li>
            <li class="active">List of topics</li>
        </ol>
        <div class="clearfix"></div>
    </div>
    <p class="lead">This is the right place to discuss any ideas, critics, feature requests and all the ideas regarding our website. Please follow the forum rules and always check FAQ before posting to prevent duplicate posts.</p>
    <!-- writePost Table -->
    <div class="col-md-5">
        <div class="form-area" >
            <form role="form" action="forum" method="GET">
                <br style="clear:both">
                <h3 style="margin-bottom: 25px; text-align: center;">Write a Post</h3>
                <div class="form-group">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="subject" name="subject" placeholder="Subject" required>
                </div>
                <div class="form-group">
                    <textarea class="form-control" type="textarea" id="message" name="msg" placeholder="Message" maxlength="140" rows="7"></textarea>
                </div>
                <button style="width: 100%" type="submit" id="submit" name="submit" class="btn btn-primary pull-right">Post</button>
            	<input type="hidden" name="token" value="${token}"/>
            </form>
        </div>
    </div>
    <!-- /writePost Table -->

    <!-- Posts Table -->
        <div class="table">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <th>User Name</th>
                    <th>Post</th>
                </tr>
          <c:forEach items="${msg}" var="msg">
			<tr >
	   			<td class="row" height="40px">${msg.userName}</td>
	   			<td class="row" height="40px">${msg.msg}</td>
	   		 </tr>	 
		</c:forEach>
                </tbody>
            </table>

        </div>
    <!-- /Posts Table -->

</div>


</body>
</html>
