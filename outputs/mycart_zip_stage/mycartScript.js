function decreaseFunction() {
    const amount = document.getElementsByName("amount")[0];
    let a = parseInt(amount.value);
    if (a > 1) {
        a--;
        amount.value = a;
        document.getElementById("inc").disabled = false;
        if (a == 1) {
            document.getElementById("dec").disabled = true;
        }
    }
}

function increaseFunction() {
    const amount = document.getElementsByName("amount")[0];
    let a = parseInt(amount.value);
    const stock = document.getElementById("stock").innerText;
    if (a < parseInt(stock)) {
        a++;
        amount.value = a;
        document.getElementById("dec").disabled = false;
        if (a == parseInt(stock)) {
            document.getElementById("inc").disabled = true;
        }
    }
}

function amountFunction() {
    const amount = document.getElementsByName("amount")[0];
    let v = amount.value;
    const stock = document.getElementById("stock").innerText;
    if (isNaN(v) || v.trim() == '') {
        amount.value = 1;
    } else {
        let a = parseInt(amount.value);
        if (a < 1 || a > parseInt(stock)) {
            amount.value = 1;
        }
    }
}

function addToCartFunction() {
    const url = "/api/users/cart";
    const formData = new FormData(document.getElementById("addToCartForm"));
    alert(formData.get("goodsId") + "|" + formData.get("amount"));
    postFetchCartFunction(url, formData);
}

function postFetchCartFunction(url, formData) {
    fetch(url, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                return "出现异常";
            }
        })
        .then(resdata => {
            if (resdata.code == 401) {
                location.href = '/html/users/login.html';
            } else {
                alert(resdata.message);
            }
        })
        .catch(error => console.error('Error:', error));
}

function getMyCartFunction() {
    const url = "/api/users/cart";
    getFetchCartFunction(url);
}

function getFetchCartFunction(url) {
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
            if (resdata.code == 401) {
                location.href = '/html/users/login.html';
            } else {
                if (resdata.success) {
                    const myCart = resdata.data;
                    if (myCart.length == 0) {
                        document.getElementById('msg').innerText = '购物车是空的';
                    } else {
                        showMyCartFunction(myCart);
                    }
                }
            }
        })
        .catch(error => console.error('Error:', error));
}

function showMyCartFunction(cartList) {
    const tBody = document.getElementById('cartTable');
    tBody.innerHTML = '';

    for (let i in cartList) {
        const tr = document.createElement('tr');
        tBody.appendChild(tr);
        if (i % 2 == 0) {
            tr.style = 'background-color:#EEEEEE';
        }

        tr.setAttribute("data-cart-id", cartList[i].cartId);
        tr.setAttribute("data-goods-id", cartList[i].goodsId);

        for (let j = 0; j < 7; j++) {
            const td = document.createElement('td');
            tr.appendChild(td);
            td.style = 'padding:5px;';
        }

        tr.childNodes[0].innerHTML = "<input type='checkbox' onchange='checkFunction()'/>";
        tr.childNodes[1].innerHTML = "<a href='/html/shopping/goods.html?id=" + cartList[i].goodsId + "' " +
            "style='text-decoration:none;'>" +
            "<img src='/images/goods/" + cartList[i].goodsId + ".jpg'" + " width='50' height='50' /></a>";
        tr.childNodes[2].innerText = cartList[i].goodsName;
        tr.childNodes[3].innerText = cartList[i].price.toFixed(2);
        tr.childNodes[4].innerText = cartList[i].amount;
        tr.childNodes[5].innerText = (cartList[i].amount * cartList[i].price).toFixed(2);
        tr.childNodes[6].innerHTML = "<button onclick='deleteCurrentRow(this)'>删除</button>";
    }
}

function deleteCurrentRow(obj) {
    if (confirm('确定要删除吗？')) {
        const tr = obj.parentNode.parentNode;
        const cartId = tr.dataset.cartId;

        const url = `/api/users/cart/${cartId}`;
        deleteFetchCartFunction(url, obj);
    }
}

function deleteFetchCartFunction(url, obj) {
    const para = "t=" + Math.random();
    fetch(`${url}?${para}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                return "出现异常";
            }
        })
        .then(resdata => {
            if (resdata.code == 401) {
                location.href = '/html/users/login.html';
            } else {
                if (resdata.success) {
                    const tr = obj.parentNode.parentNode;
                    const tBody = tr.parentNode;
                    tBody.removeChild(tr);
                    document.getElementById('msg').innerText = '删除成功';
                } else {
                    document.getElementById('msg').innerText = '删除异常：删除0或多条';
                }
            }
        })
        .catch(error => console.error('Error:', error));
}
