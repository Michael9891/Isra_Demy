package il.co.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import il.co.model.HiberneteIsraDemyDao;
import tables.Files;
import tables.Users;

@MultipartConfig
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Files> files = null;
	private HiberneteIsraDemyDao query;

	public void init() {
		//filePath = getServletContext().getInitParameter("imageFilePath");
		query = new HiberneteIsraDemyDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDis;
		if(request.getSession().getAttribute("user")!=null&&request.getSession().getAttribute("token").equals(request.getParameter("token"))){
		Users user = (Users) request.getSession().getAttribute("user");
		if (!request.getParameter("lastname").equals(""))
			user.setLastName(request.getParameter("lastname"));
		if (!request.getParameter("firstname").equals(""))
			user.setFirstName(request.getParameter("firstname"));
		if (!request.getParameter("email").equals(""))
			user.setEmail(request.getParameter("email"));
		if (!request.getParameter("phone").equals(""))
			user.setPhone(request.getParameter("phone"));

		String appPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save uploaded file
		String savePath = appPath + "img-profile";
		// creates the save directory if it does not exists
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		for (Part part : request.getParts()) {
			String contentType = part.getContentType();
			//make sure content type is image
			if (contentType!=null&& (contentType.equals("image/jpeg") || contentType.equals("image/png"))){
			String fileName = extractFileName(part);
			if(!fileName.equals("")){
			// refines the fileName in case it is an absolute path
			user.setImageName(fileName);
			part.write(savePath + File.separator + fileName);
			}
			}
		}
		query.updateUser(user);
		request.getSession().setAttribute("user", user);
		files = query.getFiles(user.getUserName());
		request.getSession().setAttribute("files", files);
		requestDis = getServletContext().getRequestDispatcher("/profile.jsp");
		requestDis.forward(request, response);
		}else{
			requestDis = getServletContext().getRequestDispatcher("/login.jsp");
			requestDis.forward(request, response);
		}
	}
	private String extractFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		String[] arrayOfContent = contentDisposition.split(";");
		for (String content : arrayOfContent) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 2, content.length()-1);
			}
		}
		return "";
	}
}
