
package com.topjavatutorial.dao;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
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
    
}