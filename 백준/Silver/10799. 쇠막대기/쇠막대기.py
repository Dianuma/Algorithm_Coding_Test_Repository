data = input()
pre = 0
num = 0
answer = 0
for s in data:
    if s == "(":
        num += 1
    elif s == ")":
        num -= 1
        if pre == "(": answer += num
        else : answer += 1
    pre = s
print(answer)