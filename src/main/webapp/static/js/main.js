function init() {

    addToCartButtonListener()
}

function addToCartButtonListener() {
    document.querySelectorAll('.btn.btn-success')
        .forEach(btn => btn.addEventListener("click",
            () => {
            // add product ID
            let productName = btn.name;
            let productPrice = btn.parentElement.parentElement.firstElementChild.innerText;
            let quantity = 1;
            let productId = btn.id;
            let sidebar = document.querySelector('.sidenav');
            // add product ID, and search if item already in cart
            let cartProductDiv = `
                        <div class="cart-item" id="${productId}">
                            <div class="product-name">${productName}</div>
                            <div class="product-price">${productPrice}</div>
                            <div class="quantity">${quantity}<a>+</a><a>-</a></div>
                        </div>`;
                // add + / - separately with event listeners
                sidebar.style.display = "block";
                sidebar.insertAdjacentHTML("beforeend", cartProductDiv);

            }
        ))
}

init()