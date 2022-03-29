
function createFn(){
    const titleValueCheck = $('#title').val().trim().length;
    const contentsValueCheck = $('#contents').val().trim().length;
    uploadNewFile()
    if(titleValueCheck != 0 && (contentsValueCheck > 20 && contentsValueCheck < 100)){
        alert("저장 되었습니다")
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
    // uploadFile()
    if(titleValueCheck != 0 && (contentsValueCheck > 20 && contentsValueCheck < 100)){
        alert("저장 되었습니다")
    }
}


function uploadFile(){
    var formData = new FormData();
    var inputFile = $("input[type='file']")
    formData.append('title', $('#title').val())
    formData.append('contents', $('#contents').val())

    for(var i = 0;  i < inputFile.length; i++){
        formData.append('fileList', inputFile[i].files[0])
    }

    $.ajax({
        url: '/board/update',
        enctype: 'multipart/form-data',
        contentType : false,
        processData: false,
        cache : false,
        type : "POST",
        data : formData,
        success: function(returnData) {
            alert('등록되었습니다.')
            // location.href = '/board/view/' + returnData
            // console.log(returnData);
        },
        error : function (){
            alert('error');

        }

    });
}

function uploadNewFile(){
    var formData = new FormData();
    var inputFile = $("input[type='file']")
    formData.append('title', $('#title').val())
    formData.append('contents', $('#contents').val())

    for(var i = 0;  i < inputFile.length; i++){
        formData.append('fileList', inputFile[i].files[0])
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
            location.href = '/board/view/' + returnData
            // console.log(returnData);
        },
        error : function (){
            alert('error');

        }

    });
}







