package dao;

import java.util.List;

import entity.History;

public interface HistoryDao {
	List<History> findByUser(String username);
	List<History> findByUserAndIsLike(String username);
	History findUserIdAndVideoId(Integer userId, Integer videoId);
	History create(History entity);
	History update(History entity);
	History delete(History entity);
}
