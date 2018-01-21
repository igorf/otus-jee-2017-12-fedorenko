package ru.otus.hw3.server.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.otus.hw3.server.services.XmlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import static ru.otus.hw3.util.ConstantHolder.*;

@WebServlet(XMLFIND_URL)
public class XmlSearchServlet extends HttpServlet {
	private final static Logger logger = Logger.getLogger(XmlSearchServlet.class.getName());

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				FileItem file = (FileItem) upload.parseRequest(request).stream().findFirst().get();
				Map<String, String> tags = XmlService.getInstance().getTagsMostPayed(
				        XmlService.getInstance().readEmployeeXML(file)
                );
                request.setAttribute(TAGS_LIST_ATTRIBUTE, tags);
                request.getRequestDispatcher(TAGS_JSP).forward(request, response);
			} catch(Exception e) {
				logger.severe(e.getLocalizedMessage());
			}
		}
	}
}
