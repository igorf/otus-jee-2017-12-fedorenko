$(document).ready(function() {
    $.getJSON("/news", function(data) {
        var items = [];
        $.each(data, function(key, val) {
            items.push(
                "<div>" +
                "<a href='" + val.href + "'>" + val.header + "</a>" +
                "<p>" + val.content + "</p>" +
                "</div>"
            );
        });

        $( "<div/>", {
            html: items.join( "" )
        }).appendTo("#newsContainer");
    });
});