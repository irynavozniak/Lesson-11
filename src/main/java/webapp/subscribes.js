function search() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById('search');
	filter = input.value.toUpperCase();
	table = document.getElementById('table');
	tr = table.getElementsByTagName('tr');
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName('td')[0];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = '';
			} else {
				tr[i].style.display = 'none';
			}
		}
	}
}

var buckets = null;
$.get('subscribes', function(data) {
	if (data !== '') {
		buckets = data;
	}
}).done(
		function() {
			var tableContent = "<tr class='header'>"
					+ "<th style='width: 20%;'>Назва</th>"
					+ "<th style='width: 20%;'>Опис</th>"
					+ "<th style='width: 10%;'>Дата публикації</th>"
					+ "<th style='width: 10%;'>Ціна підписки</th>"
					+ "<th style='width: 10%;'>Період підписки</th>"
					+ "<th style='width: 10%;'>Дата підписки</th>"
					+ "<th style='width: 5%;'>Статус підписки</th>"
					+ "<th style='width: 15%;'></th>"					
					+ "</tr>";

			jQuery.each(buckets, function(_i, value) {
				var publishingDateFormatted = ('0' + value.publishingDate.day).slice(-2) + "." + ('0' + value.publishingDate.month).slice(-2) + "." + value.publishingDate.year;
				var subscribeDateFormatted = ('0' + value.subscribeDate.day).slice(-2) + "." + ('0' + value.subscribeDate.month).slice(-2) + "." + value.subscribeDate.year;
				
				var subscribeStatus;
				if (value.subscribeStatus) {
					subscribeStatus = "class='fas fa-check'"							
				} else {
					subscribeStatus = "class='fas fa-times'"
				}
				
				tableContent += "<tr>"
						+ "<td>" + value.title + "</td>"
						+ "<td>" + value.description + "</td>"
						+ "<td>" + publishingDateFormatted + " г." + "</td>"
						+ "<td>" + value.Price/100 + " грн." + "</td>"
						+ "<td>" + value.subscribePeriod + " міс." + "</td>"
						+ "<td>" + subscribeDateFormatted + " р." + "</td>"
						+ "<td " + subscribeStatus + ">" + "</td>"
						+ "<td><button title='Оформити підписки / скасувати' class='fa fa-shopping-cart mr-3' onclick='buyOrder(" + value.id + ")'></button>" 
						+ "<button title='Видалити' class='fa fa-trash' onclick='deleteOrder(" + value.id + ")'></button></td>"
						+ "</tr>"
			});

			$('#table').html(tableContent);
		});

function buyOrder(id) {
	var customUrl = '';
	var urlContent = window.location.href.split('/');
	
	for (var i = 0; i < urlContent.length - 1; i++) {
		customUrl += urlContent[i] + '/'
	}
	
	customUrl += 'subscribe?id=' + id;

	$.ajax({
		url: customUrl,
		type: 'PUT',
		success: function(data) {
			if (data !== '') {
				location.reload();
				if (data) {
					alert('Підписка оформлена!');
				} else {
					alert('Підписка відмінена!');
				}
			}
		}
	});
}

function deleteOrder(id) {
	var customUrl = '';
	var urlContent = window.location.href.split('/');
	
	for (var i = 0; i < urlContent.length - 1; i++) {
		customUrl += urlContent[i] + '/'
	}
	
	customUrl += 'subscribe?id=' + id;

	$.ajax({
		url: customUrl,
		type: 'DELETE',
		success: function(data) {
			if (data == 'Success') {
				location.reload();
				alert('Видалено!');
			}
		}
	});
}