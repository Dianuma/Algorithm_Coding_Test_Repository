n = int(input())
arr = list(map(int, input().split()))

score = 0
answer = 0

for a in arr:
    if a == 0 : 
        score = 0
    elif a == 1 :
        score += 1
        answer += score
        
print(answer)