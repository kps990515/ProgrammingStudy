var mongo = require("mongodb").MongoClient;
var dbname = "bbsdb";
var dburl = "mongodb://localhost:27017/"+dbname;
var table = "bbs";
    // 랜덤함수
    function randomRange(n1,n2){
        return Math.floor((Math.random()*(n2-n1+1))+n1);
    }
    function make(){
        var makes="";
        for(var i=0; i<10; i++){
            makes+=String.fromCharCode(randomRange(58,126));
        }
        return makes;
    }
    var ids = ["a","b","c","d","e","f","g","h","i","j"];


mongo.connect(dburl, function(error,db){
    var Bbs = function(){
        this.no = -1;
        this.title = "";
        this.content = "내용입니다";
        this.date = new Date() + "";
        this.user_id = "";
        this.toQuery = function(){
            return {
                no : this.no,
                title : this.title,
                content : this.content,
                date : this.date,
                user_id : this.user_id
            };
        };
    };

    // 배열선언
    var count=1;
    for(var j=0; j<100; j++){
        var array = [];        
        for(var i=0; i<1000; i++){
            var bbs = new Bbs();
            bbs.no = count++;
            bbs.title = make();
            bbs.user_id = ids[Math.floor(Math.random()*10)];
            array[i]=bbs.toQuery();
            console.log(array[i]);                   
        }
        // multiple insert
        db.collection(table).insertMany(array,function(error,inserted){
            if(error){
                console.log(error);
            }else{

            }
        });
    }
});
