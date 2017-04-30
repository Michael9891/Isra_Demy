package il.co.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tables.Users;

public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher requestDis;

	public DownloadFile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("user");
		if(request.getSession().getAttribute("user")!=null&&request.getSession().getAttribute("token").equals(request.getParameter("token"))){
		// the fileName declerate with non DIRECTORY TRAVERSAL ATTACK option
		String fileName = request.getParameter("filename");
		if(!fileName.contains("/")||!fileName.contains("..")){
		String pathName = request.getServletContext().getRealPath("") + "upload" + File.separator+user.getUserName() + request.getParameter("filename");
		System.out.println(pathName);
		String fileType = "application/pdf";
		// Find this file id in database to get file name, and file type

		// You must tell the browser the file type you are going to send
		// for example application/pdf, text/plain, text/html, image/jpg
		response.setContentType(fileType);

		// Make sure to show the download dialog
		response.setHeader("Content-disposition", "attachment; filename=demy_"+fileName);

		// Assume file name is retrieved from database
		// For example D:\\file\\test.pdf

		File my_file = new File(pathName);

		// This should send the file to browser
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(my_file);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.flush();
		}else{
			requestDis = getServletContext().getRequestDispatcher("/login.jsp");
			requestDis.forward(request, response);
		}
		}
		//response.sendRedirect("/universityp");
	}
}
