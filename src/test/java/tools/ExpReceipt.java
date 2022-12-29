package tools;

import java.util.List;

public class ExpReceipt {
    public String buildExpReceipt(List<String> testIngrs, float price){
        StringBuilder expectedReceipt = new StringBuilder(String.format("(==== %s ====)%n", testIngrs.get(0)));
        expectedReceipt.append(String.format("= %s %s =%n", "sauce",
                testIngrs.get(1)));
        expectedReceipt.append(String.format("= %s %s =%n", "filling",
                testIngrs.get(2)));
        expectedReceipt.append(String.format("(==== %s ====)%n", testIngrs.get(0)));
        expectedReceipt.append(String.format("%nPrice: %f%n", (price*4)));
        return expectedReceipt.toString();
    }
}
