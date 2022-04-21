$(function() {
    let dataBoardAdminId = {
        id : $('#categoryName').attr("value")
    }
    $.ajax({
        url: '/board/validation/reply/yn',
        method: 'GET',
        data: dataBoardAdminId,
        success: function (replyYn) {
            if (replyYn == 'N') {
                $('#buttonCreate').hide().prop("disabled")
                history.back()
                alert("답글 작성이 불가능한 게시판입니다.")
            }

        }
    })
})

function addReply(obj) {

    let id = $(obj).val()
    let dataGroup = $(obj).data('group')
    let dataSort = $(obj).data('sort')
    let dataDepth = $(obj).data('depth')

    let formData = {
        id: id,
        boardGroup: dataGroup,
        boardSort: dataSort,
        boardDepth: dataDepth
    }

    $.ajax({
        method: "GET",
        url: "/board/reply/"+dataGroup,
        data: formData,
        success: function (returnData) {
            location.href = "/board/reply/"+dataGroup;
        }
    })
}

function createFnReply(){

    const titleValueCheck = $('#title').val().trim().length;
    const contentsValueCheck = $('#contents').val().trim().length;

    if(titleValueCheck == 0){
        alert("제목을 입력해 주세요.")
    }
    if(contentsValueCheck < 20 || contentsValueCheck > 100){
        alert("내용은 20자 이상 100자 이하")
    }
    if(titleValueCheck != 0 && (contentsValueCheck > 20 && contentsValueCheck < 100)){
        replyBoardAttachedExtenSionCheck()
    }

}

function replyBoardAttachedExtenSionCheck(){
    let status = true;
    let inputFile = $(".file")
    for(let i = 0; i < inputFile.length; i++ ){
        var fileval = inputFile.eq(i).val().split('.').pop().toLowerCase();
        if(inputFile[i].value != "" &&
            $.inArray(fileval, ['gif', 'png', 'jpg','jpeg','doc','docx','xls','xlsx','hwp','pdf', 'pptx', 'txt']) == -1){
            alert(fileval + "는(은) 등록할 수 없는 확장자입니다.")
            inputFile.val("");
            status = false;
        }
    }
    if(status == true){
        uploadFile()
    }

}

function uploadFile(){

    let formData = new FormData();
    let inputFile = $("input[type='file']")
    let inputFileLength = inputFile.length
    let dataGroup = parseInt($('#buttonCreate').data('group'))
    let dataSort = parseInt($('#buttonCreate').data('sort'))
    let dataDepth = parseInt($('#buttonCreate').data('depth'))

    formData.append('title', $('#title').val())
    formData.append('contents', $('#contents').val())
    formData.append('categoryName', $('#categoryName').text())
    formData.append('boardAdminId', $('#buttonCreate').val())
    formData.append('boardGroup', dataGroup)
    formData.append('boardSort', dataSort)
    formData.append('boardDepth', dataDepth)



    for(var i = 0;  i < inputFileLength; i++){
        if(!(inputFile[i].value == "")){
            formData.append('fileList', inputFile[i].files[0])
        }
    }


    $.ajax({
        method : 'POST',
        url: '/board/reply',
        enctype: 'multipart/form-data',
        contentType : false,
        processData: false,
        cache : false,
        data : formData,
        success: function(returnData) {
            alert('등록되었습니다.')
            location.href = '/board/view/' + returnData + '?'+"status="+true
        },
        error : function (){
            return false;
        }
    });
}