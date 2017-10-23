# Server
- Rest Api설계
- /서비스명/값
- /피보나치/100

### 1. 서버모듈(라이브러리)를 import
```JavaScript
var http = require("http");
```

### 2. 서버모듈을 사용해서 서버를 정의
```JavaScript
var server = http.createServer(function(request, response){
    response.writeHead(200, {'Content-Type' : 'text/html'});    
    // 2-1. 요청이 온 주소체계가 내가 제공하는 api와 맞는지 확인
    // decodeURI(주소) -> %20등의 주소문자를 원래 문자로 변환
    // encodeURI(문자) -> 주소로 사용할 수 있는 문자열로 변환
    var cmds = request.url.split("/");
    // 2-2. 잘못됐다면 알려줌
    if(cmds.length<3){
        response.end("<h>Your request is wrong<h>");        
    }else{ // 2-3. 주소체계가 정상일 때
        if(cmds[1]=="fibonacci"){
            if(!isNaN(cmds)){
                var text = fibo(cmds[2]);
                response.end(text);
            }else{
                response.end("<h>Value is Wrong<h>");
            }

        }else if(cmds[1=="anagram"]){

        }else{
            response.end("<h>No Service<h>");   
        }
    }

    /*
    // 사용자 요청에 대해 어떻게 응답할지를 정의
    var array = request.url.split("/");
    // 일반 for문은 배열의 index를 리턴
    // for(i in array)
    // response.write(" ["+item+"] ")
    // 향상된 for문은 배열안의 실제 item을 리턴
    response.writeHead(200, {'Content-Type' : 'text/html'});
    array.forEach(function(item){
        response.write(" ["+item+"] <br> ");
    */
    });
```

### 3. 서버실행
```JavaScript
server.listen(8089, function(){
    console.log("server is running");
});
```

```JavaScript
function fibo(count){
    var first=1;
    var second=1;
    var sum="";
    if(count==0){
        sum = "0보다 큰값 입력";
    }else if(count==1){
        sum = "1";
    }else if(count==2){
        sum = "1";
    }else{
        for(var i=3; i<=count; i++){
            var result = first+second;
            console.log(result);
            first=second;
            second=result;
            sum=result+"";
        }
    }
    return sum;
}
```
