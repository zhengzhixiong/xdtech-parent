package com.xdtech.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	/**
	 * 获取服务端IP地址
	 * @return
	 */
	public static String getServerIP() 
	{
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP
		try 
		{
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;// 是否找到外网IP
			while (netInterfaces.hasMoreElements() && !finded) 
			{
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) 
				{
					ip = address.nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()&& ip.getHostAddress().indexOf(":") == -1) // 外网IP
					{
						netip = ip.getHostAddress();
						finded = true;
						break;
					} 
					else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) // 内网IP
					{
						localip = ip.getHostAddress();
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if (netip != null && !"".equals(netip)) 
		{
			return netip;
		} 
		else 
		{
			return localip;
		}
	}
	
	/**
	 * 获取请求的客户端IP地址
	 * @param request
	 */
	public static String getClientIp(HttpServletRequest request) 
	{ 
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个IP为客户IP
		if (ip != null && ip.indexOf(",") != -1) 
		{
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		//如果IP地址是127.0.0.1或者是IPV6格式的0:0:0:0:0:0:0:1那说明是服务器本地的IP
		return ip;
	}
}
