function searchFunction() {
    const search = document.getElementById("search").value.trim();
    getFetchSearchFunction(search);
}

function getFetchSearchFunction(search) {
    const para = "t=" + Math.random();
    const url = `/api/goodses?search=${encodeURIComponent(search)}&${para}`;
    fetch(url)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                return "出现异常";
            }
        })
        .then(resdata => {
            if (resdata.success) {
                const goodsList = resdata.data;
                showGoodsListFunction(goodsList);
            }
        })
        .catch(error => console.error("Error:", error));
}

function newSearchFunction() {
    const search = document.getElementById("search").value.trim();
    const url = `/html/users/welcome.html?search=${encodeURIComponent(search)}`;
    window.open(url, "_blank");
}
