var count = 1;

$(function(){
    validationFileYn()

    $('.selectbox-control').change(function (){
        validationFileYn()
    })
});


function validationFileYn(){

    let id = $('.selectbox-control').data('id')
    let formData = {
        id : id
    }
    $.ajax({
        method: 'GET',
        url: '/board/validation/file/yn',
        data: formData,
        success: function(fileYn){
            if(fileYn == 'N'){
                $('#Files0').hide()
                $('#file-list').hide()
            }
            if(fileYn == 'Y'){
                $('#Files0').show()
                $('#file-list').show()
            }
        }
    })
}

function addFileForm() {

    $('.selectbox-control').val()
    let id = $('.selectbox-control').val()
    let formData = {
        id : id
    }
        if(count < 6) {
            let htmlData = '';
            htmlData += '<div id="Files' + count + '">'
            htmlData += '<input type="file" class="file" name="fileList" />'
            htmlData += '<button type="button" id="delete' + count + '" onclick="deleteNewFile(this)">삭제</button>';
            htmlData += '</div>'
            $("#file-list").append('')
            $("#file-list").append(htmlData);
            count++;
        }
}

function deleteDefaultFile() {
    $("input[name='fileList0']").remove()
}

function deleteNewFile(obj) {
    $(obj).parent().remove();
}

function deleteFile(obj) {
    let id = $(obj).parent('div').attr('value')

    let idData = {"id" : parseInt(id)};

    $(obj).parent().remove();

    $.ajax({
        url : '/board/deleteFile',
        method : "post",
        data : idData
        })
}

