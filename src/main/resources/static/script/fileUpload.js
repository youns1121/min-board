var count = 1;
$(function(){
    $('.selectbox').change(function (){
        validationFileYn()
    })
});


function validationFileYn(){

    let id = $(".selectbox option:selected").val()
    let formData = {
        id : id
    }
    $.ajax({
        method: 'GET',
        url: '/board/validation/file/yn',
        data: formData,
        success: function(categoryNumber){
            location.href="/board/new?categorynumber=" + categoryNumber;
        }
    })
}

function addFileReplyForm(){
    let id = $(".selectbox-control").data('id')
    let formData = {
        id : id
    }
    let fileCount = $('.fileCount').length
    $.ajax({
        method: 'GET',
        url: '/board/validation/file/count',
        data: formData,
        success: function (fileLimitCount) {
            if (fileCount >= fileLimitCount) {
                alert("파일은 최대 " + fileLimitCount + "개 까지 업로드 가능합니다.")
            }

            if (fileCount < fileLimitCount) {
                let htmlData = '';
                htmlData += '<div class="fileCount" id="Files' + count + '">'
                htmlData += '<input type="file" class="file" name="fileList" />'
                htmlData += '<button type="button" id="delete' + count + '" onclick="deleteNewFile(this)">삭제</button>';
                htmlData += '</div>'
                $("#file-list").append('')
                $("#file-list").append(htmlData);
                count++;
            }
        }
    })
}

function addFileUpdateForm(obj){
    let id = $(obj).data('id')
    let formData = {
        id : id
    }
    let fileCount = $('.fileCount').length
    $.ajax({
        method: 'GET',
        url: '/board/validation/file/count',
        data: formData,
        success: function (fileLimitCount) {
            if (fileCount >= fileLimitCount) {
                alert("파일은 최대 " + fileLimitCount + "개 까지 업로드 가능합니다.")
            }

            if (fileCount < fileLimitCount) {
                let htmlData = '';
                htmlData += '<div class="fileCount" id="Files' + count + '">'
                htmlData += '<input type="file" class="file" name="fileList" />'
                htmlData += '<button type="button" id="delete' + count + '" onclick="deleteNewFile(this)">삭제</button>';
                htmlData += '</div>'
                $("#file-list").append('')
                $("#file-list").append(htmlData);
                count++;
            }
        }
    })
}


function addFileForm() {
    let id = $(".selectbox option:selected").val()
    let formData = {
        id : id
    }
    let fileCount = $('.fileCount').length
    $.ajax({
        method: 'GET',
        url: '/board/validation/file/count',
        data: formData,
        success: function (fileLimitCount) {
            if (fileCount >= fileLimitCount) {
                alert("파일은 최대 " + fileLimitCount + "개 까지 업로드 가능합니다.")
            }

            if (fileCount < fileLimitCount) {
                let htmlData = '';
                htmlData += '<div class="fileCount" id="Files' + count + '">'
                htmlData += '<input type="file" class="file" name="fileList" />'
                htmlData += '<button type="button" id="delete' + count + '" onclick="deleteNewFile(this)">삭제</button>';
                htmlData += '</div>'
                $("#file-list").append('')
                $("#file-list").append(htmlData);
                count++;
            }
        }
    })
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
        data : idData,
        }).done(function (){
            location.href='/admin'
    })
}

