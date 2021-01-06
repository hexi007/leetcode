package graph;

import java.util.*;

/**
 * description 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
 * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，
 * 请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。  返回 所有问题的答案 。
 * 如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/evaluate-division 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-06 09:52
 **/
public class EvaluateDivision {

    static class Solution {
        
        /**
         * Floyd 算法保存所有可计算结果
         * (执行用时：1 ms, 在所有 Java 提交中击败了97.24%的用户)
         * (内存消耗：37.3 MB, 在所有 Java 提交中击败了58.49%的用户)
         *
         * @param equations 变量等式
         * @param values    变量等式对应除法值
         * @param queries   待查询问题
         * @return          所有待查询问题除法结果
         */
        public double[] calcEquation(List<List<String>> equations
                , double[] values, List<List<String>> queries) {
            // varCounts 变量等式中所有变量个数
            int varCounts = 0;
            // variables 给每个变量标号
            Map<String, Integer> variables = new HashMap<>(16);

            // 遍历所有变量等式
            for (List<String> equation : equations) {
                // 如果等式左部分不在 map 里，将其添加至 map 并标号，等式右部分同理
                if (!variables.containsKey(equation.get(0))) {
                    variables.put(equation.get(0), varCounts++);
                }
                if (!variables.containsKey(equation.get(1))) {
                    variables.put(equation.get(1), varCounts++);
                }
            }

            // graph 所有变量表示的图，graph[i][j] 表示 变量 i 除 变量 j 的结果
            double[][] graph = new double[varCounts][varCounts];
            int n = equations.size();

            // 初始化整个图为 -1.0，表示无法确定的答案
            for (int i = 0; i< varCounts; i++) {
                Arrays.fill(graph[i], -1.0);
            }
            // 变量每个等式
            for (int i = 0; i < n; i++) {
                // left 等式左部分在图中的标号
                int left = variables.get(equations.get(i).get(0));
                // left 等式右部分在图中的标号
                int right = variables.get(equations.get(i).get(1));
                // values[i] 就是 graph[left][right] 的值
                graph[left][right] = values[i];
                // values[i] 的倒数是 graph[right][left] 的值
                graph[right][left] = 1.0 / values[i];
            }

            // Floyd 算法
            for (int k = 0; k < varCounts; k++) {
                for (int i = 0; i < varCounts; i++) {
                    for (int j = 0; j < varCounts; j++) {
                        // 求出图中任意两点之间的除法结果
                        if (graph[i][k] > 0 && graph[k][j] > 0) {
                            graph[i][j] = graph[i][k] * graph[k][j];
                        }
                    }
                }
            }

            int querySize = queries.size();
            double[] res = new double[querySize];

            // 变量每个查询等式
            for (int i = 0; i < querySize; i++) {
                List<String> query = queries.get(i);
                // 每个查询默认结果为 -1.0
                double result = -1.0;
                // 如果查询等式左右两边都在 map 中则表示可以通过查图获取结果
                if (variables.containsKey(query.get(0))
                        && variables.containsKey(query.get(1))) {
                    // left 查询等式左部分在图中的标号
                    int left = variables.get(query.get(0));
                    // left 查询等式右部分在图中的标号
                    int right = variables.get(query.get(1));
                    // graph[left][right] > 0 表示有结果，更新 result
                    if (graph[left][right] > 0) {
                        result = graph[left][right];
                    }
                }
                res[i] = result;
            }

            return res;
        }
    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(new ArrayList<>(Arrays.asList("a", "b")));
        equations.add(new ArrayList<>(Arrays.asList("b", "c")));
        double[] values = {2.0,3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(new ArrayList<>(Arrays.asList("a", "c")));
        queries.add(new ArrayList<>(Arrays.asList("b", "a")));
        queries.add(new ArrayList<>(Arrays.asList("a", "e")));
        queries.add(new ArrayList<>(Arrays.asList("a", "a")));
        queries.add(new ArrayList<>(Arrays.asList("x", "x")));
        System.out.println(Arrays.toString(new Solution()
                .calcEquation(equations, values, queries)));
    }
}
