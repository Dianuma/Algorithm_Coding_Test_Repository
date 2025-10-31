def solution(n, computers):
    visited = [0] * n
    def dfs(i):
        visited[i] = 1
        for j, computer in enumerate(computers[i]):
            if computer and not visited[j]: dfs(j)
    return sum([not v and not dfs(i) for i, v in enumerate(visited)])