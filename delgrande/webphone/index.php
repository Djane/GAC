<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="webphone.css" rel="stylesheet" type="text/css">
		<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojo/dojo.xd.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/DGWebPhone.js"></script>
		<script type="text/javascript">
			function dgContinuar() {
				dgEnviar("nome", document.getElementById("dgNome").value);
				dgEnviar("fone", document.getElementById("dgTelefone").value);
				dgEnviar("email", document.getElementById("dgEmail").value);
				window.location.href = "webphone.php";
			}
		</script>
	</head>
	<body>
		<table class="webphone">
			<tr>
				<td class="topLeft">&nbsp;</td>
				<td class="topCenterLeft">&nbsp;</td>
				<td class="topCenterRight">&nbsp;</td>
				<td class="topRight">&nbsp;</td>
			</tr>
			<tr>
				<td style="height: 100%" class="centerLeft">&nbsp;</td>
				<td colspan="3" style="padding: 10px; text-align:center">
					<div class="texto"><p>Para melhorar seu atendimento, por favor informe seu nome, e-mail e telefone.</p>
					
					<table style="margin-left:auto;margin-right:auto;margin-top:15px">
						<tr>
							<td style="text-align:right">Nome:</td>
							<td style="text-align:left"><input type="text" id="dgNome" style="width:200px"></td>
						</tr>
						<tr>
							<td style="text-align:right">Telefone c/ DDD:</td>
							<td style="text-align:left"><input type="text" id="dgTelefone" style="width:110px;"></td>
						</tr>
						<tr>
							<td style="text-align:right">E-Mail:</td>
							<td style="text-align:left"><input type="email" id="dgEmail" style="width:200px;"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" value="Continuar" onclick="dgContinuar();"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
