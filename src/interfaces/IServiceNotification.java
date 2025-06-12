package interfaces;

public interface IServiceNotification {
    public void ajouterAbonne(String emailEmploye);
    public void retirerAbonne(String emailEmploye);
    public void notifier(String emailExp);
}
