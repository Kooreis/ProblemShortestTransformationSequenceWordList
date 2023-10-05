Here is a Java console application that solves the problem using Breadth-First Search (BFS) algorithm:

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> wordList = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        String beginWord = "hit";
        String endWord = "cog";
        List<List<String>> result = findLadders(beginWord, endWord, wordList);
        for (List<String> list : result) {
            System.out.println(list);
        }
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        wordList.add(beginWord);
        wordList.add(endWord);
        bfs(beginWord, endWord, wordList, map, distance);
        dfs(beginWord, endWord, map, distance, new ArrayList<>(Arrays.asList(beginWord)), result);
        return result;
    }

    private static void bfs(String start, String end, Set<String> dict, Map<String, List<String>> nodeNeighbors, Map<String, Integer> distance) {
        for (String str : dict)
            nodeNeighbors.put(str, new ArrayList<>());
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);
        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                List<String> neighbors = getNeighbors(cur, dict);
                for (String neighbor : neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor))
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }
            if (foundEnd)
                break;
        }
    }

    private static List<String> getNeighbors(String node, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char chs[] = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }
        }
        return res;
    }

    private static void dfs(String cur, String end, Map<String, List<String>> nodeNeighbors, Map<String, Integer> distance, List<String> solution, List<List<String>> res) {
        if (end.equals(cur)) {
            res.add(new ArrayList<>(solution));
            return;
        }
        for (String next : nodeNeighbors.get(cur)) {
            if (distance.get(next) == distance.get(cur) + 1) {
                solution.add(next);
                dfs(next, end, nodeNeighbors, distance, solution, res);
                solution.remove(solution.size() - 1);
            }
        }
    }
}
```

This program will print the shortest transformation sequence from "hit" to "cog" using the provided word list.