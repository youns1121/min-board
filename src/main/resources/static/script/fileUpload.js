var fileNo = 0;
var filesArr = new Array();

/* 첨부파일 추가 */
function addFile(obj){
        for (const file of obj.files) {
            // 첨부파일 검증
                // 파일 배열에 담기
                var reader = new FileReader();
                reader.onload = function () {
                    filesArr.push(file);
                };
                reader.readAsDataURL(file);

                // 목록 추가
                let htmlData = '';
                htmlData += '<div id="file' + fileNo + '" class="filebox">';
                htmlData += '   <p class="name" name="storeFileName">' + file.name + '</p>';
                htmlData += '   <button type="button" class="delete" onclick="deleteFile(' + fileNo + ');">삭제</button>';
                htmlData += '</div>';
                $('.file-list').append(htmlData);
                fileNo++;
                continue;

        }

    // 초기화
    $("input[type=file]").val("");
}


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
            formData.append("attach_file", filesArr[i]);
        }
    }
}