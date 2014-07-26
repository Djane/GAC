var dgRequisitarAssunto = false;
var dgNumeroDiscagem = "6403";
var dgExibirTeclado = false;
var dgArquivo = "DGWebPhone.jar";

/*
 * Copyright (c) 2010 Del Grande Informática.  Todos os direitos reservados. 
 *
 *
 * Descrição dos estados:
 *
 *   - Estado  0: Iniciando o Java
 *   - Estado  1: Erro iniciando Java
 *   - Estado  2: Sem autorização para acessar os dispositivos de áudio
 *   - Estado  3: Sem dispositivos de áudio p/ realizar a chamada
 *   - Estado  4: Lista de dispositivos de áudio
 *   - (Removido) Estado  5: Tentando conexão c/ o Intelix
 *   - (Removido) Estado  6: Falha de conexão c/ o Intelix
 *   - Estado  7: Assunto
 *   - Estado  8: Efetuando chamada
 *   - Estado  9: Falha na chamada
 *   - Estado 10: Limite de chamadas
 *   - Estado 11: Chamada em andamento
 *   - Estado 12: Encerrando chamada
 *   - Estado 13: Chamada encerrada
 *   - Estado 14: Conexão c/ o Intelix encerrada
 *
 *
 * NÃO ALTERAR NADA ABAIXO
 *
 *
*/


function dgEnviar(_tipo, _mensagem) {
	/*
	if (typeof dojo != "undefined") {
		dojo.xhrGet({
			content: {tipo: _tipo, mensagem: escape(_mensagem)},
			sync: true,
			url: "php/save.php"
		});
	}
	*/
}

var dgChamadaAtendida;
var dgDesconectar;
var dgTimer = null;

function dgEstado(estado) {
	var i;
	for (i = 0; i < 5; i++) document.getElementById("dgEstado"+i).style.display = "none";
	for (i = 7; i < 15; i++) document.getElementById("dgEstado"+i).style.display = "none";

	var webphone = document.getElementById("dgWebPhone");
	
	dgEnviar("estado", estado);
	
	switch (estado) {
		case 0:
			dgEnviar("info", "Iniciando Applet");
			break;
		case 4:
			if (parseInt(webphone.getNumDispositivosEntrada()) == 1 && parseInt(webphone.getNumDispositivosSaida()) == 1) {
				dgEstado(7);
				return;
			}
		case 5:
		case 6:
		case 7:
			var dispEntrada = document.getElementById("dgDispEntrada").value;
			var dispSaida = document.getElementById("dgDispSaida").value;
		
			webphone.setDispositivoEntrada(dispEntrada);
			webphone.setDispositivoSaida(dispSaida);
			dgEnviar("info", "Dispositivo de entrada selecionado: "+(parseInt(dispEntrada) + 1));
			dgEnviar("info", "Dispositivo de saída selecionado: "+(parseInt(dispSaida) + 1));
			if (!dgRequisitarAssunto) {
				dgEstado(8);
				return;
			} else {
				break;
			}
		case 8:
			dgEnviar("info", "Tentando realizar a chamada");
			if (dgRequisitarAssunto) dgNumeroDiscagem = document.getElementById("dgAssunto").value;
			dgTimer = setTimeout(dgTimeoutChamada, 60000);
			dgChamadaAtendida = false;
			webphone.discar(dgNumeroDiscagem);
			break;
		case 11:
			if (dgExibirTeclado) document.getElementById("dgTeclado").style.display = "";
			break;
		case 12:
			dgEnviar("info", "Encerrando a chamada");
			webphone.encerrar();
			break;
		case 13 :
			dgEnviar("completo", "");
			dgChamadaAtendida = false;
			break;
		case 14 :
			break;
	}
	document.getElementById("dgEstado"+estado).style.display = "";
}

function dgDTMF(tecla) {
	var webphone;
	
	webphone = document.getElementById("dgWebPhone");
	webphone.enviarDTMF(tecla);
}

// Timeout
function dgTimeoutOpera() {
	dgTimer = null;
	dgEstado(1);
}


function dgTimeoutChamada() {
	dgTimer = null;
	dgEnviar("erro", "A chamada não foi realizada");
	dgEstado(9);
}

function dgTimeoutLimite() {
	dgTimer = null;
	dgEnviar("info", "Tentando realizar a chamada novamente");
	dgEstado(8);
}


// Callback
function dgSemPermissao() {
	dgEnviar("erro", "Sem permissão para acessar os dispositivos");
	dgEstado(2);	
}

function dgWebPhoneStart() {
	var webphone = document.getElementById("dgWebPhone");
	dgEnviar("info", "Applet iniciado. Permissão concedida.");
	
	if (dgTimer != null) clearTimeout(dgTimer);
	
	var numDispositivosEntrada = parseInt(webphone.getNumDispositivosEntrada());
	var numDispositivosSaida = parseInt(webphone.getNumDispositivosSaida());
	dgEnviar("info", "Dispositivos de entrada/saída: "+numDispositivosEntrada+"/"+numDispositivosSaida);
	
	if (numDispositivosEntrada == 0 || numDispositivosSaida == 0) {
		dgEnviar("erro", "Nenhum dispositivo de entrada e/ou saída de áudio encontrado");
		dgEstado(3);
	} else {
		var i;
		for (i = 0; i < numDispositivosEntrada; i++) {
			dgEnviar("info", "Dispositivo de Entrada "+(i+1)+": "+webphone.getDispositivoEntrada(i));
			var dispositivo = document.createElement("option");
			dispositivo.appendChild(document.createTextNode(webphone.getDispositivoEntrada(i)));
			dispositivo.setAttribute("value", i);
			document.getElementById("dgDispEntrada").appendChild(dispositivo);
		}
		for (i = 0; i < numDispositivosSaida; i++) {
			dgEnviar("info", "Dispositivo de Saída "+(i+1)+": "+webphone.getDispositivoSaida(i));
			var dispositivo = document.createElement("option");
			dispositivo.appendChild(document.createTextNode(webphone.getDispositivoSaida(i)));
			dispositivo.setAttribute("value", i);
			document.getElementById("dgDispSaida").appendChild(dispositivo);
		}
		dgEstado(4);
	}
}

function dgAtendida() {
	dgEnviar("info", "A chamada foi atendida");
	if (dgTimer != null) {
		clearTimeout(dgTimer);
		dgTimer = null;
	}
	dgChamadaAtendida = true;
	dgEstado(11);
}

function dgFimChamada() {
	if (dgTimer != null) {
		clearTimeout(dgTimer);
		dgTimer = null;
	}
	if (dgChamadaAtendida) {
		dgEnviar("info", "Chamada encerrada");
		dgEstado(13);
	} else {
		dgEnviar("info", "Limite de chamadas encontrado");
		dgEstado(10);
		dgTimer = setTimeout(dgTimeoutLimite, 30000);
	}
}

function dgTocando() {
	dgEnviar("info", "Chamando...");
}

function dgErro() {
	dgEnviar("erro", "Erro");
	dgEstado(9);
}

function dgErroChamada() {
	dgEnviar("erro", "Erro na chamada");
	dgEstado(9);
}


function dgNaoRegistrado() {
	if (!dgDesconectar) {
		dgEnviar("erro", "A conexão com a central foi encerrada");
		dgEstado(14);
	}
}