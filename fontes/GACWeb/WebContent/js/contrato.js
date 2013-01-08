function atualizarIndiceTab(index) {
	$("#frmContrato\\:idTxtIndiceTab").val(index);
}

function modalExcluirCentral(id) {
	$("#frmContrato\\:idTxtIdDispositivo").val(id);
	excluirCentralSelecionada.show();
}

function modalExcluirDispositivo(id) {
	$("#frmContrato\\:idTxtIdDispositivo").val(id);
	excluirDispositivoSelecionado.show();
}

function handleSelecionarCentralRequest(xhr, status, args) {
	if (!args.validationError) {
		modalSelecionarCentral.hide()
	}
}

function displayTelefoneEmailContato(tipoContato) {
	if (tipoContato == 4) {
		$("#frmContrato\\:idTxtFormaContatoEmail").css("display", "block");
		$("#frmContrato\\:idLblFormaContatoEmail").css("display", "block");
		$("#frmContrato\\:idTxtFormaContatoTelefone").css("display", "none");
		$("#frmContrato\\:idLblFormaContatoTelefone").css("display", "none");
	} else {
		$("#frmContrato\\:idTxtFormaContatoEmail").css("display", "none");
		$("#frmContrato\\:idLblFormaContatoEmail").css("display", "none");

		if (tipoContato == 1) {
			$("#frmContrato\\:idTxtFormaContatoTelefone").css("display",
					"block");
			$("#frmContrato\\:idTxtFormaContatoTelefone").removeClass(
					"telefoneFixoMask");
			$("#frmContrato\\:idTxtFormaContatoTelefone").addClass(
					"telefoneCelularMask");
			$("#frmContrato\\:idTxtFormaContatoTelefone")
					.mask("(99)99999-9999");
			$("#frmContrato\\:idLblFormaContatoTelefone").css("display",
					"block");
		} else {
			$("#frmContrato\\:idTxtFormaContatoTelefone").css("display",
					"block");
			$("#frmContrato\\:idLblFormaContatoTelefone").css("display",
					"block");
			$("#frmContrato\\:idTxtFormaContatoTelefone").addClass(
					"telefoneFixoMask");
			$("#frmContrato\\:idTxtFormaContatoTelefone").removeClass(
					"telefoneCelularMask");
			$("#frmContrato\\:idTxtFormaContatoTelefone").mask("(99)9999-9999");
		}
		fixarMascara();
	}
}

function updateFormContato() {
	displayTelefoneEmailContato($(
			"#frmContrato\\:idCmbTipoFormaContato :selected").val());
}

function updateFormContatoCliente() {
	displayTelefoneEmailCliente($(
			"#frmContrato\\:idCmbTipoFormaContatoCliente :selected").val());
}

function displayTelefoneEmailCliente(tipoContato) {
	if (tipoContato == 4) {
		$("#frmContrato\\:idTxtFormaContatoClienteEmail").css("display",
				"block");
		$("#frmContrato\\:idLblFormaContatoClienteEmail").css("display",
				"block");
		$("#frmContrato\\:idTxtFormaContatoClienteTelefone").css("display",
				"none");
		$("#frmContrato\\:idLblFormaContatoClienteTelefone").css("display",
				"none");
	} else {
		$("#frmContrato\\:idTxtFormaContatoClienteEmail")
				.css("display", "none");
		$("#frmContrato\\:idLblFormaContatoClienteEmail")
				.css("display", "none");

		if (tipoContato == 1) {
			$("#frmContrato\\:idTxtFormaContatoClienteTelefone").css("display",
					"block");
			$("#frmContrato\\:idTxtFormaContatoClienteTelefone").removeClass(
					"telefoneFixoMask");
			$("#frmContrato\\:idTxtFormaContatoClienteTelefone").addClass(
					"telefoneCelularMask");
			$("#frmContrato\\:idTxtFormaContatoClienteTelefone").mask(
					"(99)99999-9999");
			$("#frmContrato\\:idLblFormaContatoClienteTelefone").css("display",
					"block");
		} else {
			$("#frmContrato\\:idTxtFormaContatoClienteTelefone").css("display",
					"block");
			$("#frmContrato\\:idLblFormaContatoClienteTelefone").css("display",
					"block");
			$("#frmContrato\\:idTxtFormaContatoClienteTelefone").addClass(
					"telefoneFixoMask");
			$("#frmContrato\\:idTxtFormaContatoClienteTelefone").removeClass(
					"telefoneCelularMask");
			$("#frmContrato\\:idTxtFormaContatoClienteTelefone").mask(
					"(99)9999-9999");
		}
		fixarMascara();

	}
}		
			
function modalExcluirTratamento(parametro) {
	$("#frmContrato\\:txtIdTratamento").val(parametro);
	excluir.show();
}

function modalExcluirHorarioTratamento(parametro) {
	$("#frmContrato\\:txtIdHorario").val(parametro);	
	$("#frmContrato\\:idTxtHorarioTratamento").val(parametro);
	fixarMascara();
	excluirHorario.show();
}

function modalExcluirContato(parametro) {
	$("#frmContrato\\:txtIdContato").val(parametro);
	excluirContato.show();
}

function modalExcluirFormaContatoPessoaContatoCliente(parametro) {
	$("#frmContrato\\:txtIdFormaContato").val(parametro);
	excluirFormaContato.show();
}

function modalExcluirFormaContatoCliente(parametro) {
	$("#frmContrato\\:txtIdFormaContatoCliente").val(parametro);
	excluirFormaContatoCliente.show();
}
