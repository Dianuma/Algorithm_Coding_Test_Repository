def solution(n, computers):
    p = list(range(n))
    def f(x):
        if p[x] != x: p[x] = f(p[x])
        return p[x]
    for i in range(n):
        for j in range(n):
            if computers[i][j]: p[f(j)] = f(i)
    return len({f(i) for i in range(n)})