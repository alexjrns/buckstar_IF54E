function confirmaExclusao(cod, srvlet){
	if(window.confirm("Tem certeza que deseja remover o registro: (" + cod + ") ?")){
		location.href = srvlet + cod;	
	}
}

function validaTelefone(){ 
	$("#txttelefone").mask("(00) 0000-00009");
}

function esconde(){
	$('#myLoad').modal('hide');
}

function mostrar(){
	$('#myLoad').modal('show');
	setTimeout(function(){esconde()}, 3000);			
}
