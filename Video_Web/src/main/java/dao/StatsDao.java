package dao;

import java.util.List;

import dto.VideoLikedinfo;
import entity.User;

public interface StatsDao {
	
	List<VideoLikedinfo> findVideoLikedinfo();
}
