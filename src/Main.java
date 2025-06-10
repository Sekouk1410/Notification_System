import repositorie.EmployeRepo;
import service.GestionEmploye;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeRepo repo = new EmployeRepo();

        GestionEmploye gestionEmploye = new GestionEmploye(repo);

        System.out.println("Bienvenue");
        System.out.println("1: Se connecter");
        System.out.println("2: Quitter");
        int choixMenu = scanner.nextInt();
        switch (choixMenu){
            case 1  :
                if (gestionEmploye.seConnecter()){

                }
                break;
            case 2 :
                break;
        }
    }
}