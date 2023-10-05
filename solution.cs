```C#
using System;
using System.Collections.Generic;
using System.Linq;

public class Program
{
    public static void Main()
    {
        var wordList = new List<string> { "hot", "dot", "dog", "lot", "log", "cog" };
        var beginWord = "hit";
        var endWord = "cog";

        var result = FindLadders(beginWord, endWord, wordList);

        foreach (var path in result)
        {
            Console.WriteLine(string.Join(" -> ", path));
        }
    }

    public static List<List<string>> FindLadders(string beginWord, string endWord, List<string> wordList)
    {
        var result = new List<List<string>>();
        var queue = new Queue<List<string>>();
        var wordSet = new HashSet<string>(wordList);

        queue.Enqueue(new List<string> { beginWord });
        var isFound = false;

        while (queue.Count > 0)
        {
            var visited = new HashSet<string>();
            var levelSize = queue.Count;

            for (var i = 0; i < levelSize; i++)
            {
                var path = queue.Dequeue();
                var word = path.Last();

                for (var j = 0; j < word.Length; j++)
                {
                    var wordArray = word.ToCharArray();

                    for (var k = 'a'; k <= 'z'; k++)
                    {
                        wordArray[j] = k;
                        var newWord = new string(wordArray);

                        if (wordSet.Contains(newWord))
                        {
                            var newPath = new List<string>(path) { newWord };
                            visited.Add(newWord);

                            if (newWord == endWord)
                            {
                                isFound = true;
                                result.Add(newPath);
                            }
                            else
                            {
                                queue.Enqueue(newPath);
                            }
                        }
                    }
                }
            }

            if (isFound)
            {
                break;
            }

            foreach (var word in visited)
            {
                wordSet.Remove(word);
            }
        }

        return result;
    }
}
```