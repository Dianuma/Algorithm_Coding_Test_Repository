from collections import deque
def solution(maps):
    def bfs(maps, start, end):
        d, q, visited = [(-1, 0), (0, -1), (1, 0), (0, 1)], deque([(*start, 0)]), set(start)
        R, C = len(maps), len(maps[0])
        while q:
            r, c, time = q.popleft()
            for dr, dc in d:
                nr, nc = r + dr, c + dc
                if 0 <= nr < R and 0 <= nc < C and (nr, nc) not in visited and maps[nr][nc] != "X":
                    if maps[nr][nc] == end: return time + 1
                    visited.add((nr, nc))
                    q.append((nr, nc, time + 1))
        return 0
    visit = {maps[r][c]: (r, c) for r in range(len(maps)) for c in range(len(maps[0])) if maps[r][c] in "SEL"}
    a, b = bfs(maps, visit["S"], "L"), bfs(maps, visit["L"], "E")
    return a + b if a and b else -1