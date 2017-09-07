# MVC모델
![model](https://github.com/kps990515/ProgrammingStudy/blob/master/Java/%EA%B3%B5%EB%B6%80%EC%9E%90%EB%A3%8C/MVC%EB%AA%A8%EB%8D%B8/500px-MVC-Process.svg.png)
### 디자인 패턴
- 디자인 패턴은 건축으로치면 공법에 해당하는 것으로 소프트웨어의 개발 방법을 공식화 한 것이다.  
 소수의 뛰어난 엔지니어가 해결한 문제를 다수의 엔지니어들이 처리 할 수 있도록 한 규칙이면서,  
  구현자들 간의 커뮤니케이션의 효율성을 높이는 기법이다

### 정의
- Model–view–controller (MVC) is a software architectural pattern  
for implementing user interfaces on computers.    
>MVC란 유저인터페이스를 구현하기 위한 소프트웨어 설계패턴이다

- MVC란 Model View Controller의 약자로 에플리케이션을 세가지의 역할로 구분한 개발 방법론이다.  
사용자가 Controller를 조작하면 Controller는 Model을 통해서 데이터를 가져오고  
 그 정보를 바탕으로 시각적인 표현을 담당하는 View를 제어해서 사용자에게 전달하게 된다.

- This is done to separate internal representations of information from the ways information is presented to, and accepted from, the user.  
>정보가 어떻게 유저에게 표현되고 받아들여지는에 따라  
  정보의 내부표현 방법을 분리시켜놓은 것이다

### Model
- 데이터 관리 & 어플의 논리구조, 규칙 관리 역활  

- model is the central component of the pattern.  
 It expresses the application's behavior in terms of the problem domain,  
independent of the user interface.  
 It directly manages the data, logic and rules of the application.  
> Model은 MVC패턴에서 중심적 요소  
어플리케이션의 행동을 유저 인터페이스와 분리하여  
problem domain(문제영역만 중점적으로 보는)에 따라 보여준다  

- A model stores data that is retrieved according to commands  
 from the controller and displayed in the view.
> Model은  
 1. Controller에서 받은 명령
 2. View에 표시된 내용  
에 의해 검색된 데이터들을 저장한다

### View
- A view can be any output representation of information, such as a chart or a diagram.  
> view는 정보가 차트나 다이어그램 등으로 표현된 output이다

- A view generates new output to the user based on changes in the model.
> view는 Model에 변경이 일어나면 유저에게 새로운 view를 제공한다

### Controller
- the controller, accepts input and converts it to commands for the model or view.
> controller는 입력을 받고 입력을 model이나 view가 받을 수 있는 명령으로 바꿔준다

- controller can send commands to the model to update the model's state (e.g., editing a document).  
It can also send commands to its associated view to change the view's presentation of the model
> controller는 model의 상태를 바꾸기 위해 명령을 보낼 수 있다  
> 또한 model을 표현하고 있는 view를 바꾸기 위해 명령을 보낸다

### 목표
##### 1. 동시개발
- MVC decouples the various components of an application,  
 developers are able to work in parallel on  
  different components without impacting or blocking one another.
> MVC는 어플리케이션을 여러 부분으로 분리하기 때문에  
개발자들이 서로 다른 부분을 평행적으로 충돌이나 방해 없이 개발할 수 있다.

##### 2. 코드 재사용성
- By creating components that are independent of one another,  
 developers are able to reuse components quickly and easily in other applications.
 > 코드를 역활마다 쪼개놓기 때문에 다른 어플을 개발할 때도  
같은 기능이 존재하면 코드를 재사용 할 수 있다.

참고사이트 : 위키피디아(https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)

참고사이트 : 생활코딩(https://opentutorials.org/course/697/3828) 
