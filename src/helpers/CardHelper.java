// ============================================================
// ECLIPSE ENGINE - CardHelper
// ============================================================
// Funcoes utilitarias para criar cards, chips e botoes.
// Cole como bloco "add source directly" no onCreate
// de qualquer Activity ANTES de usar as funcoes.
// ============================================================

// CRIAR CARD PADRAO
// Uso: aplicarCard(linear_meuCard, "#1A1A2E", 16, "#252540");
public void aplicarCard(
    android.widget.LinearLayout v, String cor, float raio, String borda
) {
    float d = getResources().getDisplayMetrics().density;
    android.graphics.drawable.GradientDrawable bg =
        new android.graphics.drawable.GradientDrawable();
    bg.setColor(Color.parseColor(cor));
    bg.setCornerRadius(raio * d);
    bg.setStroke((int)(1 * d), Color.parseColor(borda));
    v.setBackground(bg);
    int p = (int)(16 * d);
    v.setPadding(p, p, p, p);
}

// CRIAR CHIP (formato pilula)
// Uso: aplicarChip(linear_meuChip, "#252540");
public void aplicarChip(
    android.widget.LinearLayout v, String cor
) {
    float d = getResources().getDisplayMetrics().density;
    android.graphics.drawable.GradientDrawable bg =
        new android.graphics.drawable.GradientDrawable();
    bg.setColor(Color.parseColor(cor));
    bg.setCornerRadius(20 * d);
    v.setBackground(bg);
    v.setPadding((int)(12*d), (int)(6*d), (int)(12*d), (int)(6*d));
}

// CRIAR BOTAO PRIMARIO
// Uso: aplicarBotao(linear_meuBtn, "#00E5FF", 12);
public void aplicarBotao(
    android.widget.LinearLayout v, String cor, float raio
) {
    float d = getResources().getDisplayMetrics().density;
    android.graphics.drawable.GradientDrawable bg =
        new android.graphics.drawable.GradientDrawable();
    bg.setColor(Color.parseColor(cor));
    bg.setCornerRadius(raio * d);
    v.setBackground(bg);
    v.setElevation(8 * d);
}

// FEEDBACK DE TOQUE
// Uso: feedbackToque(view); dentro do onClick
public void feedbackToque(android.view.View v) {
    v.setAlpha(0.7f);
    v.animate().alpha(1f).setDuration(150);
    v.performHapticFeedback(
        android.view.HapticFeedbackConstants.VIRTUAL_KEY
    );
  }
