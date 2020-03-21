package br.com.contactBook.logic;
//feito por Robson Antas
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.contactBook.dao.ContactDao;
import br.com.contactBook.model.User;

public class ContactNameBeginWith implements Logica {

	@Override
	public String executaRetorno(HttpServletRequest request, HttpServletResponse response) {
		String input = request.getParameter("letter");
		
		List<User> users = new ContactDao().getUsersNameStartingWithLetter(input);
		request.setAttribute("users", users);
		
		return "jsp/ContactNameBeginWith.jsp";
	}

}
