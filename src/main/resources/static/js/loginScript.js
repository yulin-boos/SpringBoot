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

function loginFunction() {
    const username = document.getElementsByName("username")[0];
    const password = document.getElementsByName("password")[0];
    const captcha = document.getElementsByName("captcha")[0];
    const msg = document.getElementById("msg");

    if (username.value.trim() === "") {
        msg.innerText = "用户名为空";
        username.focus();
        return false;
    }

    if (password.value.trim() === "") {
        msg.innerText = "密码不能为空";
        password.focus();
        return false;
    }

    if (captcha.value.trim() === "") {
        msg.innerText = "验证码不能为空";
        captcha.focus();
        return false;
    }

    const formData = new FormData(document.getElementById("loginForm"));
    postFetchFunction("/api/login", formData)
        .then(json => {
            msg.innerText = json.message;
            if (json.success) {
                setTimeout(() => {
                    location.href = "/html/users/welcome.html";
                }, 1000);
            } else {
                refreshFunction();
            }
        })
        .catch(error => {
            console.error(error);
            msg.innerText = "服务器异常，请稍后重试";
            refreshFunction();
        });

    return false;
}

function refreshFunction() {
    document.getElementById("mycode").src = "/varificationcode?t=" + Math.random();
}
