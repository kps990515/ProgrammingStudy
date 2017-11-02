var http = require("http");
var httpUrlConnection = require("request");

const fcmServerUrl = "https://fcm.googleapis.com/fcm/send";
const serverKey = "AAAAw_oKdys:APA91bEv7z5MjUAOlOyn5cYvduzxtsAOkXccuEaDRw5Cme9zyZo3vt3Q0hgz1_tuNLmEWwwrgIgo6-lTgrFMwB3DVz7HOfyjfA1M_ZEXMf-84OWNTHeUwQIylnJjQMEZ7kuI1abGUNP8 ";

var msg = {
    to : "",
    notification : {
        title : "타이틀",
        body : ""
    }
};    

var server = http.createServer(function(request,response){
    // postmessage 수신
    if(request.url == "/sendNotification"){
        var postdata="";
        request.on("data",function(data){
            postdata+=data;
        });
        // 메시지 수신완료
        request.on("end",function(){
            var postObj = JSON.parse(postdata);
            // 메시지 데이터 완성
            msg.to = postObj.to;
            msg.notification.body = postObj.msg;
            // 메시지를 fcm서버로 전송
            httpUrlConnection(
                // http 메시지 객체
                {
                    url : fcmServerUrl,
                    method : "POST",
                    headers : {
                        "Authorization" : "key="+serverKey,
                        "Content-Type" : "application/json"
                    },
                    body : JSON.stringify(msg)
                },
                // 콜백 함수
                function(error, answer, body){
                    var result = {
                        code : answer.statusCode,
                        msg : body
                    };
                    response.writeHead(200,{"Content-Type" : "plaint/text"});
                    response.end(JSON.stringify(result));
                }
            );
        });
    }else{
        response.end("404 Page not Found");
    }
    
});

server.listen(8090,function(){
    console.log("Server is running...");
});