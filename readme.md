

### - 금일 완료 리스트✔️
- [x] 댓글
- [x] 댓글멘션
- [x] 대댓글
- [x] 댓글, ajax화
- [x] 좋아요, 싫어요 ajax화 변경완료
---

### - 당장 할일 리스트✔️	
- [ ] 게시물 블라인드
- [ ] 내 글에 새 댓글 알림
- [ ] 내 댓글에 추가 댓글 알림
---

### - 추후 할일 리스트✔️
- [ ] 태그
- [ ] 파일업로드
- [ ] 회원인증
- [ ] 관리자페이지
- [ ] 1:1 쪽지
- [ ] 신고

---

### - 그 동안 완료리스트✔️
- 프레임워크 기초   👌
- 로그인/로그아웃   👌
- 회원가입   👌
- 게시글 CRUD   👌
- 인터셉터   👌
- 로그인/로그아웃 상태 관련 권한체크   👌
- 게시물 수정/삭제 관련 권한체크   👌
- 비밀번호 암호화   👌
- 아이디찾기   👌
- 비번찾기(임시패스워드 발송)   👌
- 게시물 페이징   👌
- 게시물 검색   👌
- 회원 정보 수정   👌
- 게시물 작성에 토스트 에디터 붙이기   👌
- 회원정보 수정페이지(비밀번호 수정 추가) 👌
- 게시물 수정페이지에 토스트 에디터 붙이기  👌
---
기본 기능 구현 완료 !!!👍



다음 추가 작업💻
===
- 메인페이지 디자인 적용


# MySQL db 백업
- C:\xampp\mysql\bin\mysqldump -u root -p dkJspCommunity> C:\work\sts-4.9.0.RELEASE-workspace\dkJspCommunity\current.sql

# maven settings.xml 템플릿
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>서버ID</id>
            <username>톰캣웹관리자계정(배포관리자)의 로그인아이디</username>
            <password>톰캣웹관리자계정(배포관리자)의 비빌번호</password>
        </server>
    </servers>
</settings>
```