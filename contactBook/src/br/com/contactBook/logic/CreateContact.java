package br.com.contactBook.logic;
//feito por Robson Antas
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.contactBook.dao.ContactDao;
import br.com.contactBook.model.Telephone;
import br.com.contactBook.model.User;

public class CreateContact implements Logica {
	
	@Override
	public String executaRetorno(HttpServletRequest request, HttpServletResponse response) {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		String ddd_1 = request.getParameter("ddd_1");
		String tel_1 = request.getParameter("tel_1");
		String tipo_1 = request.getParameter("tipo_1");
		
		String ddd_2 = request.getParameter("ddd_2");
		String tel_2 = request.getParameter("tel_2");
		String tipo_2 = request.getParameter("tipo_2");
		
		String ddd_3 = request.getParameter("ddd_3");
		String tel_3 = request.getParameter("tel_3");
		String tipo_3 = request.getParameter("tipo_3");
		
		User user = new User();
		ContactDao dao = new ContactDao();
		List<Telephone> telephones = new ArrayList<>();
		
		
		if(ddd_1.isEmpty()!=true) {
			
			Telephone telephone_1 = new Telephone();
			int ddd1 = Integer.parseInt(ddd_1);
			
			telephone_1.setDdd(ddd1);
			telephone_1.setTelephoneNumber(tel_1);
			telephone_1.setSort(tipo_1);
			telephones.add(telephone_1);
		}
		
		if(ddd_2.isEmpty()!=true) {
			Telephone telephone_2 = new Telephone();
			int ddd2 = Integer.parseInt(ddd_2);
			
			telephone_2.setDdd(ddd2);
			telephone_2.setTelephoneNumber(tel_2);
			telephone_2.setSort(tipo_2);
			telephones.add(telephone_2);
			
		}
		
		if(ddd_3.isEmpty()!=true) {
			Telephone telephone_3 = new Telephone();
			int ddd3 = Integer.parseInt(ddd_3);
			
			telephone_3.setDdd(ddd3);
			telephone_3.setTelephoneNumber(tel_3);
			telephone_3.setSort(tipo_3);
			telephones.add(telephone_3);		
		}
		
		user.setName(nome);
		user.setEmail(email);
		user.setPassword(senha);
		user.setTelephones(telephones);
		
		dao.createUser(user);
		dao.addUserTelephoneData(user);
		return "jsp/CreateContact.jsp";
	}

	
		

		
	
	}


