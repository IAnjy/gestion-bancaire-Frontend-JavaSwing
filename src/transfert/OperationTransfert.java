package transfert;

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

import bank.ClientBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class OperationTransfert {

	public List<TransfertBean> getTransfert() {
		List<TransfertBean> lista = new ArrayList<TransfertBean>();
		String urlGetTransfert = "http://localhost:8000/api/transferts";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request
				.Builder()
				.url(urlGetTransfert)
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
						
						JSONObject unTransfert = (JSONObject) hydramember.get(i);
						Long id = (Long) unTransfert.get("id");
						Long montantTransfert = (Long) unTransfert.get("montantTransfert");
						
						//----Date
						String date = (String) unTransfert.get("dateTransfert");
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
						
						
						JSONObject Expediteur =  (JSONObject) unTransfert.get("expediteur");
						String numCompteExpediteur = (String) Expediteur.get("numCompte");
						
						JSONObject Destinataire =  (JSONObject) unTransfert.get("destinataire");
						String numCompteDestinataire = (String) Destinataire.get("numCompte");
						
						TransfertBean bean = new TransfertBean(id, montantTransfert, numCompteExpediteur, numCompteDestinataire, trueDate);
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
	
	public void getClient() {
		
		List<ClientBean> lista = new ArrayList<ClientBean>();
		String urlGetClient = "http://localhost:8000/api/clients";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request
				.Builder()
				.url(urlGetClient)
				.build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				// TODO Auto-generated method stub
				System.err.println("erora !" + e.getMessage());
				JOptionPane.showMessageDialog(null, "Impossible de contacter la base de données", "ALERTE", JOptionPane.INFORMATION_MESSAGE);
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					String jsonData = response.body().string();
					JSONObject forcast;
					try {
						forcast = (JSONObject) JSONValue.parseWithException(jsonData);
						
						JSONArray hydramember =  (JSONArray) forcast.get("hydra:member");
						
						for (int i = 0; i < hydramember.size(); i++) { 
							
							JSONObject client = (JSONObject) hydramember.get(i);
							Long id = (Long) client.get("id");
							String numCompte = (String) client.get("numCompte");
							String nom = (String) client.get("nom");
							String prenoms = (String) client.get("prenoms");
							Long solde = (Long) client.get("solde");
							
							
							ClientBean bean = new ClientBean(id, numCompte, nom, prenoms, solde);
							
						/*	bean.setId(id);
							bean.setNumCompte(numCompte);
							bean.setNom(nom);
							bean.setPrenoms(prenoms);
							bean.setSolde(solde);*/
							
							/*((ClientBean) lista).setId(id);
							((ClientBean) lista).setNumCompte(numCompte);
							((ClientBean) lista).setNom(nom);
							((ClientBean) lista).setPrenoms(prenoms);
							((ClientBean) lista).setSolde(solde);*/
							
							lista.add(bean);
							//System.out.println(lista);
							
							//response.body(lista.add(bean));		
						} 
						
						System.out.println(request);
						
						

						UpdateListeClient(lista);
						
					} catch (ParseException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ooopsa! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ooops! Une erreur est survenue! Veuillez réessayez SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
				
				}				
			}
		});
						
	}

	public List<ClientBean> UpdateListeClient(List<ClientBean> lista) {
		System.out.println(lista);
		
		for (ClientBean bean : lista) {
		
		bean.getNumCompte();
		bean.getNom();
		bean.getPrenoms();
		bean.getSolde();
		bean.getId();
		
		System.out.println(bean.getNom());
		}
	
		return lista;		
	}

	@SuppressWarnings("unchecked")
	public boolean ajoutTransfert(TransfertBean transfert) {
		
		JSONObject unTransfert = new JSONObject();
		unTransfert.put("montantTransfert", transfert.getMontantTransfert());
		unTransfert.put("expediteur", "/api/clients/"+transfert.getNumCompteExpediteur());
		unTransfert.put("destinataire", "/api/clients/"+transfert.getNumCompteDestinataire());
		
		OkHttpClient client = new OkHttpClient();		
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		
		RequestBody body = RequestBody.create(JSON, unTransfert.toString());
		String urlPostTransfert = "http://localhost:8000/api/transferts";
		
		Request request = new Request
				.Builder()
				.url(urlPostTransfert)
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
	
	

}
