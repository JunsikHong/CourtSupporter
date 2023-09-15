
var admin = "admin_proper_num";
//var user = "admin";
//관리자가 아닐시 버튼 숨김
var modifyBtn = document.getElementById("modifyBtn"); //수정버튼
var delBtn = document.getElementById("deleteBtn"); //삭제버튼
if (admin_proper_num === "1") {
    //관리자일 경우 모든 버튼을 보이게
    modifyBtn.style.display = "block";
    delBtn.style.display = "block";	    
} else {
    //관리자 제외 버튼을 숨김
    modifyBtn.style.display = "none";	    
    delBtn.style.display = "none";	    
}	


function printPageArea(areaID){
    var printContent = document.getElementById(areaID).innerHTML;
    var originalContent = document.body.innerHTML;
    document.body.innerHTML = printContent;
    window.print();
    document.body.innerHTML = originalContent;
}


/*//작성취소 - 목록으로 돌아가기
let listBtn = document.getElementById("listBtn");
listBtn.onclick = () => {
  event.preventDefault();
  window.location.href = "/announce/announceList";
}

// 삭제 기능
let deleteBtn = document.getElementById("deleteBtn");
deleteBtn.onclick = () => {		
  event.preventDefault();		
  if(confirm("삭제하시겠습니까?")){
    document.actionForm.action = "deleteForm"; //action변경 
    document.actionForm.submit();			
  }
}*/

