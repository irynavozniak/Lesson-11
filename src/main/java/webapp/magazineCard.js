$('button.openModal').click(function() {
	$('#subscribePeriod').html($('.subscribePeriod').val());
});

$('button.buy-magazine').click(function() {
	var magazineID = jQuery(this).attr('magazineID');
	var subscribePeriod = $('.subscribePeriod').val();
	
	$.post('subscribe', {'magazineID': magazineID, 'subscribePeriod': subscribePeriod},
			function(data) {
				if (data == 'Success') {
					$('#buyMagazineModal').hide();
					$('.modal-backdrop').remove();
					alert('Підписка на журнал успішно додана в корзину!');
				}
			});
});
