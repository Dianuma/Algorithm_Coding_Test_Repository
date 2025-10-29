from collections import defaultdict
def solution(tickets):
    routes, stack, path = defaultdict(list), ["ICN"], []
    for dep, arr in tickets: routes[dep].append(arr)
    for dests in routes.values(): dests.sort(reverse=True)
    while stack:
        if routes[stack[-1]]: stack.append(routes[stack[-1]].pop())
        else: path.append(stack.pop())
    return path[::-1]