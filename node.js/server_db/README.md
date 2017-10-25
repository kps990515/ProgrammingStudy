# ServerDB
- 안드로이드에서 id pw입력하면
- node.js로 id pw가 날라가고
- node.js에서 mongodb에 있는 id pw체크 한다음
- node.js에서 성공 실패 메시지를
- 앱으로 날려준다

## 코드

### 선언부

```JavaScript
var http = require("http");
var mongo = require("mongodb").MongoClient;
var u = require("url");
var qs = require("querystring");

```

### 앱 데이터 수신 & DB로 전송

#### 데이터수신
- 안드로이드의 AsyncTask중 doInBackground에서 전달됨
[HttpSignin](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/HttpSignin)
```JavaScript
var server = http.createServer(function(request,response){

    var url = u.parse(request.url);
    var cmds = url.pathname.split("/");

    if(cmds[1]=="signin"){
        var postdata="";
        request.on("data",function(data){
            postdata += data;
        });
```

#### MongoDB에서 확인

```JavaScript
        request.on("end", function(){
            var query = JSON.parse(postdata); // postdata : id=xxx&pw=xxxx
            // query ={ id : "xxx", pw : "xxx"}   
            if(query.id&&query.pw){
                mongo.connect("mongodb://localhost:27017/testdb",function(error,db){
                    if(error){
                        response.write(error);
                        response.end("");
                    }else{
                        // db.collection('테이블명').명령어();
                        var cursor = db.collection('user').find(query);
                        var obj = {
                            code : "",
                            msg : "",
                        };
```

#### 확인 후 앱으로 메시지 전달
- 전달 후 앱의 onPostExecute에서 정보를 처리

```JavaScript
                        obj.code="300";
                        obj.msg="FAIL";
                        // 데이터셋 처리방법 2가지
                        // 1. each(동기) 2. foreach(비동기)
                       cursor.toArray(function(error,dataset){
                            if(dataset.length>0){
                                obj.code="200";
                                obj.msg="OK";
                            }
                            response.write(JSON.stringify(obj));
                            response.end("");
                        });
                    }
                });
            }else{
                response.end("id or pw is wrong");                
            }
        });    
    }else{
        response.end("page not found");
    }

    // mongo db 주소 구조 = 프로토콜://주소:포트/데이터베이스이름 -> db 변수에 전달

});

server.listen(8090,function(){
    console.log("Server is running...");
});
```
