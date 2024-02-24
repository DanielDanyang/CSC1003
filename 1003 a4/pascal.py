n = int(input())
if n == 1:
    print(1)
elif n == 2:
    print("1\n1 1")
else:
    print("1\n1 1")
    list = [1, 1]
    for i in range(2, n):
        templist = []
        for j in range(len(list) - 1):
            templist.append(list[j] + list[j+1])
        list = templist.copy()
        list.append(1)
        list.insert(0, 1)
        print(*list, sep = ' ')
