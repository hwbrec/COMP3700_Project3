import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PurchaseModel {
    public int mPurchaseID, mProductID, mCustomerID;
    public double mPrice, mQuantity, mCost, mTax, mTotal;
    public String mDate;
    
    private static DecimalFormat df = new DecimalFormat("0.00");

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mPurchaseID).append(",");
        sb.append(mCustomerID).append(",");
        sb.append(mProductID).append(",");
        sb.append(df.format(mPrice)).append(",");
        sb.append(mQuantity).append(",");
        sb.append(df.format(mCost)).append(",");
        sb.append(df.format(mTax)).append(",");
        sb.append(df.format(mTotal)).append(",");
        sb.append("\"").append(mDate).append("\"").append(")");
        return sb.toString();
    }

}


