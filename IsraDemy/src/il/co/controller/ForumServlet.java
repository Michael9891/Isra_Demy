package il.co.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.ESAPI;
import il.co.model.HiberneteIsraDemyDao;
import tables.Messages;

public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiberneteIsraDemyDao query;

    public ForumServlet() {
        super();
        query = new HiberneteIsraDemyDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null&&request.getSession().getAttribute("token").equals(request.getParameter("token"))){
		//XSS protection - avoid XSS injection
		String safe = ESAPI.encoder().encodeForHTML(request.getParameter("msg"));
		Messages msg = new Messages(safe,request.getParameter("name"));
		query.addMessage(msg);
		List<Messages> messages = query.getMessages();
		request.getSession().setAttribute("msg", messages);
		}
		RequestDispatcher requestDis = getServletContext().getRequestDispatcher("/forum.jsp");
		requestDis.forward(request, response);
	}

}
