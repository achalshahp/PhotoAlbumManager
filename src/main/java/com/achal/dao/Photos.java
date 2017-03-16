/**
 * @author ashah8
 *
 */
package com.achal.dao;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Table Definition with the Getters and Setters.
@Entity
@Table (name = "photos")
public class Photos {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column
    private String title;
    @Column
    private String url;
    @Column
    private int album_id;
    @Column
    String thumbnailUrl;
    
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl(){
    	return url;
    }
    public void setUrl(String url){
    	this.url = url;
    }
    public int getAlbumID(){
    	return album_id;
    }
    public void setAlbumID(int album_id){
    	this.album_id = album_id;
    }
    public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
}