package com.court.supporter.main.service;

import java.util.ArrayList;

import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_003VO;
import com.court.supporter.command.TB_004VO;

public interface MainService {

	public ArrayList<TB_002VO> getannouncelist(String search);
	public ArrayList<TB_003VO> getnoticelist(String search);
	public ArrayList<TB_004VO> getfaqlist(String search);
	public int getannouncetotal(String search);
	public int getnoticetotal(String search);
	public int getfaqtotal(String search);

}
