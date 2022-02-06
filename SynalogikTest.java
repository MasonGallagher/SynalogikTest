import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mason on 06/02/2022 at 20:58
 */
public class SynalogikTest {
    public static void main(String[] args) {
        String str = "Hello world & good morning. The date is 18/05/2016";
        if (str.length() > 0 ){
            countWords(str);
        }
    }

    private static void countWords(String str){
        double total = 0;
        int frequency = 0;
        Map<Integer, Integer> length_map = new HashMap<>();
        List<String> commonKeys = new ArrayList<>();
        List<String> words = Arrays.asList(str.replaceAll("[^a-zA-Z&/0-9^ ]", "").toLowerCase().split("\\s+"));

        for (String word : words) {
            int key = word.length();
            total = total + key;
            Integer value = length_map.get(key);
            if (value == null) {
                length_map.put(key, 1);
            } else {
                if (value > frequency) {
                    frequency = value;
                    commonKeys.clear();
                    commonKeys.add(String.valueOf(key));
                } else if (value == frequency) {
                    commonKeys.add(String.valueOf(key));
                }
                length_map.put(key, value + 1);
            }
        }

        double avg = total / words.size();
        if (commonKeys.isEmpty()) {
            commonKeys.add(words.get(0).length() + "");
        }
        int freq_word_len = length_map.get(Integer.valueOf(commonKeys.get(0)));
        String common_word_lengths = String.join(" & ", commonKeys);

        System.out.println(String.format("Word count = %d", words.size()));
        System.out.println(String.format("Average word length = %.3f", avg));
        length_map.entrySet().forEach(entry ->
                System.out.println(String.format("Number of words of length %d is %d",
                        entry.getKey(), entry.getValue())));
        System.out.println(String.format("The most frequently occurring word length is %d, for word lengths of %s",
                freq_word_len, common_word_lengths));
    }
}
