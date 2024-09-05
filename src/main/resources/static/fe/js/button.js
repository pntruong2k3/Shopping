
 function addToCart(productId) {
     // Thực hiện AJAX request để thêm sản phẩm vào giỏ hàng
     var xhr = new XMLHttpRequest();
     xhr.open('POST', '/add-to-cart', true);
     xhr.setRequestHeader('Content-Type', 'application/json');

     xhr.onload = function() {
         if (xhr.status >= 200 && xhr.status < 300) {
             // Xử lý thành công
             var countSpan = document.querySelector('.wishlist-count');
             var currentCount = parseInt(countSpan.textContent);
             countSpan.textContent = currentCount + 1;
         } else {
             // Xử lý lỗi
             console.error('Request failed with status ' + xhr.status);
         }
     };

     xhr.send(JSON.stringify({productId: productId}));
 }
1
 
