package com.myhomeinfo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.myhomeinfo.entidades.Entrada;
import com.myhomeinfo.entidades.Fornecedor;
import com.myhomeinfo.entidades.Produto;
import com.myhomeinfo.entidades.Saida;
import com.myhomeinfo.entidades.Usuario;

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

	public boolean abreTransacao(){
		String sql = "BEGIN";
		return executaComando(sql);
	}

	public boolean commit(){
		String sql = "COMMIT;";
		return executaComando(sql);
	}
	
	public boolean rollback(){
		String sql = "ROLLBACK;";
		return executaComando(sql);
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
		String sql = "SELECT ";
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
	public Usuario buscarUsuario(int id){
		String sql = "SELECT id_usuario, cod_usuario, des_nome, val_login, val_senha FROM usuario WHERE (usuario.cod_usuario = ?) ORDER BY cod_usuario ASC;";
		Usuario usr = new Usuario();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			resultado.next();
			
			usr.setCodigo(resultado.getInt("cod_usuario"));
			usr.setNome(resultado.getString("des_nome"));
			usr.setLogin(resultado.getString("val_login"));
			usr.setSenha(resultado.getString("val_senha"));
			
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return usr;
	}
	
	public List<Usuario> buscarUsuarioTodos(){
		String sql = "SELECT id_usuario, cod_usuario, des_nome, val_login, val_senha FROM usuario ORDER BY cod_usuario ASC;";
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Usuario usu = new Usuario();
				usu.setCodigo(resultado.getInt("cod_usuario"));
				usu.setNome(resultado.getString("des_nome"));
				usu.setLogin(resultado.getString("val_login"));
				usu.setSenha(resultado.getString("val_senha"));
				lista.add(usu);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;		
	}
	
	public Produto buscarProduto(int id){
		String sql = "SELECT produto.id_produto, produto.cod_produto, produto.des_nome, produto.des_marca, produto.dat_ultimacompra, produto.des_unidadecompra, produto.des_unidadetransmissao, produto.des_descricaouso, produto.val_quantidadeatual, produto.val_quantidaderecomendada, produto.val_quantidademinima, produto.val_codigobarras, produto.val_valormediocompra, produto.opt_desativado FROM produto WHERE (produto.cod_produto = '" + id + "');";	
		Produto prod = new Produto();
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			resultado.next();
			prod.setChave(resultado.getInt("id_produto"));
			prod.setCodigo(resultado.getInt("cod_produto"));
			prod.setNome(resultado.getString("des_nome"));
			
			prod.setMarca(resultado.getString("des_marca"));
			
			java.sql.Date data = new java.sql.Date(resultado.getDate("dat_ultimacompra").getTime());
			java.util.Date dtFinal = new java.util.Date(data.getTime()); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(dtFinal);
			prod.setDataUltimaCompra(cal);

			prod.setUnidadeCompra(resultado.getString("des_unidadecompra"));
			prod.setUnidadeTransmissao(resultado.getString("des_unidadetransmissao"));
			prod.setDescricaoUso(resultado.getString("des_descricaouso"));
			prod.setQuantidadeAtual(Double.parseDouble(resultado.getString("val_quantidadeatual")));
			prod.setQuantidadeRecomendada(Double.parseDouble(resultado.getString("val_quantidaderecomendada")));
			prod.setQuantidadeMinima(Double.parseDouble(resultado.getString("val_quantidademinima")));
			prod.setCodigoBarras(resultado.getString("val_codigobarras"));
			prod.setValorMedioCompra(Double.parseDouble(resultado.getString("val_valormediocompra")));
			
			if(resultado.getString("opt_desativado").equals("Sim"))
				prod.setDesativado(true);	
			else if(resultado.getString("opt_desativado").equals("Nao"))
				prod.setDesativado(false);

			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}		
		
		return prod;
	}
	
	public List<Produto> buscarTodosProdutos(){
		String sql = "SELECT produto.id_produto, produto.cod_produto, produto.des_nome, produto.des_marca, produto.dat_ultimacompra, produto.des_unidadecompra, produto.des_unidadetransmissao, produto.des_descricaouso, produto.val_quantidadeatual, produto.val_quantidaderecomendada, produto.val_quantidademinima, produto.val_codigobarras, produto.val_valormediocompra, produto.opt_desativado FROM public.produto;";
		List<Produto> lista = new ArrayList<Produto>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Produto prod = new Produto();
				prod.setChave(resultado.getInt("id_produto"));
				prod.setCodigo(resultado.getInt("cod_produto"));
				prod.setNome(resultado.getString("des_nome"));
				
				prod.setMarca(resultado.getString("des_marca"));
				
				java.sql.Date data = new java.sql.Date(resultado.getDate("dat_ultimacompra").getTime());
				java.util.Date dtFinal = new java.util.Date(data.getTime()); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtFinal);
				prod.setDataUltimaCompra(cal);

				prod.setUnidadeCompra(resultado.getString("des_unidadecompra"));
				prod.setUnidadeTransmissao(resultado.getString("des_unidadetransmissao"));
				prod.setDescricaoUso(resultado.getString("des_descricaouso"));
				prod.setQuantidadeAtual(Double.parseDouble(resultado.getString("val_quantidadeatual")));
				prod.setQuantidadeRecomendada(Double.parseDouble(resultado.getString("val_quantidaderecomendada")));
				prod.setQuantidadeMinima(Double.parseDouble(resultado.getString("val_quantidademinima")));
				prod.setCodigoBarras(resultado.getString("val_codigobarras"));
				prod.setValorMedioCompra(Double.parseDouble(resultado.getString("val_valormediocompra")));
				
				if(resultado.getString("opt_desativado").equals("Sim"))
					prod.setDesativado(true);	
				else if(resultado.getString("opt_desativado").equals("Nao"))
					prod.setDesativado(false);

				lista.add(prod);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;
	}
	
	public List<Produto> buscarProdutosParam(String sql){
		List<Produto> lista = new ArrayList<Produto>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Produto prod = new Produto();
				prod.setChave(resultado.getInt("id_produto"));
				prod.setCodigo(resultado.getInt("cod_produto"));
				prod.setNome(resultado.getString("des_nome"));
				
				prod.setMarca(resultado.getString("des_marca"));
				
				java.sql.Date data = new java.sql.Date(resultado.getDate("dat_ultimacompra").getTime());
				java.util.Date dtFinal = new java.util.Date(data.getTime()); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtFinal);
				prod.setDataUltimaCompra(cal);

				prod.setUnidadeCompra(resultado.getString("des_unidadecompra"));
				prod.setUnidadeTransmissao(resultado.getString("des_unidadetransmissao"));
				prod.setDescricaoUso(resultado.getString("des_descricaouso"));
				prod.setQuantidadeAtual(Double.parseDouble(resultado.getString("val_quantidadeatual")));
				prod.setQuantidadeRecomendada(Double.parseDouble(resultado.getString("val_quantidaderecomendada")));
				prod.setQuantidadeMinima(Double.parseDouble(resultado.getString("val_quantidademinima")));
				prod.setCodigoBarras(resultado.getString("val_codigobarras"));
				prod.setValorMedioCompra(Double.parseDouble(resultado.getString("val_valormediocompra")));
				
				if(resultado.getString("opt_desativado").equals("Sim"))
					prod.setDesativado(true);	
				else if(resultado.getString("opt_desativado").equals("Nao"))
					prod.setDesativado(false);

				lista.add(prod);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
			return lista;
	}

	public List<Produto> buscarTodosProdutosFalta(){
		return buscarProdutosParam("SELECT produto.id_produto, produto.cod_produto, produto.des_nome, produto.des_marca, produto.dat_ultimacompra, produto.des_unidadecompra, produto.des_unidadetransmissao, produto.des_descricaouso, produto.val_quantidadeatual, produto.val_quantidaderecomendada, produto.val_quantidademinima, produto.val_codigobarras, produto.val_valormediocompra, produto.opt_desativado FROM public.produto WHERE (produto.val_quantidadeatual < produto.val_quantidademinima);");
	}	

	public List<Produto> buscarTodosProdutosAbaixo(){
		return buscarProdutosParam("SELECT produto.id_produto, produto.cod_produto, produto.des_nome, produto.des_marca, produto.dat_ultimacompra, produto.des_unidadecompra, produto.des_unidadetransmissao, produto.des_descricaouso, produto.val_quantidadeatual, produto.val_quantidaderecomendada, produto.val_quantidademinima, produto.val_codigobarras, produto.val_valormediocompra, produto.opt_desativado FROM public.produto WHERE (produto.val_quantidadeatual < produto.val_quantidaderecomendada);");
	}

	public List<Produto> buscarTodosProdutosNormais(){
		return buscarProdutosParam("SELECT produto.id_produto, produto.cod_produto, produto.des_nome, produto.des_marca, produto.dat_ultimacompra, produto.des_unidadecompra, produto.des_unidadetransmissao, produto.des_descricaouso, produto.val_quantidadeatual, produto.val_quantidaderecomendada, produto.val_quantidademinima, produto.val_codigobarras, produto.val_valormediocompra, produto.opt_desativado FROM public.produto WHERE ((produto.val_quantidadeatual >= produto.val_quantidaderecomendada) AND (produto.val_quantidadeatual >= produto.val_quantidademinima));");
	}	
	
	public Fornecedor buscarFornecedor(int id){
		String sql = "SELECT fornecedor.id_fornecedor, fornecedor.cod_fornecedor, fornecedor.des_razaosocial, fornecedor.des_nomefantasia, fornecedor.val_cnpj, fornecedor.val_ie, fornecedor.end_logradouro, fornecedor.end_numero, fornecedor.end_complemento, fornecedor.end_cep, fornecedor.end_cidade, fornecedor.end_estado, fornecedor.val_telefone, fornecedor.val_email, fornecedor.val_site, fornecedor.dat_cadastro, fornecedor.opt_desativado FROM public.fornecedor WHERE (cod_fornecedor = ?);";
		Fornecedor forn = new Fornecedor();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()){		
				forn.setChave(resultado.getInt("id_fornecedor"));
				forn.setCodigo(resultado.getInt("cod_fornecedor"));
				forn.setRazaoSocial(resultado.getString("des_razaosocial"));
				forn.setCNPJ(resultado.getString("val_cnpj"));
				forn.setNomeFantasia(resultado.getString("des_nomefantasia"));
				forn.setInscricaoEstadual(resultado.getString("val_ie"));
				forn.setLogradouro(resultado.getString("end_logradouro"));
				forn.setNumero(resultado.getString("end_numero"));
				forn.setComplemento(resultado.getString("end_complemento"));
				forn.setCEP(resultado.getString("end_cep"));
				forn.setCidade(resultado.getString("end_cidade"));
				forn.setEstado(resultado.getString("end_estado"));
				forn.setTelefone(resultado.getString("val_telefone"));
				forn.seteMail(resultado.getString("val_email"));
				forn.setSite(resultado.getString("val_site"));			
				java.sql.Date data = new java.sql.Date(resultado.getDate("dat_cadastro").getTime());
				
				java.util.Date dtFinal = new java.util.Date(data.getTime()); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtFinal);
				forn.setDataDeCadastro(cal);
				
				if(resultado.getString("opt_desativado").equals("Sim"))
					forn.setDesativado(true);	
				else if(resultado.getString("opt_desativado").equals("Nao"))
					forn.setDesativado(false);
			}
			
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return forn;
	}

	public Fornecedor buscarFornecedorCh(int chave){
		String sql = "SELECT fornecedor.id_fornecedor, fornecedor.cod_fornecedor, fornecedor.des_razaosocial, fornecedor.des_nomefantasia, fornecedor.val_cnpj, fornecedor.val_ie, fornecedor.end_logradouro, fornecedor.end_numero, fornecedor.end_complemento, fornecedor.end_cep, fornecedor.end_cidade, fornecedor.end_estado, fornecedor.val_telefone, fornecedor.val_email, fornecedor.val_site, fornecedor.dat_cadastro, fornecedor.opt_desativado FROM public.fornecedor WHERE (id_fornecedor = ?);";
		Fornecedor forn = new Fornecedor();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, chave);

			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()){		
				forn.setChave(resultado.getInt("id_fornecedor"));
				forn.setCodigo(resultado.getInt("cod_fornecedor"));
				forn.setRazaoSocial(resultado.getString("des_razaosocial"));
				forn.setCNPJ(resultado.getString("val_cnpj"));
				forn.setNomeFantasia(resultado.getString("des_nomefantasia"));
				forn.setInscricaoEstadual(resultado.getString("val_ie"));
				forn.setLogradouro(resultado.getString("end_logradouro"));
				forn.setNumero(resultado.getString("end_numero"));
				forn.setComplemento(resultado.getString("end_complemento"));
				forn.setCEP(resultado.getString("end_cep"));
				forn.setCidade(resultado.getString("end_cidade"));
				forn.setEstado(resultado.getString("end_estado"));
				forn.setTelefone(resultado.getString("val_telefone"));
				forn.seteMail(resultado.getString("val_email"));
				forn.setSite(resultado.getString("val_site"));			
				java.sql.Date data = new java.sql.Date(resultado.getDate("dat_cadastro").getTime());
				
				java.util.Date dtFinal = new java.util.Date(data.getTime()); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtFinal);
				forn.setDataDeCadastro(cal);
				
				if(resultado.getString("opt_desativado").equals("Sim"))
					forn.setDesativado(true);	
				else if(resultado.getString("opt_desativado").equals("Nao"))
					forn.setDesativado(false);
			}
			
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return forn;
	}	
	
	public List<Fornecedor> buscarTodosFornecedor(){
		String sql = "SELECT fornecedor.id_fornecedor, fornecedor.cod_fornecedor, fornecedor.des_razaosocial, fornecedor.des_nomefantasia, fornecedor.val_cnpj, fornecedor.val_ie, fornecedor.end_logradouro, fornecedor.end_numero, fornecedor.end_complemento, fornecedor.end_cep, fornecedor.end_cidade, fornecedor.end_estado, fornecedor.val_telefone, fornecedor.val_email, fornecedor.val_site, fornecedor.dat_cadastro, fornecedor.opt_desativado FROM public.fornecedor;";
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Fornecedor forn = new Fornecedor();
				forn.setChave(resultado.getInt("id_fornecedor"));
				forn.setCodigo(resultado.getInt("cod_fornecedor"));
				forn.setRazaoSocial(resultado.getString("des_razaosocial"));
				forn.setCNPJ(resultado.getString("val_cnpj"));
				forn.setNomeFantasia(resultado.getString("des_nomefantasia"));
				forn.setInscricaoEstadual(resultado.getString("val_ie"));
				forn.setLogradouro(resultado.getString("end_logradouro"));
				forn.setNumero(resultado.getString("end_numero"));
				forn.setComplemento(resultado.getString("end_complemento"));
				forn.setCEP(resultado.getString("end_cep"));
				forn.setCidade(resultado.getString("end_cidade"));
				forn.setEstado(resultado.getString("end_estado"));
				forn.setTelefone(resultado.getString("val_telefone"));
				forn.seteMail(resultado.getString("val_email"));
				forn.setSite(resultado.getString("val_site"));
				java.sql.Date data = new java.sql.Date(resultado.getDate("dat_cadastro").getTime());
				
				java.util.Date dtFinal = new java.util.Date(data.getTime()); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtFinal);
				forn.setDataDeCadastro(cal);

				if(resultado.getString("opt_desativado").equals("Sim"))
					forn.setDesativado(true);	
				else if(resultado.getString("opt_desativado").equals("Nao"))
					forn.setDesativado(false);

				lista.add(forn);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;
	}
	
	public List<Fornecedor> buscarTodosFornecedor(String nome){
		String sql = "SELECT fornecedor.id_fornecedor, fornecedor.cod_fornecedor, fornecedor.des_razaosocial, fornecedor.des_nomefantasia, fornecedor.val_cnpj, fornecedor.val_ie, fornecedor.end_logradouro, fornecedor.end_numero, fornecedor.end_complemento, fornecedor.end_cep, fornecedor.end_cidade, fornecedor.end_estado, fornecedor.val_telefone, fornecedor.val_email, fornecedor.val_site, fornecedor.dat_cadastro, fornecedor.opt_desativado FROM public.fornecedor WHERE (des_razaosocial LIKE ?);";
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%" + nome + "%");
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Fornecedor forn = new Fornecedor();
				forn.setChave(resultado.getInt("id_fornecedor"));
				forn.setCodigo(resultado.getInt("cod_fornecedor"));
				forn.setRazaoSocial(resultado.getString("des_razaosocial"));
				forn.setCNPJ(resultado.getString("val_cnpj"));
				forn.setNomeFantasia(resultado.getString("des_nomefantasia"));
				forn.setInscricaoEstadual(resultado.getString("val_ie"));
				forn.setLogradouro(resultado.getString("end_logradouro"));
				forn.setNumero(resultado.getString("end_numero"));
				forn.setComplemento(resultado.getString("end_complemento"));
				forn.setCEP(resultado.getString("end_cep"));
				forn.setCidade(resultado.getString("end_cidade"));
				forn.setEstado(resultado.getString("end_estado"));
				forn.setTelefone(resultado.getString("val_telefone"));
				forn.seteMail(resultado.getString("val_email"));
				forn.setSite(resultado.getString("val_site"));	
				java.sql.Date data = new java.sql.Date(resultado.getDate("dat_cadastro").getTime());
				
				java.util.Date dtFinal = new java.util.Date(data.getTime()); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtFinal);
				forn.setDataDeCadastro(cal);

				if(resultado.getString("opt_desativado").equals("Sim"))
					forn.setDesativado(true);	
				else if(resultado.getString("opt_desativado").equals("Nao"))
					forn.setDesativado(false);
				lista.add(forn);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;
	}
	
	public Entrada buscarEntrada(int codigo){
		String sql = "SELECT entrada.id_entrada, entrada.cod_entrada, entrada.dat_entrada, entrada.tim_entrada, entrada.val_numeronfe, entrada.val_precopago, entrada.fk_fornecedor, entrada.fk_usuario FROM entrada WHERE entrada.cod_entrada = ?;";
		Entrada ent = new Entrada();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codigo);
			
			ResultSet resultado = preparador.executeQuery();
			resultado.next();
			ent.setChave(resultado.getInt("id_entrada"));
			ent.setId(resultado.getInt("cod_entrada"));
			
			java.sql.Date dataEntrada = new java.sql.Date(resultado.getDate("dat_entrada").getTime());
			java.util.Date dtFinalEnt = new java.util.Date(dataEntrada.getTime());
			Calendar calEnt = Calendar.getInstance();
			calEnt.setTime(dtFinalEnt);
			ent.setDataEntrada(calEnt);

			java.sql.Timestamp horaEntrada = new java.sql.Timestamp(resultado.getTimestamp("tim_entrada").getTime());
			java.util.Date hrFinalEnt = new java.util.Date(horaEntrada.getTime());
			Calendar calEntTim = Calendar.getInstance();
			calEntTim.setTime(hrFinalEnt);
			ent.setHoraEntrada(calEntTim);
			ent.setNumeroNF(resultado.getString("val_numeronfe"));
			ent.setValor(resultado.getDouble("val_precopago"));
			ent.setFornecedor(this.buscarFornecedorCh(resultado.getInt("fk_fornecedor")));
			ent.setUsuario(this.buscarUsuario(resultado.getInt("fk_usuario")));
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return ent;
	}	
	
	public Entrada buscarEntradaCh(int chave){
		String sql = "SELECT entrada.id_entrada, entrada.cod_entrada, entrada.dat_entrada, entrada.tim_entrada, entrada.val_numeronfe, entrada.val_precopago, entrada.fk_fornecedor, entrada.fk_usuario FROM entrada WHERE (entrada.id_entrada = ?);";
		Entrada ent = new Entrada();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, chave);
			
			ResultSet resultado = preparador.executeQuery();
			resultado.next();
			ent.setChave(resultado.getInt("id_entrada"));
			ent.setId(resultado.getInt("cod_entrada"));
			
			java.sql.Date dataEntrada = new java.sql.Date(resultado.getDate("dat_entrada").getTime());
			java.util.Date dtFinalEnt = new java.util.Date(dataEntrada.getTime());
			Calendar calEnt = Calendar.getInstance();
			calEnt.setTime(dtFinalEnt);
			ent.setDataEntrada(calEnt);

			java.sql.Timestamp horaEntrada = new java.sql.Timestamp(resultado.getTimestamp("tim_entrada").getTime());
			java.util.Date hrFinalEnt = new java.util.Date(horaEntrada.getTime());
			Calendar calEntTim = Calendar.getInstance();
			calEntTim.setTime(hrFinalEnt);
			ent.setHoraEntrada(calEntTim);
			ent.setNumeroNF(resultado.getString("val_numeronfe"));
			ent.setValor(resultado.getDouble("val_precopago"));
			ent.setFornecedor(this.buscarFornecedorCh(resultado.getInt("fk_fornecedor")));
			ent.setUsuario(this.buscarUsuario(resultado.getInt("fk_usuario")));
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return ent;
	}	
		
	
	public List<Entrada> buscarTodosEntrada(){
		String sql = "SELECT entrada.id_entrada, entrada.cod_entrada, entrada.dat_entrada, entrada.tim_entrada, entrada.val_numeronfe, entrada.val_precopago, entrada.fk_fornecedor, entrada.fk_usuario FROM entrada;";
		List<Entrada> lista = new ArrayList<Entrada>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Entrada ent = new Entrada();
				ent.setChave(resultado.getInt("id_entrada"));
				ent.setId(resultado.getInt("cod_entrada"));
				
				java.sql.Date dataEntrada = new java.sql.Date(resultado.getDate("dat_entrada").getTime());
				java.util.Date dtFinalEnt = new java.util.Date(dataEntrada.getTime()); 
				Calendar calEnt = Calendar.getInstance();
				calEnt.setTime(dtFinalEnt);
				ent.setDataEntrada(calEnt);
				java.sql.Timestamp horaEntrada = new java.sql.Timestamp(resultado.getTimestamp("tim_entrada").getTime());
				java.util.Date hrFinalEnt = new java.util.Date(horaEntrada.getTime());
				Calendar calEntTim = Calendar.getInstance();
				calEntTim.setTime(hrFinalEnt);
				ent.setHoraEntrada(calEntTim);
				ent.setNumeroNF(resultado.getString("val_numeronfe"));
				ent.setValor(resultado.getDouble("val_precopago"));
				ent.setFornecedor(this.buscarFornecedorCh(resultado.getInt("fk_fornecedor")));
				ent.setUsuario(this.buscarUsuario(resultado.getInt("fk_usuario")));

				lista.add(ent);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;
	}
	
	public Saida buscarSaida(int codigo){
		String sql = "SELECT saida.id_saida, saida.cod_saida, saida.dat_saida, saida.tim_saida, saida.val_saida, saida.fk_entrada, saida.fk_usuario FROM saida WHERE (saida.cod_saida = ?);";
		Saida sai = new Saida();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codigo);
			
			ResultSet resultado = preparador.executeQuery();
			resultado.next();
			
			sai.setId(resultado.getInt("cod_saida"));

			java.sql.Date dataEntrada = new java.sql.Date(resultado.getDate("dat_saida").getTime());
			java.util.Date dtFinalEnt = new java.util.Date(dataEntrada.getTime());
			Calendar calEnt = Calendar.getInstance();
			calEnt.setTime(dtFinalEnt);
			sai.setData(calEnt);
			java.sql.Timestamp horaEntrada = new java.sql.Timestamp(resultado.getTimestamp("tim_saida").getTime());
			java.util.Date hrFinalEnt = new java.util.Date(horaEntrada.getTime());
			Calendar calEntTim = Calendar.getInstance();
			calEntTim.setTime(hrFinalEnt);
			sai.setHora(calEntTim);
			sai.setValorSaida(resultado.getDouble("val_saida"));
			sai.setEntrada(this.buscarEntradaCh(resultado.getInt("fk_entrada")));
			sai.setUsuario(this.buscarUsuario(resultado.getInt("fk_usuario")));
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return sai;
	}
	
	public List<Saida> buscarTodosSaida(){
		String sql = "SELECT saida.id_saida, saida.cod_saida, saida.dat_saida, saida.tim_saida, saida.val_saida, saida.fk_entrada, saida.fk_usuario FROM saida;";
		List<Saida> lista = new ArrayList<Saida>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Saida sai = new Saida();
				//ent.setChave(resultado.getInt("id_fornecedor"));
				sai.setId(resultado.getInt("cod_saida"));

				java.sql.Date dataSaida = new java.sql.Date(resultado.getDate("dat_saida").getTime());
				java.util.Date dtFinalSai = new java.util.Date(dataSaida.getTime());
				Calendar calSai = Calendar.getInstance();
				calSai.setTime(dtFinalSai);
				sai.setData(calSai);
				java.sql.Timestamp horaEntrada = new java.sql.Timestamp(resultado.getTimestamp("tim_saida").getTime());
				java.util.Date hrFinalEnt = new java.util.Date(horaEntrada.getTime());
				Calendar calEntTim = Calendar.getInstance();
				calEntTim.setTime(hrFinalEnt);
				sai.setHora(calEntTim);
				sai.setValorSaida(resultado.getDouble("val_saida"));
				sai.setEntrada(this.buscarEntradaCh(resultado.getInt("fk_entrada")));
				sai.setUsuario(this.buscarUsuario(resultado.getInt("fk_usuario")));

				lista.add(sai);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;		
	}
}
