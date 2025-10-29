def solution(n, computers):
    vis = [0]*n
    def dfs(s):
        st = [s]
        while st:
            x = st.pop()
            if vis[x]: continue
            vis[x] = 1
            st += [j for j,v in enumerate(computers[x]) if v and not vis[j]]
    return sum((dfs(i) or 1) for i in range(n) if not vis[i])