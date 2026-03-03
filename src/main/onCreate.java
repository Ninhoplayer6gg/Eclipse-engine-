// ============================================================
// ECLIPSE ENGINE - MainActivity onCreate
// ============================================================
// COMO USAR NO SKETCHWARE:
// 1. Abra MainActivity > Blocos > onCreate
// 2. Adicione um bloco "add source directly" (Java customizado)
// 3. Cole TODO este codigo dentro do bloco
// ============================================================

// --- VARIAVEIS DE COR ---
String corFundo = "#0D0D0D";
String corCartao = "#1A1A2E";
String corElevada = "#252540";
String corDestaque = "#00E5FF";
String corAmbar = "#FFB300";
String corTextoFantasma = "#6C6C80";
String corTextoClaro = "#EAEAEA";
String corBranco = "#FFFFFF";

// --- FUNDO DA ACTIVITY ---
linear_principal.setBackgroundColor(Color.parseColor(corFundo));

// --- BARRA DE STATUS ESCURA ---
if (android.os.Build.VERSION.SDK_INT >= 21) {
    getWindow().setStatusBarColor(Color.parseColor(corFundo));
    getWindow().setNavigationBarColor(Color.parseColor(corFundo));
}

// --- DIMENSOES DA TELA ---
int larguraTela = getResources().getDisplayMetrics().widthPixels;
float densidade = getResources().getDisplayMetrics().density;

// --- CARREGAR PREFERENCIAS ---
SharedPreferences sp = getSharedPreferences("eclipse_prefs", MODE_PRIVATE);
String corDestaqueUser = sp.getString("corDestaque", "#00E5FF");
int colunas = sp.getInt("colunas", 4);
boolean mostrarPerf = sp.getBoolean("mostrarPerformance", true);
boolean formato24h = sp.getBoolean("formato24h", true);

if (!corDestaqueUser.equals("#00E5FF")) {
    corDestaque = corDestaqueUser;
}

// --- ESTILO DO TEXTO DO RELOGIO ---
txt_hora.setTextSize(48);
txt_hora.setTextColor(Color.parseColor(corBranco));
txt_hora.setTypeface(Typeface.DEFAULT_BOLD);
txt_data.setTextSize(14);
txt_data.setTextColor(Color.parseColor(corTextoFantasma));

// --- CHIPS DE STATUS (formato pilula) ---
android.graphics.drawable.GradientDrawable chipBg =
    new android.graphics.drawable.GradientDrawable();
chipBg.setColor(Color.parseColor(corElevada));
chipBg.setCornerRadius((int)(20 * densidade));

linear_chipBateria.setBackground(chipBg);
linear_chipRAM.setBackground(chipBg.getConstantState().newDrawable());
linear_chipRede.setBackground(chipBg.getConstantState().newDrawable());

int ph = (int)(8 * densidade);
int pw = (int)(12 * densidade);
linear_chipBateria.setPadding(pw, ph, pw, ph);
linear_chipRAM.setPadding(pw, ph, pw, ph);
linear_chipRede.setPadding(pw, ph, pw, ph);

txt_bateria.setTextSize(12);
txt_bateria.setTextColor(Color.parseColor(corTextoFantasma));
txt_ram.setTextSize(12);
txt_ram.setTextColor(Color.parseColor(corTextoFantasma));
txt_rede.setTextSize(12);
txt_rede.setTextColor(Color.parseColor(corTextoFantasma));

// --- CARD DE PERFORMANCE ---
if (mostrarPerf) {
    linear_cardPerf.setVisibility(View.VISIBLE);

    android.graphics.drawable.GradientDrawable perfBg =
        new android.graphics.drawable.GradientDrawable();
    perfBg.setColor(Color.parseColor(corCartao));
    perfBg.setCornerRadius((int)(16 * densidade));
    perfBg.setStroke((int)(1 * densidade), Color.parseColor(corElevada));
    linear_cardPerf.setBackground(perfBg);
    int padCard = (int)(16 * densidade);
    linear_cardPerf.setPadding(padCard, padCard, padCard, padCard);

    txt_perfTitulo.setTextSize(10);
    txt_perfTitulo.setTextColor(Color.parseColor(corDestaque));
    txt_perfTitulo.setTypeface(Typeface.DEFAULT_BOLD);
    txt_perfTitulo.setText("PERFORMANCE");

    // Trilha das barras
    android.graphics.drawable.GradientDrawable trilhaBg =
        new android.graphics.drawable.GradientDrawable();
    trilhaBg.setColor(Color.parseColor(corElevada));
    trilhaBg.setCornerRadius((int)(3 * densidade));
    barra_cpuTrilha.setBackground(trilhaBg);
    barra_ramTrilha.setBackground(trilhaBg.getConstantState().newDrawable());
    barra_storageTrilha.setBackground(trilhaBg.getConstantState().newDrawable());
} else {
    linear_cardPerf.setVisibility(View.GONE);
}

// --- GRADE DE ATALHOS (calculo de largura) ---
int paddingLateral = (int)(24 * densidade);
int gapTiles = (int)(12 * densidade);
int larguraUtil = larguraTela - (paddingLateral * 2) - (gapTiles * (colunas - 1));
larguraTile = larguraUtil / colunas;

// --- ANIMACOES DE ENTRADA (efeito cascata) ---
linear_relogio.setAlpha(0f);
linear_relogio.setTranslationY(20f);
linear_relogio.animate().alpha(1f).translationY(0f)
    .setDuration(400).setStartDelay(100);

linear_chipBateria.setAlpha(0f);
linear_chipBateria.animate().alpha(1f).setDuration(300).setStartDelay(200);
linear_chipRAM.setAlpha(0f);
linear_chipRAM.animate().alpha(1f).setDuration(300).setStartDelay(250);
linear_chipRede.setAlpha(0f);
linear_chipRede.animate().alpha(1f).setDuration(300).setStartDelay(300);

if (mostrarPerf) {
    linear_cardPerf.setAlpha(0f);
    linear_cardPerf.setTranslationY(30f);
    linear_cardPerf.animate().alpha(1f).translationY(0f)
        .setDuration(400).setStartDelay(350);
}
timer_relogio.scheduleAtFixedRate(new TimerTask() { @Override public void run() { runOnUiThread(new Runnable() { @Override public void run() { 
    // O código do clockUpdate vai rodar aqui a cada 1 segundo
}});}}, 0, 1000);

timer_perf.scheduleAtFixedRate(new TimerTask() { @Override public void run() { runOnUiThread(new Runnable() { @Override public void run() { 
    // O código do performanceMonitor vai rodar aqui a cada 3 segundos
}});}}, 0, 3000);
    
