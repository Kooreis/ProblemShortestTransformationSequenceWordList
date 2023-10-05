Here is a Python console application that solves the problem using a breadth-first search algorithm:

```python
from collections import deque

def word_ladder(start, end, word_list):
    word_list = set(word_list)
    queue = deque([[start, 1]])
    while queue:
        word, length = queue.popleft()
        if word == end:
            return length
        for i in range(len(word)):
            for c in 'abcdefghijklmnopqrstuvwxyz':
                next_word = word[:i] + c + word[i+1:]
                if next_word in word_list:
                    queue.append([next_word, length + 1])
                    word_list.remove(next_word)
    return 0

def main():
    start = input("Enter the start word: ")
    end = input("Enter the end word: ")
    word_list = input("Enter the word list, separated by spaces: ").split()
    print("Shortest transformation sequence length: ", word_ladder(start, end, word_list))

if __name__ == "__main__":
    main()
```

This program will prompt the user to enter the start word, end word, and a list of words. It will then print the length of the shortest transformation sequence from the start word to the end word using only words from the provided list. Each transformation in the sequence changes exactly one letter from the previous word.