
package com.achal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.achal.dao.AlbumDAO;
import com.achal.dao.PhotosDAO;
 
 
@Path("/init")
public class DataInit {
	
	// GET call to initialize the albums, users and the photos based on the endpoints. 
	// URL : http://localhost:8080/Photo_Album_Manager/webapi/init
	// VERB : GET
    @GET
    @Produces("application/json")
    public String getAlbumsHttp() throws Exception {
    	AlbumDAO album_dao = new AlbumDAO();
		String albums = album_dao.getAlbumsHttp();
		PhotosDAO photo_dao = new PhotosDAO();
		String photo = photo_dao.getPhotosHttp();
		String str = "GOT DONE WITH INIT. THE DATA IS INTO THE DB NOW.";
		
		return str;
    }
}
 
 