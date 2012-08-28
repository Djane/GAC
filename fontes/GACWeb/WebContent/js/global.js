
$(document).ready(function() {
	fixarMascara();	
});


function fixarMascara () {
	
	$(".cepMask").mask("99999-999");
	$(".cpfMask").mask("999.999.999/99");
	$(".telefoneMask").mask("(99)99999-9999");
	$(".horaMinutoMask").mask("99:99");
	$(".moedaMask").maskMoney({decimal:",",thousands:"."});
	
}