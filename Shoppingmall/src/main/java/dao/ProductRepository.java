package dao;	// Database Access Object

import java.util.ArrayList;

import dto.Product;

public class ProductRepository {	// 상품 저장소
	
	private ArrayList<Product> listOfProducts = new ArrayList<Product>();

	// ProductRepository 클래스의 기본 생성자의 객체 변수 작성, 반드시 static 사용해야 함
	private static ProductRepository instance = new ProductRepository();
	
	// 객체 변수 instance에 대한 getter 메소드 작성
	public static ProductRepository getInstance() {
		return instance;
	}
	
	public ProductRepository() {
		Product phone = new Product("P1234", "iPhone 6s", 800000);
		phone.setDescription("4.7인치 디스플레이, 레티나 아몰레드, 8백메가 픽셀 카메라");
		phone.setCategory("Smart Phone");
		phone.setManufacturer("Apple");
		phone.setUnitsInStock(1000);
		phone.setCondition("New");
		phone.setFilename("P1234.png");
		
		Product notebook = new Product("P1235", "LG PC 그램", 1500000);
		notebook.setDescription("13.3인치 디스플레이, ips 패널, 5세대 인텔 코어 프로세서");
		notebook.setCategory("Notebook");
		notebook.setManufacturer("LG");
		notebook.setUnitsInStock(1000);
		notebook.setCondition("Refurbished");
		notebook.setFilename("P1235.png");
		
		Product tablet = new Product("P1236", "Galaxy Tab S", 900000);
		tablet.setDescription("11인치 디스플레이, 슈퍼 아몰레드 패널, 6개 CPU 사용");
		tablet.setCategory("Tablet");
		tablet.setManufacturer("Samsumg");
		tablet.setUnitsInStock(1000);
		tablet.setCondition("Old");
		tablet.setFilename("P1236.png");
		
		listOfProducts.add(phone);
		listOfProducts.add(notebook);
		listOfProducts.add(tablet);
		
	}
	
	// 전체 정보를 가져오는 메소드
	public ArrayList<Product> getAllProducts(){
		return listOfProducts;
	}
	
	// 상품 아이디와 일치하는 정보를 가져오는 메소드
	public Product getProductById(String productId) {
		Product productById = null;
		
		for(int i = 0; i < listOfProducts.size(); i++) {
			Product product = listOfProducts.get(i);
			if(product != null && product.getProductId() != null 
					&& product.getProductId().equals(productId)) {
				productById = product;
				break;
			}
		}
		return productById;
	}
	
	// 새로운 상품 정보를 등록하는 addProduct 작성
	public void addProduct(Product product) {
		listOfProducts.add(product);
	}
	
}
