num = int(input())
word = []
for _ in range(num):
    word.append(input())

group_word_count = 0
for i in word:
    if list(i) == sorted(i, key=i.find):
        group_word_count += 1

print(group_word_count)