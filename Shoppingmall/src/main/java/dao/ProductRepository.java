package dao;	// Database Access Object

import java.util.ArrayList;

import dto.Product;

public class ProductRepository {	// ��ǰ �����
	
	private ArrayList<Product> listOfProducts = new ArrayList<Product>();

	// ProductRepository Ŭ������ �⺻ �������� ��ü ���� �ۼ�, �ݵ�� static ����ؾ� ��
	private static ProductRepository instance = new ProductRepository();
	
	// ��ü ���� instance�� ���� getter �޼ҵ� �ۼ�
	public static ProductRepository getInstance() {
		return instance;
	}
	
	public ProductRepository() {
		Product phone = new Product("P1234", "iPhone 6s", 800000);
		phone.setDescription("4.7��ġ ���÷���, ��Ƽ�� �Ƹ�����, 8��ް� �ȼ� ī�޶�");
		phone.setCategory("Smart Phone");
		phone.setManufacturer("Apple");
		phone.setUnitsInStock(1000);
		phone.setCondition("New");
		phone.setFilename("P1234.png");
		
		Product notebook = new Product("P1235", "LG PC �׷�", 1500000);
		notebook.setDescription("13.3��ġ ���÷���, ips �г�, 5���� ���� �ھ� ���μ���");
		notebook.setCategory("Notebook");
		notebook.setManufacturer("LG");
		notebook.setUnitsInStock(1000);
		notebook.setCondition("Refurbished");
		notebook.setFilename("P1235.png");
		
		Product tablet = new Product("P1236", "Galaxy Tab S", 900000);
		tablet.setDescription("11��ġ ���÷���, ���� �Ƹ����� �г�, 6�� CPU ���");
		tablet.setCategory("Tablet");
		tablet.setManufacturer("Samsumg");
		tablet.setUnitsInStock(1000);
		tablet.setCondition("Old");
		tablet.setFilename("P1236.png");
		
		listOfProducts.add(phone);
		listOfProducts.add(notebook);
		listOfProducts.add(tablet);
		
	}
	
	// ��ü ������ �������� �޼ҵ�
	public ArrayList<Product> getAllProducts(){
		return listOfProducts;
	}
	
	// ��ǰ ���̵�� ��ġ�ϴ� ������ �������� �޼ҵ�
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
	
	// ���ο� ��ǰ ������ ����ϴ� addProduct �ۼ�
	public void addProduct(Product product) {
		listOfProducts.add(product);
	}
	
}
