def solution(answers):
    s = [[1,2,3,4,5],[2,1,2,3,2,4,2,5],[3,3,1,1,2,2,4,4,5,5]]
    a = [0, 0, 0]
    answer = []
    for i in range(len(answers)):
        for j in range(3):
            if s[j][i%len(s[j])] == answers[i]: a[j]+=1
    max_score = max(a)
    answer = [i + 1 for i, score in enumerate(a) if score == max_score]
    return answer