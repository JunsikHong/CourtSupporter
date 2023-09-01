package com.court.supporter.notice.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.command.TB_016VO;
import com.court.supporter.command.TB_003VO;
import com.court.supporter.util.Criteria;

@Service("noticeService")
public class noticeServiceImpl implements noticeService {

	@Autowired
	private noticeMapper noticeMapper;
	
	/////////////////////////////파일 업로드 경로와 폴더 생성
	//업로드 경로
	@Value("${project.upload.path}")
	private String uploadPath;
	
	//폴더 생성 함수
	public String makeFolder() {
		String path = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		File file = new File(uploadPath+"/"+path);
		if(file.exists()==false) {//존재하면 true, 존재하지 않으면 false
			file.mkdirs();
		}
		return path;
	}
	////////////////////////////
	//하나의 메서드에서 여러 CRUD작업이 일어나는 경우에 한 부분이 에러가 발생하면 그 에러를 처리하고, 롤백처리를 대신랍니다.
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int noticeRegist(TB_003VO vo, List<MultipartFile> list) {
		
		//sql 처리
		int result = noticeMapper.noticeRegist(vo);
		
		//업로드 처리
		for(MultipartFile file : list) {
			
			//파일이름
			String originName = file.getOriginalFilename();
			//브라우저 별로 파일의 경로가 다를 수 있기 때문에 \\기준으로 파일명만 잘라서 다시 저장
			String filename = originName.substring(originName.lastIndexOf("\\")+1);
			//파일 사이즈
			long size = file.getSize();
			//동일한 파일 재업로드시 기존파일을 덮어씌우므로 난수 이름으로 파일명을 바꿔서 올림
			String uuid = UUID.randomUUID().toString();
			//날짜별로 폴더 생성
			String filepath = makeFolder();
			//세이브할 경로
			String savepath = uploadPath+"/"+filepath+"/"+uuid+"_"+filename;
			//이건 작동하는지 확인하기 위한 임시저장이므로 데이터베이스에 다시 저장해야 함
			
			
			//파일확인
			System.out.println(originName);
			System.out.println("size: "+size);
			System.out.println("uuid: "+uuid);
			System.out.println("실제 파일명: "+filename);
			System.out.println("날짜폴더 경로: "+filepath);
			System.out.println("세이브경로: "+savepath);
			System.out.println("=========================================");
			try {
				File saveFile = new File(savepath);
				file.transferTo(saveFile);//파일업로
			} catch (Exception e) {
				System.out.println("파일업로드 중 error 발생");
				e.printStackTrace();
				return 0;//실패
			}
			//noticeUpload테이블에 파일 경로 인서트
			//prod_id는 insert전에 selectKey를 통해 얻습니다??
			
//			noticeMapper.noticeFileRegist(noticeUploadVO.builder()
//														.filename(filename)
//														.filepath(filepath)
//														.uuid(uuid)
//														.writer(vo.getAdmin_proper_num())
//														.build());
		}
		return result;
	}
	
	@Override
	public ArrayList<TB_003VO> noticeList(String writer, Criteria cri) {
		
		return noticeMapper.noticeList(writer, cri);
	}

	@Override
	public int getTotal(String writer, Criteria cri) {
		
		return noticeMapper.getTotal(writer, cri);
	}

	@Override
	public TB_003VO noticeDetail(int notice_proper_num) {
		
		return noticeMapper.noticeDetail(notice_proper_num);
	}

	

	@Override
	public int noticeModify(TB_003VO vo) {
		
		return noticeMapper.noticeModify(vo);
	}

	@Override
	public void noticeDelete(int notice_proper_num) {
		noticeMapper.noticeDelete(notice_proper_num);
		
	}

}
