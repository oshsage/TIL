# # 1644: 소수의 연속합

### 문제

하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.

- 3 : 3 (한 가지)
- 41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
- 53 : 5+7+11+13+17 = 53 (두 가지)

하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다. 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다. 또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.

자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.

---

### 문제풀이

-  소수를 발견하기 위한 방법을 찾는게 우선이다. 소수를 구하는 방법 중 **에라토스테네스의 체**라는 방법을 사용하였다. **해당 수보다 작은 모든 수로 나누어 소수를 판단하는 방법이다. **
   N 이하의 소수를 추려낸 후, **투 포인터** 알고리즘으로 **부분합이 N이 되는 경우를 카운트**하면 문제가 해결된다. 두 가지 알고리즘으로 문제가 해결되는 것이다.
-  Reference의 **에라토스테네스의 체**의 코드를 따라 사용해보면서 **"이중 for문이니까 시간복잡도가 O(N<sup>2</sup>)일텐데, N이 최대 400만이서 시간초과가 뜨지 않을까?"**라는 의문을 가졌지만, python3, pypy3에서 모두 성공하였다. 
   이중 for문만 보고 **n=400만 인 경우를 생각해보았을 때, 최소 4000000<sup>2</sup> 의 연산으로, 1억/sec의 연산속도로 최소 160000초가 걸린다는 예상**할 수 있을 것이다.
   그러나, **False&True 리스트를 통해** 소수가 아닌 수를 제거하는 과정에서 **연산 수는 기하 급수적으로 줄어든다.** 이중 for문의 **첫번째 루프에서 2의 배수인 수의 인덱스를 False&True 리스트에서 모두 False 처리하면서, 다음 루프에서 부터는 연산 수가 절반으로 줄어든다.** 마찬가지로 3의 배수, 5의 배수, 7의 배수 순서로 False 처리를 해나가며 연산 수를 기하급수적으로 줄여나간다. 이 때문에 코드 상 시간초과가 뜨지 않았다고 예상해 볼 수 있다.

```python
import sys
n = int(sys.stdin.readline().rstrip())
a = [False,False] + [True]*(n-1)
primes=[]

# 에라토스테네스의 체
for i in range(2,n+1):                  # 2부터 n까지의 수를
  if a[i]:                              # a의 원소가 True이면
    primes.append(i)                    # primes 리스트에 i를 append
    for j in range(2*i, n+1, i):        # i의 배수 값을 가진 모든 인덱스에 대하여
        a[j] = False                    # a 리스트에 False 처리. 연산 수가 기하급수적으로 줄어든다.

cnt = 0                                 # 카운트
sum = 0                                 # 부분합
end = 0                                 # 끝점

# 투 포인터 알고리즘
for start in range(len(primes)):        
    while sum < n and end < len(primes):
        sum += primes[end]
        end += 1
    if sum == n:                        # 부분합이 n일 시
        cnt += 1                        # 카운트 +1
    sum -= primes[start]

print(cnt)
```



---

### 결과

![image-20210208181057334](C:\Users\oh12s\Desktop\TIL\Coding Test\md-image\image-20210208181057334.png)

---

### Reference

- 에라토스테네스의 체: https://wikidocs.net/21638
- 투 포인터: [# 2003: 수들의 합2](../2003_수들의 합2) or [# 1806: 부분합](../1806_부분합)

