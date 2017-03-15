
package com.achal;
 
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.achal.dao.AlbumDAO;
import com.achal.dao.Albums;

//This is the main path for all the albums related calls.
@Path("/albums")
public class AlbumResource {
	
	// GET Call to get a list of all the albums. 
	//URL : http://localhost:8080/Photo_Album_Manager/webapi/albums
	//VERB : GET
    @GET
    @Produces("application/json")
    public List<Albums> getAlbums() {
        AlbumDAO dao = new AlbumDAO();
        List albums = dao.getAlbums();
        return albums;
    }
    
    // GET call to get a list of the photos and the albums.
    // URL : http://localhost:8080/Photo_Album_Manager/webapi/albums/albumsandphotos
    // VERB : GET
    @Path("/albumsandphotos")
    @GET
    @Produces("application/json")
    public List<Albums> getAlbumswithPhotos() {
    	AlbumDAO dao = new AlbumDAO();
        List albums = dao.getAlbumswithPhotos();
        return albums;
    }
    
    // POST(Create) call to create new Albums. Please provide a body with this. for e.g.
    // URL : http://localhost:8080/Photo_Album_Manager/webapi/albums/create
    // VERB : POST
    // {"title":"NewAlbumTitle"}
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addAlbums(Albums album){
        album.setTitle(album.getTitle());            
        AlbumDAO dao = new AlbumDAO();
        String retTitle = dao.addAlbum(album);
        
        return Response.ok("New album has been created : " + retTitle).build();
    }
    
    // PUT(Update) call to update an already existing album. Provide the album id as a parameter.
    // URL : http://localhost:8080/Photo_Album_Manager/webapi/albums/update/1
    // VERB : PUT
    // Body : {"title":"NewAlbumName"}
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateAlbums(@PathParam("id") int id, Albums albums_value){
    	AlbumDAO dao = new AlbumDAO();
    	int count = dao.updateAlbums(id, albums_value.getTitle());
    	if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok("The update was successful.No of rows updated are : " + count).build();
    }
    
    // DELETE call to delete an album.Provide the album id as a parameter.
    // URL : http://localhost:8080/Photo_Album_Manager/webapi/albums/delete/1
    // VERB : DELETE
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteAlbums(@PathParam("id") int id){
    	AlbumDAO dao = new AlbumDAO();
        int count = dao.deleteAlbum(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok("The delete was successful.No of rows updated are : " + count).build();
    }
}
 
 