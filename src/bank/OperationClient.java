package bank;



import java.io.IOException;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OperationClient {
	
	public static HttpURLConnection connection;
	
	@SuppressWarnings("unchecked")
	public boolean insert(ClientBean clientBean) {
		
		JSONObject unClient = new JSONObject();
		unClient.put("id", clientBean.getId());
		unClient.put("numCompte", clientBean.getNumCompte());
		unClient.put("nom", clientBean.getNom());
		unClient.put("prenoms", clientBean.getPrenoms());
		unClient.put("solde", clientBean.getSolde());
		
		OkHttpClient client = new OkHttpClient();		
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		
		RequestBody body = RequestBody.create(JSON, unClient.toString());
		String urlPostClient = "http://localhost:8000/api/clients";
		
		Request request = new Request
				.Builder()
				.url(urlPostClient)
				.post(body)
				.build();
		
		try {
			Response response = client.newCall(request).execute();
			return true;
			/*if (response != null) {
				JOptionPane.showMessageDialog(null,  "Ajout avec succès ^^");
				return true;
			}	*/		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,  "Ooopsa! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return false;
		
	}
	
	public List<ClientBean> getClient() {
		
		List<ClientBean> lista = new ArrayList<ClientBean>();
		//System.out.println("migety client izy zao ");
		String urlGetClient = "http://localhost:8000/api/clients";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request
				.Builder()
				.url(urlGetClient)
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
						
						JSONObject unClient = (JSONObject) hydramember.get(i);
						Long id = (Long) unClient.get("id");
						String numCompte = (String) unClient.get("numCompte");
						String nom = (String) unClient.get("nom");
						String prenoms = (String) unClient.get("prenoms");
						Long solde = (Long) unClient.get("solde");
					
						ClientBean bean = new ClientBean(id, numCompte, nom, prenoms, solde);
						lista.add(bean);						
						
						
					} 						
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Ooopsa! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block						
			System.err.println("erora !" + e);
			JOptionPane.showMessageDialog(null, "Impossible de contacter la base de données", "ALERTE", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
		return lista;
		/*call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				// TODO Auto-generated method stub
				System.err.println("erora !" + e.getMessage());
				JOptionPane.showMessageDialog(null, "Impossible de contacter la base de données", "ALERTE", JOptionPane.INFORMATION_MESSAGE);
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					//System.out.println(response.body().string());
					String jsonData = response.body().string();
					JSONObject forcast;
					try {
						forcast = (JSONObject) JSONValue.parseWithException(jsonData);
						JSONArray hydramember =  (JSONArray) forcast.get("hydra:member");
						// JSONArray forcast.get("hydra:member");
						
						
						for (int i = 0; i < hydramember.size(); i++) { 
							
							JSONObject client = (JSONObject) hydramember.get(i);
							Long id = (Long) client.get("id");
							String numCompte = (String) client.get("numCompte");
							String nom = (String) client.get("nom");
							String prenoms = (String) client.get("prenoms");
							Long solde = (Long) client.get("solde");
							//System.out.println(id);
							//System.out.println(nom);
							//System.out.println(client.get("id").getClass());
							
							//ClientBean bean = new ClientBean(id, numCompte, nom, prenoms, solde);
							ClientBean bean = new ClientBean(id, numCompte, nom, prenoms, solde);
							
							((ClientBean) lista).setId(id);
							((ClientBean) lista).setNumCompte(numCompte);
							((ClientBean) lista).setNom(nom);
							((ClientBean) lista).setPrenoms(prenoms);
							((ClientBean) lista).setSolde(solde);
							System.out.println(bean);
							lista.add(bean);		
						} 						
						//System.out.println(forcast);
						//System.out.println(hydramember);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ooopsa! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Ooops! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
				
				}				
			}
		});
		*/					
	}

	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		OkHttpClient client = new OkHttpClient();
		String urlDeleteClient = "http://localhost:8000/api/clients/"+id;
		//System.out.println(urlDeleteClient);
		Request request = new Request
				.Builder()
				.url(urlDeleteClient)
				.delete()
				.build();		
		try {
			Response response = client.newCall(request).execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null,  "Ooopsa! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return false;
		
		
	}

	public List<ClientBean> getClientById(Long id) {
		List<ClientBean> lista = new ArrayList<ClientBean>();
		OkHttpClient client = new OkHttpClient();
		String urlGetClientById = "http://localhost:8000/api/clients/"+id;
		Request request = new Request
				.Builder()
				.url(urlGetClientById)
				.build();
		try {
			Response response = client.newCall(request).execute();
			String jsonData = response.body().string();
			
			JSONObject UNclient = (JSONObject) JSONValue.parse(jsonData);
			//System.out.println(UNclient.get("nom"));
			Long idClient = (Long) UNclient.get("id");
			String numCompte = (String) UNclient.get("numCompte");
			String nom = (String) UNclient.get("nom");
			String prenoms = (String) UNclient.get("prenoms");
			Long solde = (Long) UNclient.get("solde");
			
			ClientBean bean = new ClientBean(idClient, numCompte, nom, prenoms, solde);
			lista.add(bean);	
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,  "Ooopsa! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}		
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Boolean update(ClientBean clientBean) {
		JSONObject unClient = new JSONObject();
		Long id = clientBean.getId();
		unClient.put("numCompte", clientBean.getNumCompte());
		unClient.put("nom", clientBean.getNom());
		unClient.put("prenoms", clientBean.getPrenoms());
		unClient.put("solde", clientBean.getSolde());
				
		OkHttpClient client = new OkHttpClient();		
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		
		RequestBody body = RequestBody.create(JSON, unClient.toString());
		String urlPostClient = "http://localhost:8000/api/clients/"+id;
		
		Request request = new Request
				.Builder()
				.url(urlPostClient)
				.put(body)
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

}
