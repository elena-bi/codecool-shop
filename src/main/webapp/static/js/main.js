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
            let sidebar = document.querySelector('.sidenav');
            let cartProductDiv = `
                        <div class="cart-item">
                            <div class="product-name">${productName}</div>
                            <div class="product-price">${productPrice}</div>
                            <div class="quantity">${quantity}</div>
                            <div class="plus-minus">
                                <div class="quant-plus">+</div>/<div class="quant-minus">-</div>
                            </div> 
                        </div>`;
                sidebar.style.display = "flex";
                sidebar.insertAdjacentHTML("beforeend", cartProductDiv);

            }
        ))
}

init()