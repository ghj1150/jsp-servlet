package servlet.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
public class Upload extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024);
		factory.setRepository(new File("c:/upload/tmp"));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(req);
			for(FileItem item : items) {
				System.out.println(item.getName());
				System.out.println(item.getSize());
				
				String origin = item.getName();
				int dotIdx = origin.lastIndexOf(".");
				String ext = "";
				if(dotIdx != -1) {
					ext = origin.substring(dotIdx);
				}
				String realName = UUID.randomUUID() + ext;
				File parentPath = new File("c:/upload", getTodayStr());
				if(!parentPath.exists()) {
					parentPath.mkdirs();
				}
				item.write(new File(parentPath, realName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}
}
