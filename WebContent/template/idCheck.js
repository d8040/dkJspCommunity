
// 회원가입 화면의 입력값들을 검사한다.
function checkValue()
{
  var form = document.userInfo;

  if(!form.loginId.value){
    alert("아이디를 입력하세요.");
    return false;
  }

  if(form.idDuplication.value != "idCheck"){
    alert("아이디 중복체크를 해주세요.");
    return false;
  }

  if(!form.loginPw.value){
    alert("비밀번호를 입력하세요.");
    return false;
  }

  // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
  if(form.loginPw.value != form.loginPwCheck.value ){
    alert("비밀번호를 동일하게 입력하세요.");
    return false;
  }    

  if(!form.name.value){
    alert("이름을 입력하세요.");
    return false;
  }

  if(!form.email.value){
    alert("메일 주소를 입력하세요.");
    return false;
  }

  if(!form.cellphoneNo.value){
    alert("전화번호를 입력하세요.");
    return false;
  }

  if(isNaN(form.cellphoneNo.value)){
    alert("전화번호는 - 제외한 숫자만 입력해주세요.");
    return false;
  }

}

// 취소 버튼 클릭시 첫화면으로 이동
function goFirstForm() {
  location.href="MainForm.do";
}    

// 아이디 중복체크 화면open
function openIdChk(){

  window.name = "parentForm";
  window.open("/dkJspCommunity/usr/member/idCheckForm",
              "chkForm", "width=500, height=300, resizable = no, scrollbars = no");    
}

// 아이디 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
// 이렇게 하는 이유는 중복체크 후 다시 아이디 창이 새로운 아이디를 입력했을 때
// 다시 중복체크를 하도록 한다.
function inputIdChk(){
  document.userInfo.idDuplication.value ="idUncheck";
}


var httpRequest = null;

// httpRequest 객체 생성
function getXMLHttpRequest(){
  var httpRequest = null;

  if(window.ActiveXObject){
    try{
      httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
    } catch(e) {
      try{
        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) { httpRequest = null; }
    }
  }
  else if(window.XMLHttpRequest){
    httpRequest = new window.XMLHttpRequest();
  }
  return httpRequest;    
}


// 회원가입창의 아이디 입력란의 값을 가져온다.
function pValue(){
  document.getElementById("userId").value = opener.document.userInfo.loginId.value;
}

// 아이디 중복체크
function idCheck(){

  var id = document.getElementById("loginId").value;

  if (!id) {
    alert("아이디를 입력하지 않았습니다.");
    return false;
  } 
  else if((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")){ 
    alert("한글 및 특수문자는 아이디로 사용하실 수 없습니다.");
    return false;
  }
  else
  {
    var param="loginId="+id
    httpRequest = getXMLHttpRequest();
    httpRequest.onreadystatechange = callback;
    httpRequest.open("POST", "MemberIdCheckAction.do", true);    
    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
    httpRequest.send(param);
  }
}

function callback(){
  if(httpRequest.readyState == 4){
    // 결과값을 가져온다.
    var resultText = httpRequest.responseText;
    if(resultText == 0){
      alert("사용할수없는 아이디입니다.");
      document.getElementById("cancelBtn").style.visibility='visible';
      document.getElementById("useBtn").style.visibility='hidden';
      document.getElementById("msg").innerHTML ="";
    } 
    else if(resultText == 1){ 
      document.getElementById("cancelBtn").style.visibility='hidden';
      document.getElementById("useBtn").style.visibility='visible';
      document.getElementById("msg").innerHTML = "사용 가능한 아이디입니다.";
    }
  }
}

// 사용하기 클릭 시 부모창으로 값 전달 
function sendCheckValue(){
  // 중복체크 결과인 idCheck 값을 전달한다.
  opener.document.userInfo.idDuplication.value ="idCheck";
  // 회원가입 화면의 ID입력란에 값을 전달
  opener.document.userInfo.id.value = document.getElementById("userId").value;

  if (opener != null) {
    opener.chkForm = null;
    self.close();
  }    
}    