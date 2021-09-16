package greedyalgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description 服装店新进了a条领带，b条裤子，c个帽子，d件衬衫，现在要把这些搭配起来售卖。
 * 有三种搭配方式，一条领带和一件衬衫，一条裤子和一件衬衫，一个帽子和一件衬衫。
 * 卖出一套领带加衬衫可以得到e元，卖出一套裤子加衬衫可以得到f元，卖出一套帽子加衬衫可以得到g元。现在你需要输出最大的获利方式
 *
 * @author 27771
 * create 2021-09-15 17:09
 **/
public class MatchingSale {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Cloth[] cloths = new Cloth[3];
        cloths[0] = new Cloth(in.nextInt());
        cloths[1] = new Cloth(in.nextInt());
        cloths[2] = new Cloth(in.nextInt());
        int d = in.nextInt();
        cloths[0].price = in.nextInt();
        cloths[1].price = in.nextInt();
        cloths[2].price = in.nextInt();
        Arrays.sort(cloths, (o1, o2) -> o2.price != o1.price ? o2.price - o1.price : o1.count - o2.count);
        long res = 0;
        int temp = Math.min(cloths[0].count, d);
        res += (long)temp * cloths[0].price;
        d -= temp;
        if (d <= 0) {
            System.out.println(res);
            return;
        }
        temp = Math.min(cloths[1].count, d);
        res += (long)temp * cloths[1].price;
        d -= temp;
        if (d <= 0) {
            System.out.println(res);
            return;
        }
        temp = Math.min(cloths[2].count, d);
        res += (long)temp * cloths[2].price;
        System.out.println(res);
    }

    static class Cloth {
        int count, price;

        public Cloth(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "Cloth{" + " count=" + count +
                    ", price=" + price +
                    '}';
        }
    }
}