package tuslibros.com.supermarket;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    public static final String PRODUCT_IS_NOT_SELL_BY_SUPERMARKET = "Product is not sell by supermarket";
    public static final String PRODUCT_QUANTITY_MUST_BE_STRICTLY_POSITIVE = "Product quantity must be strictly positive";

    private List<Object> catalog;
    private List<Object> products = new ArrayList<Object>();

    public Cart(List<Object> catalog) {
        this.catalog = catalog;
    }

    public void add(Object aProduct) {
        add(aProduct, 1);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public boolean contains(Object aProduct) {
        return products.contains(aProduct);
    }

    public void add(Object aProduct, int aQuantity) {
        assertQuantityIsStrictlyPositive(aQuantity);
        assertProductIsSellBySupermarket(aProduct);

        for (int i = 0; i < aQuantity; i++)
            products.add(aProduct);
    }

    public long numberOf(Object aProduct) {
        return products.stream().
            filter(addedProduct -> addedProduct.equals(aProduct)).
            count();

//	Para Java pre 1.8
//		long count = 0;
//		for (Object product : products)
//			if(aProduct.equals(product))
//				count ++;
//
//		return count;
    }

    public void assertProductIsSellBySupermarket(Object aProduct) {
        if (!catalog.contains(aProduct)) throw new RuntimeException(PRODUCT_IS_NOT_SELL_BY_SUPERMARKET);
    }

    public void assertQuantityIsStrictlyPositive(int aQuantity) {
        if (aQuantity < 1) throw new RuntimeException(PRODUCT_QUANTITY_MUST_BE_STRICTLY_POSITIVE);
    }

}
