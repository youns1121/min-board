

function createFn(obj){

    let titleValueCheck = $('#title').val().trim().length;
    let contentsValueCheck = $('#contents').val().trim().length;

    if(titleValueCheck == 0){
        alert("제목은 필수 값 입니다.")
    }

    if(contentsValueCheck < 20){
        alert("내용은 20자 이상 작성해야 합니다.")
    }
    if(contentsValueCheck > 100){
        alert("내용은 100자 이상 넘을 수 없습니다.")
    }

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
    alert("수정 되었습니다");
}



