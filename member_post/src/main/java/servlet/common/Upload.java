package servlet.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.codehaus.jackson.map.ObjectMapper;

import vo.Attach;

@WebServlet("/upload")
public class Upload extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024);
		factory.setRepository(new File("c:/upload/tmp"));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<Attach> attachs = new ArrayList<>();
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
				String uuid = UUID.randomUUID().toString();
				String realName = uuid + ext;
				String path = getTodayStr();
				File parentPath = new File("c:/upload", path);
				if(!parentPath.exists()) {
					parentPath.mkdirs();
				}
				item.write(new File(parentPath, realName));
				attachs.add(Attach.builder().uuid(realName).path(path).origin(origin).build());
			}
			System.out.println(attachs);
			
			resp.setContentType("application/json; charset=utf-8");
			resp.getWriter().print(new ObjectMapper().writeValueAsString(attachs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}
}
