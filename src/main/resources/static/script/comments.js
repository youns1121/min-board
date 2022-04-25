
$(function(){
    let id = parseInt($('#id').val())
    $.ajax({
        method: 'GET',
        url : '/board/commentsList/'+ id,
        success: function(data){
            $("#commentsTable").append(data)
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
    })
}


function addComment() {

    let id = parseInt($('#id').val())
    let contents = $('input[name="commentContents"]').val()
    let contentsLength = $('input[name="commentContents"]').val().trim().length
    let dataGroup = parseInt($('input[name="commentContents"]').data('group'))
    let dataDepth = parseInt($('input[name="commentContents"]').data('depth'))
    let dataSort = parseInt($('input[name="commentContents"]').data('sort'))


    let formData = {
        boardId: id,
        contents : contents,
        commentGroup : dataGroup,
        commentDepth : dataDepth,
        sort : dataSort
    }


    if (contentsLength < 1) {
        alert("공백은 불가합니다.")
    }

    if (contentsLength > 1) {
        $.ajax({
            url: '/board/comment',
            method: "POST",
            data: formData,
            success: function () {
                alert("댓글작성 완료");
                $('input[name="commentContents"]').val("")
                getBoardHierarchicalCommentsAll()
            },
            error: function () {
                alert("댓글작성 실패")
            }
        })
    }

}

function getCommentReply(obj){
    let id = parseInt($(obj).data('group'))

    $('.optionButton').hide()

    $(obj).parents('tr').find('.commentsContents').after(
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
    let contents = $(obj).siblings('input').val()
    let boardId = parseInt($('#id').val())
    let dataGroup =  parseInt($(obj).val())
    let dataDepth = $(obj).parents('td').find('span').data('depth')
    let dataSort = $(obj).parents('td').find('span').data('sort')

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
    })


}

function getUpdateComment(val){
    $('.optionButton').hide()
    let contents = $('#spanComment_'+ val).text()
    $('#spanComment_'+ val).hide();
    $('#boardComment_'+val).val(contents)
    $('#boardComment_'+val).show();
    $('.modifyButton'+val).show();
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

function cancelUpdateComment(val) {
    $('.modifyButton'+val).hide();
    $('#boardComment_'+val).hide();
    $('.optionButton').show()
    $('.commentsContents').show()
}

function cancelCommentReply(obj){
    $(obj).parent('div').remove()
    $('.optionButton').show()
}

function deleteComment(obj) {
    let id = parseInt($(obj).parent().parent().find('.commentId').val())
    let dataGroup = parseInt($(obj).siblings()[0].dataset['group'])
    let dataSort = parseInt($(obj).siblings()[0].dataset['sort'])
    let formData = {
        id: id,
        commentGroup : dataGroup,
        sort : dataSort
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