function recommendBestBar() {
  if (!navigator.geolocation) {
    alert('No geolocation support!');
    return;
  }

  navigator.geolocation.getCurrentPosition(position => {
    $.getJSON(APICall.recommend + '/?pos=' + position.coords.latitude + ',' + position.coords.longitude,
      data => {
        if (data.status !== 'OK') {
          console.log('Error getting recommendation: ' + data.message);
          return;
        }
        $.each(data.data, (i, item) => {
          var listItem = $('<tr/>');
          listItem.attr('onclick', 'barItemClicked(this);');
          listItem.attr('id', item.bar.id);
          $('<td/>').text(item.bar.name).appendTo(listItem);
          $('<td/>').text(Number((item.distance / 1000).toFixed(1)).toFixed(2)).appendTo(listItem);
          $('<td/>').text('').appendTo(listItem);
          listItem.appendTo('#bars');
        });
      });
  });
}

function barItemClicked(e) {
  let id = e.attributes.id.value;
  window.document.location = '/bars/' + id;
}

function getBar(id) {
  $.getJSON(APICall.getBar(id), barRes => {
    $.getJSON(APICall.getBarDrinks(id), drinkRes => {
      if (barRes.status !== 'OK') {
        console.log(`Error getting bar with id ${id}:  + ${barRes.message}`);
        return;
      }

      if (drinkRes.status !== 'OK') {
        console.log(`Error getting drinks with bar id ${id}:  + ${drinkRes.message}`);
        return;
      }

      let drink = drinkRes.data[0]; // Again, only one drink for now
      let bar = barRes.data;

      $('<h2/>').text(bar.name).appendTo('#barBody');
      let barDiv = $('<div/>');
      $('<b/>').text('Address: ' + bar.address.replace('+', ' ')).appendTo(barDiv);
      $('<br/><br/>').appendTo(barDiv);
      $('<b/>').text('Drink name: ' + drink.name).appendTo(barDiv);
      $('<br/>').appendTo(barDiv);
      $('<b/>').text('Drink price: ' + (drink.price / 100.0) + '€').appendTo(barDiv);
      $('<br/>').appendTo(barDiv);
      $('<b/>').text('Drink volume: ' + (drink.volume / 10.0) + 'l').appendTo(barDiv);
      barDiv.appendTo('#barBody');
    });
  });
}

function onFormSubmit(e) {
  let bar = {
    name: e.form.barName.value,
    address: e.form.barAddress.value.replace(',', ' ')
  };

  let drink = {
    name: e.form.cheapestBeerName.value,
    price: Number(e.form.cheapesBeerPrice.value),
    volume: Number(e.form.cheapestBeerVolume.value),
    drinkType: 'beer',
    alcoholPercentage: Number(e.form.cheapestBeerAlcohol.value)
  };

  // Send request to server
  $.ajax({
    xhrFields: { // This is probably not safe in any way...
      withCredentials: true
    },
    type: 'POST',
    url: APICall.bars,
    data: JSON.stringify({ bar: bar, drink: drink }),
    success: e => {
      console.log(e);
    }
  });
}

$(function () {
  $('li').on('click', function () {
    $('#buttonText').text($('#' + this.id).text());
  });
});


