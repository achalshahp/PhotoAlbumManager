package com.achal.dao;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
public class PhotosDAO {
    
	static String photosURL = "http://jsonplaceholder.typicode.com/photos";
	
    public void addPhoto(Photos bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addPhoto(session,bean);        
        tx.commit();
        session.close();
        }
    
    private void addPhoto(Session session, Photos bean){
    	Photos photo = new Photos();
        
        photo.setTitle(bean.getTitle());
        photo.setUrl(bean.getUrl());
        photo.setAlbumID(bean.getAlbumID());
        session.save(photo);
    }
    
    public List<Photos> getPhotos(){
        Session session = SessionUtil.getSession();  
      //Query to get the photos list from the Photos table.
        Query query = session.createQuery("from Photos");
        System.out.println(query.list());
        List<Photos> photos =  query.list();
        session.close();
        return photos;
    }
    
    public List<Photos> getPhotosforAlbumID(int id){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        //Query to get the list of photos for the album id passed.
        String getphotos = "from Photos where album_id = :id";
        Query query = session.createQuery(getphotos);
        query.setInteger("id", id);
        System.out.println(query.list());
        List<Photos> photos =  query.list();
        tx.commit();
        session.close();
        return photos;
    }
 
    public int deletePhoto(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        //Query to delete the photo based on the id passed.
        String hql = "delete from photos where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updatePhotos(int id, Photos photo){
         if(id <=0)  
               return 0;  
         	Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            //Query to update the photo based on the id passed.
            String hql = "update Photos set title = :title, url = :url, album_id = :album_id where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            query.setString("title",photo.getTitle());
            query.setString("url",photo.getUrl());
            query.setInteger("album_id", photo.getAlbumID());
            System.out.println("THE ALBUM ID THAT IS PASSED IS : " + photo.getAlbumID());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }

	public String getPhotosHttp() {
		// TODO Auto-generated method stub
		String responseString = null;
		try {
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			//HttpGet request to get all the data from the URL for the Init.
			HttpGet getRequest = new HttpGet(photosURL);
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));
			
			String title = null;
			
			StringBuilder sb = new StringBuilder();
			for (int c;(c=br.read())>=0;)
				sb.append((char)c);
			responseString = sb.toString();
			httpClient.getConnectionManager().shutdown();
			System.out.println("Response is now now now:.... \n" + responseString);
			
			int rowCount = 0;
			//Convert the json String into a Json array so that it can be parsed to insert into the db.
			JSONArray jsonArray = new JSONArray(responseString);
			
			for (int i=0;i<jsonArray.length();i++){
				JSONObject obj = jsonArray.getJSONObject(i);
				System.out.println("THE OBJECT IN THIS RUN IS: \n" + obj);
				int id_get = obj.getInt("id");
				System.out.println("THE ID FOUND IS : \n" + id_get);
				String str_get = obj.getString("title");
				System.out.println("THE ID FOUND IS : \n" + id_get);
				String url_get = obj.getString("url");
				int album_id_get = obj.getInt("albumId");
				insertIntoDB(id_get,str_get,url_get,album_id_get);
			}
		

	} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return responseString;

	}
	
	//Insert the photos data into the database. This will be then used to do the rest calls. 
	private int insertIntoDB(int id,String title, String url, int album_id)
	{
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Photos photo = new Photos();
		photo.setId(id);
		photo.setTitle(title);
		photo.setUrl(url);
		photo.setAlbumID(album_id);
		Integer ret_id = (Integer) session.save(photo);
        tx.commit();
        session.close();
		return ret_id;
		
	}
}