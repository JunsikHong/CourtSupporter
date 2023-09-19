/**
 * 
 */

 // AccessToken을 쿠키에서 가져오는 함수
function getAccessTokenFromCookie() {
  const cookies = document.cookie.split(';');
  for (let i = 0; i < cookies.length; i++) {
    const cookie = cookies[i].trim();
    if (cookie.startsWith('Authorization=')) {
		console.log('쿠키에서 token 가져옴')
      return cookie.substring('Authorization='.length);
    }
  }
  return null; // AccessToken이 존재하지 않을 경우 null 반환
}

// API 호출 함수
function callApi() {
  const accessToken = getAccessTokenFromCookie();
  
  if (accessToken) {
	  console.log(accessToken)
    // AccessToken이 존재하는 경우에만 헤더에 추가
    const headers = {
      'Authorization': `Bearer ${accessToken}`
    };

    // API 호출
    fetch('http://localhost:8788/announce/announceList', {
      method: 'GET',
      headers: headers
    })
    .then(response => {
		console.log(response)
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('API 요청 실패');
      }
    })
    .then(data => {
      // API 응답을 처리하는 코드
      console.log(data);
    })
    .catch(error => {
      // 오류 처리
      console.log('에러다')
      console.error(error);
    });
  } else {
    // AccessToken이 없는 경우에 대한 처리
    console.log('AccessToken이 존재하지 않습니다.');
  }
}

// API 호출 함수 호출
callApi();