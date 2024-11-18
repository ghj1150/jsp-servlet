package servlet.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import service.ReplyService;
import service.ReplyServiceImpl;
import vo.Reply;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet{

    private ReplyService service = ReplyServiceImpl.getInstance();
    private Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd-HH:mm:ss").create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        uri = uri.replace(req.getContextPath() + "/reply/", "");
        System.out.println(uri);
        Object ret = null;
        if(uri.startsWith("list")) { // 목록 조회
            int tmpIdx = uri.lastIndexOf("/");
            Long pno =  0L;
            if(tmpIdx != -1) {
                pno = Long.valueOf(uri.substring(tmpIdx+1));
            }
            ret = service.list(pno);
        }else { // 단일 조회
            Long rno = Long.valueOf(uri);
            ret = service.findBy(rno);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(gson.toJson(ret));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	service.write(gson.fromJson(req.getReader(), Reply.class));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	Reply reply = gson.fromJson(req.getReader(), Reply.class);
    	
    	service.modify(reply);
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String uri = req.getRequestURI();
        uri = uri.replace(req.getContextPath() + "/reply/", "");

        if(uri.startsWith("list")) { // 목록 조회
            int tmpIdx = uri.lastIndexOf("/");
            Long pno =  0L;
            if(tmpIdx != -1) {
                pno = Long.valueOf(uri.substring(tmpIdx+1));
            }
            service.removeAll(pno);
        }
        else { // 단일 조회
        	Long rno = Long.valueOf(uri);
        	service.remove(rno);
        }
    }
}