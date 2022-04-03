
function newComment() {
    let contents = $('input[name="comments"]').val()
    let id = parseInt($('#id').val())
    let formData =
        {"boardId": id,
         "contents" : contents}

    $.ajax({
        url: "/board/newComment",
        method: "POST",
        data: formData,
        success : function (returnData){
            console.log(returnData)
            window.location.reload();
        }
    })
}
