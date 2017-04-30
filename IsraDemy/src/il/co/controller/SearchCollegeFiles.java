package il.co.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.co.model.HiberneteIsraDemyDao;
import tables.Files;

public class SearchCollegeFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiberneteIsraDemyDao query;

	public SearchCollegeFiles() {
		super();
		query = new HiberneteIsraDemyDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDis;
		if(request.getSession().getAttribute("user")!=null) {
			List<Files> files = query.getCollegeFiles(request.getParameter("searchEngine"));
			if (!files.isEmpty()) {
				System.err.println(files.toString()+"*****");
				System.out.println("uuuuuuuuuuu");
				request.getSession().setAttribute("list", files);
				requestDis = getServletContext().getRequestDispatcher("/universityList.jsp");
				requestDis.forward(request, response);
			} else {
				System.out.println("hhhhhhhhhhhhhh");
				request.setAttribute("msg", "college not found");
				requestDis = getServletContext().getRequestDispatcher("/Home.jsp");
				requestDis.forward(request, response);
			}
		}
	}

}
