// ============================================================
// ECLIPSE ENGINE - AnimationHelper
// ============================================================
// Animacoes de entrada para dar vida a interface.
// Cole como bloco "add source directly" no onCreate.
// ============================================================

// FADE IN + SLIDE UP
// Uso: animarEntrada(linear_meuCard, 200, 30);
// delay = tempo antes de comecar (ms)
// distancia = quanto sobe em pixels
public void animarEntrada(
    android.view.View v, long delay, float distancia
) {
    v.setAlpha(0f);
    v.setTranslationY(distancia);
    v.animate()
        .alpha(1f)
        .translationY(0f)
        .setDuration(400)
        .setStartDelay(delay);
}

// FADE IN SIMPLES (sem movimento)
// Uso: animarFade(linear_meuChip, 300);
public void animarFade(android.view.View v, long delay) {
    v.setAlpha(0f);
    v.animate()
        .alpha(1f)
        .setDuration(300)
        .setStartDelay(delay);
}

// EFEITO CASCATA (aplicar em varios views de uma vez)
// Uso:
// android.view.View[] cards = {card1, card2, card3};
// animarCascata(cards, 100);
//
// Cada view aparece 100ms depois da anterior
public void animarCascata(
    android.view.View[] views, long intervalo
) {
    for (int i = 0; i < views.length; i++) {
        views[i].setAlpha(0f);
        views[i].setTranslationY(20f);
        views[i].animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(350)
            .setStartDelay(100 + (i * intervalo));
    }
}

// SCALE IN (efeito de "pular" pra dentro)
// Uso: animarScale(linear_meuBtn, 250);
// Bom para botoes e icones
public void animarScale(android.view.View v, long delay) {
    v.setScaleX(0f);
    v.setScaleY(0f);
    v.animate()
        .scaleX(1f)
        .scaleY(1f)
        .setDuration(300)
        .setStartDelay(delay);
                        }
