package br.com.contactBook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.contactBook.logic.Logica;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/Sistema")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ServletController() {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametro = request.getParameter("logica");
		String className = "br.com.contactBook.logic." + parametro;
		try {
			Class<?> classe = Class.forName(className, true, this.getClass().getClassLoader());
			Logica logica = (Logica) classe.getDeclaredConstructor().newInstance();
			String page = logica.executaRetorno(request, response);
			request.getRequestDispatcher(page).forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível criar o objeto" + className, e);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
