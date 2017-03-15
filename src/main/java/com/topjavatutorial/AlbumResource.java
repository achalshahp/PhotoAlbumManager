
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

import org.json.JSONException;

import com.topjavatutorial.dao.AlbumDAO;
import com.topjavatutorial.dao.Albums;
import com.topjavatutorial.dao.PhotosDAO;
import com.topjavatutorial.dao.Photos;
 
 
@Path("/albums")
public class AlbumResource {
 
    @GET
    @Produces("application/json")
    public List<Albums> getAlbums() {
        AlbumDAO dao = new AlbumDAO();
        List albums = dao.getAlbums();
        return albums;
    }
 
    
    @Path("/albumsandphotos")
    @GET
    @Produces("application/json")
    public List<Albums> getAlbumswithPhotos() {
    	AlbumDAO dao = new AlbumDAO();
        List albums = dao.getAlbumswithPhotos();
        return albums;
    }
    
    
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addAlbums(Albums album){
        album.setTitle(album.getTitle());
        //emp.setAge(emp.getAge());
                
        AlbumDAO dao = new AlbumDAO();
        dao.addAlbum(album);
        
        return Response.ok().build();
    }
    
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateAlbums(@PathParam("id") int id, Albums album){
    	AlbumDAO dao = new AlbumDAO();
        int count = dao.updateAlbums(id, album);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteAlbums(@PathParam("id") int id){
    	AlbumDAO dao = new AlbumDAO();
        int count = dao.deleteAlbum(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
 
 