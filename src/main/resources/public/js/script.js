$(document).ready(function () {
  recommendBestBar();
});

function recommendBestBar() {
  if (!navigator.geolocation) {
    alert('No geolocation support!');
    return;
  }

  navigator.geolocation.getCurrentPosition(position => {
    $.getJSON("http://localhost:8080/api/recommend/?pos=" + position.coords.latitude + ',' + position.coords.longitude,
      (data) => {
        console.log(data);
        $.each(data.data, (i, item) => {
          var listItem = $("<tr/>");
          $("<td/>").text(item.bar.name).appendTo(listItem);
          $("<td/>").text(Number((item.distance / 1000).toFixed(1)).toFixed(2)).appendTo(listItem);
          $("<td/>").text("").appendTo(listItem);
          listItem.appendTo("#bars");
        });
      });
  });
}

$(function () {
  $("li").on("click", function () {
    $("#buttonText").text($("#" + this.id).text());
  });
});


