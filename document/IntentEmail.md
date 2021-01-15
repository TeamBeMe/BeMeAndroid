## 정말 간단하게 이메일 양식받아 보내기

```
        val intent = Intent(Intent.ACTION_SEND)    //이메일을 보내는 액션을 지정
        intent.type = "plain/text"   
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("teambeme@naver.com"))  //받는 사람을 지정
        intent.putExtra(Intent.EXTRA_SUBJECT, "BeMe 유저 신고 ")  //메일의 제목 지정
        intent.putExtra( // 메일의 내용 지정
            Intent.EXTRA_TEXT, "1. 문의 유형 ( 문의, 버그 제보, 탈퇴하기, 기타) : \n" +
                    "2. 회원 닉네임 (필요시 기입) :\n" +
                    "3. 문의 내용 :\n" +
                    "\n" +
                    "문의하신 사항은 BeMe팀이 신속하게 처리하겠습니다. 감사합니다 :)"
        )
        intent.type = "message/rfc822"
        startActivity(intent)
```

