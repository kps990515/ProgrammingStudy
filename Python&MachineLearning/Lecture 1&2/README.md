# Lecture 1&2

### 별 찍기
```Python
space=" "
star="*"
starRange=5
line=starRange
all=""
enter="\n"

print("range = "+str(starRange))
while line>0:
    spaceCnt = line - 1
    space1=spaceCnt
    space2=spaceCnt
    starCnt = (starRange - line) * 2 + 1
    while space1>0:
        all+=space
        space1-=1
    while starCnt>0:
        all+=star
        starCnt-=1
    while space2>0:
        all+=space
        space2-=1
    all+=enter
    line-=1
print(all)
```

```Python
range = 5
    *    
   ***   
  *****  
 *******
*********
```

### List 평균 구하기
```Python
sum=0
list = [70,60,55,75,95,90,80,80,85,100]
for i in list:
    sum+=i
print(list)
print("Average = "+str(sum/len(list)))
print("==================================")
```
```Python
[70, 60, 55, 75, 95, 90, 80, 80, 85, 100]
Average = 79.0
```

### 소수 구하기
```Python
list2=[2]
p=1
while p<=100:
    if(p>=3):
        i=2
        count=0
        while i<=p-1:
            if p%i!=0:
                count+=1
            i+=1
        if count==p-2:
            list2.append(p)
    p+=1
print("PrimeNumber = "+str(list2))
```
```Python
PrimeNumber = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
```

### 쌍둥이 소수
```Python
index=0
count=0
list3=[]
while index<len(list2):
    p1=list2[index]
    p2=p1+2
    if p2 in list2:
        list3.append((p1,p2))
    index+=1
print("Twin-PrimeDouble = "+str(list3))
```
```Python  
Twin-PrimeDouble = [(3, 5), (5, 7), (11, 13), (17, 19), (29, 31), (41, 43), (59, 61), (71, 73)]
```

### 평균구하는 함수
```Python
def avg(a,b,c):
    average = (a+b+c)/3
    return average
print("avgFunction = "+str(avg(1,2,3)))
```
```Python  
avgFunction = 2.0
```

### 팩토리얼 함수
```Python
def fact(a):
    if(a==1):sum=1
    else:sum=fact(a-1)*a
    return sum
print("factFunction = "+str(fact(4)))
```
```Python  
factFunction = 24
```
