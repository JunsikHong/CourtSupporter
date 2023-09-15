// 클래스 이름이 "downloadLink"인 모든 링크 엘리먼트를 가져옵니다.
var downloadLinks = document.getElementsByClassName("downloadLink");

	console.log(10);
// 각 링크에 대한 처리를 수행합니다.
for (var i = 0; i < downloadLinks.length; i++) {
    var downloadLink = downloadLinks[i];
    var fileName = downloadLink.getAttribute("name"); // 파일 이름을 "data-file-name" 속성에서 가져옵니다.
    var encodedFileName = encodeURIComponent(fileName);

	console.log(fileName);
    // AWS S3 주소를 구성하여 href 속성을 설정합니다.
    downloadLink.href = "https://court-supporter-project-t4.s3.ap-northeast-2.amazonaws.com/" + vo.file_path + vo.notice_file_uuid + "_" + encodedFileName;
    console.log(vo.file_path);
    console.log(vo.notice_file_uuid);
    console.log(vo.file_path);
}
