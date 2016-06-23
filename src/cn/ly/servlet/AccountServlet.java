package cn.ly.servlet;

import java.io.IOException;

import javax.security.auth.login.AccountException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ly.Service.AccountService;
import cn.ly.bean.User;
import cn.ly.dao.LoginDao;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user")==null){
			//request.getRequestDispatcher("/LoginServlet").forward(request, response);
			response.sendRedirect("/LoginTest/LoginServlet");
			return;
		}
		User currentUser=(User) request.getSession().getAttribute("user");
		String username=currentUser.getUsername();
		String userTo=request.getParameter("userto");
		String money=request.getParameter("money");
		
		
		AccountService service=new AccountService();
		try {
			service.transfer(username,userTo,money);
			response.getWriter().println("transfer success!");
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().println("transfer error!");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
