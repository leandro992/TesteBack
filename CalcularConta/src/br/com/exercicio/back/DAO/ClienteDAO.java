package br.com.exercicio.back.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.exercicio.back.bean.Cliente;
import br.com.exercicio.conexaobanco.ConnectionManager;
import br.com.exercicio.utils.Utils;

/**
 * Classe DAO da entidade <code>Cliente</code> no banco de dados
 * @author victor leandro
 *
 */
public class ClienteDAO {

	public void incluirCliente(Cliente cliente) throws SQLException{
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String  sql = "INSERT INTO TB_CUSTOMER_ACCOUNT(id_customer, cpf_cnpj, nm_customer, is_active, vl_total) VALUES (SQ_CUSTOMER.NEXTVAL, ?,?,?,?)";
			
			PreparedStatement stmtInsert = conn.prepareStatement(sql);
			
			stmtInsert.setInt(1, cliente.getId() );
			stmtInsert.setInt(2, cliente.getCpf_cnpj());
			stmtInsert.setString(3, cliente.getNome());
			stmtInsert.setInt(4, cliente.getAtivo());
			stmtInsert.setDouble(5, cliente.getValor());
			
			stmtInsert.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o banco de dados!", e);
			
		}finally {
			if (conn != null) { // se ha uma conexao, fecha ela.
				try {
					
					// fechar a conexao com SGBDR. 
					conn.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
					throw new SQLException(
							"Erro ao fechar a conexao com o banco de dados!", e2);
				}
			}
		}
		
	}
	
	
	
	public boolean MediaSalarial() throws SQLException{
		
		
		Connection conn = null; // conexao com o SGBDR
		try {
		
		  // obtem a conexao com SGBDR 	
			
			conn = ConnectionManager.getInstance().getConnection();
			
			PreparedStatement stmtSelect = conn.prepareStatement("SELECT ROUND(avg(vl_total)) FROM TB_CUSTOMER_ACCOUNT where ID_CUSTOMER BETWEEN 1500 and 2700 and VL_TOTAL > 560;");
			
			ResultSet rs = stmtSelect.executeQuery();
			
			while (rs.next()){
				
				Cliente cliente = new Cliente(); 
				// pega o valor da coluna e coloca na propriedade do objeto 
				//Integer id = rs.getInt("ID_customer");
				Double valor = rs.getDouble("vl_total");
				
				System.out.println("\t[" + valor + "]");
				
			}
			
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    throw new SQLException("Erro ao conectar ou manipular o banco de dados!", ex);
		}finally {
			if (conn != null) { // se ha uma conexao, fecha ela
				try {
					// Fecha a conexao com o SGBDR
					conn.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new SQLException("Erro ao fechar a conexao com o banco de dados!", e);
				}
			}
		}
		return false;
		
	}
	
	
	
	public boolean buscaDecrescente() throws SQLException {
		Connection conn = null; // conexao com o SGBDR
	      try {
	    	  conn = ConnectionManager.getInstance().getConnection();
	  		
	    	  
	  		PreparedStatement stmtselect = conn.prepareStatement("Select * from tb_customer_account "
	  				+ "order by vl_total desc, nm_customer asc");
	  		
	  		 ResultSet rs = stmtselect.executeQuery();
	  		Utils utils = new Utils();
	  		
	  		 System.out.println("Registros dos Clientes");
	  		 
	  		 while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer cpf_cnpj = rs.getInt("cpf_cnpj");
				String nome = rs.getString("nome");
				int  ativo  = rs.getInt("Ativo");
				Double valor = rs.getDouble("Motor");
				
			
				System.out.println("\t["+ id +", "+ cpf_cnpj +", "+ nome +", "+ utils.traduzBoolean(ativo) + ", "+ valor +"]");
				
			}
	  		 
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ao banco");
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("Desconectado");
				} catch (Exception e2) {
					System.err.println("Erro ao fechar o banco"+ e2.getMessage());
				}
			}
			
		}
			return false;
			
		}
	
	
	
	
	
	
	
	
	
	
	
}
