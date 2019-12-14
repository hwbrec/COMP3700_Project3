import java.math.RoundingMode;
import java.text.DecimalFormat;
public class TXTReceiptBuilder implements IReceiptBuilder {
    private static DecimalFormat df = new DecimalFormat("0.00");
    StringBuilder sb = new StringBuilder();

    @Override
    public void appendHeader(String header) {
        sb.append(header).append("\n");
    }

    @Override
    public void appendCustomer(CustomerModel customer) {
        sb.append("Customer ID: ").append(customer.mCustomerID).append("\n");
        sb.append("Customer Name: ").append(customer.mName).append("\n");
    }
    
    @Override
    public void appendProduct(ProductModel product) {
        sb.append("Product ID: ").append(product.mProductID).append("\n");
        sb.append("Product Name: ").append(product.mName).append("\n");
        sb.append("Product Price: ").append(product.mPrice).append("\n");
    }

    @Override
    public void appendProductPurchase(ProductModel product, PurchaseModel purchase) {
        sb.append("Product ID: ").append(product.mProductID).append("\n");
        sb.append("Product Name: ").append(product.mName).append("\n");
        sb.append("Product Price: ").append(product.mPrice).append("\n");
        sb.append("Purchase ID: ").append(purchase.mPurchaseID).append("\n");
        sb.append("Quantity: ").append(purchase.mQuantity).append("\n");
        sb.append("Cost: ").append(df.format(purchase.mQuantity * product.mPrice)).append("\n");
        sb.append("Tax: ").append(df.format((purchase.mQuantity * product.mPrice) * 0.04)).append("\n");
        sb.append("Total Cost: ").append(df.format((purchase.mQuantity * product.mPrice) + ((purchase.mQuantity * product.mPrice) * 0.04))).append("\n");
    }

    @Override
    public void appendPurchase(PurchaseModel purchase) {
        sb.append("Purchase ID: ").append(purchase.mPurchaseID).append("\n");
        sb.append("Quantity: ").append(purchase.mQuantity).append("\n");
    }

    @Override
    public void appendFooter(String footer) {
        sb.append(footer).append("\n");
    }
    
    @Override
    public void toString(String header, CustomerModel customer, ProductModel product, PurchaseModel purchase, String footer) {
        appendHeader(header);
        appendCustomer(customer);
        appendProductPurchase(product, purchase);
        appendFooter(footer);
        System.out.print(sb);
    }
}

