package in.adcast.dao;


import java.util.List;
import in.adcast.common.GenericDAO;
import in.adcast.model.Media;


public interface MediaDao extends GenericDAO<Media, Integer> {

	public String upload(Media image);

	public List<Media> getImageForGallery(Integer id);

}
