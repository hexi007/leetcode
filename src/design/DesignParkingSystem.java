package design;

/**
 * description 请你给一个停车场设计一个停车系统。
 * 停车场总共有三种不同大小的车位：大，中和小，每种尺寸分别有固定数目的车位。
 * 请你实现 ParkingSystem 类：
 * ParkingSystem(int big, int medium, int small) 初始化 ParkingSystem 类，三个参数分别对应每种停车位的数目。
 * bool addCar(int carType) 检查是否有 carType 对应的停车位。
 * carType 有三种类型：大，中，小，分别用数字 1， 2 和 3 表示。一辆车只能停在 carType 对应尺寸的停车位中。
 * 如果没有空车位，请返回 false ，否则将该车停入车位并返回 true 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/design-parking-system 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-19 09:08
 **/
public class DesignParkingSystem {

    /**
     * (执行用时：10 ms, 在所有 Java 提交中击败了96.81%的用户)
     * (内存消耗：39.2 MB, 在所有 Java 提交中击败了56.48%的用户)
     */
    static class ParkingSystem {

        int[] park = new int[3];

        public ParkingSystem(int big, int medium, int small) {
            park[0] = big;
            park[1] = medium;
            park[2] = small;
        }

        public boolean addCar(int carType) {
            if (park[carType - 1] > 0) {
                park[carType - 1]--;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        System.out.println(parkingSystem.addCar(1));
        System.out.println(parkingSystem.addCar(2));
        System.out.println(parkingSystem.addCar(3));
        System.out.println(parkingSystem.addCar(1));
    }
}
