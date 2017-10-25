var http = require("http");
var mongo = require("mongodb").MongoClient;
var u = require("url");
var qs = require("querystring");


var server = http.createServer(function(request,response){

    var url = u.parse(request.url);
    var cmds = url.pathname.split("/");

    if(cmds[1]=="signin"){
        var postdata="";
        request.on("data",function(data){
            postdata += data;
        });

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