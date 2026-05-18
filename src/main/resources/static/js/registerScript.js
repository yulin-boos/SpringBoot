async function postFetchFunction(url, formData) {
    const response = await fetch(url, {
        method: "POST",
        body: formData
    });
    if (!response.ok) {
        throw new Error("HTTP异常");
    }
    return await response.json();
}

function checkRegisterFunction() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const repassword = document.getElementById("repassword").value;
    const msg = document.getElementById("msg");

    if (username.trim() === "") {
        msg.innerText = "用户名不能为空";
        return false;
    }

    if (password.trim() === "") {
        msg.innerText = "密码不能为空";
        return false;
    }

    if (password !== repassword) {
        msg.innerText = "两次密码不一致";
        return false;
    }

    msg.innerText = "正在注册，请稍候...";

    const formData = new FormData(document.getElementById("regForm"));
    postFetchFunction("/api/register", formData)
        .then(json => {
            msg.innerText = json.message;
            if (json.success) {
                setTimeout(() => {
                    location.href = "/html/users/login.html";
                }, 1500);
            }
        })
        .catch(error => {
            console.error(error);
            msg.innerText = "服务器异常，请稍后重试";
        });

    return false;
}
