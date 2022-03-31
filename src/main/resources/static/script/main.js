
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
        attachedExtenSionCheck()
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
        attachedExtenSionCheck()
    }

}

function attachedExtenSionCheck(){
    let inputFile = $(".file")
    for(let i = 0; i < inputFile.length; i++ ){
        var fileval = inputFile.eq(i).val().split('.').pop().toLowerCase();
        if(inputFile[i].value != "" &&
            $.inArray(fileval, ['gif', 'png', 'jpg','jpeg','doc','docx','xls','xlsx','hwp','pdf', 'pptx']) == -1){
            alert(fileval + "는(은) 등록할 수 없는 파일명입니다.")
            inputFile.val("");
            return false;
        }
    }
    checkSpecialPattern()
}

function checkSpecialPattern(){
    let inputFile = $(".file")
    let specialPattern = /[\{\}\/?,;:|*~`!^\+<>@\#$%&\\\=\'\"]/gi;
    let inputFileSize = inputFile.length


    for(let i = 0; i < inputFileSize; i++) {
        if (inputFile[i].value != "") {
            let filePathSplit = $(".file").eq(i).val().split('\\')
            let fileLength = $(".file").eq(i).val().split('\\').length
            let fileName = filePathSplit[fileLength - 1].split('.')[0]
            if (specialPattern.test(fileName)) {
                alert("파일명에 특수문자는 포함할 수 없습니다.");
                inputFile.eq(i).val("");
                return false
            }
        }
    }
    uploadNewFile()
}

function uploadNewFile(){
    let formData = new FormData();
    let inputFile = $("input[type='file']")
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







