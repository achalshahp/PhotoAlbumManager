/**
 * @author ashah8
 *
 */
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
    	//Make the call to get the albums data and insert into albums table.
		album_dao.getAlbumsHttp();
		PhotosDAO photo_dao = new PhotosDAO();
		//Make the call to get the photos data and insert into photos table.
		photo_dao.getPhotosHttp();
		String str = "Done with the Init. The data is now in the db. Please use the back button to go back to the Homepage and issue commands from there or you can just use a RESTClient to perform REST calls.";
		
		return str;
    }
}
 
 