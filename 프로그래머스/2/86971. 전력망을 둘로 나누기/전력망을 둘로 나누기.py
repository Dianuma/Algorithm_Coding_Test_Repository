from collections import defaultdict
import copy

def solution(n, wires):
    answer = []
    wire = defaultdict(list)
    for w in wires:
        wire[w[0]].append(w[1])
        wire[w[1]].append(w[0])
    
    for w in wires:
        curr = copy.deepcopy(wire)
        curr[w[0]].remove(w[1])
        curr[w[1]].remove(w[0])
        answer.append(abs(n - 2 * count_n(curr)))
        
    return min(answer)

def count_n(wires):
    n = 1
    node = set()
    node.add(n)
    li = wires[n]
    while li:
        n = li.pop()
        node.add(n)
        li += [i for i in wires[n] if i not in node]
    return len(node)