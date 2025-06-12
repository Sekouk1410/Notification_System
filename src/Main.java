import model.Employe;
import repositorie.EmployeRepo;
import service.GestionEmploye;
import service.ServiceNotification;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        EmployeRepo repo = new EmployeRepo();
        GestionEmploye gestionEmploye = new GestionEmploye();
        ServiceNotification serviceNotification =new ServiceNotification();

        System.out.println("Bienvenue");
        int choix;
        int choixMenu;

        do {
            System.out.println("1: Se connecter");
            System.out.println("2: Quitter");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Entrez votre adresse email : ");
                    String email = scanner1.nextLine();
                    System.out.println("Entrez votre mot de passe : ");
                    String motDePasse = scanner1.nextLine();
                    Employe employe = repo.getEmploye(email,motDePasse);
                    if (gestionEmploye.seConnecter(employe)) {
                        if (gestionEmploye.verifierProfil(employe)){
                            do {
                                System.out.println("1: Afficher la liste des employés");
                                System.out.println("2: Ajouter des abonnés");
                                System.out.println("3: Retirer des abonnés");
                                System.out.println("4: Envoyer un message");
                                System.out.println("5: Afficher les notifications reçues");
                                System.out.println("6: Vérifier l'abonnement");
                                System.out.println("7: Se déconnecter");

                                choixMenu = scanner.nextInt();
                                switch (choixMenu) {
                                    case 1:
                                        repo.afficherEmploye();
                                        break;
                                    case 2:
                                        System.out.println("Entrez l'email de l'employé : ");
                                        String emailAbonne = scanner1.nextLine();
                                        serviceNotification.ajouterAbonne(emailAbonne);
                                        break;
                                    case 3:
                                        System.out.println("Entrez l'email de l'employé : ");
                                        String emailAbonne1 = scanner1.nextLine();
                                        serviceNotification.retirerAbonne(emailAbonne1);
                                        break;
                                    case 4:
                                        serviceNotification.notifier(email);
                                        break;
                                    case 5:
                                        serviceNotification.afficherNotifications(email,motDePasse);
                                        break;
                                    case 6:
                                        serviceNotification.verifierAbonnement();
                                        break;
                                    case 7:
                                        break;
                                }

                            } while (choixMenu != 7) ;
                            break;
                        }else {
                            do {
                                System.out.println("1: S'abonné au service de notification");
                                System.out.println("2: Se désabonné du service de notification");
                                System.out.println("3: Envoyer un message");
                                System.out.println("4: Afficher les notifications reçuées");
                                System.out.println("5: Se déconnecter");

                                choixMenu = scanner.nextInt();

                                switch (choixMenu){
                                    case 1: serviceNotification.ajouterAbonne(email);
                                            break;
                                    case 2: serviceNotification.retirerAbonne(email);
                                            break;
                                    case 3: serviceNotification.notifier(email);
                                            break;
                                    case 4: serviceNotification.afficherNotifications(email,motDePasse);
                                }
                            }while (choixMenu != 5);
                        }
                    }else{
                        System.out.println("Mot de passe ou email incorrecte");
                    }
                case 2 :
                    System.out.println("Au revoir !");
            }

        }while (choix != 2) ;
    }
}