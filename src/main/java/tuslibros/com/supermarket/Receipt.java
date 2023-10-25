package tuslibros.com.supermarket;

import java.math.BigDecimal;

public class Receipt {

    BigDecimal total;

    public Receipt(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
