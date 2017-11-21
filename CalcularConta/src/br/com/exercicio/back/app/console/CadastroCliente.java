/*
 *===== ***************************** ATENCAO ***************************** ====== 
 * Antes de executar este programa execute estas SQLs no banco de dados Oracle
================================================================================
drop SEQUENCE SQ_CUSTOMER;
drop table TB_CUSTOMER_ACCOUNT;

 ---CREATE
  
create table TB_CUSTOMER_ACCOUNT(
	id_customer integer not null,
	cpf_cnpj NUMBER(14)  null,
    nm_customer VARCHAR2(50) not null,
	is_active NUMBER(1) default 0 not null,
    vl_total NUMBER(9,2),
	 primary key (id_customer)
);

CREATE SEQUENCE SQ_CUSTOMER INCREMENT BY 1 START WITH 1 NOCYCLE ORDER;
 */
package br.com.exercicio.back.app.console;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.exercicio.back.DAO.ClienteDAO;
import br.com.exercicio.back.bean.Cliente;



public class CadastroCliente {

	/**
	 * Metodo principal do programa - (fluxo de execucao do programa)
	 * @param args Os parametros de execucao do programa
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		//para a entrada de dados
		Scanner tec = new Scanner(System.in);
		
		//Interface e fluxo do programa
		
		
		System.out.println("***** *** * PROGRAMA PARA CADASTRO DE CLIENTE * *** *****");
		
		 ClienteDAO clientedao = new ClienteDAO();
		 
		
		System.out.print("Nome: ");
		String nome = tec.next();
		nome += tec.nextLine();
		
		System.out.print("Digite o seu CPF ou CNPJ : ");
	    int cpf_cnpj = tec.nextInt();
		
       System.out.println("Digite o seu valor que voce quer depositar ");
       double valor = tec.nextDouble();
       
       System.out.println("Digite se o cliente estar ativo 1 para ativo ou o para Inativo deixe vazio");
       int ativo = tec.nextInt();
       
     
       
       try {
    	  
    	   Cliente cliente = new Cliente();
    	   
    	   cliente.setNome(nome);
    	   cliente.setCpf_cnpj(cpf_cnpj);
           cliente.setValor(valor);
           cliente.setAtivo(ativo);
           
           clientedao.incluirCliente(cliente);
           
           
           System.out.println("O cliente " + cliente.getNome() + "foi incluido com sucesso!");
           
           System.out.println( clientedao.MediaSalarial());
           System.out.println( clientedao.buscaDecrescente());
           
           
           
	} catch (Exception e) {
		//menssagem de erro exibida se o Cliente nao for incluido
		System.err.println("ERRO - aluno nao incluido: " + e.getMessage());
		
	}
       
	    
	}

}
