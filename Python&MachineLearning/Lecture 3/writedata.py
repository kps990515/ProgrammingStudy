'''
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
'''
