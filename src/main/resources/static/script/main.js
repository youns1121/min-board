

function createFn(obj){
    alert("저장 되었습니다")
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



