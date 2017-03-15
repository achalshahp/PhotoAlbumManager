package com.topjavatutorial.dao;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.json.Json;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.glassfish.jersey.server.JSONP;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@JsonSerialize
public class AlbumDAO {
    
	public void addAlbum(Albums bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addAlbum(session,bean);        
        tx.commit();
        session.close();
        
    }
    
	private void addAlbum(Session session, Albums bean){
    	Albums album = new Albums();
        
        album.setTitle(bean.getTitle());
        session.save(album);
    }
    
	public List<Albums> getAlbums(){
        Session session = SessionUtil.getSession();  
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
        String hql = "delete from albums where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateAlbums(int id, Albums album){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update albums set title = :title where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            query.setString("name",album.getTitle());
            //query.setInteger("age",emp.getAge());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }

	public String getAlbumsHttp() throws Exception{
		// TODO Auto-generated method stub
		
		String response1 = null;
		try {
			

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://jsonplaceholder.typicode.com/albums");
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));
			
			
			System.out.println("THE RESPONSE WE GOT IS: ]n" + response.getEntity().getContent() );
			String title = null;
			
			System.out.println("Response is :.... \n" + response);
			StringBuilder sb = new StringBuilder();
			for (int c;(c=br.read())>=0;)
				sb.append((char)c);
			response1 = sb.toString();
			httpClient.getConnectionManager().shutdown();
			System.out.println("Response is now now now:.... \n" + response1);
			
			
			int rowCount = 0;
			//String hql = "insert into Albums (title)";
			JSONArray jsonArray = new JSONArray(response1);
			
			for (int i=0;i<jsonArray.length();i++){
				JSONObject obj = jsonArray.getJSONObject(i);
				System.out.println("THE OBJECT IN THIS RUN IS: \n" + obj);
				int id_get = obj.getInt("id");
				System.out.println("THE ID FOUND IS : \n" + id_get);
				String str_get = obj.getString("title");
				System.out.println("THE ID FOUND IS : \n" + id_get);
				insertIntoDB(id_get,str_get);
			}
		

	} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //catch (JSONException e) {
 catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return response1;

	}
	
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