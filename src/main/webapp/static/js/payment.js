init();

function init() {
    onPaymentMethodSubmit();
}

function onPaymentMethodSubmit() {
    document.getElementById('submit-payment-type').addEventListener('click', getPaymentMethod);
}

function getPaymentMethod() {
    if (document.getElementById('bank-card').checked) {
        createBankCardField();
    } else if (document.getElementById('paypal').checked) {
        document.getElementById('main').innerHTML = '';
        createPayPalField();
    } else {
        console.log('none');
    }
}

function createPayPalField() {

}

function createBankCardField() {

}