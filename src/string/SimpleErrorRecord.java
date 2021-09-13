package string;

import java.util.*;

/**
 * description 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 * 处理:
 * 1.记录最多8条错误记录，对相同的错误记录(即文件名称和行号完全匹配)只记录一条，错误计数增加；
 * (文件所在的目录不同，文件名和行号相同也要合并)
 * 2.超过16个字符的文件名称，只记录文件的最后有效16个字符；
 * (如果文件名不同，而只是文件名的后16个字符和行号相同，也不要合并)
 * 3.输入的文件可能带路径，记录文件名称不能带路径
 *
 * @author 27771
 * create 2021-09-13 10:39
 **/
public class SimpleErrorRecord {

    /*
E:\V1R2\product\test.c 1325

test.c 1325 1
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String s = input.next();
            int row = input.nextInt();
            store(s, row);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(MAP.entrySet());
        // 排序还有考虑记录先后顺序
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        int count = 0, maxCount = 8;
        for (Map.Entry<String, Integer> entry : list) {
            String[] f = entry.getKey().split(" ");
            String fileName = f[0];
            if (fileName.length() > 16) {
                fileName = fileName.substring(fileName.length() - 16);
            }
            System.out.println(fileName + " " + f[1] + " " + entry.getValue());
            count++;
            if (count > maxCount) {
                break;
            }
        }
    }

    private static final Map<String, Integer> MAP = new HashMap<>(16);

    private static void store(String s, int row) {
        String[] file = s.split("\\\\");
        MAP.merge(file[file.length - 1] + " " + row, 1,
                (a, b) -> MAP.get(file[file.length - b] + " " + row) + b);
    }
}
