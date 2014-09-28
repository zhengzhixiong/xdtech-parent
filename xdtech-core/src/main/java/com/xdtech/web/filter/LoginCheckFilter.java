package com.xdtech.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;

public class LoginCheckFilter extends PassThruAuthenticationFilter{

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		String repeatLogin ="";
		if (isLoginRequest(request, response))
		{
            return true;
        } 
		else
		{
			HttpServletRequest hrequest = (HttpServletRequest)request;
			boolean isRepeat = false;
			Cookie[] cookies = hrequest.getCookies();
			if(cookies!=null)
			{
				for(Cookie cookie:cookies)
				{
//					if(SessionListener.getRepeatSession().containsKey(cookie.getName()))
//					{
//						isRepeat = true;
//						repeatLogin = SessionListener.getRepeatSession().get(cookie.getName());
//						SessionListener.getRepeatSession().remove(cookie.getName());
//						
//					}
				}
			}
			String url = hrequest.getRequestURL().toString();
			url = url.substring(url.lastIndexOf("/")+1);
			if(isRepeat&&!url.equals("authLoginAction!logout.action"))
			{
				//response.getWriter().write("<script type='text/javascript'>alert(\"${base_fp_repeatLogin}\");window.location.href='/login.jsp';</script>");
				((HttpServletResponse) response).setHeader("Cache-Control","No-Cache"); //用户在其他地方登陆,请重新登陆!alert('"+repeatLogin+"');window.location.href='/login.jsp';
				((HttpServletResponse) response).setStatus(202);//设定返回状态，便于ajax获取处理  
				response.getWriter().write("<script type='text/javascript'>messageBox({messageType:'alert',text:'"+repeatLogin+"',callback:function(){window.location.href='/login.jsp';}});</script>");
			}
			else
			{
				 saveRequestAndRedirectToLogin(request, response);
			}
			
        	
            return false;
        }
	}

}
