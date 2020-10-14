package node;

/**
 * description 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字
 * @author 27771
 * create 2020-10-14 10:24
 **/
public class AddTwoNumbers {
    static class Solution {
        ListNode ret;

        /**
         * @Description  在l1的基础上实现加法
         * (执行用时：2 ms, 在所有 Java 提交中击败了99.92% 的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了98.17% 的用户)
         * @param l1 l1链表
         * @param l2 l2链表
         * @return  两链表的和
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if(l1 == null || l2 == null){
                return l1 == null ? l2 : l1;
            }
            ret = l1;
            // 两节点和超过carryBit，也就是10之后触发进位
            int carryBit = 10;
            // 初始没有进位，tempCarry = 0
            int tempCarry = 0;
            while(true){
                //两节点和加上进位超过carryBit，触发进位
                if(l1.val + l2.val + tempCarry >= carryBit){
                    l1.val = l1.val + l2.val + tempCarry - carryBit;
                    tempCarry = 1;
                } else {
                    l1.val = l1.val + l2.val + + tempCarry;
                    tempCarry = 0;
                }
                //如果两节点同时走到头，判断是否有进位
                if(l1.next == null && l2.next == null && tempCarry == 1){
                    ListNode temp = new ListNode(1);
                    l1.next = temp;
                    return ret;
                }
                //l2走到头，l1继续走并退出
                if(l2.next == null){
                    l1 = l1.next;
                    break;
                }
                //l1走到头，l1就切换到l2并退出
                if(l1.next == null){
                    l1.next = l2.next;
                    l1 = l1.next;
                    break;
                }
                //正常情况两节点一起向前走
                l1 = l1.next;
                l2 = l2.next;
            }
            while(l1 != null){
                //l1继续走并判断是否会触发进位
                if(l1.val + tempCarry >= carryBit){
                    l1.val = l1.val + tempCarry - carryBit;
                    tempCarry = 1;
                } else {
                    l1.val = l1.val + tempCarry;
                    tempCarry = 0;
                }
                //l1走到头了还有进位的情况
                if(l1.next == null && tempCarry == 1){
                    ListNode temp = new ListNode(1);
                    l1.next = temp;
                    break;
                }
                l1 = l1.next;
            }
            return ret;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{5});
        ListNode l2 = new ListNode(new int[]{5});
        Solution solution = new Solution();
        ListNode ret = solution.addTwoNumbers(l1,l2);
        ret.printNodeFromRoot();
    }
}
