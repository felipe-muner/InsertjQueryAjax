package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Pessoa;

public class PessoaDao extends Dao{

	public void alterar(Pessoa p) throws Exception {
		open();
		
		stmt = con.prepareStatement("UPDATE BANCO SET NOME = ?, IDADE = ?, CIDADE = ? WHERE ID = ?");
        stmt.setString(1, p.getNome());
        stmt.setInt(2, p.getIdade());
        stmt.setString(3, p.getCidade());
        stmt.setInt(4, p.getId());
        stmt.execute();		
		close();
	}
	
	public void incluir(Pessoa p) throws Exception {
		open();
		
		stmt = con.prepareStatement("INSERT INTO BANCO VALUES(NULL, ?, ?, ?)");
        stmt.setString(1, p.getNome());
        stmt.setInt(2, p.getIdade());
        stmt.setString(3, p.getCidade());
        stmt.execute();		
		close();
	}
	
	public List<Pessoa> findAll() throws Exception{
		open();
		List<Pessoa> listap = new ArrayList<Pessoa>();
		stmt = con.prepareStatement("SELECT * FROM BANCO ORDER BY ID DESC");
		rs = stmt.executeQuery();
		while (rs.next()) {
			Pessoa p1 = new Pessoa();
			p1.setId(rs.getInt("id"));
			p1.setNome(rs.getString("nome"));
			p1.setIdade(rs.getInt("idade"));
			p1.setCidade(rs.getString("cidade"));
			listap.add(p1);
		}		
		close();
		return listap;
	}
	
	
	public static void main(String[] args) throws Exception {
		try {
			//new PessoaDao().alterar(new Pessoa(2, "lulis", 20, "mengo"));
			//System.out.println("foi");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
