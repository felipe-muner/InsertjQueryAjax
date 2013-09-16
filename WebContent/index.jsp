<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajax + jQuery + Servlet</title>
<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/estilo.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/smoothness/jquery-ui-1.10.3.custom.min.css" />
<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript">

function abrirDialog(identificador,nom,ida,cid){
	$("#id").val(identificador);
	$("#n").val(nom);
	$("#i").val(ida);
	$("#c").val(cid);
	$('div#thedialog').dialog('open');
}

$(document).ready(function() {
		$("table").hide();

		$('div#thedialog').dialog({ 
			autoOpen: false,
			modal:true,
			buttons: [ { text: "Ok", click: function() {
				var id = $("#id").val();
				var nome = $("#n").val();
				var idade = $("#i").val();
				var cidade = $("#c").val();

				$.post("/InsertjQueryAjax/Controle",   
				        {
							   cmd: "alterar",
							   id: id,
				               nome: nome,
				               idade: idade,
				               cidade: cidade         
				        },function(data){	
				        	$("table").fadeOut().empty();
				        	
				        	$("table").fadeIn('slow',function(){
				        		$("table").append('<tr><th>Nome</th><th>Idade</th><th>Cidade</th></tr>');
							 	for(var i=0;i<data.Pessoa.length;i++){
							 		$("table").append("<tr>"+'<td>'+data.Pessoa[i].nome+'</td>'+
							 					  '<td>'+data.Pessoa[i].idade+'</td>'+
							 					  '<td>'+data.Pessoa[i].cidade+'</td>'+
							 					  '<td><a href="javascript:void(0)" onclick="abrirDialog('+data.Pessoa[i].id+',\''+data.Pessoa[i].nome+'\','+data.Pessoa[i].idade+',\''+data.Pessoa[i].cidade+'\');">Editar</a></td>'+
							 				      '<td><a href="Controle?cmd=excluir&id='+data.Pessoa[i].id+'">Excluir</a>'+'</td></tr>');
							 	}
				        	});
				        });
				
				$("div#thedialog").fadeOut('fast', function() {
						  $("div#thedialog").dialog('close');
				});
				

			} } ]
		
		});
		
 		var flag = 0;
		$("#enviar").click(function(){
			var nome = $("#nome").val();
			var idade = $("#idade").val();
			var cidade = $("#cidade").val();
			
			$.post("/InsertjQueryAjax/Controle",   
			        {
						   cmd: "inserir",
			               nome: nome,
			               idade: idade,
			               cidade: cidade         
			        });
			$("#nome").val("");
			$("#idade").val("");
			$("#cidade").val("");
			flag = 0;
		});
		
		$("#listag").click(function(){
			
			$.get("/InsertjQueryAjax/Controle",
					{
						cmd: "listag"
					},
					
			        function(data){
						if(flag === 0){
							$("table").show();
							$("table").empty();	
							$("table").append('<tr><th>Nome</th><th>Idade</th><th>Cidade</th></tr>');
						 	for(var i=0;i<data.Pessoa.length;i++){
						 		$("table").append("<tr>"+'<td>'+data.Pessoa[i].nome+'</td>'+
						 					  '<td>'+data.Pessoa[i].idade+'</td>'+
						 					  '<td>'+data.Pessoa[i].cidade+'</td>'+
						 					  '<td><a href="javascript:void(0)" onclick="abrirDialog('+data.Pessoa[i].id+',\''+data.Pessoa[i].nome+'\','+data.Pessoa[i].idade+',\''+data.Pessoa[i].cidade+'\');">Editar</a></td>'+
						 				      '<td><a href="Controle?cmd=excluir&id='+data.Pessoa[i].id+'">Excluir</a>'+'</td></tr>');
						 	}
						 	flag = 1;
					 	}
			        });			
		});
		
		
	});
</script>

</head>
<body>
	<p style="text-align: center">Inclusao de maluquinho :</p>
	<hr style="width: 300px;" />
	<input type="text" placeholder="Nome" id="nome" />
	<br />
	<input type="text" placeholder="Idade" id="idade" />
	<br />
	<input type="text" placeholder="Cidade" id="cidade" />
	<br />
	<br />
	<input type="submit" value="Cadastrar" id="enviar" />
	<input type="button" value="Listagem Completa" id="listag"/>
	<table align="center" class="table table-striped table-bordered table-hover">
	</table>
	
	
	
	
<div id="thedialog" title="Editar Usuario">
	<span class="ui-icon ui-icon-note" style="float:right; margin:0 7px 50px 0;"></span>
	<form action="#" method="post">

		<input type="hidden" id="id" />
		Nome :<input type="text" name="nome" id="n"/><br />
		Idade :<input type="text" name="idade" id="i"/><br />
		Cidade :<br /><input type="text" name="cidade" id="c"/><br />

	</form>
</div>
</body>
</html>