import java.time.Duration;
import java.time.LocalDateTime;

public class IdadeEmDias {
    public long calcularIdadeEmDias(String nascimento) {
        LocalDateTime hoje = LocalDateTime.now();
        LocalDateTime diaDefensoria = LocalDateTime.parse(nascimento);

        Duration duracao = Duration.between(diaDefensoria, hoje);
        long quantidaeddeDias = duracao.toDays();

        return quantidaeddeDias;
    }
}
