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
    let form = document.createElement('form');
    form.innerHTML = '<label for="paypal-username">Username:</label><br>' +
        '<input type="text" id="paypal-username"><br>' +
        '<label for="paypal-password">Password:</label><br>' +
        '<input type="password" id="paypal-password"><br>' +
        '<input type="button" id="submit-paypal-payment" value="Submit">';
    document.getElementById('main').appendChild(form);
    document.getElementById('submit-paypal-payment').addEventListener('click', submitPayPalPayment);
}

function createBankCardField() {

}

function submitPayPalPayment() {
    console.log('pay');
}