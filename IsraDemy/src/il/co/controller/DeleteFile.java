package il.co.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.co.model.HiberneteIsraDemyDao;
import tables.Users;

public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiberneteIsraDemyDao query;

    public DeleteFile() {
        super();
        query = new HiberneteIsraDemyDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDis;
		request.setAttribute("college", request.getParameter("college"));
		Users user = (Users) request.getSession().getAttribute("user");
		if(user!=null&&user.getAdmin()==1){
		//check if  user is admin for delete file
		if (user.getAdmin() == 1) {
			String deletePath = request.getServletContext().getRealPath("") + "upload" + File.separator
					+ user.getUserName() + request.getParameter("filename");
			Path path = Paths.get(deletePath);
			Files.delete(path);
			int id = Integer.parseInt(request.getParameter("id"));
			query.deleteFile(id);
		}
		}
		requestDis = getServletContext().getRequestDispatcher("/university");
		requestDis.forward(request, response);
	}

}
