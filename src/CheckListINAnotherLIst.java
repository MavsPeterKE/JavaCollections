import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckListINAnotherLIst {
    public static String [] productIdArray = {"1000","8000","2000","300","320","321","322","145","300","302"};
    public static String [] promotionalArray1 = {"1000","2000","300"};
    public static String [] promotionalArray2 = {"322","300","145"};
    public static String [] promotionalArray3 = {"8000","3209","302"};
    public static HashMap<String,List<String>> promotionsHashList = new HashMap<>();
    public static String promotions = "Order Qualifies for the following Promotions: ";
    public static List<String> productIdList = Arrays.asList(productIdArray);
    public static int promotionCount = 0;


    public static void main(String [] args){
        //add promotion Arrays to Hashmap
        promotionsHashList.put("Promo 1 =300",Arrays.asList(promotionalArray1));
        promotionsHashList.put("Promo 2 =450",Arrays.asList(promotionalArray2));
        promotionsHashList.put("Promo 3 =100",Arrays.asList(promotionalArray3));

        int count = 0;
        double totalPromotionValue =0.0;
        Matcher matcher;
        //Loop Through the HashList Checking how many promotions a product Qualifies For
        for (HashMap.Entry<String,List<String>> entry:promotionsHashList.entrySet()){
           if (productIdList.containsAll(entry.getValue())){
               promotionCount+=1;
               matcher = Pattern.compile("^\\w+\\s\\d").matcher(entry.getKey());
               if (matcher.find()){
                   promotions+="\n"+matcher.group(0) +" == "+entry.getValue();
               }
               matcher = Pattern.compile("(\\d+)$").matcher(entry.getKey());
               if (matcher.find()){
                   totalPromotionValue+=Double.parseDouble(matcher.group(0));
               }
           }
        }

        if (promotionCount>0) {
            System.out.println(productIdList.toString() + " " + promotions);
            System.out.println("\n"+"Total Promotion Amount is : " + totalPromotionValue+"0");
        }else {
            System.out.println(productIdList.toString() + " Does Not Qualify For Any Promotion " );
        }


    }

}
