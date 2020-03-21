package br.com.contactBook.logic;
//feito por Robson Antas
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.contactBook.dao.ContactDao;
import br.com.contactBook.model.User;

public class SearchContactById implements Logica {

	@Override
	public String executaRetorno(HttpServletRequest request, HttpServletResponse response) {
		long input =(long)Integer.parseInt(request.getParameter("id"));

		List<User> users = new ContactDao().getById(input);
		request.setAttribute("users", users);
		return "jsp/SearchContactById.jsp";
	}

}
