package br.com.contactBook.logic;
//feito por Robson Antas
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.contactBook.dao.ContactDao;
import br.com.contactBook.model.User;

public class ContactNameContaining implements Logica {

	@Override
	public String executaRetorno(HttpServletRequest request, HttpServletResponse response) {

		String input = request.getParameter("wordPiece");

		List<User> users = new ContactDao().getUserNameContaining(input);
		request.setAttribute("users", users);

		return "jsp/ContactNameContaining.jsp";
	}

}
