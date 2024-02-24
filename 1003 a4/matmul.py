def dot_product(vector1, vector2):
    result = 0
    for i in range(n):
        result += vector1[i] * vector2[i]
    return result
dimension = input()
m, n, p = map(int, dimension.split(' '))
A = []
for i in range(m):
    row = input()
    templist = list(map(int, row.split(' ')))
    A.append(templist)
B = []
for i in range(n):
    row = input()
    templist = list(map(int, row.split(' ')))
    B.append(templist)
temp = []
for i in range(p):
    column = []
    for j in range(n):
        column.append(B[j][i])
    temp.append(column)
B = temp
C = []
for i in range(m):
    row = []
    for j in range(p):
        row.append(dot_product(A[i], B[j]))
    C.append(row)
print(str(m) + " " + str(p))
for i in range(m):
    for j in range(p):
        if j != p - 1:
            print(str(C[i][j]) + " ", end = '')
        else: 
            print(C[i][j])