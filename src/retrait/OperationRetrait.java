package retrait;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;


import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class OperationRetrait {

	public List<RetraitBean> getRetrait()  {
		List<RetraitBean> lista = new ArrayList<RetraitBean>();
		String urlGetRetrait = "http://localhost:8000/api/retraits";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request
				.Builder()
				.url(urlGetRetrait)
				.build();
		Call call = client.newCall(request);		
		try {
			Response response = call.execute();
			if (response.isSuccessful()) {
				String jsonData = response.body().string();
				JSONObject forcast;
				try {
					forcast = (JSONObject) JSONValue.parseWithException(jsonData);
					JSONArray hydramember =  (JSONArray) forcast.get("hydra:member");
	
					for (int i = 0; i < hydramember.size(); i++) { 
						
						JSONObject unRetrait = (JSONObject) hydramember.get(i);
						Long id = (Long) unRetrait.get("id");
						String numCheque = (String) unRetrait.get("numCheque");
						
						Long montantRetrait = (Long) unRetrait.get("montantRetrait");
						
						//----Date
						String date = (String) unRetrait.get("dateRetrait");
						String trueDate = null;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
						try {
							Date daty = formatter.parse(date);
							trueDate = new SimpleDateFormat("dd/MMM/YYYY HH:mm").format(daty);
							
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//----Date
						
						
						JSONObject Client =  (JSONObject) unRetrait.get("client");
						String numCompte = (String) Client.get("numCompte");
						String nomPrenoms = (String) Client.get("nom") + " " +Client.get("prenoms");							
											
						
						RetraitBean bean = new RetraitBean(id, numCheque ,numCompte, nomPrenoms, montantRetrait, trueDate);
						lista.add(bean);
					} 						
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Ooopsa! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block						
			System.err.println("erora !" + e);
			JOptionPane.showMessageDialog(null, "Impossible de contacter la base de données", "ALERTE", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
		return lista;		
	}

	@SuppressWarnings("unchecked")
	public Boolean ajoutRetrait(RetraitBean retrait) {
		// TODO Auto-generated method stub
		JSONObject unRetrait = new JSONObject();
		unRetrait.put("numCheque", retrait.getNumCheque());
		unRetrait.put("montantRetrait", retrait.getMontantRetrait());
		unRetrait.put("client", "/api/clients/"+retrait.getId());
		
		OkHttpClient client = new OkHttpClient();		
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		
		RequestBody body = RequestBody.create(JSON, unRetrait.toString());
		String urlPostRetrait = "http://localhost:8000/api/retraits";
		
		Request request = new Request
				.Builder()
				.url(urlPostRetrait)
				.post(body)
				.build();
		
		try {
			Response response = client.newCall(request).execute();
			return true;	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,  "Ooopsa! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return false;
		
	}

	public List<RetraitBean> getRetraitByClientId(Long id) {
		List<RetraitBean> lista = new ArrayList<RetraitBean>();
		OkHttpClient client = new OkHttpClient();
		String urlGetRetraitByClientId = "http://localhost:8000/api/clients/"+id+"/retraits";
		Request request = new Request
				.Builder()
				.url(urlGetRetraitByClientId)
				.build();
		Call call = client.newCall(request);	
		try {
			Response response = call.execute();
			if (response.isSuccessful()) {
				String jsonData = response.body().string();
				JSONObject forcast;
				try {
					forcast = (JSONObject) JSONValue.parseWithException(jsonData);
					JSONArray hydramember =  (JSONArray) forcast.get("hydra:member");
	
					for (int i = 0; i < hydramember.size(); i++) { 
						
						JSONObject unRetrait = (JSONObject) hydramember.get(i);
						String numCheque = (String) unRetrait.get("numCheque");
						Long montantRetrait = (Long) unRetrait.get("montantRetrait");
						
						//----Date
						String date = (String) unRetrait.get("dateRetrait");
						String trueDate = null;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
						try {
							Date daty = formatter.parse(date);
							trueDate = new SimpleDateFormat("dd/MMM/YYYY HH:mm").format(daty);
							
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//----Date
												
											
						RetraitBean bean = new RetraitBean(null, null, numCheque, null, montantRetrait, trueDate);
						//RetraitBean bean = new RetraitBean(numVersement, null, null, montantVersement, trueDate);
						lista.add(bean);
					} 						
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Ooopsa! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block						
			System.err.println("erora !" + e);
			JOptionPane.showMessageDialog(null, "Impossible de contacter la base de données", "ALERTE", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}		
		
		return lista;
	
	}

}
