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
          $('<td/>').text(item.bar.nFavourites).appendTo(listItem);
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
  $.getJSON(APICall.barAction(id), barRes => {
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

      let newline = $('<b/>');

      $('<h2/><br/><br/>').text(bar.name).appendTo('#barStats');
      let barDiv = $('<div/>');
      $('<b/><br/>').text('Address: ' + bar.address.replace('+', ' ')).appendTo(barDiv);

      $('<b/><br/>').text('Drink name: ' + drink.name).appendTo(barDiv);
      $('<b/><br/>').text('Drink price: ' + (drink.price / 100.0) + '€').appendTo(barDiv);
      $('<b/><br/><br/>').text('Drink volume: ' + (drink.volume / 10.0) + 'l').appendTo(barDiv);
      $('<b/>').text('Favourites: ' + bar.nFavourites).appendTo(barDiv);
      barDiv.appendTo('#barStats');
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
    price: Number(e.form.cheapesBeerPrice.value.replace('.', '')) * 100,
    volume: Number(e.form.cheapestBeerVolume.value),
    drinkType: 'beer',
    alcoholPercentage: Number(e.form.cheapestBeerAlcohol.value.replace('.', '')) * 100
  };

  // Send request to server
  fetch(APICall.bars, {
    credentials: 'include',
    method: 'POST',
    body: JSON.stringify({ bar: bar, drink: drink }),
  }).then(response => {
    console.log(response);
  }).catch(e => {
    console.log(e);
  });
}

function getMe() {
  fetch(APICall.me, { credentials: 'include' }).then(res => res.json())
    .then(data => {
      if (data.status === 'Error') {
        console.log('Error getting profile: ' + data.message);
        window.document.location = '/signin';
      }

      let me = data.data;
      let div = $('#profileHeader');
      $('<h1/>').text('Hello, ' + me.username).appendTo(div);
      let listItem = $('<tr/>');
      me.favouriteBars.forEach(bar => {
        $('<td/>').text(bar.name).appendTo(listItem);
        $('<td/>').text(bar.address).appendTo(listItem);
        $('<td/>').text(bar.nFavourites).appendTo(listItem);
      });
      listItem.appendTo('#bars');
    }).catch(e => {
      window.document.location = '/signin';
    })
}

function onFavourite() {
  let pathvars = window.location.pathname.split('/');
  let barId = pathvars[pathvars.length - 1];

  fetch(APICall.barAction(barId), {
    credentials: 'include',
    method: 'PUT',
  }).then(response => {
    if (response.status === 401) window.document.location = '/signin';
    response.json();
  }).then(data => {
    if (data.status === "OK") location.reload();
  }).catch(e => {
    console.log(e);
  });
}

$(function () {
  $('li').on('click', function () {
    $('#buttonText').text($('#' + this.id).text());
  });
});


