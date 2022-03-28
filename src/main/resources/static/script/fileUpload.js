var count = 1;

function addFileForm() {
    let htmlData = '';
    htmlData += '<div id="Files'+count+'">'
    htmlData += '<input type="file" name="fileList" />'
    htmlData += '<button type="button" id="delete'+count+'" onclick="deleteFile(this)">삭제</button>';
    htmlData += '</div>'
    $("#file-list").append('')
    $("#file-list").append(htmlData);
    count++;

}

function deleteDefaultFile() {
    $("input[name='fileList0']").remove()
}

function deleteFile(obj) {
    $(obj).parent().remove()
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