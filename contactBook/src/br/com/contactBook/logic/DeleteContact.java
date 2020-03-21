package br.com.contactBook.logic;
//feito por Robson Antas
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.contactBook.dao.ContactDao;
import br.com.contactBook.model.User;

public class DeleteContact implements Logica {

		
	@Override
	public String executaRetorno(HttpServletRequest request, HttpServletResponse response) {
			
		long id = Long.parseLong(request.getParameter("id"));
		
		User user = new User();
		user.setId(id);
		ContactDao dao = new ContactDao();
		
		dao.deleteUser(user);
		return "jsp/DeleteContact.jsp";
	}

}
