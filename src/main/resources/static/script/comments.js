
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
        url : '/board/view/'+ id,
        data : id,
        success: function(data){   //데이터 주고받기 성공했을 경우 실행할 결과
            alert(data);
        },
        error:function(){   //데이터 주고받기가 실패했을 경우 실행할 결과
            alert('실패');
        }
    })
});


function drawReply(replys) {
    $("#cnt").text("등록된 댓글 - " + replys.length)
    var html = '';
    html += '<form class="form-inline" action="writeReply" method="post"><input type="hidden" name="idx" value = "' + IDX + '"><input type="hidden" name="replyIdx" value = "0"><input type="text" class="form-control mb-2 mr-sm-2" id="contents" placeholder="답글" name="contents"><button type="submit" class="btn btn-primary mb-2">등록</button></form>';

    replys.forEach(function(reply){
        if (reply.replyIdx == 0) {
            var rc = 0;
            replys.forEach(function(i){
                if (reply.idx == i.replyIdx) rc++;
            })
            html += '<div class="row"><div class="col-sm-12">';
            html += '<form class="form-inline" action="writeReply" method="post"><label for="pwd" class="mr-sm-2">' + reply.contents + '(' + rc + ')' + '</label>'
            html += '<input type="hidden" name="idx" value = "' + IDX + '"><input type="hidden" name="replyIdx" value = "' + reply.idx + '"><input type="text" class="form-control mb-2 mr-sm-2" id="contents" placeholder="답글" name="contents"><button type="submit" class="btn btn-primary mb-2">등록</button></form>';
            html += '<div class="row"><div class="col-sm-12 sub' + reply.idx + '"></div></div></div></div>';
        }
    })
    // $("#replyArea").append(html);
    // replys.forEach(function(reply){
    //     if (reply.replyIdx != 0) {
    //         var rc = 0;
    //         replys.forEach(function(i){
    //             if (reply.idx == i.replyIdx) rc++;
    //         })
    //         var subHtml = '';
    //         subHtml = '<div class="row"><div class="col-sm-12 subReply">';
    //         subHtml += '<form class="form-inline" action="writeReply" method="post"><label for="pwd" class="mr-sm-2">' + reply.contents + '(' + rc + ')' + '</label>'
    //         subHtml += '<input type="hidden" name="idx" value = "' + IDX + '"><input type="hidden" name="replyIdx" value = "' + reply.idx + '"><input type="text" class="form-control mb-2 mr-sm-2" id="contents" placeholder="답글" name="contents"><button type="submit" class="btn btn-primary mb-2">등록</button></form>';
    //         subHtml += '<div class="row"><div class="col-sm-12 sub' + reply.idx + '"></div></div></div></div>';
    //         $(".sub" + reply.replyIdx).append(subHtml);
    //     }
    // })
}

// $.ajax({
//     url: "/board/view/"+id,
//     success: function(replys){
//         drawReply(replys)
//     }});