init();

function init() {
    onPaymentMethodSubmit();
}

function onPaymentMethodSubmit() {
    document.getElementById('submit-payment-type').addEventListener('click', getPaymentMethod);
}

function getPaymentMethod() {
    if (document.getElementById('bank-card').checked) {
        document.getElementById('main').innerHTML = '';
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
    let form = document.createElement('form');
    form.innerHTML = '<label for="card-number">Card number:</label><br>' +
        '<input type="number" id="card-number"><br>' +
        '<label for="card-holder-name">Card holder:</label><br>' +
        '<input type="text" id="card-holder-name"><br>' +
        '<label for="expiry-date">Expiry date:</label><br>' +
        '<input type="month" id="expiry-date"><br>' +
        '<label for="safety-code">Safety code:</label><br>' +
        '<input type="password" id="safety-code"><br>' +
        '<input type="button" id="submit-card-payment" value="Submit">';
    document.getElementById('main').appendChild(form);
    document.getElementById('submit-card-payment').addEventListener('click', submitCardPayment);
}

function submitPayPalPayment() {
    let data = getPayPalData();
    if (data == null) {
        console.log('Data not satisfied');
        return;
    }
    fetch('/payment/paypal', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.json())
        .then(resp => console.log(resp)); // Temporary
}

function submitCardPayment() {
    console.log('card pay');
}

function getPayPalData() {
    let userNameElem = document.getElementById('paypal-username');
    let passwordElem = document.getElementById('paypal-password');
    if (userNameElem == null) return null;
    if (userNameElem.value.length === 0) return null;
    if (passwordElem == null) return null;
    if (passwordElem.value.length === 0) return null;
    return {
        'username' : `${userNameElem.value}`,
        'password' : `${passwordElem.value}`
    }
}