window.onload = () => {
    fetch("/api/users/myaccount")
        .then(response => response.json())
        .then(json => {
            if (json.code === 401) {
                location.href = "/html/users/login.html";
                return;
            }
            if (json.success) {
                const username = document.getElementById("username");
                if (username) {
                    username.innerText = json.data.username;
                }
                const myaccount = document.getElementById("myaccount");
                if (myaccount) {
                    myaccount.innerText = "用户名：" + json.data.username;
                }
            }
        });
};
