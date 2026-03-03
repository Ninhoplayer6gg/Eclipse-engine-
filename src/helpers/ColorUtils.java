// ============================================================
// ECLIPSE ENGINE - ColorUtils
// ============================================================
// Utilitarios de cor para tema dinamico e barras.
// Cole como bloco "add source directly" no onCreate.
// ============================================================

// COR DE DESTAQUE POR HORA DO DIA
// Uso: String cor = corPorHora();
public String corPorHora() {
    int hora = java.util.Calendar.getInstance()
        .get(java.util.Calendar.HOUR_OF_DAY);
    if (hora >= 6 && hora < 12) return "#00E5FF";
    if (hora >= 12 && hora < 18) return "#FFB300";
    if (hora >= 18 && hora < 22) return "#7C4DFF";
    return "#00E5FF";
}
// Manha (6-12): Ciano energico
// Tarde (12-18): Ambar quente
// Noite (18-22): Violeta relaxante
// Madrugada: Ciano padrao

// COR DA BARRA POR PORCENTAGEM
// Uso: String cor = corPorNivel(85);
public String corPorNivel(int percent) {
    if (percent > 80) return "#FF1744";
    if (percent > 50) return "#FFB300";
    return "#00E5FF";
}
// 0-50%: Ciano (normal)
// 51-80%: Ambar (atencao)
// 81-100%: Vermelho (critico)

// ESCURECER COR (para estados pressionados)
// Uso: int corEscura = escurecerCor(Color.parseColor("#00E5FF"), 0.7f);
public int escurecerCor(int cor, float fator) {
    int a = Color.alpha(cor);
    int r = Math.max((int)(Color.red(cor) * fator), 0);
    int g = Math.max((int)(Color.green(cor) * fator), 0);
    int b = Math.max((int)(Color.blue(cor) * fator), 0);
    return Color.argb(a, r, g, b);
}

// CLAREAR COR (para estados hover)
// Uso: int corClara = clarearCor(Color.parseColor("#1A1A2E"), 1.3f);
public int clarearCor(int cor, float fator) {
    int a = Color.alpha(cor);
    int r = Math.min((int)(Color.red(cor) * fator), 255);
    int g = Math.min((int)(Color.green(cor) * fator), 255);
    int b = Math.min((int)(Color.blue(cor) * fator), 255);
    return Color.argb(a, r, g, b);
}
