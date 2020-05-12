package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.Product;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class RequestItemBuilder {

    private static Product sampleProduct = new Product(Id.generate(), new Money(5), "Bag",
        ProductType.STANDARD);

    public static RequestItem buildRequestItem() {
        return new RequestItem(sampleProduct.generateSnapshot(), 1, Money.ZERO);
    }
}
