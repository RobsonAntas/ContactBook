package br.com.contactBook.logic;
//feito por Robson Antas
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.contactBook.dao.ContactDao;
import br.com.contactBook.model.User;

public class ListAllHavingTelephone implements Logica {

	

	@Override
	public String executaRetorno(HttpServletRequest request, HttpServletResponse response) {
		
		List<User> users = new ContactDao().getUsersHavingTelephone();
		request.setAttribute("users", users);
		
		return "jsp/ListAllHavingTelephone.jsp";
	}

	

	

}
