function logoutFunction() {
    fetch("/api/logout")
        .then(response => response.json())
        .then(json => {
            if (json.success) {
                location.href = "/html/users/login.html";
            }
        });
}
