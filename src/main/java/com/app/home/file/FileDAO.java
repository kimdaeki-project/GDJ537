package com.app.home.file;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDAO {
	
	public int setFile(FileVO fileVO)throws Exception;

}
