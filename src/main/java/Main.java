public class Main {
    public static void main(String[] args) {
        Birthday alerta = new Birthday();
        alerta.checkBirthday();

        HoraExtra verifyHour = new HoraExtra();
        verifyHour.checkHoraExtra();

        IdadeEmDias idade = new IdadeEmDias();
        System.out.println("A quandidade de dias desde o seu nascimento é: " + idade.calcularIdadeEmDias("2023-03-30T12:00:00"));
    }


}
