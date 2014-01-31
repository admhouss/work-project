function getModels(contextPath) {
    var models;
    $.ajax({
        url: contextPath+"/get/all/models",
        type: 'POST',
        cashed: false,
        'success': function (models) {
            console.log(models);
            $('#typeahead-models').typeahead({source: models});
        }
    });
}