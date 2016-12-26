$(document).ready(function () {
    // alert("öli");
    getBars();
});

function getBars() {
    // $("#bars").clearAttributes();
    $.getJSON("https://api.myjson.com/bins/17ahj",
        function (data) {
            console.log(data.bars);
            $.each(data.bars, function (i, item) {
                var className = item.name.split(" ").join("").toLocaleLowerCase();

                // var info = item.name + ' (' + item.distance + ')';
                // $("<p/>").text(info).appendTo("#bars");
                var listItem = $("<tr/>",
                    {
                        // class: "clickable",
                        "data-toggle": "collapse",
                        "data-target": "#" + className


                    });
                $("<td/>").text(item.name).appendTo(listItem);
                $("<td/>").text(item.distance).appendTo(listItem);
                $("<td/>").text(item.beer.price / item.beer.volume).appendTo(listItem);
                listItem.appendTo("#bars");
                $("<div/>", {
                    class: "collapse",
                    id: className
                }).html("Address, Helsinki </br>Times Favourited: 100")
                .appendTo($("<td/>", {colspan: 3, class: "hiddenRow"})
                    .appendTo($("<tr/>")
                        .appendTo("#bars")));


            })
        })
}

$(function () {
    $("li").on("click", function () {
        $("#buttonText").text($("#" + this.id).text());
    });
});


