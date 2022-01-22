elementList = document.querySelectorAll("#flexCheckDefault");
let newUrl;
var baseUrl = window.location.href;
if (!baseUrl.includes('page')){
    baseUrl = baseUrl+document.getElementById('hidden').value;
}
var url = window.location.href;
for (let singleBox of elementList) {
    $(singleBox).on('click', function () {
        if ($(singleBox).is(':checked')) {
            if (baseUrl.includes(singleBox.value)){
                return
            }
            if (baseUrl.includes('brand=')) {
                newUrl = baseUrl + ',' + singleBox.value;
                history.replaceState(null, null, newUrl);
            }
            else {
                newUrl = baseUrl + '&brand=' + singleBox.value;
                history.replaceState(null, null, newUrl);
            }
        }
        else{
            if (baseUrl.includes(','+singleBox.value)) {
                newUrl = baseUrl.replace( ','+singleBox.value,'') ;
                history.replaceState(null, null, newUrl);
            }
            else if (baseUrl.includes(singleBox.value+',')){
                newUrl = baseUrl.replace(singleBox.value+',','') ;
                history.replaceState(null, null, newUrl);
            }
            else {
                newUrl = url;
                history.replaceState(null, null, newUrl);
            }
        }
        location.reload();
    })
}
