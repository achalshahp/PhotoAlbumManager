
package com.topjavatutorial;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.topjavatutorial.dao.AlbumDAO;
import com.topjavatutorial.dao.Albums;
import com.topjavatutorial.dao.PhotosDAO;
import com.topjavatutorial.dao.Photos;
 
 
@Path("/init")
public class DataInit {
	
    @GET
    @Produces("application/json")
    public String getAlbumsHttp() throws Exception {
    	AlbumDAO album_dao = new AlbumDAO();
        String albums = null;
		albums = album_dao.getAlbumsHttp();
		PhotosDAO photo_dao = new PhotosDAO();
		String photo = photo_dao.getPhotosHttp();
		String str = "GOT DONE WITH INIT. THE DATA IS INTO THE DB NOW.";
        //return albums;
		return str;
    }
}
 
 