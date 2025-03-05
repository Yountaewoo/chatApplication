# API SPEC

## Member
#### - Long : id
#### - String : userName
#### - String : loginId
#### - String : password
#### - LocalDateTime : createdAt
#### - LocalDateTime : updatedAt
#### - LocalDateTime : deletedAt

## 회원가입 (/signup) Post
### request / RequestBody
#### - String : userName
#### - String : loginId
#### - String : password

### response
#### - Long : id
#### - String : userName
#### - String : loginId
#### - String : password

## 로그인(/signin) Post
### request / RequestBody
#### - String : loginId
#### - String : password

### response
### String : token

## 회원 탈퇴 (/member) Delete
### request / RequestHeader
### String : token


-------------------------------------------------------------------------


## ChatRoom
#### - Long : id
#### - String : name
#### - boolean : isDeleted(기본 false)

## 채팅방 생성 (/chatroom) Post
### request / RequestBody, RequestHeader
### String : token

### response
#### - Long : id
#### - String : name

## 채팅방 수정 (/chatroom/{chatRoomId}) Put
### request / RequestBody, RequestHeader
### - Long : chatRoomId
### - String : name
### - String : token

### response
### - Long : chatRoomId
### - String : name


-------------------------------------------------------------------------------------------


## Message
#### - Long : id
#### - Member : memberId(보낸 사용자)
#### - String : content
#### - boolean : isDeleted(기본 false)

## 메세지 생성 (/message) Post
### request / RequestBody, RequestHeader
### - String : token
### - Long : userId
### - String : content

### response
### - Long : messageId
### - String : content

## 사용자가 속해 있는 채팅방의 메세지 조회 (/message/{roomId}) Get
### request / PathVariable, RequestHeader
### - String : token
### - Long : userId

### request
### - String : content

------------------------------------------------------------------------------------------


## RoomMemberShip(사용자와 채팅방의 가운데 테이블)
### - Long : id
### - Member : memberId
### - ChatRoom : chatRoomId

## RoomMemberShip 생성 (/RoomMemberShip) Post
### request RequestBody
### - Long : userId
### - Long : chatRoomId