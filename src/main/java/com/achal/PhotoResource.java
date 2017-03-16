/**
 * @author ashah8
 *
 */
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

import com.achal.dao.Photos;
import com.achal.dao.PhotosDAO;

//This is the main path for all the photos related calls.
@Path("/photos")
public class PhotoResource {
	
	// GET Call to get a list of all the photos. 
	//URL : http://localhost:8080/Photo_Album_Manager/webapi/photos
	//VERB : GET
    @GET
    @Produces("application/json")
    public List<Photos> getPhotos() {
    	PhotosDAO dao = new PhotosDAO();
        List photos = dao.getPhotos();
        return photos;
    }
    
    // GET Call to get a list of all the photos based on the album id passed as parameter. . 
 	//URL : http://localhost:8080/Photo_Album_Manager/webapi/photos/album_id/{album_id}
 	//VERB : GET
    @Path("/album_id/{id}")
    @GET
    @Produces("application/json")
    public List<Photos> getPhotosforAlbumID(@PathParam("id") int id) {
    	PhotosDAO dao = new PhotosDAO();
        List photos = dao.getPhotosforAlbumID(id);
        return photos;
    }
 
    // POST(Create) call to create a new photo. Provide the title,url and the album id associated with the photo. 
 	//URL : http://localhost:8080/Photo_Album_Manager/webapi/photos/create
 	// VERB : POST
    // BODY : {"title":"NewAlbumTitle", "url":"newurl","albumID":"1","thumbnailUrl","http://placehold.it/150/92c952"}
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addPhotos(Photos photo){
        photo.setTitle(photo.getTitle());
        photo.setUrl(photo.getUrl());
        photo.setAlbumID(photo.getAlbumID());
        PhotosDAO dao = new PhotosDAO();
        String retTitle = dao.addPhoto(photo);
        return Response.ok("New photo has been created with these: " + retTitle).build();
    }
    
    // PUT(Update) call to update an already existing photo. Provide the photo id as a parameter.
    // URL : http://localhost:8080/Photo_Album_Manager/webapi/photos/update/1
    // VERB : PUT
    // Body : {"title":"NewAlbumTitle", "url":"newurl","albumID":"1","thumbnailUrl","http://placehold.it/150/92c952"}
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updatePhotos(@PathParam("id") int id, Photos photos_value){
    	PhotosDAO dao = new PhotosDAO();
        int count = dao.updatePhotos(id, photos_value);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok("The update was successful.No of rows updated are : " + count).build();
    }
    
    // DELETE call to delete an photo.Provide the photo id as a parameter.
    // URL : http://localhost:8080/Photo_Album_Manager/webapi/photos/delete/1
    // VERB : DELETE
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deletePhotos(@PathParam("id") int id){
    	PhotosDAO dao = new PhotosDAO();
        int count = dao.deletePhoto(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok("The delete was successful.No of rows updated are : " + count).build();
    }
    
    
}
 
 