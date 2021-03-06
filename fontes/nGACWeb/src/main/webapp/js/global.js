
$(document).ready(function() {
	fixarMascara();	
	$(".serializeField").val($( "form" ).serialize());	
});

function fixarMascara () {
	
	$(".dateMask").mask("99/99/9999");
	$(".cepMask").mask("99999-999");
	$(".cpfMask").mask("999.999.999/99");
	$(".telefoneMask").mask("(99)99999-9999");
	$(".telefoneCelularMask").mask("(99)99999-9999");
	$(".telefoneFixoMask").mask("(99)9999-9999");
	$(".horaMinutoMask").mask("99:99");
	$(".numeric2").mask("99");
	$(".numeric3").mask("999");
	$(".moedaMask").maskMoney({decimal:",",thousands:"."});
	iniciarCampoNumerico();
		
}

function iniciarCampoNumerico() {
	   $('.numericField').keypress(function(event) {
	        var tecla = (window.event) ? event.keyCode : event.which;
	        if ((tecla > 47 && tecla < 58)) return true;
	        else {
	            if (tecla != 8) return false;
	            else return true;
	        }
     });
}

function handleSaveRequest(xhr, status, args) {
	dlgWaiting.hide();
	$(".serializeField").val($( "form" ).serialize());
}

function confirmClosePage() {

	var newFormSerialize = $("form").serialize();
	var originalSerialize = $("#idTxtSerialize").val();
	if (originalSerialize == newFormSerialize ) {
		closePage();					
	} else {
		modalClosePage.show();
	}
}
