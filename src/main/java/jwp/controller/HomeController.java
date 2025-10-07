package jwp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//main jsp page 띄우기

@WebServlet("/")
public class HomeController extends HttpServlet {
	@Override
	//doGet() : 클라이언트가 GET 요청(URL 입력 또는 링크 클릭 등)을 보냈을 때 호출됨.
	//HttpServletRequest req : 클라이언트 요청 정보를 담고 있음.
	//HttpServletResponse resp : 서버가 클라이언트에 보낼 응답을 제어하는 객체.
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getRequestDispatcher("/home.jsp") : 서버 내부에서 특정 리소스(home.jsp)로 요청을 전달하는 객체를 얻음.
		//forward(req, resp) : 현재 처리 중인 요청과 응답을 home.jsp로 넘기고, 그 JSP가 화면을 그리게 함.
		//즉, 클라이언트(브라우저)는 여전히 같은 URL에 요청했지만 내부적으로 서버는 JSP로 포워딩하여 결과를 보여줌.
		RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
		rd.forward(req, resp);
	}

}
/*
클라이언트가 홈페이지 URL을 요청하면 (/),

서블릿이 호출됨 (HomeController의 doGet())

서버 내부에서 home.jsp로 포워딩하여 화면을 출력함.

이 구조는 서버가 요청을 받아서 JSP를 이용해 동적 페이지를 보여주는 전통적인 방식입니다.
 */