package utils;

import java.time.LocalDate;

public class DataUtils {

    public static boolean dataValida(LocalDate data) {
        return data != null;
    }

    public static boolean medicamentoVencido(LocalDate validade) {
        return validade.isBefore(LocalDate.now());
    }

    /**
     * Avisa se o medicamento vence nos próximos 'dias' dias.
     * Ex: DataUtils.validadeProxima(validade, 90) → vence em até 3 meses
     */
    public static boolean validadeProxima(LocalDate validade, int dias) {
        LocalDate hoje = LocalDate.now();
        return !validade.isBefore(hoje)
                && validade.isBefore(hoje.plusDays(dias));
    }
}