CREATE OR REPLACE FUNCTION hist_clienteDEL() RETURNS TRIGGER AS
$$
BEGIN
	INSERT INTO hist_cliente(id_clientehist, id_cliente, cod_cliente, des_razaosocial, des_nomefantasia, val_cnpj, val_inscricaoestadual, dat_datadecadastro, opt_clientedesativado, opt_clientemanutencao, end_logradouro, end_numero, end_complemento, end_bairro, end_cep, end_cidade, end_uf, des_contato, val_fone, val_email, fk_vendedor, fk_representante, dat_dataalteracao, tim_horaalteracao, des_usuarioalteracao, des_tipoalteracao) VALUES(DEFAULT, OLD.id_cliente, OLD.cod_cliente, OLD.des_razaosocial, OLD.des_nomefantasia, OLD.val_cnpj, OLD.val_inscricaoestadual, OLD.dat_datadecadastro, OLD.opt_clientedesativado, OLD.opt_clientemanutencao, OLD.end_logradouro, OLD.end_numero, OLD.end_complemento, OLD.end_bairro, OLD.end_cep, OLD.end_cidade, OLD.end_uf, OLD.des_contato, OLD.val_fone, OLD.val_email, OLD.id_clientehist, OLD.fk_vendedor, OLD.fk_representante, 'DEL', NOW(), NOW(), CURRENT_USER);
	RETURN OLD;
END;
$$
LANGUAGE 'plpgsql'