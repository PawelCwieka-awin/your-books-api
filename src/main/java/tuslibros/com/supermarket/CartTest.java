package tuslibros.com.supermarket;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartTest {

	@Test
	public void cartsAreCreatedEmpty (){
		assertTrue(createCartWithCatalogWithProducts().isEmpty());
	}

	@Test
	public void cartIsNotEmptyAfterAddingProducts (){
		Cart cart = createCartWithCatalogWithProducts();
		
		cart.add(productSellBySupermarket());
		
		assertFalse(cart.isEmpty());
	}

	@Test
	public void cartContainsAddedProduct(){
		Cart cart = createCartWithCatalogWithProducts();
		
		cart.add(productSellBySupermarket());
		
		assertTrue(cart.contains(productSellBySupermarket()));
	}

	@Test
	public void cartCanContainManyProducts(){
		Cart cart = createCartWithCatalogWithProducts();
		
		cart.add(productSellBySupermarket());
		cart.add(otherProductSellBySupermarket());
			
		assertTrue(cart.contains(productSellBySupermarket()));
		assertTrue(cart.contains(otherProductSellBySupermarket()));
	}
		
	@Test
	public void testCanNotAddProductsNotSellByTheSupermarket() {
		Cart cart = createCartWithCatalogWithProducts();
		
		try {
			cart.add(productNotSellBySupermarket());
			fail(); //assertTrue(false)
		} catch (RuntimeException e) {
			assertEquals(Cart.PRODUCT_IS_NOT_SELL_BY_SUPERMARKET,e.getMessage());
			assertTrue(cart.isEmpty());
		}
	}
	
	@Test
	public void testCanNotAddLessThanOneProduct() {
		Cart cart = createCartWithCatalogWithProducts();
		
		try {
			cart.add(productSellBySupermarket(),0);
			fail();
		} catch (RuntimeException e) {
			assertEquals(Cart.PRODUCT_QUANTITY_MUST_BE_STRICTLY_POSITIVE,e.getMessage());
			assertTrue(cart.isEmpty());
		}
	}
	
	@Test
	public void testCanAddManyProductsAtTheSameTime() {
		Cart cart = createCartWithCatalogWithProducts();
		
		cart.add(productSellBySupermarket(),2);
		
		assertEquals(2,cart.numberOf(productSellBySupermarket()));
	}

	@Test
	public void testCanAddSameProductMoreThanOnce() {
		Cart cart = createCartWithCatalogWithProducts();
		
		cart.add(productSellBySupermarket(),2);
		cart.add(productSellBySupermarket(),3);
		
		assertEquals(5,cart.numberOf(productSellBySupermarket()));
	}
	
	@Test
	public void testCanAddManyDifferentProductsAtTheSameTime() {
		Cart cart = createCartWithCatalogWithProducts();
		
		cart.add(productSellBySupermarket(),2);
		cart.add(otherProductSellBySupermarket(),3);
		
		assertEquals(2,cart.numberOf(productSellBySupermarket()));
		assertEquals(3,cart.numberOf(otherProductSellBySupermarket()));
	}

	public Cart createCartWithCatalogWithProducts() {
		return new Cart(catalogWithProducts());
	}

	private List<Object> catalogWithProducts() {
		List<Object> catalog = new ArrayList<Object>();
		
		catalog.add(productSellBySupermarket());
		catalog.add(otherProductSellBySupermarket());
		
		return catalog;
	}

	private Object productNotSellBySupermarket() {
		return "productNotSellBySupermarket";
	}

	private Object otherProductSellBySupermarket() {
		return "otherProductSellBySupermarket";
	}

	private Object productSellBySupermarket() {
		return "productSellBySupermarket";
	}


}
