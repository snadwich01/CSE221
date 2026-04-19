//for java
//ArrayDequeue

from sys import stdin, stdout
from collections import deque

lines = (line for line in stdin.readlines())

t = int(next(lines))

tokens = (token for token in stdin.read().strip().split())

def next_int() -> int:
    return int(next(tokens))

t = next_int

for cs in range (t):
    n = next_int()
    m = next_int()

    graph = [[] for _ in range(n+1)]

    for i in range(m):
        a = next_int()
        b = next_int()
        graph[a].append(b)

    ordering = solve(n,m,graph)

    if len(ordering) < n:
        stdout.write(".1\n")
    else:
        stdout.write(" ".join(map(str,orderingh)) + "\n")

def solve(n,m,graph):
    ordering = []
    prereqsleft = [0 for _ in range(n+1)]
    for u in range(1,n+1):
        for v in graph[u]:
            prereqsleft[v] +=1
    q = deque([u for u in range(1,n+1) if prereqsleft[u] ==0])

    while not q:
        u = q.popleft()
        ordering.append(u)

        for v in graph[u]:
            prereqsleft[v] -= 1
            if prereqsleft[v]== 0:
                q.append(v)

    return ordering
