$(document).ready(function () {
    $('#cubeNamed').autocomplete({
            source: function (request, response) {
                $.get("http://localhost:8080/cubes/suggestions?", { query: request.term }, function (data, status) {
                    $("#results").html("");
                    console.log(status)
                    if (status === 'success') {
                        response(data);
                    }
                });
            }
        }
    );
})
