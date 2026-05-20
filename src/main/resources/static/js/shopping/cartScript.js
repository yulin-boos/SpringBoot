function decreaseFunction() {
    const amount = document.getElementsByName("amount")[0];
    let value = Number(amount.value) || 1;
    value = Math.max(1, value - 1);
    amount.value = value;
    amountFunction();
}

function increaseFunction() {
    const amount = document.getElementsByName("amount")[0];
    amount.value = (Number(amount.value) || 1) + 1;
    amountFunction();
}

function amountFunction() {
    const amount = document.getElementsByName("amount")[0];
    const dec = document.getElementById("dec");
    const value = Number(amount.value) || 1;
    amount.value = value;
    dec.disabled = value <= 1;
}

function addToCartFunction() {
}
