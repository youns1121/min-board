jQuery.fn.serializeObject = function() {
    var obj = null;
    try {
        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function() {
                    obj[this.name] = this.value;
                });
            }//if ( arr ) {
        }
    } catch (e) {
        alert(e.message);
    } finally {
    }

    return obj;
};



function createAdminFn(){

    var obj = $('#boardAdminDetails').serializeObject();

    $.ajax({
        url: '/admin/setting',
        type : "POST",
        data : JSON.stringify(obj),
        contentType: "application/json",
        dataType : "JSON",
        success: function(returnData) {
            alert('등록되었습니다.')
            location.href = '/admin/view/' + returnData + '?'+"status="+true
        },
        error : function (){
            return false;
        }
    });
}


function deleteAdminFn(adminId){

    $.ajax({
        method: 'POST',
        url: '/admin/remove',
        data: adminData = {id : adminId },
        success: function (){
            alert("삭제 완료되었습니다.")
            location.href='/admin'
        }
    })
}

function updateAdminFn(){
    let formData = new FormData();
    formData.append("id", parseInt($('#adminUpdatebutton').val()))
    formData.append("categoryName", $('#categoryName').val())
    formData.append("contents", $('#contents').val())
    formData.append("commentsYn", $("input:radio[name=commentsYn]:checked").val())
    formData.append("replyYn", $("input:radio[name=replyYn]:checked").val())
    formData.append("boardFileYn", $("input:radio[name=boardFileYn]:checked").val())
    formData.append("boardFileCount", $("input:radio[name=boardFileCount]:checked").val())

    $.ajax({
        url: '/admin/setting/update',
        type : "POST",
        contentType : false,
        processData: false,
        data : formData,
        success: function(returnData) {
            alert('등록되었습니다.')
            location.href = '/admin/setting/update/' + returnData + '?'+"status="+true
        },
        error : function (){
            return false;
        }
    });
}

