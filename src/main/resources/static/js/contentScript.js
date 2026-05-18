window.addEventListener("DOMContentLoaded", () => {
    getFetchTypeFunction("/api/goodstypes");

    const queryStr = location.search;
    const search = queryStr.split("=")[1];
    if (search !== undefined && search.trim() !== "") {
        const searchInput = document.getElementById("search");
        if (searchInput) {
            searchInput.value = decodeURIComponent(search);
        }
        getFetchSearchFunction(decodeURIComponent(search));
    } else {
        getFetchFunction("/api/goodses");
    }
});

function withCacheBuster(url) {
    const separator = url.includes("?") ? "&" : "?";
    return `${url}${separator}t=${Date.now()}`;
}

function getFetchTypeFunction(url) {
    fetch(withCacheBuster(url))
        .then(response => response.ok ? response.json() : Promise.reject(new Error("商品分类加载失败")))
        .then(resdata => {
            if (resdata.success) {
                showTypeListFunction(resdata.data);
            }
        })
        .catch(error => console.error("Error:", error));
}

function showTypeListFunction(typeList) {
    const content = document.getElementById("goodsTypeList");
    content.innerHTML = "";

    for (const type of typeList) {
        const typeId = type.id ?? type.typeId;
        const typeName = type.gtName ?? type.typeName;
        content.innerHTML += "<li>" +
            "<a href='#' style='text-decoration:none;' onclick='getByTypeFunction(" + typeId + ")'>" +
            typeName + "</a></li>";
    }
}

function getFetchFunction(url) {
    fetch(withCacheBuster(url))
        .then(response => response.ok ? response.json() : Promise.reject(new Error("商品列表加载失败")))
        .then(resdata => {
            if (resdata.success) {
                showGoodsListFunction(resdata.data);
            }
        })
        .catch(error => console.error("Error:", error));
}

function getByTypeFunction(typeId) {
    getFetchFunction(`/api/goodses?type=${typeId}`);
}

let allGoods = [];

function showGoodsListFunction(goodsList) {
    allGoods = goodsList;
    const content = document.getElementById("goodsList");
    content.innerHTML = "";

    for (const goods of goodsList) {
        const goodsId = goods.id ?? goods.goodsId;
        const goodsName = goods.goodsName ?? "";
        const price = goods.price ?? "";
        content.innerHTML += "<div style='display:flex;padding:2%;'>" +
            "<a href='#' style='text-decoration:none;width:200px;height:300px;'>" +
            "<img src='/images/goods/" + goodsId + ".jpg'" +
            " alt='" + goodsName + "' width='150' height='150' onerror=\"this.src='http://39.104.74.203/images/goods/" + goodsId + ".jpg'\" />" +
            "<br/>" + goodsName + "<br/>" +
            "<span style='color:red'>￥" + price + "</span>" +
            "</a>" +
            "</div>";
    }
}
