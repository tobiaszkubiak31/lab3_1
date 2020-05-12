package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.Product;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

@ExtendWith(MockitoExtension.class)
class BookKeeperTest {


    private static InvoiceFactory invoiceFactory;
    private static Tax tax;

    @BeforeAll
    static public void setUpVar() {
        invoiceFactory = new InvoiceFactory();
        tax = new Tax(Money.ZERO, "test");
    }

    @Test
    void givenOneItemAsRequestItem_checkIfIssuanceReturnOneItem() {
        BookKeeper bookKeeper = new BookKeeper(invoiceFactory);
        TaxPolicy taxPolicy = Mockito.mock(TaxPolicy.class);
        when(taxPolicy.calculateTax(any(), any()))
            .thenReturn(this.tax);

        InvoiceRequest invoiceRequest = new InvoiceRequestBuilder()
            .addRequestItem(RequestItemBuilder.buildRequestItem())
            .build();

        Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);

        Assertions.assertEquals(1, invoice.getItems().size());
    }

    @Test
    void givenThreeItemAsRequestItem_checkIfIssuanceReturnThreeItems() {
        BookKeeper bookKeeper = new BookKeeper(invoiceFactory);
        TaxPolicy taxPolicy = Mockito.mock(TaxPolicy.class);
        when(taxPolicy.calculateTax(any(), any()))
            .thenReturn(this.tax);

        InvoiceRequest invoiceRequest = new InvoiceRequestBuilder()
            .addRequestItem(RequestItemBuilder.buildRequestItem())
            .addRequestItem(RequestItemBuilder.buildRequestItem())
            .addRequestItem(RequestItemBuilder.buildRequestItem())
            .build();

        Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);

        Assertions.assertEquals(3, invoice.getItems().size());
    }

    @Test
    void givenTaxPolicyNullObject_checkIfNullExceptionThrowed() {
        BookKeeper bookKeeper = new BookKeeper(invoiceFactory);

        InvoiceRequest invoiceRequest = new InvoiceRequestBuilder()
            .addRequestItem(RequestItemBuilder.buildRequestItem())
            .build();

        Assertions.assertThrows(NullPointerException.class,
            () -> bookKeeper.issuance(invoiceRequest, null));
    }


}
