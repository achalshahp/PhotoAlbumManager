
package com.achal.dao;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Table Definition with the Getters and Setters.
@Entity
public class Albums {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int id;
    
    @Column
    private String title;

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
    
