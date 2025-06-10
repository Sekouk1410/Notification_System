package service;

import model.Employe;
import repositorie.EmployeRepo;

import java.util.Scanner;

public class GestionEmploye {
    Scanner scanner =new Scanner(System.in);
    //injection de dependance
    private EmployeRepo repo;
    public GestionEmploye(EmployeRepo repo)
    {
        this.repo = repo;
    }
    public boolean seConnecter(){
        System.out.println("Entrez votre adresse email : ");
        String email = scanner.nextLine();
        System.out.println("Entrez votre mot de passe : ");
        String motDePasse = scanner.nextLine();
        Employe employe = repo.getEmploye(email, motDePasse);
        if(employe !=null)
        {
            return true;
        }
        return false;
    }
}
