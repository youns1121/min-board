
/* 첨부파일 삭제 */
function deleteFile(num) {
    $('#file'+num).remove();
    filesArr[num].is_delete = true;
}

/* 폼 전송 */

function submitForm() {

    // 폼데이터 담기
    var form = document.querySelector("form");
    var formData = new FormData(form);
    for (var i = 0; i < filesArr.length; i++) {
        // 삭제되지 않은 파일만 폼데이터에 담기
        if (!filesArr[i].is_delete) {
            formData.append("fileList[]", filesArr[i]);
        }
    }

}