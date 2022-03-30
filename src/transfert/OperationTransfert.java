package transfert;

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
import versement.VersementBean;



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

	

}
