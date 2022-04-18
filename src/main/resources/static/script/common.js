function boardPageMove(obj){
    let valData = parseInt($(obj).val())

    formData = {
        categoryCode : valData
    }

    $.ajax({
        url : '/board/category/'+ valData,
        type : "GET",
        // cache: false,
        // headers: {"cache-control":"no-cache", "pragma": "no-cache"},
        data : formData,
        success : function(data){
            console.log(data);

            $('body').html(data);
        },
        error : function(data){
            alert('error');

        }
    })
}

