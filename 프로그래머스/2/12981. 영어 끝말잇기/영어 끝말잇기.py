def solution(n, words):
    used = set()
    last = words[0][0]
    for i, word in enumerate(words):
        if last != word[0] or word in used:
            return [i % n + 1, i // n + 1]
        used.add(word)
        last = word[-1]
    return [0, 0]