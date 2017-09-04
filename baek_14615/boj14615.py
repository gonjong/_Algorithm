#2017.08.01
#BaekJoon 14615 : bfs
#입출력이 많아, 간선이 많은 경우, test_case가 새롭게 반복되어야 하는 것인지 판단.
#반복되지 않아도 되는 것이면 bfs를 한번만 돌리고 답을 냄(시간 초과의 원인)
#N->1까지 bfs 결과로만 판단할 경우 문제, 1이 true인 경우는 C를 통과하지 않고 온 경우일 수 있는데
#그 경우도 visit[1] = True가 됨
#따라서 1에서 나가는 경우와 N에서 거꾸로 오는 경우를 한번씩 계산하고 test_case는 이미 만든 결과로 구해야 
#노드로 들어오는 경우와 노드에서 나가는 경우를 나누어서 풀이
#시간 복잡도 감소

import sys
import pdb
import queue

Input = sys.stdin.readline().split()
N = int(Input[0])
M = int(Input[1])
forward = [[]for v in range(N+1)]
backward = [[] for v in range(N+1)]
#adj = [[[] for u in range(2)] for v in range(N+1)]
#visit = [[[] for j in range(2)] for i in range(N+1)]

#for i in range(N+1):
#    visit[i][0] = False
#    visit[i][1] = False

visitF  = [ False for j in range(N+1)]
visitB = [False for j in range(N+1)]

for i in range(M):
    Input = sys.stdin.readline().split()
    X = int(Input[0])
    Y = int(Input[1])
    #adj[X][0].append(Y)
    forward[X].append(Y)
    backward[Y].append(X)


##for i in range(2):


##    if i==1:
##        q.put(N)
##    else :
##        q.put(1)
q = queue.Queue()
q.put(N)
visitF[1] = True 
visitB[N] = True

while(q.qsize()>0):
    curr = q.get()
    for next in backward[curr]:
        if not visitB[next]:
            visitB[next] = True
            q.put(next)

q = queue.Queue()
q.put(1)
while(q.qsize()>0):
    curr = q.get()
    for next in forward[curr]:
        if not visitF[next]:
            visitF[next] = True
            q.put(next)


##    while(q.qsize()>0):
##        curr = q.get()
##        for next in adj[curr][i]:
##            if not visit[next][i]:
##                visit[next][i] = True
##                q.put(next)

T = int(sys.stdin.readline())
for i in range(T):
    C = int(sys.stdin.readline())
    if visitF[C] and visitB[C]:
        print("Defend the CTP")
    else :
        print("Destroyed the CTP")
        
##    if visit[C][0] and visit[C][1] :
##        print("Defend the CTP")
##    else :
##        print("Destroyed the CTP")

