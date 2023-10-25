package tuslibros.com.supermarket;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

	/**
	 *
	 *
	 * Resource: /checkOutCart Parameters:
	 *
	 * cartId: cart id created using /createCart
	 * ccn: Credit card number
	 * cced: Credit card expiration date, with 2 digits for the month and 4 digits for the year
	 * cco: Credit card owner’s name.
	 * Output:
	 * On success: 0|TRANSACTION_ID
	 * On error: 1|ERROR_DESCRIPTION
	 */
//	@Test
//	public void testCanNotCheckoutEmptyCart() {
//		CreditCard creditCard = new CreditCard("1234567890123456", "012019", "John Doe");
//		Cart emptyCart = createCartWithCatalogWithProducts();
//
//		assertThrows(RuntimeException.class, () -> {emptyCart.checkout(creditCard);});
//	}
//
//	@Test
//	public void testCanCheckoutCartWithOneProduct() {
//		Cart cart = createCartWithCatalogWithProducts();
//		cart.add(productSellBySupermarket(), 1);
//
//		CreditCard creditCard = new CreditCard("1234567890123456", "012019", "John Doe");
//		Receipt receipt = cart.checkout(creditCard);
//
//		assertEquals(new BigDecimal("10.00"), receipt.getTotal());
//	}

	@Test
	public void testTotalPriceWithNoProducts() {
		Cart emptyCart = new Cart(catalogWithProducts());
		BigDecimal total = emptyCart.getTotal();
		assertEquals(new BigDecimal("0.00"), total);
	}

	@Test
	public void testTotalPriceWithOneProduct() {
		Cart cart = createCartWithCatalogWithProducts();
		cart.add(productSellBySupermarket(), 1);
		BigDecimal total = cart.getTotal();

		assertEquals(priceOfProductsSellBySupermarket(), total);
	}

	@Test
	public void testTotalPriceWithTwoProducts() {
		Cart cart = createCartWithCatalogWithProducts();
		cart.add(productSellBySupermarket(), 1);
		cart.add(otherProductSellBySupermarket(), 2);
		BigDecimal actualTotal = cart.getTotal();
		BigDecimal expectedTotal = priceOfProductsSellBySupermarket().add(priceOfOtherProductSellBySupermarket().multiply(new BigDecimal(2)));
		assertEquals(expectedTotal, actualTotal);
	}

	public Cart createCartWithCatalogWithProducts() {
		return new Cart(catalogWithProducts());
	}

	private Map<Object, BigDecimal> catalogWithProducts() {
		Map<Object, BigDecimal> catalog = new HashMap<>();

		catalog.put(productSellBySupermarket(), priceOfProductsSellBySupermarket());
		catalog.put(otherProductSellBySupermarket(), priceOfOtherProductSellBySupermarket());
		
		return catalog;
	}

	private static BigDecimal priceOfProductsSellBySupermarket() {
		return new BigDecimal("10.00");
	}

	private static BigDecimal priceOfOtherProductSellBySupermarket() {
		return new BigDecimal("1.00");
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
