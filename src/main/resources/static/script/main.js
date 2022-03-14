


function createFn(){
    const titleValueCheck = $('#title').val().trim().length;
    const contentsValueCheck = $('#contents').val().trim().length;
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
    if(titleValueCheck != 0 && (contentsValueCheck > 20 && contentsValueCheck < 100)){
        alert("저장 되었습니다")
    }
}





