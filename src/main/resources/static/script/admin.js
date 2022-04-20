function createAdminFn(){
    let formData = new FormData();
    formData.append("categoryName", $('#categoryName').val())
    formData.append("contents", $('#contents').val())
    formData.append("commentsYn", $("input:radio[name=commentsYn]:checked").val())
    formData.append("replyYn", $("input:radio[name=replyYn]:checked").val())
    formData.append("attachedFileYn", $("input:radio[name=attachedFileYn]:checked").val())
    formData.append("attachedFileCount", $("input:radio[name=attachedFileCount]:checked").val())

    $.ajax({
        url: '/admin/setting',
        type : "POST",
        contentType : false,
        processData: false,
        data : formData,
        success: function(returnData) {
            alert('등록되었습니다.')
            location.href = '/admin/view/' + returnData + '?'+"status="+true
        },
        error : function (){
            return false;
        }
    });
}

function updateAdminFn(){
    let formData = new FormData();
    formData.append("id", parseInt($('#adminUpdatebutton').val()))
    formData.append("categoryName", $('#categoryName').val())
    formData.append("contents", $('#contents').val())
    formData.append("commentsYn", $("input:radio[name=commentsYn]:checked").val())
    formData.append("replyYn", $("input:radio[name=replyYn]:checked").val())
    formData.append("attachedFileYn", $("input:radio[name=attachedFileYn]:checked").val())
    formData.append("attachedFileCount", $("input:radio[name=attachedFileCount]:checked").val())

    $.ajax({
        url: '/admin/setting/update',
        type : "POST",
        contentType : false,
        processData: false,
        data : formData,
        success: function(returnData) {
            alert('등록되었습니다.')
            location.href = '/admin/view/' + returnData + '?'+"status="+true
        },
        error : function (){
            return false;
        }
    });
}

