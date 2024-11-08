package servlet.post;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PostService;
import service.PostServiceImpl;
import vo.Post;

@WebServlet("/post/write")
public class Write extends HttpServlet {
	private PostService postService = new PostServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("member") == null) {
			String cp = req.getContextPath();
			resp.sendRedirect(cp + "/signin?url=" + 
						URLEncoder.encode(cp + "/post/write", "utf-8"));
			return;
		}
		req.getRequestDispatcher("/WEB-INF/jsp/post/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		
		postService.write(Post.builder().title(title).content(content).writer(writer).build());
		
		resp.sendRedirect("list");
	}
	
}
