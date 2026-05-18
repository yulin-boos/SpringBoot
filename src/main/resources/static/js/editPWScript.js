function editPWFunction() {
    const password = document.getElementsByName("password")[0].value;
    const rePassword = document.getElementsByName("rePassword")[0].value;
    const msg = document.getElementById("msg");

    if (password.trim() === "") {
        msg.innerText = "密码不能为空";
        return false;
    }

    if (password !== rePassword) {
        msg.innerText = "两次密码不一致";
        return false;
    }

    const formData = new FormData(document.getElementById("editPWForm"));
    fetch("/api/users/myaccount", {
        method: "PUT",
        body: formData
    })
        .then(response => response.json())
        .then(json => {
            msg.innerText = json.message;
        });

    return false;
}
