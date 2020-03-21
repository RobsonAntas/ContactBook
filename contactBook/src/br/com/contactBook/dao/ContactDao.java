package br.com.contactBook.dao;
//feito por Robson Antas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.contactBook.connectionjdbc.DBconnect;
import br.com.contactBook.model.Telephone;
import br.com.contactBook.model.User;

public class ContactDao {
	private Connection connection;
	private long id_usuario;
	private PreparedStatement ps2;
	public ContactDao() {

	}

	public void createUser(User user) {

		try {
			this.connection = new DBconnect().getConnection();
			PreparedStatement stm_1 = connection
					.prepareStatement("insert into usuarios (nome, senha, email) values (?,?,?)");

			stm_1.setString(1, user.getName());
			stm_1.setString(2, user.getPassword());
			stm_1.setString(3, user.getEmail());

			stm_1.executeUpdate();
			stm_1.close();
			connection.close();

		} catch (Exception e) {
			throw new RuntimeException("Não foi possível inserir usuário", e);
		}

	}

	public void addUserTelephoneData(User user) {

		try {
			this.connection = new DBconnect().getConnection();
			PreparedStatement stm = connection.prepareStatement("select id from usuarios ORDER BY id DESC LIMIT 1");

			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				this.id_usuario = rs.getLong("id");

			}

			rs.close();
			stm.close();
			this.connection.close();
			this.connection = new DBconnect().getConnection();
			int tamanho = user.getTelephones().size();
			if (user.getTelephones().isEmpty()) {
				return;
			} else {
				for (int index = 0; index <= tamanho - 1; index++) {
					PreparedStatement stm_1 = this.connection
							.prepareStatement("insert into telefone (ddd,numero,tipo,id_usuario) values (?,?,?,?)");
					stm_1.setInt(1, user.getTelephones().get(index).getDdd());
					stm_1.setString(2, user.getTelephones().get(index).getTelephoneNumber());
					stm_1.setString(3, user.getTelephones().get(index).getSort());
					stm_1.setLong(4, this.id_usuario);
					stm_1.executeUpdate();
					stm_1.close();
				}

			}

			this.connection.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteUser(User user) {
		try {
			this.connection = new DBconnect().getConnection();
			PreparedStatement stm = this.connection.prepareStatement(
					"delete telefone, usuarios FROM usuarios LEFT JOIN telefone ON  telefone.id_usuario= usuarios.id where usuarios.id=?");
			stm.setLong(1, user.getId());
			stm.executeUpdate();

			stm.close();
			this.connection.close();

		} catch (Exception e) {
			throw new RuntimeException("Operação de remoção não realizada", e);
		}

	}

	public void updateUser(User user) {
		
		try {
			this.connection = new DBconnect().getConnection();
			PreparedStatement ps = this.connection.prepareStatement("update usuarios set email=? where id=?");
			ps.setString(1, user.getEmail());
			ps.setLong(2, user.getId());
			ps.executeUpdate();
			ps.close();
			this.connection.close();
			//pega todos os telefones dos usuários para serem atualizados
		    List<Telephone> updating= this.updateUsersPhoneNumber(user, this.getPhones(user.getId()));
			
			this.connection=new DBconnect().getConnection();
			int leng =updating.size();
			for(int index=0;index<=leng-1;index++) {
		    this.ps2 = this.connection.prepareStatement("update telefone set ddd=?,numero=?,tipo=? where id=?");
		    this.ps2.setInt(1, updating.get(index).getDdd());
		    this.ps2.setString(2, updating.get(index).getTelephoneNumber());
		    this.ps2.setString(3, updating.get(index).getSort());
		    this.ps2.setLong(4,updating.get(index).getId());
		    this.ps2.executeUpdate();
			}
			this.ps2.close();
			this.connection.close();		
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		}

	public List<User> getUsersHavingTelephone() {

		try {
			List<User> users = new ArrayList<>();
			this.connection = new DBconnect().getConnection();
			PreparedStatement ps = this.connection.prepareStatement(
					"select nome, email, ddd, numero, tipo, id_usuario from usuarios INNER JOIN telefone On usuarios.id=telefone.id_usuario");
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				User user = new User();
				Telephone telephone = new Telephone();
				user.setName(resultado.getString("nome"));
				user.setEmail(resultado.getString("email"));
				telephone.setDdd(resultado.getInt("ddd"));
				telephone.setTelephoneNumber(resultado.getString("numero"));
				telephone.setSort(resultado.getString("tipo"));
				telephone.setUserId(resultado.getInt("id_usuario"));
				user.setTelephone(telephone);
				users.add(user);
			}
			ps.close();
			this.connection.close();
			return users;

		} catch (Exception e) {
			throw new RuntimeException("Lista não retornada", e);
		}

	}

	public List<User> getAllUsers() {
		try {

			this.connection = new DBconnect().getConnection();
			PreparedStatement ps = this.connection.prepareStatement(
					"select usuarios.id,nome, email, ddd, numero,tipo, id_usuario from usuarios left JOIN telefone On usuarios.id=telefone.id_usuario ORDER BY usuarios.id");
			ResultSet resultado = ps.executeQuery();
			List<User> users = new ArrayList<>();
			while (resultado.next()) {
				User user = new User();
				Telephone telephone = new Telephone();
				user.setId(resultado.getLong("usuarios.id"));
				user.setName(resultado.getString("nome"));
				user.setEmail(resultado.getString("email"));
				telephone.setDdd(resultado.getInt("ddd"));
				telephone.setTelephoneNumber(resultado.getString("numero"));
				telephone.setSort(resultado.getString("tipo"));
				telephone.setUserId(resultado.getInt("id_usuario"));
				user.setTelephone(telephone);
				users.add(user);
			}
			ps.close();
			resultado.close();
			this.connection.close();
			return users;

		} catch (Exception e) {
			throw new RuntimeException("Lista não retornada", e);
		}
	}

	public List<User> getUsersNameStartingWithLetter(String letter) {
		try {
			this.connection = new DBconnect().getConnection();
			PreparedStatement ps = this.connection
					.prepareStatement("select usuarios.id, nome, email, ddd, numero, tipo from usuarios"
							+ "	 left join telefone on usuarios.id=telefone.id_usuario where nome like'" + letter + "%' order by nome");
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<>();
			while (rs.next()) {
				User user = new User();
				Telephone telephone = new Telephone();
				user.setId(rs.getLong("usuarios.id"));
				user.setName(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
				telephone.setDdd(rs.getInt("ddd"));
				telephone.setTelephoneNumber(rs.getString("numero"));
				telephone.setSort(rs.getString("tipo"));
				user.setTelephone(telephone);
				users.add(user);
			}

			rs.close();
			ps.close();
			this.connection.close();
			return users;

		} catch (Exception e) {
			throw new RuntimeException("Não foi possível realizar a busca", e);
		}
	}

	public List<User> getById(long id) {

		try {
			this.connection = new DBconnect().getConnection();
			PreparedStatement st = this.connection.prepareStatement("select * from usuarios left join telefone on "
					+ "usuarios.id=telefone.id_usuario where usuarios.id=?");
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();
			List<User> userById = new ArrayList<>();
			while (rs.next()) {
				User user = new User();
				Telephone telephone = new Telephone();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
				telephone.setDdd(rs.getInt("ddd"));
				telephone.setTelephoneNumber(rs.getString("numero"));
				telephone.setSort(rs.getString("tipo"));
				user.setTelephone(telephone);
				userById.add(user);
			}
			rs.close();
			st.close();
			this.connection.close();
			return userById;
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível realizar a busca", e);
		}
	}

	public List<User> getUserNameContaining(String wordPiece) {
		try {
			this.connection = new DBconnect().getConnection();
			PreparedStatement ps = this.connection
					.prepareStatement("select usuarios.id, nome, email, ddd, numero, tipo from usuarios"
							+ "	 left join telefone on usuarios.id=telefone.id_usuario where nome like '%" + wordPiece
							+ "%' order by nome");
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<>();
			while (rs.next()) {
				User user = new User();
				Telephone telephone = new Telephone();
				user.setId(rs.getLong("usuarios.id"));
				user.setName(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
				telephone.setDdd(rs.getInt("ddd"));
				telephone.setTelephoneNumber(rs.getString("numero"));
				telephone.setSort(rs.getString("tipo"));
				user.setTelephone(telephone);
				users.add(user);
			}

			rs.close();
			ps.close();
			this.connection.close();
			return users;

		} catch (Exception e) {
			throw new RuntimeException("Não foi possível realizar a busca", e);
		}

	}

	public List<User> getUsersHavingTelephoneByDdd(int ddd) {

		try {
			List<User> users = new ArrayList<>();
			this.connection = new DBconnect().getConnection();
			PreparedStatement ps = this.connection.prepareStatement(
					"select nome, email, ddd, numero, tipo, id_usuario from usuarios INNER JOIN telefone On usuarios.id=telefone.id_usuario where ddd=?");
			ps.setInt(1, ddd);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				User user = new User();
				Telephone telephone = new Telephone();
				user.setName(result.getString("nome"));
				user.setEmail(result.getString("email"));
				telephone.setDdd(result.getInt("ddd"));
				telephone.setTelephoneNumber(result.getString("numero"));
				telephone.setSort(result.getString("tipo"));
				telephone.setUserId(result.getInt("id_usuario"));
				user.setTelephone(telephone);
				users.add(user);
			}
			result.close();
			ps.close();
			this.connection.close();
			return users;

		} catch (Exception e) {
			throw new RuntimeException("Lista não retornada", e);
		}

	}

	public User userAuthentication(String email) {
		try {
			this.connection = new DBconnect().getConnection();
			PreparedStatement ps = this.connection.prepareStatement("select * from usuarios where email=?");
			ps.setString(1, email);
			ResultSet result = ps.executeQuery();
			
			User user = new User();
			while (result.next()) {
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("senha"));
						}
			result.close();
			ps.close();
			this.connection.close();
			return user;
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível buscar o e-mail", e);
		}
	}
//pega todos os números de telefones do usuário;
	private List<Telephone> getPhones(long id){
		try {
		List<Telephone> phones = new ArrayList<>();
		this.connection = new DBconnect().getConnection();
		PreparedStatement ps = this.connection.prepareStatement(
				"select * from telefone where id_usuario=?");
		ps.setLong(1, id);
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			Telephone phone = new Telephone();
			phone.setDdd(result.getInt("ddd"));
			phone.setTelephoneNumber(result.getString("numero"));
			phone.setSort(result.getString("tipo"));
			phone.setUserId(id);
			phone.setId(result.getLong("id"));
			phones.add(phone);
		}
		result.close();
		ps.close();
		this.connection.close();
		return phones;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List<Telephone> updateUsersPhoneNumber(User user, List<Telephone> phones) {
		try {
		this.connection= new DBconnect().getConnection();
		int tam1 = user.getTelephones().size();
		int tam2 = phones.size();
		List<Telephone> telephones = new ArrayList<>();
 		for(int i=0; i<=tam1-1;i++) {
			for(int j=0;j<=tam2-1;j++ ) {
				if(user.getTelephones().get(i).getId()==phones.get(j).getId()) {
					phones.get(j).setId(user.getTelephones().get(i).getId());
					phones.get(j).setDdd(user.getTelephones().get(i).getDdd());
					phones.get(j).setTelephoneNumber(user.getTelephones().get(i).getTelephoneNumber());
					phones.get(j).setSort(user.getTelephones().get(i).getSort());
					telephones.add(phones.get(j));
				}
			}
		}
 		this.connection.close();
 		return telephones;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
