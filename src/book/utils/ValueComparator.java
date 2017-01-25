package book.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by p on 01/25/2017.
 */
public class ValueComparator implements Comparator<String> { // took it from stackoverflow.
    Map<String, Integer> base;
    public ValueComparator(Map<String,Integer> base){
        this.base = base;
    }

    @Override
    public int compare(String a, String b) {
        if(base.get(b) >= base.get(a))
            return -1;
        return 1;
    }
}
