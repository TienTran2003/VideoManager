package impl;

import java.util.ArrayList;
import java.util.List;

import dao.AbstractDao;
import dao.StatsDao;
import dto.VideoLikedinfo;
import entity.User;

public class StatsDaoImpl extends AbstractDao<Object[]> implements StatsDao {

	@Override
	public List<VideoLikedinfo> findVideoLikedinfo() {
		String sql = "select v.id, v.title, v.href, sum(cast(h.isLiked as int)) as totalLike"
				+ " from video v left join history h on v.id = h.videoId"
				+ " where v.isActive = 1"
				+ " group by v.id, v.title, v.href"
				+ " order by sum(cast(h.isLiked as int)) desc";
		List<Object[]> objects = super.findManyByNativeQuery(sql);
		List<VideoLikedinfo> result = new ArrayList<>();
		objects.forEach(object -> {
			VideoLikedinfo videoLikedInfo = setDataVideoLikedInfo(object);
			result.add(videoLikedInfo);
		});
		return result;
	}
	
	private VideoLikedinfo setDataVideoLikedInfo(Object[] object) {
		VideoLikedinfo videoLikedInfo = new VideoLikedinfo();
		videoLikedInfo.setVideoId((Integer) object[0]);
		videoLikedInfo.setTitle((String) object[1]);
		videoLikedInfo.setHref((String) object[2]);
		videoLikedInfo.setTotalLike(object[3] == null ? 0 : (Integer) object[3]);
		return videoLikedInfo;
		
	}


}
