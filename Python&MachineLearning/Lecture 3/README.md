# Lecture 3

### File I/O
```Python
파일 만들기 & 입력
f = open("test.txt",'w')
for i in range(1,11):
    data = "%d번째 줄입니다\n"%i
    f.write(data)
f.close()

읽어오기
f=open("test.txt",'r')
data=f.read()
print(data)
f.close()

파일 내용 추가
f=open("test.txt",'a')
for i in range(11,20):
    data = "%d번째 줄입니다\n"%i
    f.write(data)
f.close()
```

### 계산기
```Python
class FourCal:
    def setdata(self,a,b):
        self.a=a
        self.b=b
    def sum(self):
        return self.a+self.b
    def mul(self):
        return self.a*self.b
    def sub(self):
        return self.a-self.b
    def div(self):
        return self.a/self.b

a=FourCal()
a.setdata(4,2)
print(a.sum())
print(a.mul())
print(a.sub())
print(a.div())
```

### Import
```Python
mod1에서 함수 정의
def sum(a,b):
    return a+b
def mul(a,b):
    return a*b
print(sum(3,5))

main에서 mod1받아와서 사용
import mod1 as cal
print(cal.sum(2,3))
print(cal.mul(3,3))
```
