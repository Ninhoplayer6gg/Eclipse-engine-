// ============================================================
// ECLIPSE ENGINE - SettingsActivity onCreate
// ============================================================
// COMO USAR NO SKETCHWARE:
// 1. Crie uma nova Activity chamada "SettingsActivity"
// 2. Monte o layout conforme a arvore de IDs abaixo
// 3. No onCreate, cole este codigo
//
// ARVORE DE IDs DO LAYOUT:
// linear_settingsPrincipal (vertical, MATCH x MATCH, padding 24)
//   txt_settingsTitulo (WRAP x WRAP, margem base 32)
//   linear_cardCor (vertical, MATCH x WRAP, margem base 16)
//     txt_corLabel (WRAP x WRAP, margem base 12)
//     linear_coresRow (horizontal, WRAP x WRAP)
//       btn_corCiano (36x36dp, margem direita 12)
//       btn_corAmbar (36x36dp, margem direita 12)
//       btn_corVioleta (36x36dp, margem direita 12)
//       btn_corVerde (36x36dp, margem direita 12)
//       btn_corRosa (36x36dp)
//   linear_cardColunas (vertical, MATCH x WRAP, margem base 16)
//     txt_colunasLabel (WRAP x WRAP)
//   linear_cardSwitches (vertical, MATCH x WRAP)
//     txt_perfSwLabel + switch_perf (numa linha horizontal)
//     txt_formatoLabel + switch_formato (numa linha horizontal)
// ============================================================

String corFundo = "#0D0D0D";
String corCartao = "#1A1A2E";
String corElevada = "#252540";
String corDestaque = "#00E5FF";
String corTextoFantasma = "#6C6C80";
String corTextoClaro = "#EAEAEA";
String corBranco = "#FFFFFF";

// --- FUNDO ---
linear_settingsPrincipal.setBackgroundColor(Color.parseColor(corFundo));
if (android.os.Build.VERSION.SDK_INT >= 21) {
    getWindow().setStatusBarColor(Color.parseColor(corFundo));
    getWindow().setNavigationBarColor(Color.parseColor(corFundo));
}

float densidade = getResources().getDisplayMetrics().density;

// --- TITULO ---
txt_settingsTitulo.setText("CONFIGURACOES");
txt_settingsTitulo.setTextSize(28);
txt_settingsTitulo.setTextColor(Color.parseColor(corBranco));
txt_settingsTitulo.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);

// --- CARREGAR PREFS ATUAIS ---
SharedPreferences sp = getSharedPreferences("eclipse_prefs", MODE_PRIVATE);
String corAtual = sp.getString("corDestaque", "#00E5FF");
int colunasAtual = sp.getInt("colunas", 4);
boolean mostrarPerf = sp.getBoolean("mostrarPerformance", true);
boolean formato24h = sp.getBoolean("formato24h", true);

// --- CARD: COR DE DESTAQUE ---
android.graphics.drawable.GradientDrawable cardCorBg =
    new android.graphics.drawable.GradientDrawable();
cardCorBg.setColor(Color.parseColor(corCartao));
cardCorBg.setCornerRadius((int)(16 * densidade));
linear_cardCor.setBackground(cardCorBg);
int pc = (int)(16 * densidade);
linear_cardCor.setPadding(pc, pc, pc, pc);

txt_corLabel.setText("Cor de Destaque");
txt_corLabel.setTextSize(14);
txt_corLabel.setTextColor(Color.parseColor(corTextoClaro));

// Botoes de cor (5 presets)
String[] cores = {"#00E5FF", "#FFB300", "#7C4DFF", "#00E676", "#FF4081"};
android.widget.LinearLayout[] btnsCor = {
    btn_corCiano, btn_corAmbar, btn_corVioleta, btn_corVerde, btn_corRosa
};

for (int i = 0; i < btnsCor.length; i++) {
    android.graphics.drawable.GradientDrawable cb =
        new android.graphics.drawable.GradientDrawable();
    cb.setColor(Color.parseColor(cores[i]));
    cb.setCornerRadius((int)(18 * densidade));
    if (cores[i].equals(corAtual)) {
        cb.setStroke((int)(3 * densidade), Color.parseColor(corBranco));
    }
    btnsCor[i].setBackground(cb);
}

// --- CARD: COLUNAS ---
android.graphics.drawable.GradientDrawable cardColBg =
    new android.graphics.drawable.GradientDrawable();
cardColBg.setColor(Color.parseColor(corCartao));
cardColBg.setCornerRadius((int)(16 * densidade));
linear_cardColunas.setBackground(cardColBg);
linear_cardColunas.setPadding(pc, pc, pc, pc);

txt_colunasLabel.setText("Colunas: " + colunasAtual);
txt_colunasLabel.setTextSize(14);
txt_colunasLabel.setTextColor(Color.parseColor(corTextoClaro));

// --- CARD: SWITCHES ---
android.graphics.drawable.GradientDrawable cardSwBg =
    new android.graphics.drawable.GradientDrawable();
cardSwBg.setColor(Color.parseColor(corCartao));
cardSwBg.setCornerRadius((int)(16 * densidade));
linear_cardSwitches.setBackground(cardSwBg);
linear_cardSwitches.setPadding(pc, pc, pc, pc);

txt_perfSwLabel.setText("Mostrar Performance");
txt_perfSwLabel.setTextSize(14);
txt_perfSwLabel.setTextColor(Color.parseColor(corTextoClaro));

txt_formatoLabel.setText("Formato 24 horas");
txt_formatoLabel.setTextSize(14);
txt_formatoLabel.setTextColor(Color.parseColor(corTextoClaro));

switch_perf.setChecked(mostrarPerf);
switch_formato.setChecked(formato24h);

// ============================================================
// EVENTOS DOS BOTOES DE COR
// No Sketchware, para CADA btn_cor va em Eventos > onClick
// Cole (trocando a cor pra cada botao):
//
// SharedPreferences.Editor ed =
//     getSharedPreferences("eclipse_prefs", MODE_PRIVATE).edit();
// ed.putString("corDestaque", "#00E5FF");
// ed.commit();
// finish();
//
// Cores: Ciano=#00E5FF Ambar=#FFB300 Violeta=#7C4DFF
//        Verde=#00E676 Rosa=#FF4081
// ============================================================
// EVENTO DO SWITCH PERFORMANCE
// switch_perf > onCheckedChanged:
//
// SharedPreferences.Editor ed =
//     getSharedPreferences("eclipse_prefs", MODE_PRIVATE).edit();
// ed.putBoolean("mostrarPerformance", _isChecked);
// ed.commit();
// ============================================================
// EVENTO DO SWITCH FORMATO
// switch_formato > onCheckedChanged:
//
// SharedPreferences.Editor ed =
//     getSharedPreferences("eclipse_prefs", MODE_PRIVATE).edit();
// ed.putBoolean("formato24h", _isChecked);
// ed.commit();
// ============================================================
