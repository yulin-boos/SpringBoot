window.onload = () => {
    getMyAccountFunction();

    const queryStr = location.search;
    const goodsId = queryStr.split("=")[1];
    const url = "/api/users/goodses/" + goodsId;
    getFetchGoodsFunction(url);
};

function getFetchGoodsFunction(url) {
    const para = "t=" + Math.random();
    fetch(`${url}?${para}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                return "出现异常";
            }
        })
        .then(resdata => {
            if (resdata.code === 401) {
                location.href = "/html/users/login.html";
            } else {
                if (resdata.success) {
                    const goods = resdata.data;
                    showGoodsFunction(goods);
                }
            }
        })
        .catch(error => console.error("Error:", error));
}

function showGoodsFunction(goods) {
    const goodsIdValue = goods.id ?? goods.goodsId;
    const title = document.getElementsByTagName("title")[0];
    title.innerText = goods.goodsName;

    const goodsImg = document.getElementById("goodsImg");
    goodsImg.src = "/images/goods/" + goodsIdValue + ".jpg";

    const goodsId = document.getElementsByName("goodsId")[0];
    goodsId.value = goodsIdValue;

    for (let key in goods) {
        const item = document.getElementById(key);
        if (item) {
            if (key === "price") {
                item.innerText = Number(goods[key]).toFixed(2);
            } else {
                item.innerText = goods[key];
            }
        }
    }
}
