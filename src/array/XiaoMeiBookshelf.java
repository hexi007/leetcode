package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * description 小美的书架上有很多书。小美是个爱读书的新时代好青年。  小团虽然也喜欢看书，但小团大多数时候都更喜欢来小美家蹭书读。
 * 这就导致小美的书架上很多书都会被小团借走。
 * 小美很烦这一点，就想出了一个招数，小美的书架是一行一行的，他会对一些行加锁，这样小团就借不走了。
 * 现在小团想要借书，请你帮忙看看小团能不能借到书，如果可以借到的话在哪一行书架上有这本书。
 * 为了简单起见，每本书将用一个正整数进行编号，小美的书架一共有N行。
 *
 * 1 x y : x是书本的编号，y是书架的行编号，代表小美将编号为x的书本放置到y行上。
 * 若该书本在小团手上则放置无效，若原来该书在书架上且原行上锁则放置无效，若该书被放置到一个锁了的行上则放置无效。
 *
 * 2 y : y是书架的行编号，代表小美将行编号为y的书架加锁，对已经上锁的书架行该操作无效。
 *
 * 3 y : y是书架的行编号，代表小美将行编号为y的书架锁去掉，对无锁的书架行该操作无效。
 *
 * 4 x : x是书本的编号，代表小团想借编号为x的书本，对该操作若可以借到输出一行正整数在哪一行，借不到输出一行-1
 *
 * 5 x : x是书本的编号，代表小团还回来编号为x的书本。若该书本不在小团手上该操作无效。
 *
 * @author 27771
 * create 2021-03-22 10:13
 **/
public class XiaoMeiBookshelf {

    static class Solution {
        public void getBook(int n, int m, int q, int[][] query) {
            boolean[] lock = new boolean[n + 1];
            HashMap<Integer, Integer> map = new HashMap<>(16);
            HashSet<Integer> borrowedBooks = new HashSet<>();
            HashSet<Integer> returnedBooks = new HashSet<>();

            for (int i = 0; i < q; i++) {
                switch (query[i][0]) {
                    case 1 :
                        int x = query[i][1], y = query[i][2];
                        if (x < 1 || x > m || y < 1 || y > n) {
                            break;
                        }
                        if (!borrowedBooks.contains(x) && !lock[y]) {
                            if (!map.containsKey(x)) {
                                map.put(x, y);
                            } else if (returnedBooks.contains(x))  {
                                map.put(x, y);
                                returnedBooks.remove(x);
                            }
                            else {
                                int index = map.get(x);
                                if (!lock[index]) {
                                    map.put(x, y);
                                }
                            }
                        }
                        break;
                    case 2 :
                        y = query[i][1];
                        if (y >= 1 && y <= n) {
                            lock[query[i][1]] = true;
                        }
                        break;
                    case 3 :
                        y = query[i][1];
                        if (y >= 1 && y <= n) {
                            lock[query[i][1]] = false;
                        }
                        break;
                    case 4 :
                        int book = query[i][1];
                        if (book < 1 || book > m || borrowedBooks.contains(book)) {
                            System.out.println(-1);
                            break;
                        }
//                        System.out.println("book " + book);
                        if (map.containsKey(book)) {
                            int index = map.get(book);
//                            System.out.println("index " + index + " !lock[book] " + !lock[book]);
//                            System.out.println(Arrays.toString(lock));
                            if (!lock[index]) {
                                borrowedBooks.add(book);
                                System.out.println(index);
                                break;
                            }
                        }
                        System.out.println(-1);
                        break;
                    case 5 :
                        book = query[i][1];
                        if (book < 1 || book > m) {
                            break;
                        }
                        borrowedBooks.remove(book);
                        returnedBooks.add(book);
                        break;
                    default:
                }
            }
        }
    }

    /*
    5 5 10
1 1 4
1 2 3
1 3 1
2 1
4 1
5 2
4 3
4 5
3 1
4 2
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt(), q = input.nextInt();
        int[][] query = new int[q][3];
        for (int i = 0; i < q; i++) {
            int a = input.nextInt();
            query[i][0] = a;
            query[i][1] = input.nextInt();
            if (a == 1) {
                query[i][2] = input.nextInt();
            }
        }

        new Solution().getBook(n, m, q, query);
    }
}