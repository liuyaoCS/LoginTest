package cn.ly.filter;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.CookieStore;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ly.Service.LoginService;
import cn.ly.bean.User;

public class MyFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//访问登录页面直接跳过,已经登录直接跳过
		//如果cookie包含登录信息，设置自动登录
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		String url=request.getRequestURI();
		if(!url.equals("/UserSystem/index.jsp")){
			User exitUser=(User) request.getSession().getAttribute("user");
			if(exitUser==null){
				Cookie[] cookies=request.getCookies();
				if (cookies != null && cookies.length > 0) {
					for(Cookie c:cookies){
						if(c.getName().equals("autologin")){
							String username=c.getValue().split(":")[0];
							String password=c.getValue().split(":")[1];
							User user=new User(username,password);
							LoginService ls=new LoginService();
							try {
								exitUser=ls.login(user);
								if(exitUser!=null){
									request.getSession().setAttribute("user", exitUser);
									return;
								}						
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (PropertyVetoException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			
			}
		}
		//放行
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
