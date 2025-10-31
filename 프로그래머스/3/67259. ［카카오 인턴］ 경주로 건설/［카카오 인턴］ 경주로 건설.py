from collections import deque

def solution(board):
    n = len(board)
    INF = int(1e9)

    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    cost = [[[INF] * 4 for _ in range(n)] for _ in range(n)]
    q = deque()

    for i, (dx, dy) in enumerate(directions):
        nx, ny = dx, dy
        if 0 <= nx < n and 0 <= ny < n and board[nx][ny] == 0:
            cost[nx][ny][i] = 100
            q.append((nx, ny, i, 100))

    while q:
        x, y, dir_prev, c = q.popleft()

        for i, (dx, dy) in enumerate(directions):
            nx, ny = x + dx, y + dy
            if 0 <= nx < n and 0 <= ny < n and board[nx][ny] == 0:
                if i == dir_prev:
                    nc = c + 100
                else:
                    nc = c + 600
                if nc < cost[nx][ny][i]:
                    cost[nx][ny][i] = nc
                    q.append((nx, ny, i, nc))

    return min(cost[n-1][n-1])