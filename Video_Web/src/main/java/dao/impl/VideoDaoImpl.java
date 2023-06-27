package dao.impl;

import java.util.List;

import dao.AbstractDao;
import dao.VideoDao;
import entity.Video;

public class VideoDaoImpl extends AbstractDao<Video>  implements VideoDao{

	@Override
	public Video findId(Integer id) {
		
		return super.findById(Video.class, id);
	}

	@Override
	public Video findByHref(String href) {
		String sql="SELECT o FROM Video o WHERE o.href = ?0";
		return super.findOne(Video.class, sql, href);
	}

	@Override
	public List<Video> findAll() {
		return super.findAll(Video.class, true);
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		
		return super.findAll(Video.class, true, pageNumber, pageSize);
	}

//	@Override
//	public Video create(Video entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Video update(Video entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Video delete(Video entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
