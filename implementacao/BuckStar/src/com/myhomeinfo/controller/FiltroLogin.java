package com.myhomeinfo.controller;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroLogin
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST,
				DispatcherType.FORWARD
		}
					, urlPatterns = { "/pages/*" })
public class FiltroLogin implements Filter {
    public FiltroLogin() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = ((HttpServletRequest) request);

		String url = httpServletRequest.getRequestURI();
		HttpSession sessao = httpServletRequest.getSession();
		if ((sessao.getAttribute("usuarioLogado") != null) || (url.lastIndexOf("login.jsp") > -1) || (url.lastIndexOf("logincontroller.do") > -1)){
			request.setAttribute("pagina", "teste.jsp");
			chain.doFilter(request, response);	//Permite o fluxo da requisição
		} else{
			((HttpServletResponse) response).sendRedirect("login.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
