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
    $(obj).parent().remove();
    var id = $(obj).parent('div').attr('value')

    var idData = {"id" : parseInt(id)};


    $.ajax({
        url : '/board/deleteFile',
        type : "DELETE",
        data :  idData,
        success : function (returnData){

            console.log(returnData)
        }
    })



}

