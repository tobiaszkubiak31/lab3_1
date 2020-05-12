package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.util.ArrayList;
import java.util.List;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;

public class InvoiceRequestBuilder {

    private ClientData client = new ClientData(Id.generate(), "Michal");
    private List<RequestItem> items = new ArrayList<RequestItem>();

    public InvoiceRequestBuilder() {
    }

    InvoiceRequestBuilder addRequestItem(RequestItem item) {
        items.add(item);
        return this;
    }

    InvoiceRequestBuilder withRequestItems(List<RequestItem> items) {
        this.items = items;
        return this;
    }

    public InvoiceRequestBuilder withClient(ClientData client) {
        this.client = client;
        return this;
    }

    InvoiceRequest build() {

        return new InvoiceRequest(client, items);
    }

}
