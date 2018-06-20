package sda.pl;

import java.math.BigDecimal;

public class MyParser {
    public static Long stringToLong(String text) {
        try {
            return Long.valueOf(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0L;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;

        }
    }
    public static BigDecimal stringToBigDecimal(String text){
        try{
            return new BigDecimal(text.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;

        }
    }
}
