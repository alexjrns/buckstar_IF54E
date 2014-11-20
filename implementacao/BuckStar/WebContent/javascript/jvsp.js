var cont = 1;

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

function mostrarExc(cod){
	console.log("valor do código: " + cod);
	$('#excAlert').modal('show');
}

function adicionaProduto(ele){
	cont++;
	var no = "<div id='item" + cont + "' class='itemprodutos'>" +
		"<label for='txtprodutos" + cont + "' class='linha'>Produto</label>" +
		"<select id='txtprodutos' name='txtprodutos" + cont + "'>" +
		"	<c:forEach items='${requestScope.listaPro}' var='u'>" +
		"		<option value='${u.codigo}'>${u.nome}</option>" +
		"	</c:forEach>" +
		"</select>" +
		"<label for='quantidade" + cont + "' class='linha'>Quantidade</label>" +
		"<input type='number' name='quantidade" + cont + "' id='quantidade' class='quantidade' min='1' max='9999' />" +
		"<label for='valor" + cont + "' class='linha'>Valor</label>" +
		"<input type='text' name='valor" + cont + "' id='valor' class='quantidade' />" +
		"<button type='button' class='btn btn-primary' onclick='adicionaProduto();'>+</button>" +							
	"</div>";
	var ant = ele.parentNode.parentNode; 
	console.log(ant);
	//ant.insertAdjacentHTML('beforeend', no);
	ant.innerHTML += no;
}

$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
})