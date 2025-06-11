package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import interfaces.IServiceNotification;
import model.Employe;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ServiceNotification implements IServiceNotification {
    private final File fichierJson = new File("data.json") ;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void ajouterAbonne(String emailEmploye) {
        try {
            // Lire tout le fichier comme un objet JSON
            JsonNode rootNode = mapper.readTree(fichierJson);

            // Récupérer le tableau "Employes"
            JsonNode employesNode = rootNode.get("Employes");

            if (employesNode == null || !employesNode.isArray()) {
                System.out.println("Erreur : le champ 'Employes' est introuvable ou mal formé.");
                return;
            }
            // Convertir le tableau JSON en liste d'objets Java
            List<Employe> employes = mapper.readValue(
                    mapper.treeAsTokens(employesNode),
                    new TypeReference<List<Employe>>() {}
            );

            boolean trouve = false;
            for (Employe emp : employes) {
                if (emp.getEmail().equalsIgnoreCase(emailEmploye)) {
                    emp.setEstAbonne(true);
                    trouve = true;
                    break;
                }
            }

            if (trouve) {
                // Encapsule la liste dans un objet racine
                ObjectNode racine = mapper.createObjectNode();
                racine.set("Employes", mapper.valueToTree(employes));

                // Écriture correcte dans le fichier
                mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson, racine);

                System.out.println("L'employé avec l'email " + emailEmploye + " est maintenant abonné.");
            } else {
                System.out.println("Aucun employé trouvé avec l'email : " + emailEmploye);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void retirerAbonne(String emailEmploye) {
        try {
            // Lire tout le fichier comme un objet JSON
            JsonNode rootNode = mapper.readTree(fichierJson);

            // Récupérer le tableau "Employes"
            JsonNode employesNode = rootNode.get("Employes");

            if (employesNode == null || !employesNode.isArray()) {
                System.out.println("Erreur : le champ 'Employes' est introuvable ou mal formé.");
                return;
            }
            // Convertir le tableau JSON en liste d'objets Java
            List<Employe> employes = mapper.readValue(
                    mapper.treeAsTokens(employesNode),
                    new TypeReference<List<Employe>>() {}
            );

            boolean trouve = false;
            for (Employe emp : employes) {
                if (emp.getEmail().equalsIgnoreCase(emailEmploye)) {
                    emp.setEstAbonne(false);
                    trouve = true;
                    break;
                }
            }

            if (trouve) {
                // Encapsule la liste dans un objet racine
                ObjectNode racine = mapper.createObjectNode();
                racine.set("Employes", mapper.valueToTree(employes));

                // Écriture correcte dans le fichier
                mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson, racine);

                System.out.println("L'employé avec l'email " + emailEmploye + " est maintenant abonné.");
            } else {
                System.out.println("Aucun employé trouvé avec l'email : " + emailEmploye);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
