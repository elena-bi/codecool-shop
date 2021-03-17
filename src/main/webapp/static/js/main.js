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
            let productId = btn.id;
            let sidebar = document.querySelector('.sidenav');
            if (findProductInCartAndIncrement(productId)) {}
            else {
                let cartProductDiv = `
                        <div class="cart-item" id="${productId}">
                            <div class="product-name">${productName}</div>
                            <div class="product-price">${productPrice}</div>
                            <div class="quantity">${quantity}</div>
                        </div>`;

                sidebar.style.display = "block";

                let plus = document.createElement("a");
                let minus = document.createElement("a");

                plus.innerText = "+";
                minus.innerText = "-";

                plus.setAttribute("class", "plus");
                minus.setAttribute("class", "minus");

                plus.addEventListener("click", () => {
                    console.log(plus.parentElement);
                });
                minus.addEventListener("click", () => {
                    console.log(plus.parentElement);
                });

                sidebar.insertAdjacentHTML("beforeend", cartProductDiv);

                sidebar.insertAdjacentElement("beforeend", plus);
                sidebar.insertAdjacentElement("beforeend", minus);
            }
            }
        ))
}

function findProductInCartAndIncrement(productId) {
    let cart = document.querySelectorAll(".cart-item");
    for (let cartItem of cart) {
        if (parseInt(cartItem.id) == parseInt(productId)) {
            cartItem.querySelector(".quantity").innerText =
                parseInt(cartItem.querySelector(".quantity").innerText) + 1;
            return true;
        }
    }
    return false;
}

init()