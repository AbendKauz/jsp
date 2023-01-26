function CheckAddProduct(){
	
	let productId = document.getElementById("productId");	// 상품코드
	// let productId = document.newProduct.id.value;
	let name = document.getElementById("pname");	// 상품명
	let unitPrice = document.getElementById("unitPrice");	// 상품가격
	let unitsInStock = document.getElementById("unitsInStock");	// 재고수량
	
	let regId = /^[a-z|A-Z|0-9]*$/;
	let regName = /^[a-zA-Z0-9가-힣]{4,12}$/;
	let regPrice = /^[0-9]*$/;
	let regStock = /^[0-9]*$/;
	
	if(productId.value == ""){
		alert("상품코드를 입력하세요.");
		productId.focus();
		return false;
	}else if(!regId.test(productId.value)){
		alert("상품코드는 영어+숫자만 입력하세요.");
		productId.value = "";
		productId.focus();
		return false;
	}
	
	if(name.value == ""){
		alert("상품명을 입력하세요.");
		name.focus();
		return false;
	} else if(!regName.test(name.value)){
		alert("상품명은 4~12자 사이로 입력하세요.");
		name.value = "";
		name.focus();
		return false;
	}
	
	if(unitPrice.value == ""){
		alert("가격을 입력하세요.");
		unitPrice.focus();
		return false;
	} else if(!regPrice.test(unitPrice.value)){
		alert("가격은 숫자만 입력하세요.");
		unitPrice.value = "";
		unitPrice.focus();
		return false;
	}
	
	if(unitsInStock.value == ""){
		alert("재고량을 입력하세요.");
		unitsInStock.focus();
		return false;
	} else if(!regStock.test(unitsInStock.value)){
		alert("재고량은 숫자만 입력하세요.");
		unitsInStock.value = "";
		unitsInStock.focus();
		return false;
	}
	newProduct.submit();
}