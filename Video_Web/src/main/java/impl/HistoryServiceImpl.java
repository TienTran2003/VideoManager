package impl;

import java.sql.Timestamp;
import java.util.List;

import dao.HistoryDao;
import dao.impl.HistoryImpl;
import entity.History;
import entity.User;
import entity.Video;
import service.HistoryService;
import service.VideoService;

public class HistoryServiceImpl implements HistoryService{

	private HistoryDao dao;
	private VideoService videoService = new VideoServiceImpl();
	public HistoryServiceImpl() {
		dao = new HistoryImpl();
	}
	
	@Override
	public List<History> findByUser(String username) {
		return dao.findByUser(username);
	}

	@Override
	public List<History> findByUserAndIsLike(String username) {
		return dao.findByUserAndIsLike(username);
	}

	@Override
	public History findUserIdAndVideoId(Integer userId, Integer videoId) {
		return dao.findUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Video video) {
		History existHistory = findUserIdAndVideoId(user.getId(), video.getId());
		if(existHistory==null) {
			existHistory = new History();
			existHistory.setUser(user);
			existHistory.setVideo(video);
			existHistory.setViewedDate(new Timestamp(System.currentTimeMillis()));
			existHistory.setIsLiked(Boolean.FALSE);
			return dao.create(existHistory);
		}
		
		return existHistory;
	}

	@Override
	public Boolean updateLikeOrUnlike(User user, String videoHref) {
		Video video = videoService.findByHref(videoHref);
		History existHistory = findUserIdAndVideoId(user.getId(), video.getId());
		if(existHistory.getIsLiked() == Boolean.FALSE) {
			existHistory.setIsLiked(Boolean.TRUE);
			existHistory.setLikedDate(new Timestamp(System.currentTimeMillis()));
		} else {
			existHistory.setIsLiked(Boolean.FALSE);
			existHistory.setLikedDate(null);
		}
		History updatedHistory = dao.update(existHistory);
		return updatedHistory != null ? true : false;
	}
//
//	@Override
//	public History delete(History entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
