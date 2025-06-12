package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import interfaces.IServiceNotification;
import model.Employe;
import model.Messages;
import repositorie.EmployeRepo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ServiceNotification implements IServiceNotification {
    private final File fichierJson = new File("data.json") ;
    private final ObjectMapper mapper = new ObjectMapper();
    EmployeRepo employeRepo = new EmployeRepo();
    Scanner scanner = new  Scanner(System.in);

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
        List<Employe> listesEmployes = employeRepo.listeEmploye();
        if (!listesEmployes.isEmpty()){
            boolean trouve = false;
            for (Employe emp : listesEmployes) {
                if (emp.getEmail().equalsIgnoreCase(emailEmploye)) {
                    emp.setEstAbonne(false);
                    trouve = true;
                    break;
                }
            }

            if (trouve) {
                // Encapsule la liste dans un objet racine
                ObjectNode racine = mapper.createObjectNode();
                racine.set("Employes", mapper.valueToTree(listesEmployes));

                // Écriture correcte dans le fichier
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson, racine);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("L'employé avec l'email " + emailEmploye + " est maintenant abonné.");
            } else {
                System.out.println("Aucun employé trouvé avec l'email : " + emailEmploye);
            }
        }

    }

    @Override
    public void notifier(String emailExp) {
        List<Employe> listesEmployesNotifier = employeRepo.listeEmploye();
        EmailMessages emailMessages = new EmailMessages();
        System.out.println("Objet : ");
        String objet = scanner.nextLine();
        System.out.println("Message : ");
        String message = scanner.nextLine();
        for (Employe emp : listesEmployesNotifier){
            if (emp.isEstAbonne() && !emp.getEmail().equals(emailExp)){
                emailMessages.envoyer(emailExp,emp.getEmail(), objet, message);
                Messages messages = new Messages(objet,message,emailExp);
                emp.getNotifications().add(messages);
            }
        }
        ObjectNode racine = mapper.createObjectNode();
        racine.set("Employes", mapper.valueToTree(listesEmployesNotifier));
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson, racine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void afficherNotifications(String email, String motDePasse)
    {
        List<Employe> listEmploye = employeRepo.listeEmploye();
        for(Employe emp : listEmploye)
        {
            if(emp.getEmail().equals(email)
                    && emp.getMotDePasse().equals(motDePasse)&& emp.isEstAbonne())
            {
                int i = 1;
                for(Messages messages : emp.getNotifications())
                {
                    System.out.println(i+"-----------------------------");
                    System.out.println("Objet:" + messages.getObjet());
                    System.out.println("Content:"+messages.getContenu());
                    System.out.println("Expéditeur"+messages.getEmailMessaExp());
                    i++;
                }
            }
        }
    }
}
