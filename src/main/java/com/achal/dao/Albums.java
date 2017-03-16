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
@Table(name = "albums")
public class Albums {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int id;
    
    @Column
    private String title;
    
    @Column
    private int userID;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

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
}
    
