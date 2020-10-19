package depthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * description 给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的id。
 * 现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。
 *
 * @author 27771
 * create 2020-10-19 20:11
 **/
public class EmployeeImportance {
    /**
     *  其实就是个带权多叉树
     */
    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }

    static class Solution {
        /**
         * 递归重要度之和
         * (执行用时：9 ms, 在所有 Java 提交中击败了38% 的用户)
         * (内存消耗：39.8 MB, 在所有 Java 提交中击败了89% 的用户)
         * @param employees 整个employee数组
         * @param id 当前领导id
         * @return  该领导和他所有下属的重要度之和
         */
        public int getImportance(List<Employee> employees, int id) {
            Employee employee = null;
            for(Employee e : employees){
                if(e.id == id){
                    employee = e;
                    break;
                }
            }
            List<Integer> subordinateList = employee.subordinates;
            int ret = employee.importance;
            //所有下属重要度之和
            for(Integer i : subordinateList){
                ret += getImportance(employees, i);
            }
            return ret;
        }

        /**
         *  最优递归形式
         */
        public int getImportance1(List<Employee> employees, int id){
            for (Employee e: employees) {
                if (e.id == id) {
                    // 没有子结点
                    if (e.subordinates.size() == 0) {
                        return e.importance;
                    }
                    for (int subId: e.subordinates) {
                        e.importance += getImportance(employees, subId);
                    }
                    return e.importance;
                }
            }
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        /**
         *  广度优先搜索
         *  (执行用时：17 ms, 在所有 Java 提交中击败了7.72% 的用户)
         *  (内存消耗：39.6 MB, 在所有 Java 提交中击败了95.36% 的用户)
         * @param employees 整个employee数组
         *  @param id 当前领导id
         *  @return  该领导和他所有下属的重要度之和
         */
        public int getImportance2(List<Employee> employees, int id){
            int allImportance = 0;
            stack.push(id);
            while (!stack.isEmpty()){
                Integer curEmployee = stack.pop();
                Employee employee = null;
                for(Employee e : employees){
                    if(e.id == curEmployee){
                        employee = e;
                        break;
                    }
                }
                allImportance += employee.importance;
                for(Integer i : employee.subordinates){
                    stack.push(i);
                }
            }
            return allImportance;
        }

    }

    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, 5 , new ArrayList<>(Arrays.asList(2, 3))));
        employeeList.add(new Employee(2, 3 , new ArrayList<>()));
        employeeList.add(new Employee(3, 3 , new ArrayList<>()));

        System.out.println(new Solution().getImportance1(employeeList, 1));
    }
}