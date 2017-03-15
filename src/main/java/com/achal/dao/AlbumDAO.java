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
//import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlbumDAO {
    
	static String albumsURL = "http://jsonplaceholder.typicode.com/albums";
	
	public String addAlbum(Albums bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        String returnVal = addAlbum(session,bean);        
        tx.commit();
        session.close();
        return returnVal;
        }
    
	private String addAlbum(Session session, Albums bean){
    	Albums album = new Albums();
        album.setTitle(bean.getTitle());
        session.save(album);
        return bean.getTitle();
    }
    
	public List<Albums> getAlbums(){
        Session session = SessionUtil.getSession(); 
        //Query to get the albums list from the Albums table.
        Query query = session.createQuery("from Albums");
        System.out.println(query.list());
        List<Albums> albums =  query.list();
        session.close();
        return albums;
    }
    
    public List<Albums> getAlbumswithPhotos() {
		// TODO Auto-generated method stub
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		//Query to get the list of Albums and their associated Photos.
		String albumswithphotos = "from Albums,Photos";
        Query query = session.createQuery(albumswithphotos);
        //query.setInteger();
        System.out.println(query.list());
        List<Albums> albums =  query.list();
        tx.commit();
        session.close();
        return albums;
	}
 
    public int deleteAlbum(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        //Query to delete the album based on the id passed.
        String hql = "delete from Albums where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateAlbums(int id, String string){
         if(id <=0)  
               return 0;
         Session session = SessionUtil.getSession();
         Transaction tx = session.beginTransaction();
         String hql = "update Albums set title = :title where id = :id";
         Query query = session.createQuery(hql);
         query.setInteger("id",id);
         query.setString("title",string);
         System.out.println("THE TITLE PASSED IS : " + string);
         int rowCount = query.executeUpdate();
         System.out.println("Rows affected: " + rowCount);
         tx.commit();
         session.close();
         return rowCount;
    }

	public String getAlbumsHttp() throws Exception{
		// TODO Auto-generated method stub
		String responseString = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			//HttpGet request to get all the data from the URL for the Init. 
			HttpGet getRequest = new HttpGet(albumsURL);
			getRequest.addHeader("accept", "application/json");

			HttpResponse httpResponse = httpClient.execute(getRequest);

			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ httpResponse.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(httpResponse.getEntity().getContent())));
			
			StringBuilder sb = new StringBuilder();
			
			for (int c; (c = br.read())>=0;)
				sb.append((char)c);
			
			responseString = sb.toString();
			httpClient.getConnectionManager().shutdown();			
			
			//Convert the json String into a Json array so that it can be parsed to insert into the db.
			JSONArray jsonArray = new JSONArray(responseString);
			
			for (int i=0;i<jsonArray.length();i++){
				JSONObject obj = jsonArray.getJSONObject(i);
				int id_get = obj.getInt("id");
				String str_get = obj.getString("title");
				insertIntoDB(id_get,str_get);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return responseString;

	}
	
	//Insert the album data into the database. This will be then used to do the rest calls. 
	private int insertIntoDB(int id,String title)
	{
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		Albums album = new Albums();
		album.setId(id);
		album.setTitle(title);
		Integer ret_id = (Integer) session.save(album);
        tx.commit();
        session.close();
		return ret_id;
		
	}

}