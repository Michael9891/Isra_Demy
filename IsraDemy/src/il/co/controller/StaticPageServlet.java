package il.co.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StaticPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StaticPageServlet() {
        super();
    }

    /* 
     * doGet method gets user URL and checks if user logged in, and send him to specific page.
     * else, send to login page.
     * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDis;
		String url = request.getRequestURL().toString().substring(request.getRequestURL().toString().lastIndexOf("/") + 1);
		if(request.getSession().getAttribute("user")!=null||url.equals("registp")||url.equals("loginp")){
		switch (url) {
		case "registp":
			requestDis = getServletContext().getRequestDispatcher("/regist.jsp");
			requestDis.forward(request, response);
			break;
		case "loginp":
			requestDis = getServletContext().getRequestDispatcher("/login.jsp");
			requestDis.forward(request, response);
			break;
		case "forump":
			requestDis = getServletContext().getRequestDispatcher("/forum.jsp");
			requestDis.forward(request, response);
			break;
		case "uploadp":
			requestDis = getServletContext().getRequestDispatcher("/upload.jsp");
			requestDis.forward(request, response);
			break;
		case "aboutp":
			requestDis = getServletContext().getRequestDispatcher("/about.jsp");
			requestDis.forward(request, response);
			break;
		case "profilep":
			requestDis = getServletContext().getRequestDispatcher("/profile.jsp");
			requestDis.forward(request, response);
			break;
		case "logoutp":
			HttpSession session = request.getSession(false);
			if(session!=null)
			session.invalidate();
			//cant back
		    response.setHeader("Cache-Control","no-cache");  
		    response.setHeader("Cache-Control","no-store");  
		    response.setDateHeader("Expires", -1); // method that remove "comeback" option when user logged out
			requestDis = getServletContext().getRequestDispatcher("/login.jsp");
			requestDis.forward(request, response);
			break;
		case "homep":
			requestDis = getServletContext().getRequestDispatcher("/Home.jsp");
			requestDis.forward(request, response);
			break;
		case "universityp":
			response.sendRedirect("/universityList.jsp");
			break;
		default:
			System.out.println("hhcdh");
		}
		}else{
			System.out.println("asdasd");
			requestDis = getServletContext().getRequestDispatcher("/login.jsp");
			requestDis.forward(request, response);
		}
	}
}
