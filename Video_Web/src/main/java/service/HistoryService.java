package service;

import java.util.List;

import entity.History;
import entity.User;
import entity.Video;

public interface HistoryService {
	List<History> findByUser(String username);
	List<History> findByUserAndIsLike(String username);
	History findUserIdAndVideoId(Integer userId, Integer videoId);
	History create(User user, Video video);
	Boolean updateLikeOrUnlike(User user, String videoHref);
}
