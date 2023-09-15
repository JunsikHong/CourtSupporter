/*//검색
var searchBtn = document.getElementById("searchBtn");
searchBtn.onclick = () => {
  event.preventDefault();
  document.searchForm.submit();
}

var menu = document.querySelector(".menu");
menu.onclick = function() {

  if(menu.contains("on")) {
    menu.classList.remove("on");
    menu.style.backgroundColor = "white";
  } else {  
    menu.classList.add("on"); 
    menu.style.backgroundColor = "black";
  }      
}


var admin = "admin_proper_num";
//var user = "admin";
//관리자가 아닐시 버튼 숨김
var registBtn = document.getElementById("registBtn");
if (admin_proper_num === "1") {
  //관리자일 경우 모든 버튼을 보이게
  registBtn.style.display = "block";
} else {
  //관리자 제외 버튼을 숨김
  registBtn.style.display = "none";
}


var searchOption = document.querySelector("#searchOption");
	var search = document.querySelector(".search");

	document.querySelector("#searchOption").addEventListener("change", function () {
		var selectValue = this.value;

		var searchInput = document.querySelector(".search");

		if (selectValue === "announce_title") {
			searchInput.setAttribute("name", "announce_title");
		} else if (selectValue === "announce_content") {
			searchInput.setAttribute("name", "announce_content");
		} else if (selectValue === "announce_start_date") {
			searchInput.setAttribute("name", "announce_start_date");
		} else if (selectValue === "announce_end_date") {
			searchInput.setAttribute("name", "announce_end_date");
		}
	});*/