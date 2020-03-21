package br.com.contactBook.logic;
//feito por Robson Antas
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.contactBook.dao.ContactDao;
import br.com.contactBook.model.Telephone;
import br.com.contactBook.model.User;

public class UpdateContact implements Logica {

	@Override
	public String executaRetorno(HttpServletRequest request, HttpServletResponse response) {
		long id = (long)Integer.parseInt(request.getParameter("user"));
		String email = request.getParameter("email");
		
		String idTel_1 = request.getParameter("idTel1");
		String idTel_2 = request.getParameter("idTel2");
		String idTel_3 = request.getParameter("idTel3");
		
		String ddd_1 = request.getParameter("ddd1");
		String tel_1 = request.getParameter("tel1");
		String tipo_1 = request.getParameter("tipo1");
		
		String ddd_2 = request.getParameter("ddd2");
		String tel_2 = request.getParameter("tel2");
		String tipo_2 = request.getParameter("tipo2");
		
		String ddd_3 = request.getParameter("ddd3");
		String tel_3 = request.getParameter("tel3");
		String tipo_3 = request.getParameter("tipo3");
		
		
		List<Telephone> telephones = new ArrayList<>();
		
		
		if(idTel_1.isEmpty()!=true) {
			
			Telephone telephone_1 = new Telephone();
			int ddd1 = Integer.parseInt(ddd_1);
			telephone_1.setId(Long.parseLong(idTel_1));
			telephone_1.setDdd(ddd1);
			telephone_1.setTelephoneNumber(tel_1);
			telephone_1.setSort(tipo_1);
			telephones.add(telephone_1);
		}
		
		if(idTel_2.isEmpty()!=true) {
			Telephone telephone_2 = new Telephone();
			int ddd2 = Integer.parseInt(ddd_2);
			telephone_2.setId(Long.parseLong(idTel_2));
			telephone_2.setDdd(ddd2);
			telephone_2.setTelephoneNumber(tel_2);
			telephone_2.setSort(tipo_2);
			telephones.add(telephone_2);
			
		}
		
		if(idTel_2.isEmpty()!=true) {
			Telephone telephone_3 = new Telephone();
			int ddd3 = Integer.parseInt(ddd_3);
			telephone_3.setId(Long.parseLong(idTel_3));
			telephone_3.setDdd(ddd3);
			telephone_3.setTelephoneNumber(tel_3);
			telephone_3.setSort(tipo_3);
			telephones.add(telephone_3);		
		}
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setTelephones(telephones);
		
		ContactDao dao = new ContactDao();
		dao.updateUser(user);
		
		return "jsp/UpdateContact.jsp";
	}

}
