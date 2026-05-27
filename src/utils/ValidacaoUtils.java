package utils;

public class ValidacaoUtils {

    public static boolean textoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean numeroPositivo(double numero) {
        return numero > 0;
    }

    public static boolean estoqueValido(int estoque) {
        return estoque >= 0;
    }

    /**
     * Valida formato do CPF: aceita "000.000.000-00" ou "00000000000"
     */
    public static boolean cpfFormatoValido(String cpf) {

        if (textoVazio(cpf)) return false;

        String apenasNumeros = cpf.replaceAll("[^0-9]", "");

        if (apenasNumeros.length() != 11) return false;

        // Rejeita CPFs com todos os dígitos iguais (ex: 111.111.111-11)
        if (apenasNumeros.matches("(\\d)\\1{10}")) return false;

        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
                || cpf.matches("\\d{11}");
    }

    /**
     * Valida formato básico de email: algo@algo.algo
     */
    public static boolean emailFormatoValido(String email) {

        if (textoVazio(email)) return false;

        return email.matches("^[\\w.+\\-]+@[\\w\\-]+\\.[a-zA-Z]{2,}$");
    }


/**
 * Valida formato do CNPJ: aceita "00.000.000/0000-00" ou "00000000000000"
 */
public static boolean cnpjFormatoValido(String cnpj) {

    if (textoVazio(cnpj)) return false;

    String apenasNumeros = cnpj.replaceAll("[^0-9]", "");

    if (apenasNumeros.length() != 14) return false;

    // Rejeita CNPJs com todos os dígitos iguais (ex: 11.111.111/1111-11)
    if (apenasNumeros.matches("(\\d)\\1{13}")) return false;

    return cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")
            || cnpj.matches("\\d{14}");
}

/**
 * Login: apenas letras, números e _ , entre 3 e 20 caracteres.
 * Ex: "joao_silva" válido | "jo ão!" inválido
 */
public static boolean loginFormatoValido(String login) {

    if (textoVazio(login)) return false;

    return login.matches("[a-zA-Z0-9_]{3,20}");
}

/**
 * Senha: mínimo 6 caracteres, ao menos 1 letra e 1 número.
 * Ex: "abc123" válido | "abcdef" inválido | "123456" inválido
 */
public static boolean senhaForteValida(String senha) {

    if (textoVazio(senha)) return false;

    if (senha.length() < 6) return false;

    boolean temLetra  = senha.matches(".*[a-zA-Z].*");
    boolean temNumero = senha.matches(".*[0-9].*");

    return temLetra && temNumero;
}
}