package cn.ly.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ly.Service.LoginService;
import cn.ly.bean.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		
		User user=new User(username,password);
		
		try {
			User exitUser=new LoginService().login(user);
			if(exitUser!=null){
				//auto login
				String autologin=request.getParameter("autologin");
				if(autologin!=null && autologin.equals("ok")){
					Cookie cookie=new Cookie("autologin", exitUser.getUsername()+":"+exitUser.getPassword());
					cookie.setMaxAge(60*60*24);
					cookie.setPath("/");
					response.addCookie(cookie);	
				}
				//response.getWriter().println("username:"+exitUser.getUsername());
				request.getSession().setAttribute("user", exitUser);
				request.getRequestDispatcher("/account.jsp").forward(request, response);
			}else{
				//response.getWriter().println("username or password error");
				request.setAttribute("loginmessage", "username or password error");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.getWriter().println("username:"+username);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
