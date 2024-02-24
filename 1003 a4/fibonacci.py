def fibonacci(n, d):
    list = [1, 1]
    a, b = 1, 1
    for i in range(n-2):
        temp = a
        a = a + b
        b = temp
        list.append(a)
    for i in range(d):
        if i != d - 1:
            print(str(list[n-1]) + ", " ,end = "")
        else:
            print(list[n-1])
        n -= 1
N = int(input())
for i in range(N):
    string = input()
    n, d = map(int, string.split(", "))
    if d == 0:
        print()
    elif d > n:
        print("invalid")
    else:
        fibonacci(n, d)

