window.onload = () => {
    fetch("/api/users/myaccount")
        .then(response => response.json())
        .then(json => {
            if (json.code === 401) {
                location.href = "/html/users/login.html";
                return;
            }
            if (json.success) {
                document.getElementById("username").innerText = json.data.username;
                document.getElementsByName("username")[0].value = json.data.username;
            }
        });
};
