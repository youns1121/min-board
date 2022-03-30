
function createFn(){
    const titleValueCheck = $('#title').val().trim().length;
    const contentsValueCheck = $('#contents').val().trim().length;

    if(titleValueCheck == 0){
        alert("제목을 입력해 주세요.")
    }
    if(contentsValueCheck < 20 || contentsValueCheck > 100){
        alert("내용은 20자 이상 100자 이하")
    }
    if(titleValueCheck != 0 && (contentsValueCheck > 20 && contentsValueCheck < 100)){
        uploadNewFile()
    }

}

function deleteFn(){
    let formConrole = $('form').eq(0)
    formConrole.attr("action", "/board/delete");
    formConrole.attr("method","post");
    formConrole.submit();
    alert("삭제 되었습니다")
}

function updateFn(){
    const titleValueCheck = $('#title').val().trim().length;
    const contentsValueCheck = $('#contents').val().trim().length;

    if(titleValueCheck == 0){
        alert("제목을 입력해 주세요.")
    }
    if(contentsValueCheck < 20 || contentsValueCheck > 100){
        alert("내용은 20자 이상 100자 이하")
    }
    if(titleValueCheck != 0 && (contentsValueCheck > 20 && contentsValueCheck < 100)){
        uploadUpdateFile()
    }

}

function uploadUpdateFile(){
    var formData = new FormData();
    var inputFile = $("input[type='file']")
    formData.append('title', $('#title').val())
    formData.append('contents', $('#contents').val())
    var id = parseInt($("input[type='hidden']").attr('value'))
    formData.append('id', id)

    for(var i = 0;  i < inputFile.length; i++){
        if(!(inputFile[i].value == "")){
            formData.append('fileList', inputFile[i].files[0])
        }
    }
    $.ajax({
        url: '/board/update',
        type : "POST",
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

function uploadNewFile(){
    var formData = new FormData();
    var inputFile = $("input[type='file']")
    formData.append('title', $('#title').val())
    formData.append('contents', $('#contents').val())

    for(var i = 0;  i < inputFile.length; i++){
        if(!(inputFile[i].value == "")){
            formData.append('fileList', inputFile[i].files[0])
        }
    }

    $.ajax({
        url: '/board/new',
        enctype: 'multipart/form-data',
        contentType : false,
        processData: false,
        cache : false,
        type : "POST",
        data : formData,
        success: function(returnData) {
            alert('등록되었습니다.')
            // console.log(returnData);
            location.href = '/board/view/' + returnData + '?'+"status="+true
        },
        error : function (){
            return false;
        }
    });
}







