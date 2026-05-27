package utils;

import enums.TipoMensagem;

public class MensagemUtils {

    public static void exibir(TipoMensagem tipo,
                              String modulo,
                              String mensagem) {

        System.out.println(
                "[" + tipo.name() + "][" + modulo + "] "
                        + mensagem
        );
    }
}