package repositorie;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import model.Employe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeRepo {
    static ObjectMapper mapper = new ObjectMapper(); // à rechercher
    File file = new File("data.json");
    List<Employe> listEmploye = new ArrayList<>();
    public ArrayNode retounerEmpploye(){
        JsonNode root = null;
        try {
             root = mapper.readTree(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return (ArrayNode) root.get("Employes");//retourne la liste des employés dans le fichier json
    }
    public Employe getEmploye(String email, String motDePasse){
        ArrayNode listeEmployes = retounerEmpploye();
        for (JsonNode node : listeEmployes){
            if(node.get("email").asText().equals(email) && node.get("motDePasse").asText().equals(motDePasse) ){
                Employe employe = new Employe(node.get("nom").asText(), node.get("prenom").asText(),
                        node.get("email").asText(),
                        node.get("motDePasse").asText());
                employe.setEstAdmin(node.get("estAdmin").asBoolean());
                return employe;
            }
        }
        return null;
    }

    //Fonction pour afficher la liste des employés
    public void afficherEmploye(){
        ArrayNode listeEmployes = retounerEmpploye();
        for (JsonNode node : listeEmployes){
            System.out.println("Nom : "+node.get("nom").asText());
            System.out.println("Prénom : "+node.get("prenom").asText());
            System.out.println("Email : "+node.get("email").asText()+"\n");
            System.out.println("***************************************************");
        }
    }

    }