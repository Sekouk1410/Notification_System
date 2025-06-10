package service;

import java.util.Scanner;

public class GestionEmploye {
    Scanner scanner =new Scanner(System.in);
    public void seConnecter(){
        System.out.println("Entrez votre adresse email : ");
        String email = scanner.nextLine();
        System.out.println("Entrez votre mot de passe : ");
        String motDePasse = scanner.nextLine();


    }
}
