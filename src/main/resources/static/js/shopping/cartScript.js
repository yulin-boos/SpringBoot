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
