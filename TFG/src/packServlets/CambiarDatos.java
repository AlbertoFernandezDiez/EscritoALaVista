package packServlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import packBD.GestorBD;
import packBeans.Autor;
import packBeans.BreadCrumb;

/**
 * Servlet implementation class CambiarDatos
 */
@WebServlet("/CambiarDatos")
@javax.servlet.annotation.MultipartConfig

public class CambiarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CambiarDatos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String user = (String) session.getAttribute("username");
		int id = 0;

		try {
			id = (int) session.getValue("id");
			// TODO Auto-generated method stub
			if (id != 0)
			{
				request.setAttribute("userId",id);
				request.setAttribute("userName",user);
updateBreadCrumb(request, session);
				Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);
				request.setAttribute("autor", autor);

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/modifyAutor.jsp");
				rd.forward(request, response);
			}
			else
				response.sendRedirect("Error/NoLogeado.html");



		}
		catch(NullPointerException e){
			e.printStackTrace();
			response.sendRedirect("Error/NoLogeado.html");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String user = (String) session.getAttribute("username");
		int id = 0;

		try {
			id = (int) session.getValue("id");
			if (id != 0)
			{
				updateBreadCrumb(request,session);
				String mailS = null,aboutS = null, paisS = null;

				mailS = request.getParameter("email");
				paisS = request.getParameter("pais");
				aboutS = request.getParameter("about");

				Part filePart = request.getPart("file");

				if (filePart.getSize()==0)
				{
					GestorBD.getGestorBD().updateAutor(id,mailS,paisS,aboutS,null);
				}
				else
				{
					GestorBD.getGestorBD().updateAutor(id,mailS,paisS,aboutS,loadFile(request, "file"));
				}
				// TODO Auto-generated method stub
				response.sendRedirect("Index");

			}
			else
				response.sendRedirect("Error/NoLogeado.html");

		}catch(NullPointerException e){
			e.printStackTrace();
			response.sendRedirect("Error/NoLogeado.html");
		}
	}

	private String loadFile(HttpServletRequest request, String fileID) throws IOException,
	ServletException, FileNotFoundException {
		Part file = request.getPart(fileID);
		String filepath =null;


		if (file.getSize() != 0){
			String filename = getFileName(file);
			java.io.InputStream is = file.getInputStream();

			filepath = "imagenes/"  + System.currentTimeMillis() + filename.substring(filename.lastIndexOf('.'));
			OutputStream outputStream = new FileOutputStream(new File(getServletContext().getInitParameter("file-upload") +File.separator +  
					filepath));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
		}
		return filepath;
	}

	public static String getFileName(Part filePart)
	{
		String header = filePart.getHeader("content-disposition");
		for(String headerPart : header.split(";"))
		{
			if(headerPart.trim().startsWith("filename"))
			{
				return headerPart.substring(headerPart.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
	
	private void updateBreadCrumb(HttpServletRequest request,
			HttpSession session) {
		Stack<BreadCrumb> breadcrumb = null;
		BreadCrumb bread = new BreadCrumb();
		bread.setName("ModificarDatos");
		String parameters = request.getQueryString();
		if (parameters == null)
			parameters ="";
		bread.setUrl(request.getRequestURL().toString() + '?' + parameters);	
		breadcrumb = (Stack<BreadCrumb>) session.getAttribute("breadcrumb");
		if (breadcrumb == null)
		{			
			breadcrumb = new Stack<BreadCrumb>();
		}
		System.out.println(breadcrumb.size());

		if (breadcrumb.contains(bread))
		{
			System.out.println(breadcrumb.indexOf(bread));
			while(!breadcrumb.peek().equals(bread))
			{
				breadcrumb.pop();
			}
		}
		else
		{
			breadcrumb.push(bread);
		}
		session.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("breadcrumb", breadcrumb);
	}
	
	
}
