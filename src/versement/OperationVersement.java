package versement;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import bank.ClientBean;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class OperationVersement {

	public List<VersementBean> getVersement()  {
		List<VersementBean> lista = new ArrayList<VersementBean>();
		String urlGetVersement = "http://localhost:8000/api/versements";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request
				.Builder()
				.url(urlGetVersement)
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
						
						JSONObject unVersement = (JSONObject) hydramember.get(i);
						Long id = (Long) unVersement.get("id");
						Long montantVersement = (Long) unVersement.get("montantVersement");
						
						//----Date
						String date = (String) unVersement.get("dateVersement");
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
						
						
						JSONObject Client =  (JSONObject) unVersement.get("client");
						String numCompte = (String) Client.get("numCompte");
						String nomPrenoms = (String) Client.get("nom") + " " +Client.get("prenoms");							
											
						
						VersementBean bean = new VersementBean(id, numCompte, nomPrenoms, montantVersement, trueDate);
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
	public Boolean ajoutVersement(VersementBean versement) {

		JSONObject unVersement = new JSONObject();
		unVersement.put("montantVersement", versement.getMontantVersement());
		unVersement.put("client", "/api/clients/"+versement.getId());
		
		OkHttpClient client = new OkHttpClient();		
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		
		RequestBody body = RequestBody.create(JSON, unVersement.toString());
		String urlPostClient = "http://localhost:8000/api/versements";
		
		Request request = new Request
				.Builder()
				.url(urlPostClient)
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



	public List<VersementBean> getVersementByClientId(Long id) {
		List<VersementBean> lista = new ArrayList<VersementBean>();
		OkHttpClient client = new OkHttpClient();
		String urlGetVersementByClientId = "http://localhost:8000/api/clients/"+id+"/versements";
		Request request = new Request
				.Builder()
				.url(urlGetVersementByClientId)
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
						
						JSONObject unVersement = (JSONObject) hydramember.get(i);
						Long numVersement = (Long) unVersement.get("id");
						Long montantVersement = (Long) unVersement.get("montantVersement");
						
						//----Date
						String date = (String) unVersement.get("dateVersement");
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
												
											
						
						VersementBean bean = new VersementBean(numVersement, null, null, montantVersement, trueDate);
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
