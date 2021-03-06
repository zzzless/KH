package com.kh.sample02.service;

import java.util.List;

import com.kh.sample02.vo.BoardVo;
import com.kh.sample02.vo.LikeVo;
import com.kh.sample02.vo.PagingDto;

public interface BoardService {
	public void writeRun(BoardVo boardVo);
	public List<BoardVo> listAll(PagingDto pagingDto);
	public BoardVo content(int b_no);
	public void removeRun(int b_no);
	public void modifyRun(BoardVo boardVo);
	public int getCount(PagingDto pagingDto);
	public int updateLikeCount(LikeVo likeVo);
	public LikeVo checkLike(LikeVo likeVo);
}
