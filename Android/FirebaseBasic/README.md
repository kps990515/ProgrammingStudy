# FirebaseBasic

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/FirebaseBasic/Screenshot_1509325335.png)

### on Create()
- database = FirebaseDatabase
- bbsRef = bbs테이블
- userRef = user테이블

```java
@Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);

     // 데이터베이스 connection
     database = FirebaseDatabase.getInstance();
     bbsRef = database.getReference("bbs");
     userRef = database.getReference("user");

     initView();
     initListener();
 }
```

### signup버튼
- 버튼을 누르면 id,name을 가져와서 user테이블에 새로운 값을 넣는다

```java
 public void signup(View v){
     String id = edit_id.getText().toString();
     String name = edit_name.getText().toString();

     User user = new User(name,"none",17);
     userRef.child(id).setValue(user);
 }
```

### post버튼
- user_id가 선택된것이 없으면 user선택하라는 메시지 띄움
- user_id가 선택되었으면 bbsRef테이블을 만들고
- bbskey에 테이블 key를 가져온다
- bbs테이블에 bbs값들을 넣고
- user테이블에 bbs의 값들을 넣는다

```java
 public String user_id="";

 public void post(View v){
     if(user_id == null || "".equals(user_id)){
         Toast.makeText(this,"user를 선택하세요",Toast.LENGTH_SHORT);
     }else{
         String title = edit_title.getText().toString();
         Bbs bbs = new Bbs(title,"내용","2017-10-30",user_id);

         // 노드 생성
         String bbsKey = bbsRef.push().getKey();
         // 노드에 데이터 입력
         bbsRef.child(bbsKey).setValue(bbs);
         // 사용자 아이디에 게시글을 추가
         userRef.child(user_id).child("bbs").child(bbsKey).setValue(bbs);
     }
 }
 ```

### initListener()
- valueEventListener를 각 테이블에 달아줌
- 경로전체에 내용에 대한 변경사항을 읽고 수신대기
- 변동이 있을 시 스냅샷을 찍음

#### user테이블 ValueEventListener
- user테이블에 bbs노드가 있을 경우
- user스냅샷을 hashmap으로 저장하고 user에 해당하는 내용들만 저장한다
- user.child("bbs")를 통해 bbs노드를 꺼내서 저장한다  
- bbs노드가 없으면 스냅샷의 정보를 user클래스 형태로 저장한다
- 그리고 refresh - [userAdapter](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/FirebaseBasic/app/README.md)


```java
 private void initListener(){
     valueEventListener = (new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             data = new ArrayList<>();
             // 변경사항이 스냅샷 형태로 넘어오면
             // 해당 스냅샷의 하위 노드를 배열로 꺼내서 사용한다.
             for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                 String id = snapshot.getKey();
                 // 하위 노드 아래에 리스트 형태의 또 다른 노드가 있으면
                 // 부가적인 처리가 필요하다
                 if(snapshot.hasChild("bbs")){
                     User user = new User();
                     Map map = (HashMap) snapshot.getValue();
                     user.username = (String) map.get("username");
                     user.email = (String) map.get("email");
                     user.user_id = id;

                     // 하위노드의 스냅샷 꺼내기
                     DataSnapshot bbsSnapShot = snapshot.child("bbs");
                     user.bbs = new ArrayList<>();
                     // 하위 노드에 리스트가 존재하면 해당 리스트를
                     // 배열로 꺼내서 위의 방법처럼 사용한다
                     for(DataSnapshot item : bbsSnapShot.getChildren()) {
                         Bbs bbs = item.getValue(Bbs.class);
                         user.bbs.add(bbs);
                     }
                     data.add(user);
                 }else{
                     User user = snapshot.getValue(User.class);
                     user.user_id = id;
                     data.add(user);
                 }
             }
             adapter.refresh(data);
         }
         @Override
         public void onCancelled(DatabaseError databaseError) {

         }
     });
     userRef.addValueEventListener(valueEventListener);
```

#### bbs테이블 valueEventListener달기
- 스냅샷을 bbs.class형태로 꺼내와서 저장한다
- 그리고 refresh
- [bbsAdapter](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/FirebaseBasic/app/src/README.md)

```java
     bbsvalueEventListener = (new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             List<Bbs> bbsdata = new ArrayList<>();

             for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                 Bbs bbs = snapshot.getValue(Bbs.class);
                 bbs.id = snapshot.getKey();
                 bbsdata.add(bbs);
             }
             // 데이터 어뎁터에 반영
             // 아답터를 notify
             bbsAdapter.refresh(bbsdata);

         }

         @Override
         public void onCancelled(DatabaseError databaseError) {

         }
     });
     bbsRef.addValueEventListener(bbsvalueEventListener);
 }
```

#### initView()

```java
 private void initView(){
     edit_id = findViewById(R.id.edit_id);
     edit_name = findViewById(R.id.edit_name);
     edit_title = findViewById(R.id.edit_title);

     userList = findViewById(R.id.userList);
     bbsList = findViewById(R.id.bbsList);

     adapter = new UserAdapter(this);
     userList.setAdapter(adapter);
     manager = new LinearLayoutManager(getApplicationContext());
     userList.setLayoutManager(manager);

     bbsAdapter = new BbsAdapter();
     bbsList.setAdapter(bbsAdapter);
     bbsmanager = new LinearLayoutManager(getApplicationContext());
     bbsList.setLayoutManager(bbsmanager);
 }
 ```

### onResume(), onPause(), setUserid()
- setUserid는 userAdapter의 인터페이스를 구현한 것
- 초기화면에서 id를 누르면 작동한다

```java
 @Override
 protected void onResume() {
     super.onResume();
     userRef.addValueEventListener(valueEventListener);
     bbsRef.addValueEventListener(bbsvalueEventListener);
 }

 @Override
 protected void onPause() {
     super.onPause();
     userRef.removeEventListener(valueEventListener);
     bbsRef.removeEventListener(bbsvalueEventListener);
 }

 @Override
 public void setUserid(String userid) {
     this.user_id = userid;
 }
```
