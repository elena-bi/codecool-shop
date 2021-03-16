function init() {

    addToCartButtonListener()
}

function addToCartButtonListener() {
    document.querySelectorAll('.btn.btn-success')
        .forEach(btn => btn.addEventListener("click",
            () => {
            let productName = btn.name;
            let productPrice = btn.parentElement.parentElement.firstElementChild.innerText;
            let quantity = 1;
            


            }
        ))
}

init()