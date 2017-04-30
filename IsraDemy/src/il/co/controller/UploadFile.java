package il.co.controller;
import java.io.File;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.itextpdf.text.pdf.PdfReader;

import il.co.model.HiberneteIsraDemyDao;
import tables.Files;
import tables.Users;
@MultipartConfig
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
   private HiberneteIsraDemyDao query;
   private String fileName;
   public void init( ){
      query = new HiberneteIsraDemyDao();
   }

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
		Users user = (Users) request.getSession().getAttribute("user");
		RequestDispatcher requestDis;
		if(request.getSession().getAttribute("user")!=null&&request.getSession().getAttribute("token").equals(request.getParameter("token"))){
		String appPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save uploaded file
		String savePath = appPath + "upload";
		// creates the save directory if it does not exists
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		for (Part part : request.getParts()) {
			String contentType = part.getContentType();
			if (contentType!=null&& (contentType.equals("application/pdf") || contentType.equals("application/msword")
					|| contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")||contentType.equals("text/plain"))) {
				fileName = extractFileName(part);
				if (fileName != null) {
					fileName = new File(fileName).getName();		
					// MALICIOUS FIE UPLOAD PDF js scripts protection
					if(contentType.equals("application/pdf")){
						PdfReader read = new PdfReader(fileName);
						String jsCode = read.getJavaScript();
						if(jsCode!=null){
							requestDis = getServletContext().getRequestDispatcher("/login.jsp");
							requestDis.forward(request, response);
						}
					}
					Files file = new Files(fileName, request.getParameter("college"), user);
					// saving file details in db ans server query
					query.addFile(file);
					// another PROTECTION - adding userName to each file
					part.write(savePath + File.separator + user.getUserName() + fileName);
				}
			}
		}
		}
		request.setAttribute("notice", "OK");
		requestDis = getServletContext().getRequestDispatcher("/upload.jsp");
		requestDis.forward(request, response);
	}
	private String extractFileName(Part part) {
		// content-dispositio HHTP header that tell us what is the file type
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return null;
	}

}
/*String fileName = null;
		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);
		try {
			// Parse the request to get file items.
			List<FileItem> fileItems = upload.parseRequest(request);
			// Process the uploaded file items
			@SuppressWarnings("rawtypes")
			Iterator i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem currentFile = (FileItem) i.next();
				String contentType = currentFile.getContentType();
				if(contentType.equals("application/pdf")||contentType.equals("application/msword")||contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
					// Get the file location where it would be stored.
					filePath = getServletContext().getInitParameter("file-upload");
				}else if (contentType.equals("image/jpeg")||contentType.equals("image/png")) {
					// Get the file location where it would be stored.
					filePath = getServletContext().getInitParameter("imageFilePath");
				}else{
					request.setAttribute("notice", "error type");
					RequestDispatcher requestDis = getServletContext().getRequestDispatcher("/upload.jsp");
					requestDis.forward(request, response);
				}
				if (!currentFile.isFormField()) {
					// Get the uploaded file parameters
					fileName = currentFile.getName();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					currentFile.write(file);
				}else{
					s = currentFile.getString();
					System.out.println(s);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}*/