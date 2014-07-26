<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="webphone.css" rel="stylesheet" type="text/css">
		<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojo/dojo.xd.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/DGWebPhone.js"></script>
		<script type="text/javascript" src="http://www.java.com/js/deployJava.js"></script>
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
					<!-- Estado 0 -->
					<div id="dgEstado0" style="display:none">
						<div class="texto"><p>Iniciando a máquina virtual JAVA e aguardando autenticação.</p></div>
						<div class="texto"><img src="images/spinner.gif"></div>
					</div>
					<!-- Estado 1 -->
					<div id="dgEstado1" style="display:none;">
						<div class="texto"><p>Voc&ecirc; n&atilde;o possui a m&aacute;quina virtual Java instalada ou n&atilde;o est&aacute; habilitada.</p></div>
						<div class="texto"><p>Se voc&ecirc; n&atilde;o possui a m&aacute;quina virtual Java instalada, <a href="http://www.java.com/pt_BR/" target="_blank">clique aqui</a>.</p></div>
					</div>
					<!-- Estado 2 -->
					<div id="dgEstado2" style="display:none">
						<div class="texto"><p>A chamada n&atilde;o poder&aacute; ser realizada pois voc&ecirc; n&atilde;o autorizou o WebPhone a acessar os dispositivos de &aacute;udio de seu computador.</p></div>
						<div class="texto"><p>Para tentar novamente voc&ecirc; precisa reiniciar seu navegador.</p></div>
						<div class="texto"><p>Voc&ecirc; tamb&eacute;m pode entrar em contato conosco atrav&eacute;s do telefone +55 (48) 3254-8600.</p></div>
					</div>
					<!-- Estado 3 -->
					<div id="dgEstado3" style="display:none;">
						<div class="texto"><p>O WebPhone n&atilde;o encontrou dispositivos de entrada e/ou sa&iacute;da de &aacute;udio em seu computador para realizar a chamada.</p></div>
						<div class="texto"><p>Voc&ecirc; tamb&eacute;m pode entrar em contato conosco através do telefone +55 (48) 3254-8600.</p></div>
					</div>
					<!-- Estado 4 -->
					<div id="dgEstado4" style="display:none;">
						<div class="texto"><p>Selecione os dispositivos de &aacute;udio para o WebPhone utilizar durante a chamada:</p></div>
						<div class="dispSaida"><select id="dgDispSaida" style="width: 250px;"></select></div>
						<div class="dispEntrada"><select id="dgDispEntrada" style="width: 250px;"></select></div>
						<div class="texto"><input type="button" value="Continuar" onclick="dgEstado(7);"></div>
					</div>
					<!-- Estado 7 -->
					<div id="dgEstado7" style="display:none;">
						<div class="texto"><p>Para agilizar seu atendimento, por favor selecione sobre qual assunto voc&ecirc; deseja falar conosco.</p></div>
						<select id="dgAssunto" class="select"></select>
						<div class="texto"><input type="button" value="Falar Conosco" onclick="dgEstado(8);"></div>
					</div>
					<!-- Estado 8 -->
					<div id="dgEstado8" style="display:none;">
						<div class="texto"><p>O WebPhone est&eacute; efetuando a chamada.</p></div>
						<div class="texto"><p>Por favor, aguarde.</p></div>
						<img src="images/spinner.gif">
					</div>
					<!-- Estado 9 -->
					<div id="dgEstado9" style="display:none;">
						<div class="texto"><p>O WebPhone n&atilde;o conseguiu estabelecer a chamada.</p></div>
						<div class="texto"><p>Voc&ecirc; pode tentar novamente ou entrar em contato conosco atrav&eacute;s do telefone +55 (48) 3254-8600.</p></div>
						<div class="texto"><input type="button" value="Tentar Novamente" onclick="dgEstado(7);"></div>
					</div>
					<!-- Estado 10 -->
					<div id="dgEstado10" style="display:none;">
						<div class="texto"><p>Nossa central telef&ocirc;nica est&aacute; no limite de chamadas simult&acirc;neas permitidas. O WebPhone tentar&aacute; novamente em 30 segundos.</p></div>
						<div class="texto"><p>Voc&ecirc; pode entrar em contato conosco atrav&eacute;s do telefone +55 (48) 3254-8600.</p></div>
						<div class="texto"><p style="display:block; text-align:center"><input type="button" value="Cancelar Pr&oacute;xima Tentativa"></p></div>
					</div>
					<!-- Estado 11 -->
					<div id="dgEstado11" style="display:none;">
						<table id="dgTeclado" style="display:none; margin-left:auto; margin-right:auto;">
							<tr>
								<td><input type="button" class="teclado" value="1" onclick="dgDTMF('1');"></td>
								<td><input type="button" class="teclado" value="2" onclick="dgDTMF('2');"></td>
								<td><input type="button" class="teclado" value="3" onclick="dgDTMF('3');"></td>
							</tr>
							<tr>
								<td><input type="button" class="teclado" value="4" onclick="dgDTMF('4');"></td>
								<td><input type="button" class="teclado" value="5" onclick="dgDTMF('5');"></td>
								<td><input type="button" class="teclado" value="6" onclick="dgDTMF('6');"></td>
							</tr>
							<tr>
								<td><input type="button" class="teclado" value="7" onclick="dgDTMF('7');"></td>
								<td><input type="button" class="teclado" value="8" onclick="dgDTMF('8');"></td>
								<td><input type="button" class="teclado" value="9" onclick="dgDTMF('9');"></td>
							</tr>
							<tr>
								<td><input type="button" class="teclado" value="*" onclick="dgDTMF('*');"></td>
								<td><input type="button" class="teclado" value="0" onclick="dgDTMF('0');"></td>
								<td><input type="button" class="teclado" value="#" onclick="dgDTMF('#');"></td>
							</tr>
						</table>
						<input type="button" value="Encerrar Chamada" onclick="dgEstado(12);">
					</div>
					<!-- Estado 12 -->
					<div id="dgEstado12" style="display:none;">
						<div class="texto"><p style="text-align: center;">Encerrando a chamada...</p></div>
						<img src="images/spinner.gif">
					</div>
					<!-- Estado 13 -->
					<div id="dgEstado13" style="display:none;">
						<div class="texto"><p>A chamada foi encerrada.</p></div>
						<div class="texto"><p>Muito obrigado pelo seu contato!</p></div>
						<div class="texto"><input type="button" value="Voltar" onclick="dgEstado(4);"></div>
					</div>
					<!-- Estado 14 -->
					<div id="dgEstado14" style="display:none;">
						<div class="texto"><p>A conex&atilde;o com nossa central telef&ocirc;nica foi encerrada.</p></div>
						<div class="texto"><input type="button" value="Voltar" onclick="dgEstado(4);"></div>
					</div>
					<!-- Sem Javascript -->
					<div>
						<noscript>
							<div class="texto"><p>Para executar o WebPhone voc&ecirc; precisa habilitar a execu&ccedil;&atilde;o do Javascript</p></div>
						</noscript>
					</div>
				</td>
			</tr>
		</table>
		<script>
			if (deployJava.versionCheck("1.6") && navigator.javaEnabled()) {
				deployJava.getBrowser();
				if (deployJava.browserName2.toLowerCase() == "opera") {
					var attributes = {
						code: "com.dg.webphone.WebPhoneApplet.class",
						archive: dgArquivo,
						width: 1,
						height: 1,
						mayscript: "",
						id: "dgWebPhone"
					};
					dgTimer = setTimeout(dgTimeoutOpera, 60000);
				} else {
					var attributes = {
						code: "com.dg.webphone.WebPhoneApplet.class",
						archive: dgArquivo,
						width: 0,
						height: 0,
						mayscript: "",
						id: "dgWebPhone"
					};
				}
				dgEstado(0);
				deployJava.runApplet(attributes, {}, "1.6");			
			} else {
				dgEstado(1);
			}
		</script>
	</body>
</html>
