# Stream

### 정의 - 자료의 입출력을 도와주는 중간 매개체

##### 스트림 전체 정의
- 스트림은 데이터를 읽고 기록하는 중간역할을 한다.  
스트림은 빨대다.  
빨대는 음료수를 마시는 중간역할을 한다.  
빨대는 입에 있는 음료수를 다시 내뱉는 중간역할을 한다.  
스트림은 단 방향 빨대이다. 음료수를 내뱉고 다시 마시려면 빨대가 2개 필요하다.  

##### 입력 스트림
- 음료수를 마실 때 빨대를 이용하여 음료수를 빨대에 모으고 빨대에 들어있는 음료수를 흡입한다.  
그러면 음료수가 입안으로 들어 올 것이다.

##### 출력 스트림
- 출력 스트림으로 데이터를 보낸다.   
출력 스트림에 보낸 데이터를 비워 버린다.   
그렇게 되면 출력 스트림에 존재하던 데이터가 모두 목표지점에 저장된다.

- 입 안에 있던 음료수를 빨대로 일단 보낸다.   
빨대에 들어있는 음료수를 불어 버린다.  
그렇게 되면 음료수는 다시 컵 안으로 들어가게 된다.

### 스트림의 종류

##### 1. 바이트 스트림 - 데이터를 byte단위로 주고받음
![바이트스트림input](https://github.com/kps990515/ProgrammingStudy/blob/master/Java/%EA%B3%B5%EB%B6%80%EC%9E%90%EB%A3%8C/Stream/%EB%B0%94%EC%9D%B4%ED%8A%B8%EC%8A%A4%ED%8A%B8%EB%A6%BCinput.png)

![바이트스트림output](https://github.com/kps990515/ProgrammingStudy/blob/master/Java/%EA%B3%B5%EB%B6%80%EC%9E%90%EB%A3%8C/Stream/%EB%B0%94%EC%9D%B4%ED%8A%B8%EC%8A%A4%ED%8A%B8%EB%A6%BCoutput.png)

##### 2. 문자 스트림
- 문자는 인코딩 방식에 따라서 byte의 크기가 다르다
- 바이트 스트림으로 byte단위로 데이터를 받아오면
- 맞는 인코딩 방식(자바 : utf-8)으로 byte를 묶어줘야한다

![문자스트림input](https://github.com/kps990515/ProgrammingStudy/blob/master/Java/%EA%B3%B5%EB%B6%80%EC%9E%90%EB%A3%8C/Stream/%EB%AC%B8%EC%9E%90%EC%8A%A4%ED%8A%B8%EB%A6%BCinput.png)

![문자스트림output](https://github.com/kps990515/ProgrammingStudy/blob/master/Java/%EA%B3%B5%EB%B6%80%EC%9E%90%EB%A3%8C/Stream/%EB%AC%B8%EC%9E%90%EC%8A%A4%ED%8A%B8%EB%A6%BCoutput.png)

##### 2-1. Buffered - 문자스트림의 종류
- 단독으로 바로 inputStream을 받지는 않는다
- InputStreamReader가 한 문자씩 읽어들이면
- 그 문자들을 인수로 받아 한 줄씩 출력할 수 있게 도와준다
- 입/출력이 훨씬 효율적(readLine()이용!)

참고사이트 : 스트림 정의(http://elena90.tistory.com/entry/Java-%ED%8C%8C%EC%9D%BC-%EC%9E%85%EC%B6%9C%EB%A0%A5%EC%8A%A4%ED%8A%B8%EB%A6%BCInputStreamOutputStreamReaderWriter)

참고사이트 : 바이트 / 문자 스트림(http://stevenjsmin.tistory.com/96)

참고사이트 : stream / buffered 차이(http://dodocap.tistory.com/entry/JAVA%EC%9D%98-%EC%9E%85%EC%B6%9C%EB%A0%A5-%EA%B8%B0%EB%B3%B8%EA%B0%9C%EB%85%90-Stream-ReaderWriter-Buffered)
