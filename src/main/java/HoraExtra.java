import java.time.LocalTime;

public class HoraExtra {
    public void checkHoraExtra() {
        LocalTime horaAtual = LocalTime.now();
        LocalTime fimDoExped = LocalTime.parse("17:00:00");

        if (horaAtual.isAfter(fimDoExped)) {
            System.out.println("este funcionário(a) está fazendo hora extra");
        } else {
            System.out.println("este funcionário(a) está no horário de expediente!");
        }
        }
    }
