
package com.topjavatutorial;
 
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

import com.topjavatutorial.dao.Albums;
import com.topjavatutorial.dao.AlbumDAO;
import com.topjavatutorial.dao.Photos;
import com.topjavatutorial.dao.PhotosDAO;
 
@Path("/photos")
public class PhotoResource {
 
    @GET
    @Produces("application/json")
    public List<Photos> getPhotos() {
    	PhotosDAO dao = new PhotosDAO();
        List photos = dao.getPhotos();
        return photos;
    }
    
    @Path("/album_id/{id}")
    @GET
    @Produces("application/json")
    public List<Photos> getPhotosforAlbumID(@PathParam("id") int id) {
    	PhotosDAO dao = new PhotosDAO();
        List photos = dao.getPhotosforAlbumID(id);
        return photos;
    }
 
    
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addPhotos(Photos photo){
        photo.setTitle(photo.getTitle());
        photo.setUrl(photo.getUrl());
        photo.setAlbumID(photo.getAlbumID());
                
        PhotosDAO dao = new PhotosDAO();
        dao.addPhoto(photo);
        
        return Response.ok().build();
    }
    
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updatePhotos(@PathParam("id") int id, Photos photo){
    	PhotosDAO dao = new PhotosDAO();
        int count = dao.updatePhotos(id, photo);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deletePhotos(@PathParam("id") int id){
    	PhotosDAO dao = new PhotosDAO();
        int count = dao.deletePhoto(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
 
 