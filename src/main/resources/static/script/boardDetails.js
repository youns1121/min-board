$(function() {
    let idData = {
        id : $('#id').val()
    }
    $.ajax({
        url: '/board/validation/reply/yn',
        method: 'GET',
        data: idData,
        success: function (replyYn){
            if(replyYn == 'N'){
                $('.replyButton').hide().prop("disabled")
            }
            if(replyYn == 'Y'){
                $('.replyButton').show().prop("enabled")
            }

        }
    })

})