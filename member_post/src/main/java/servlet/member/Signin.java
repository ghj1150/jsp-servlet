package servlet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import service.MemberServiceImpl;
import vo.Member;

@WebServlet("/signin")
public class Signin extends HttpServlet {
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/signin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String save = null;
		

		System.out.println(id);
		System.out.println(pw);

//		service.register(member);
		if (service.login(id, pw)) {
			save = req.getParameter("remember-id");
			// 로그인 성공
			if ("yes".equals(save)) {
				Cookie cookie = new Cookie("id", id);
				cookie.setMaxAge(60 * 60 * 24);
				resp.addCookie(cookie);
			}
			HttpSession session = req.getSession();
			session.setAttribute("member", service.findBy(id));
			resp.sendRedirect(req.getContextPath() + "/index");

		} else {
			resp.sendRedirect("login?msg=fail");
		}

	}
}
