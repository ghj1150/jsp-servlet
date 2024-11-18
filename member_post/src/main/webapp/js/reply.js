// const replyService =  {};
const replyService = (function() {
    const url = "/member_post/reply";

    function write(reply,callback) {
        const data = JSON.stringify(reply);
        $.post({url,data}).done(function(data){
            if(callback)
            callback(data);
        })
    }
    function list(pno,callback) {
        $.getJSON(url + "/list/" + pno).done(function(data) {
            if(callback)
            callback(data);
        });
    }
    function view(rno, callback) {
        $.getJSON(url + "/" + rno).done(function(data) {
            if(callback)
            callback(data);
        })
    }
    function modify(reply, callback) {
        const data = JSON.stringify(reply);
        $.ajax(url, {
            method : 'put',
            data
        }).done(function(data) {
            if(callback)
            callback(data);
        })
    }
    function remove(rno, callback) {
        console.log(rno);
        $.ajax(url + "/" + rno, {
            method : 'delete'
        }).done(function(data) {
            if(callback)
            callback(data);
        })
    }
    return {write, list, view, modify, remove}
})();