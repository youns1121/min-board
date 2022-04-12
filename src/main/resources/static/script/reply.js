function addReply(obj){

    let id = parseInt($('#id').val())
    let dataDepth = $(obj).data('depth')
    let dataSort=  $(obj).data('sort')
    let dataGroup= $(obj).data('group')

    location.href='/board/reply'

    $('#buttonCreate').value(dataDepth)





}