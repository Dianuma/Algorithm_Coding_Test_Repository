from collections import deque

def validate(r, c, len_r, len_c):
    return r >= 0 and c >= 0 and len_r > r and len_c > c

def get_reach_time(maps, start, end):
    visit, visited = deque([(start[0], start[1], 0)]), set()
    len_r, len_c = len(maps), len(maps[0])
    count = 0
    while visit:
        r, c, time = visit.popleft()
        visited.add((r, c))
        for dr, dc in [(-1, 0), (0, -1), (1, 0), (0, 1)]:
            count += 1
            nr, nc = r + dr, c + dc
            if validate(nr, nc, len_r, len_c) and ( (nr, nc) not in visited ):
                if maps[nr][nc] == end: return time + 1
                elif maps[nr][nc] != "X": 
                    visited.add((nr, nc))
                    visit.append((nr, nc, time + 1))
    return 0

def solution(maps):
    visit = {maps[r][c]: (r, c) for r in range(len(maps)) for c in range(len(maps[0])) if maps[r][c] in ["S", "E", "L"]}
    first = get_reach_time(maps, visit["S"], "L")
    second = get_reach_time(maps, visit["L"], "E")
    return first + second if first and second else -1