/**
 * 评论功能
 */
function comment2Target(parentId, content, type) {
    if (!content) {
        alert("评论不能为空~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": parentId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isOK = confirm(response.message);
                    if (isOK) {
                        window.open("https://github.com/login/oauth/authorize?client_id=a0ed95dee88b2851dccf&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

/**
 * 一级评论
 */
function post() {
    var id = Number($("#question_id").val());
    var content = $("#comment_content").val();
    comment2Target(id, content, 1);
}

/**
 * 二级评论
 * @param e
 */
function comment(e) {
    var id = e.getAttribute("data-id");
    var content = $("#input-" + id).val();
    comment2Target(id, content, 2);
}


/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    // 获取二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.classList.remove("active");
        e.removeAttribute("data-collapse");
    } else {
        $.getJSON("/comment/" + id, function (resp) {
            // 展开二级标题
            comments.addClass("in");
            // 标记状态为展开
            e.classList.add("active");
            e.setAttribute("data-collapse", "in");
            // 追加标签
            // 格式化日期
            Vue.filter('dateformat', function(dataStr) {
                return moment(dataStr).format('YYYY-MM-DD HH:mm:ss')
            });
            var app = new Vue({
                el: "#comment-" + id,
                data: {
                    code:resp.code,
                    items: resp.data
                }
            });
        });
    }
}


