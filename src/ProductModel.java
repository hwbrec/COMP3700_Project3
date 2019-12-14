import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ProductModel {
    public int mProductID;
    public String mName, mVendor, mDescription;
    public double mPrice, mQuantity;
    
    private static DecimalFormat df = new DecimalFormat("0.00");
    

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mProductID).append(",");
        sb.append("\"").append(mName).append("\"").append(",");
        sb.append(df.format(mPrice)).append(",");
        sb.append(mQuantity).append(")");
        return sb.toString();
    }
}
