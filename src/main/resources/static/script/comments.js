
function addComment() {
    let contents = $('input[name="newComment"]').val()
    let id = parseInt($('#id').val())
    let formData =
        {
            boardId: id,
            contents : contents
        }

    $.ajax({
        url: '/board/comment',
        method: "POST",
        data :  formData,
    }).done(function(resp){
        alert("댓글작성이 완료되었습니다.");
        $('input[name="comments"]').val("")
        location.reload()
    })
}

$(document).ready(function(){
    let id = parseInt($('#id').val())

    $.ajax({
        type: 'GET',
        url : '/board/commentsList/'+ id,
        data : id,
        success: function(data){   //데이터 주고받기 성공했을 경우 실행할 결과
            alert(data);
        },
        error:function(){   //데이터 주고받기가 실패했을 경우 실행할 결과
            alert('실패');
        }
    })
});
