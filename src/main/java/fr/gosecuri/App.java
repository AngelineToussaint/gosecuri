package fr.gosecuri;
import fr.gosecuri.model.Kit;
import fr.gosecuri.model.User;

public class App {
    public static void main(String... args) {
        //new MainPage();

        /*User user = new User();
        user.update("test", "test");*/

        Kit kit = new Kit();
        kit.store("lampe torche", 5);

        //kit.delete("lampe torche");

        kit.get("brassard de sécurité");
    }
}
