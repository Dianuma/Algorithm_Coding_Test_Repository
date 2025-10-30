def solution(maps):
    vis, R, C = set(), len(maps), len(maps[0])
    dirs = [(-1,0),(1,0),(0,-1),(0,1)]
    ok = lambda r,c: maps[r][c] != "X" and (r,c) not in vis
    def dfs(stack, s):
        while stack:
            r, c = stack.pop()
            s += int(maps[r][c])
            for dr, dc in dirs:
                nr, nc = r + dr, c + dc
                if 0 <= nr < R and 0 <= nc < C and ok(nr, nc): 
                    vis.add((nr, nc))
                    stack.append((nr, nc))
        return s
    return sorted(dfs([(r,c)], 0) for r in range(R) for c in range(C) if ok(r, c) and not vis.add((r,c))) or [-1]