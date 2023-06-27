package impl;

import java.util.List;

import dao.StatsDao;
import dto.VideoLikedinfo;
import service.StatsService;

public class StatsServiceImpl implements StatsService{

	private StatsDao statsDao;
	
	public StatsServiceImpl() {
		statsDao = new StatsDaoImpl();
	}
	
	@Override
	public List<VideoLikedinfo> findVideoLikedInfo() {
		return statsDao.findVideoLikedinfo();
	}

}
