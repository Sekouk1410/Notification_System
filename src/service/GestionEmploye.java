package service;

import model.Employe;
import repositorie.EmployeRepo;

import java.util.Scanner;

public class GestionEmploye {
    Scanner scanner =new Scanner(System.in);

    public boolean seConnecter(Employe employe){
        return employe != null;
    }

    //Methode pour verifier le profil à la connexion
    public boolean verifierProfil(Employe employe){
        return employe.isEstAdmin();
    }
}
