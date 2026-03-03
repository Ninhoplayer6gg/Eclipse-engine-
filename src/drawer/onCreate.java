// ============================================================
// ECLIPSE ENGINE - AppDrawerActivity onCreate
// ============================================================
// COMO USAR NO SKETCHWARE:
// 1. Crie uma nova Activity chamada "AppDrawerActivity"
// 2. Monte o layout conforme a arvore de IDs abaixo
// 3. No onCreate, cole este codigo no bloco "add source directly"
//
// ARVORE DE IDs DO LAYOUT:
// linear_drawerPrincipal (vertical, MATCH x MATCH)
//   linear_searchBar (horizontal, MATCH x WRAP, margem 24/16/24/0)
//     txt_searchIcon (WRAP x WRAP)
//     edittext_busca (weight 1, margem esquerda 8)
//   txt_drawerTitulo (WRAP x WRAP, margem 24/16/24/8)
//   listview_apps (MATCH x MATCH, weight 1)
//
// VARIAVEIS List String para criar no Sketchware:
//   listaNomesApps
//   listaPacotesApps
//   listaFiltradaNomes
//   listaFiltradaPacotes
// ============================================================

String corFundo = "#0D0D0D";
String corCartao = "#1A1A2E";
String corElevada = "#252540";
String corDestaque = "#00E5FF";
String corTextoFantasma = "#6C6C80";
String corTextoClaro = "#EAEAEA";

// --- FUNDO ---
linear_drawerPrincipal.setBackgroundColor(Color.parseColor(corFundo));
if (android.os.Build.VERSION.SDK_INT >= 21) {
    getWindow().setStatusBarColor(Color.parseColor(corFundo));
    getWindow().setNavigationBarColor(Color.parseColor(corFundo));
}

float densidade = getResources().getDisplayMetrics().density;

// --- BARRA DE BUSCA ---
android.graphics.drawable.GradientDrawable searchBg =
    new android.graphics.drawable.GradientDrawable();
searchBg.setColor(Color.parseColor(corCartao));
searchBg.setCornerRadius((int)(12 * densidade));
searchBg.setStroke((int)(1 * densidade), Color.parseColor(corElevada));
linear_searchBar.setBackground(searchBg);
int ps = (int)(12 * densidade);
linear_searchBar.setPadding(ps, ps, ps, ps);

txt_searchIcon.setText("🔍");
txt_searchIcon.setTextSize(16);

edittext_busca.setHint("Buscar apps...");
edittext_busca.setHintTextColor(Color.parseColor(corTextoFantasma));
edittext_busca.setTextColor(Color.parseColor(corTextoClaro));
edittext_busca.setTextSize(14);
edittext_busca.setBackgroundColor(Color.TRANSPARENT);

// --- TITULO ---
txt_drawerTitulo.setText("APPS");
txt_drawerTitulo.setTextSize(10);
txt_drawerTitulo.setTextColor(Color.parseColor(corDestaque));
txt_drawerTitulo.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);

// --- LISTA ---
listview_apps.setBackgroundColor(Color.TRANSPARENT);
listview_apps.setDividerHeight(0);

// --- ANIMACAO DE ENTRADA ---
linear_searchBar.setAlpha(0f);
linear_searchBar.setTranslationY(-20f);
linear_searchBar.animate().alpha(1f).translationY(0f)
    .setDuration(300).setStartDelay(100);
listview_apps.setAlpha(0f);
listview_apps.animate().alpha(1f).setDuration(400).setStartDelay(200);
