$(document).ready(function() {
    $.getJSON("/currency", function(data) {
        var items = [];
        $.each(data, function(key, val) {
            items.push( "<li id='" + key + "'>" + val.code + ":&nbsp;" + val.course + "</li>" );
        });

        $( "<ul/>", {
            "class": "list-unstyled mb-0",
            html: items.join( "" )
        }).appendTo("#currencyContainer");
    });
});