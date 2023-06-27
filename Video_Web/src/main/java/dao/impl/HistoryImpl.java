package dao.impl;

import java.util.List;

import dao.AbstractDao;
import dao.HistoryDao;
import entity.History;

public class HistoryImpl extends AbstractDao<History> implements HistoryDao {

	@Override
	public List<History> findByUser(String username) {
		String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.video.isActive = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, username);
	
	}

	@Override
	public List<History> findByUserAndIsLike(String username) {
		String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.isLiked = 1"
				+ " AND o.video.isActive = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public History findUserIdAndVideoId(Integer userId, Integer videoId) {
		String sql = "SELECT o FROM History o WHERE o.user.id = ?0 AND o.video.id = ?1"
				+ " AND o.video.isActive = 1";
		return super.findOne(History.class, sql, userId, videoId);
	}

//	@Override
//	public History create(History entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public History update(History entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public History delete(History entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
