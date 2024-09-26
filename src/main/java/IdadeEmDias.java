import java.time.Duration;
import java.time.LocalDateTime;

public class IdadeEmDias {
    public long calcularIdadeEmDias(String nascimento) {
        LocalDateTime hoje = LocalDateTime.now();
        LocalDateTime diaNascimento = LocalDateTime.parse(nascimento);

        Duration duracao = Duration.between(diaNascimento, hoje);
        long quantidaeddeDias = duracao.toHours();

        return quantidaeddeDias;
    }
}
