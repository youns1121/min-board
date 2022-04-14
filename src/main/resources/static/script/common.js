function boardPageMove(obj){
    let valData = parseInt($(obj).val())

    formData = {
        categoryCode : valData
    }

            location.href = '/category/' + valData
}