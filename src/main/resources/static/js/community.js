function post() {
    var id = Number($("#question_id").val());
    var content = $("#comment_content").val();
    if(!content){
        alert("评论不能为空~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": id,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if(response.code == 200){
               window.location.reload();
            } else{
                if(response.code == 2003){
                    var isOK = confirm(response.message);
                    if(isOK){
                        window.open("https://github.com/login/oauth/authorize?client_id=a0ed95dee88b2851dccf&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else{
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}