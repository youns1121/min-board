
$(document).ready(function(){
    let id = parseInt($('#id').val())
    $.ajax({
        type: 'GET',
        url : '/board/commentsList/'+ id,
        success: function(data){
            $("#commentsTable").append(data)
        },
        error:function(){
            alert('초기 댓글리스트 불러오기 실패');
        }
    })
});


function addComment() {
    let id = parseInt($('#id').val())

    let contents = $('input[name="commentContents"]').val()
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
        $('input[name="commentContents"]').val("")
        $.ajax({
            type: 'GET',
            url : '/board/commentsList/'+ id,
            success: function(data){
                location.reload()
            },
            error:function(){
                alert('댓글리스트 불러오기 실패');
            }
        })
    })
}

function getUpdateComment(obj){
     let id = parseInt($(obj).parent().parent().find('.commentId').val())
    $.ajax({
        url: '/board/comment/update/' + id,
        method: "GET",
        data: id,
        success: function(returnData){
            $(obj).parent().parent().find('[class^=commentContents]').wrap('<input class="commentContents" style="width: 300px">').val(returnData);
            $(obj).parent().parent().find('[class^=modify]').attr('type', 'button')
            $(obj).parent().parent().find('[class^=commentContents]').val(returnData)
        },
        error:function(){
            alert('수정 실패');
        }
    })
}

function updateComment(obj){
    let id = parseInt($(obj).parent().parent().find('.commentId').val())
    let inputData = $(obj).parent().parent().find('input').val()

    let formData ={
        id : id,
        contents : inputData
    }

    $.ajax({
        url: '/board/comment/update',
        method: "POST",
        data:formData,
        success: function (returnData){
            location.reload()
        }
    })
}


function cancelUpdateComment(){
    let id = parseInt($('#id').val())
    $.ajax({
        type: 'GET',
        url : '/board/commentsList/'+ id,
        success: function(data){
            location.reload()
        },
        error:function(){
            alert('댓글리스트 불러오기 실패');
        }
    })
}

function deleteComment(obj){
    let id = parseInt($(obj).parent().parent().find('.commentId').val())
    let formData={
        id : id
    }

    $.ajax({
        type: 'POST',
        url: '/board/commentDelete',
        data: formData,
        success: function(data){
            $(obj).parent().parent('tr').remove()
            alert("댓글삭제 완료")
        },
        error:function(){
            alert('댓글리스트 불러오기 실패');
       }
    })
}




