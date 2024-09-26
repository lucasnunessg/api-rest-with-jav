import java.time.LocalDate;

public class Birthday {

   public void checkBirthday() {
        LocalDate dataHj = LocalDate.now();
        LocalDate nascimento = LocalDate.parse("1999-09-26");

        if (dataHj.getMonth() == nascimento.getMonth()) {
            if (dataHj.getDayOfMonth() == nascimento.getDayOfMonth()) {
                System.out.println("Feliz aniversário!");
            } else if (dataHj.getDayOfMonth() < nascimento.getDayOfMonth()) {
                System.out.println("Seu aniversário está próximo!");
            } else {
                System.out.println("Seu aniversário já passou!");
            }
        } else {
            System.out.println("Não é seu aniversário.");
        }
    }
}
