
$(document).ready(function(){
    let id = parseInt($('#id').val())
    $.ajax({
        method: 'GET',
        url : '/board/commentsList/'+ id,
        success: function(data){
            $("#commentsTable").append(data)
            disableModifyButton()

        },
        error:function(){
            alert('초기 댓글리스트 불러오기 실패');
        }
    })
});

function getBoardHierarchicalCommentsAll(){
    let boardId = parseInt($('#id').val())

    $.ajax({
        method: "GET",
        url: '/board/commentsList/' + boardId,
    }).done(function (returnData) {
        $('#commentsTable').html(returnData)
        disableModifyButton()

    })
}


function addComment() {
    let id = parseInt($('#id').val())

    let contents = $('input[name="commentContents"]').val()
    let contentsLength = $('input[name="commentContents"]').val().trim().length
    let dataGroup = parseInt($('input[name="commentContents"]').data('group'))
    let dataDepth = parseInt($('input[name="commentContents"]').data('depth'))
    let formData = {
            boardId: id,
            contents : contents,
            commentGroup : dataGroup,
            commentDepth : dataDepth
        }

    if(contentsLength < 1){
        alert("공백은 불가합니다.")
    }

    if(contentsLength > 1) {
        $.ajax({
            url: '/board/comment',
            method: "POST",
            data: formData,
            success:function (){
                alert("댓글작성 완료");
                $('input[name="commentContents"]').val("")
                getBoardHierarchicalCommentsAll()
            },
            error:function () {
                alert("댓글작성 실패")
            }})
    }
}

function getCommentReply(obj){
    let id = parseInt($(obj).data('group'))

    $('.optionButton').hide()

    $(obj).parents('tr').find('[class=contents] p').after(
        '<div id="commentReply">'+
        '<input type="text" name ="commentReplyContents">' +
        '<button type="button" id="commentReplyButton" onclick="addCommentReply(this)">저장</button>' +
        '<button type="button" onclick="cancelCommentReply(this)">취소</button>'+
        '</div>'
    )
    $('#commentReplyButton').val(id)
    $('#commentReplyButton').data('group')

}

function addCommentReply(obj){
    let id = parseInt($(obj).val())
    let contents = $(obj).siblings('input').val()
    let boardId = parseInt($('#id').val())
    let dataGroup =  parseInt($(obj).val())
    let dataDepth = $(obj).parents('td').find('p').data('depth')
    let dataSort = $(obj).parents('td').find('p').data('sort')




    let formData ={
        commentGroup : dataGroup,
        contents : contents,
        boardId : boardId,
        commentDepth : dataDepth,
        sort : dataSort
    }

    $.ajax({
            type: 'POST',
            url: '/board/comment/reply',
            data: formData,
            success: function () {
                alert("답글작성 완료")
                getBoardHierarchicalCommentsAll()
            },
            error: function () {
                alert("답글작성 실패")
            }
        }
    )
}

function getUpdateComment(obj){

    let returnData = $(obj).parents('tr').children('td').find('p').text()

    $('.optionButton').hide()
    $(obj).parent().parent().find('[class^=commentContents]').wrap('<input class="commentContents" style="width: 300px">').val(returnData);
    $(obj).parent().parent().find('[class^=modify]').attr('type', 'button')
    $(obj).parent().parent().find('[class^=commentContents]').val(returnData)
}

function updateComment(obj) {

    let id = parseInt($(obj).parent().parent().find('.commentId').val())
    let inputData = $(obj).parent().parent().find('input').val()
    let formData = {
        id: id,
        contents: inputData
    }

    if (inputData.trim().length < 1) {
        alert("공백은 불가합니다.")
    }

    if (inputData.trim().length >= 1) {
        $.ajax({
            method: "POST",
            url: '/board/comment/update',
            data: formData,
            success: function () {
                alert("댓글 수정완료")
                getBoardHierarchicalCommentsAll()
            },
            error: function () {
                alert("댓글 수정실패")
            }
        })
    }
}

function cancelUpdateComment() {
    getBoardHierarchicalCommentsAll()
}

function cancelCommentReply(obj){
    $(obj).parent('div').remove()
    $('.optionButton').show()
}

function deleteComment(obj) {
    let id = parseInt($(obj).parent().parent().find('.commentId').val())
    let formData = {
        id: id
    }

    $.ajax({
        type: 'POST',
        url: '/board/commentDelete',
        data: formData,
        success: function () {
            alert("댓글삭제 완료")
            getBoardHierarchicalCommentsAll()
        },
        error: function (){
            alert("댓글삭제 실패")
        }
    })
}

function disableModifyButton(){
    let commentsLength = $('[class^=commentContents]').length
    for(let i = 0; i < commentsLength; i++ ){
        if($('[class^=commentContents]'+':eq(' +i+ ')').data('depth') > 0){
            $('[class^=commentContents]'+':eq(' +i+ ')')
                .parent()
                .find('.modifyButton')
                .prop("disabled", true).hide()
        }

    }
}