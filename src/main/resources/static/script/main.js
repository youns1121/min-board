


function createFn(obj){
    const titleValueCheck = $('#title').val().trim().length;
    const contentsValueCheck = $('#contents').val().trim().length;
    if(titleValueCheck != 0 && (contentsValueCheck > 20 && contentsValueCheck < 100)){
        alert("저장 되었습니다")
    }
}

function deleteFn(obj){
    $(obj).parent('form').attr("action", "/board/delete");
    $(obj).parent('form').attr("method","post");
    $(obj).parent('form').submit();
    alert("삭제 되었습니다")
}

function updateFn(obj){
    const titleValueCheck = $('#title').val().trim().length;
    const contentsValueCheck = $('#contents').val().trim().length;
    if(titleValueCheck != 0 && (contentsValueCheck > 20 && contentsValueCheck < 100)){
        alert("저장 되었습니다")
    }
}

function validationSignUp(obj){
    $(obj).parent('form').val()

}



