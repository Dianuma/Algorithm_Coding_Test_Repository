from collections import deque
def solution(board):
    def bfs(s):
        q, n = deque([s]), len(board)
        dirs, vis = [(-1,0),(0,1),(1,0),(0,-1)], [[1e9] * n for _ in range(n)]
        while q:
            r, c, d, cost = q.popleft()
            for i, (dr, dc) in enumerate(dirs):
                nr, nc, ncost= r + dr, c + dc, 500 * ( i != d ) + 100 + cost
                if 0 <= nr < n and 0 <= nc < n and board[nr][nc] == 0 and ncost < vis[nr][nc]:
                    vis[nr][nc] = ncost
                    q.append((nr, nc, i, ncost))
        return vis[-1][-1]
    return min(bfs((0,0,1,0)), bfs((0,0,2,0)))