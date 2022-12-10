package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateInnerTax {
	public static Integer calculate(Double taxRate,Integer price) {
		
		BigDecimal bigPrice =new BigDecimal(price);
		BigDecimal bigTaxRate =  new BigDecimal(taxRate).setScale(2,RoundingMode.HALF_UP);
		BigDecimal one =  new BigDecimal(1);
		BigDecimal taxRatePlus = one.add(bigTaxRate);
		BigDecimal bigInnerTax = bigPrice.multiply(bigTaxRate);
		BigDecimal big2 = bigInnerTax.divide(taxRatePlus,RoundingMode.DOWN);
		Integer innerTax =big2.intValue();
		System.out.println(bigPrice);
		System.out.println(bigTaxRate);
		System.out.println(taxRatePlus);
		System.out.println(bigInnerTax);
		System.out.println(big2);
		System.out.println(innerTax);
		
		return innerTax;
	}

}
