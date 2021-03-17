function init() {

    cancelCart()
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
                    plus.parentElement.querySelector(".quantity").innerText =
                        parseInt(plus.parentElement.querySelector(".quantity").innerText) + 1;
                });
                minus.addEventListener("click", () => {
                    if (parseInt(plus.parentElement.querySelector(".quantity").innerText) > 1) {
                        plus.parentElement.querySelector(".quantity").innerText =
                            parseInt(plus.parentElement.querySelector(".quantity").innerText) - 1;
                    } else {
                        plus.parentElement.remove();
                    }
                });

                sidebar.insertAdjacentHTML("beforeend", cartProductDiv);

                sidebar.querySelector(`div[id="${productId}"]`).insertAdjacentElement("beforeend", minus);
                sidebar.querySelector(`div[id="${productId}"]`).insertAdjacentElement("beforeend", plus);
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

function cancelCart() {
    document.querySelector(".cancel-cart").addEventListener("click", () => {
        document.querySelectorAll(".cart-item").forEach(item => item.remove());
        document.querySelector(".sidenav").style.display = "none";
    })
}

init()