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
        alert('Credentials are invalid!');
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
        .then(resp => {
            if (resp) {
                window.location.href = '/confirmation';
            } else {
                alert('Credentials are invalid!');
            }
        }); // Temporary
}

function submitCardPayment() {
    let data = getCardData();
    if (data == null) {
        console.log('Data not satisfied');
        alert('Card details are not valid!');
        return;
    }
    fetch('payment/card', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.json())
        .then(resp => {
            if (resp) {
                window.location.href = '/confirmation';
            } else {
                alert('Card details are not valid!');
            }
        }); // Temporary
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

function getCardData() {
    let cardNumberElem = document.getElementById('card-number');
    let holderElem = document.getElementById('card-holder-name');
    let expiryElem = document.getElementById('expiry-date');
    let safetyCodeElem = document.getElementById('safety-code');
    if ((cardNumberElem == null) || (holderElem == null) || (expiryElem == null) || (safetyCodeElem == null)) return null;
    if ((cardNumberElem.value.length === 0) || (isNaN(cardNumberElem.value))) return null;
    if (holderElem.value.length === 0) return null;
    if ((safetyCodeElem.value.length === 0) || (isNaN(safetyCodeElem.value))) return null;
    return {
        'cardNumber' : `${cardNumberElem.value}`,
        'cardHolder' : `${holderElem.value}`,
        'expiryDate' : `${expiryElem.value}`,
        'safetyCode' : `${safetyCodeElem.value}`
    };
}