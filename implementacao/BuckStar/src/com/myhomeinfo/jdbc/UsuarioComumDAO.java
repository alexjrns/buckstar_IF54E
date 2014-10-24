package com.myhomeinfo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myhomeinfo.entidades.Vendedor;

public class UsuarioComumDAO{
	private Connection con = Conexao.getConnection();
	
	private boolean executaComando(String comando){
		try {
			PreparedStatement preparador = con.prepareStatement(comando);

			preparador.execute();
			preparador.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de execucao de comando: " + e.getMessage() + "\n" + "Comando com erro: " + comando);
			return false;
		}		
	}
	
	private ResultSet executaComandoRetorno(String comando){
		try{
			PreparedStatement preparador = con.prepareStatement(comando);
			return preparador.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL: " + e.getMessage() + "\n" + "Comando com erro: " + comando);
			return null;
		}		
	}	
	
	public boolean remover(String tabela, String condicao){
		String sql = "DELETE FROM "+ tabela + " WHERE (" + condicao + ");";
		return executaComando(sql);
	}
	
	public int codAtual(String tabela){
		String sql = "SELECT MAX(cod_"+ tabela +") FROM " + tabela + ";";
			ResultSet resultado = executaComandoRetorno(sql);
			try{
				resultado.next();
				return (resultado.getInt(1) + 1);
			}catch (SQLException e) {
				System.out.println("Erro no comando SQL de obtencao codigo: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
				return 0;
			}

	}

	public boolean alterar(String tabela, String[][] campos){
		String sql = "UPDATE " + tabela + " SET ";

		/* Varre o Array e adiciona o nome do campo e o valor ao comando SQL*/
		for(int i = 0; i < campos.length; i++){
			sql += campos[i][0];
			sql += " = ";
			sql += "'" + campos[i][1] + "'";
			if(i != (campos.length -1))
				sql += ", ";
		}
		sql += (" WHERE (" + tabela + ".cod_" + tabela + " = " + campos[0][1] + ");"); 
		return executaComando(sql);
	}
	
	public boolean cadastrar(String tabela, String[][] campos){
		String sql = "INSERT INTO " + tabela + "(";

		for(int i = 0; i < campos.length; i++){
			sql += campos[i][0];
			if(i != (campos.length -1))
				sql += ", "; 
		}

		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		int codg = usuDAO.codAtual(tabela);
		sql += ") VALUES (DEFAULT, " + codg + ", ";

		for(int i = 2; i < campos.length; i++){
			sql += "'" + campos[i][1] + "'";
			if(i != (campos.length -1))
				sql += ", ";
		}
		sql += ");";
		return executaComando(sql);
	}

	public ArrayList<String> buscarTodos(String[] campos, String tabela){
		String sql = "SELECT "; //id_usuario, cod_usuario, des_nome, val_login, val_senha FROM usuario ORDER BY cod_usuario ASC;";
		for(int i = 0; i < campos.length; i++){
			sql += campos[i];
			if(i != (campos.length -1))
				sql += ", ";			
		}
		sql += " FROM " + tabela;

		ArrayList<String> vet = new ArrayList<String>();
		try {
			ResultSet resultado = executaComandoRetorno(sql);
			int cont = 0;
			while(resultado.next()){
				vet.add(resultado.getString(cont));
				cont++;
			}
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
			return null;
		}
		return vet;
	}
	
	/* Temporários */
	
	public List<Vendedor> buscarTodosVendedores(){
		String sql = "SELECT vendedor.id_vendedor, vendedor.cod_vendedor, vendedor.des_nome, vendedor.des_cidade, vendedor.des_uf, vendedor.val_fone, vendedor.val_email FROM vendedor;";
		List<Vendedor> lista = new ArrayList<Vendedor>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Vendedor ven = new Vendedor();
				ven.setCodigo(resultado.getInt("cod_vendedor"));
				ven.setNome(resultado.getString("des_nome"));
				ven.setCidade(resultado.getString("des_cidade"));
				ven.setEstado(resultado.getString("des_uf"));
				ven.setFone(resultado.getString("val_fone"));
				ven.setEmail(resultado.getString("val_email"));

				lista.add(ven);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;
	}
	
	public Vendedor buscarVendedor(int id){
		String sql = "SELECT vendedor.id_vendedor, vendedor.cod_vendedor, vendedor.des_nome, vendedor.des_cidade, vendedor.des_uf, vendedor.val_fone, vendedor.val_email, vendedor.fk_representante FROM vendedor WHERE (vendedor.cod_vendedor = '" + id + "');";
		Vendedor ven = new Vendedor();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			resultado.next();
			ven.setCodigo(resultado.getInt("cod_vendedor"));
			ven.setCodigoRepresentante(resultado.getInt("fk_representante"));
			ven.setNome(resultado.getString("des_nome"));
			ven.setCidade(resultado.getString("des_cidade"));
			ven.setEstado(resultado.getString("des_uf"));
			ven.setFone(resultado.getString("val_fone"));
			ven.setEmail(resultado.getString("val_email"));

			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return ven;	
	}
/*
	public List<Cliente> buscarTodosClientes(){
		String sql = "SELECT cliente.id_cliente, cliente.cod_cliente, cliente.des_razaosocial, cliente.des_nomefantasia, cliente.val_cnpj, cliente.val_inscricaoestadual, cliente.dat_datadecadastro, cliente.opt_clientedesativado, cliente.opt_clientemanutencao, cliente.end_logradouro, cliente.end_numero, cliente.end_complemento, cliente.end_bairro,   cliente.end_cep,  cliente.end_cidade, cliente.end_uf, cliente.des_contato, cliente.val_fone, cliente.val_email, cliente.fk_vendedor, cliente.fk_representante FROM cliente;";
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Cliente cli = new Cliente();

				cli.setCodigo(resultado.getInt("cod_cliente"));
				cli.setRazaoSocial(resultado.getString("des_razaosocial"));
				cli.setNomeFantasia(resultado.getString("des_nomefantasia"));
				cli.setCnpj(resultado.getString("val_cnpj"));
				cli.setInscricaoEstadual(resultado.getString("val_inscricaoestadual"));
				java.sql.Date data = new java.sql.Date(resultado.getDate("dat_datadecadastro").getTime());				
				java.util.Date dtFinal = new java.util.Date(data.getTime()); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtFinal);
				cli.setDataCadastro(cal);

				if(resultado.getString("opt_clientedesativado").equals("Sim"))
					cli.setDesativado(true);
				else if(resultado.getString("opt_clientedesativado").equals("Nao"))
					cli.setDesativado(false);

				if(resultado.getString("opt_clientemanutencao").equals("Sim"))
					cli.setManutencao(true);
				else if(resultado.getString("opt_clientemanutencao").equals("Nao"))
					cli.setManutencao(false);

				cli.setLogradouro(resultado.getString("end_logradouro"));
				cli.setNumero(resultado.getString("end_numero"));
				cli.setComplemento(resultado.getString("end_complemento"));
				cli.setBairro(resultado.getString("end_bairro"));
				cli.setCep(resultado.getString("end_cep"));
				cli.setCidade(resultado.getString("end_cidade"));
				cli.setEstado(resultado.getString("end_uf"));
				cli.setContato(resultado.getString("des_contato"));
				cli.setFone(resultado.getString("val_fone"));
				cli.setEmail(resultado.getString("val_email"));
				cli.setCdgRepresentante(resultado.getInt("fk_representante"));
				cli.setCdgVendedor(resultado.getInt("fk_vendedor"));
				lista.add(cli);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;
	}	
	
	public Cliente buscarCliente(int id){
		String sql = "SELECT cliente.id_cliente, cliente.cod_cliente, cliente.des_razaosocial, cliente.des_nomefantasia, cliente.val_cnpj, cliente.val_inscricaoestadual, cliente.dat_datadecadastro, cliente.opt_clientedesativado, cliente.opt_clientemanutencao, cliente.end_logradouro, cliente.end_numero, cliente.end_complemento, cliente.end_bairro,   cliente.end_cep,  cliente.end_cidade, cliente.end_uf, cliente.des_contato, cliente.val_fone, cliente.val_email, cliente.fk_vendedor, cliente.fk_representante FROM cliente WHERE (cliente.cod_cliente = ?);";
		Cliente cli = new Cliente();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();
			resultado.next();

			cli.setCodigo(resultado.getInt("cod_cliente"));
			cli.setRazaoSocial(resultado.getString("des_razaosocial"));
			cli.setNomeFantasia(resultado.getString("des_nomefantasia"));
			cli.setCnpj(resultado.getString("val_cnpj"));
			cli.setInscricaoEstadual(resultado.getString("val_inscricaoestadual"));
			java.sql.Date data = new java.sql.Date(resultado.getDate("dat_datadecadastro").getTime());				
			java.util.Date dtFinal = new java.util.Date(data.getTime()); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(dtFinal);
			cli.setDataCadastro(cal);

			if(resultado.getString("opt_clientedesativado").equals("Sim"))
				cli.setDesativado(true);
			else if(resultado.getString("opt_clientedesativado").equals("Nao"))
				cli.setDesativado(false);

			if(resultado.getString("opt_clientemanutencao").equals("Sim"))
				cli.setManutencao(true);
			else if(resultado.getString("opt_clientemanutencao").equals("Nao"))
				cli.setManutencao(false);

			cli.setLogradouro(resultado.getString("end_logradouro"));
			cli.setNumero(resultado.getString("end_numero"));
			cli.setComplemento(resultado.getString("end_complemento"));
			cli.setBairro(resultado.getString("end_bairro"));
			cli.setCep(resultado.getString("end_cep"));
			cli.setCidade(resultado.getString("end_cidade"));
			cli.setEstado(resultado.getString("end_uf"));
			cli.setContato(resultado.getString("des_contato"));
			cli.setFone(resultado.getString("val_fone"));
			cli.setEmail(resultado.getString("val_email"));
			cli.setCdgRepresentante(resultado.getInt("fk_representante"));
			cli.setCdgVendedor(resultado.getInt("fk_vendedor"));

			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return cli;
	}	
*/
}
