public interface IReceiptBuilder {
    public void appendHeader(String header);
    public void appendCustomer(CustomerModel customer);
    public void appendProduct(ProductModel product);
    public void appendProductPurchase(ProductModel product, PurchaseModel purchase);
    public void appendPurchase(PurchaseModel purchase);
    public void appendFooter(String footer);

    public void toString(String header, CustomerModel customer, ProductModel product, PurchaseModel purchase, String footer);
}
