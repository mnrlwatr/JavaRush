package CustomTree;

import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }

        list.remove((Object) "3");
    }
}
