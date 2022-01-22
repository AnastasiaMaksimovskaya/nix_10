elementListSort = document.querySelectorAll("a.order");
let newUrlSort;
let baseUrlSort = window.location.href;
for (let button of elementListSort) {
    $(button).on('click', function () {
        if ( !baseUrlSort.includes('brand')){
            if (button.id==='createdUp'){
                newUrlSort= '?sort=created&order=asc&page=1&size='+ document.getElementById('hiddenSort').value;
            }
            else if(button.id==='priceUp') {
                newUrlSort= '?sort=price&order=asc&page=1&size='+ document.getElementById('hiddenSort').value;
            }
            else if(button.id==='priceDown') {
                newUrlSort= '?sort=price&order=desc&page=1&size='+ document.getElementById('hiddenSort').value;
            }
        }
        else if (baseUrlSort.includes("brand")){
            let index = baseUrlSort.lastIndexOf('&');
            let substr = baseUrlSort.substring(index,baseUrlSort.length);
            if (button.id==='createdUp'){
                newUrlSort= '?sort=created&order=asc&page=1&size='+ document.getElementById('hiddenSort').value+substr;
            }
            else if(button.id==='priceUp') {
                newUrlSort= '?sort=price&order=asc&page=1&size='+ document.getElementById('hiddenSort').value+substr;
            }
            else if(button.id==='priceDown') {
                newUrlSort= '?sort=price&order=desc&page=1&size='+ document.getElementById('hiddenSort').value+substr;
            }
        }
        history.replaceState(null, null, newUrlSort);
        location.reload();
    })
}

