package il.co.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.reference.DefaultEncoder;

import il.co.model.HiberneteIsraDemyDao;
import tables.Users;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiberneteIsraDemyDao query;
	private String csrfToken;
	public LoginServlet() {
		super();
		csrfToken = resetCSRFToken();
		query = new HiberneteIsraDemyDao();
	}

	//query method that check if user exist, if do so pass to homePage, else loginPage
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// AVTAHA: before checking, we have to make
		// HASH so it will be the same as password
		// for comparing		
		boolean matched = false;
		Users user = query.getUser(request.getParameter("username"));
		if (user != null)
			matched = BCrypt.checkpw(request.getParameter("username"), user.getPassword());
		if (matched) {
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("token", csrfToken);
			RequestDispatcher requestDis = getServletContext().getRequestDispatcher("/Home.jsp");
			requestDis.forward(request, response);
		} else {
			RequestDispatcher requestDis = getServletContext().getRequestDispatcher("/login.jsp");
			requestDis.forward(request, response);
		}
	}
	@SuppressWarnings("deprecation")
	private String resetCSRFToken() {

		csrfToken = ESAPI.randomizer().getRandomString(8, DefaultEncoder.CHAR_ALPHANUMERICS);

		return csrfToken;

	}

}
