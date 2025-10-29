def solution(n, computers):
    groups = []
    for s in [{r,c} for r in range(len(computers)) for c in range(r, len(computers)) if computers[r][c]]:
        merged = []
        for g in groups:
            if s & g: s |= g
            else: merged.append(g)
        groups = merged + [s]
    return len(groups)