// ============================================================
// ECLIPSE ENGINE - Filtro de Busca
// ============================================================
// COMO USAR NO SKETCHWARE:
// 1. Va em AppDrawerActivity > Eventos
// 2. Selecione edittext_busca > onTextChanged
// 3. Cole este codigo no bloco "add source directly"
//
// VARIAVEIS List String necessarias:
//   listaNomesApps (lista completa, ja preenchida)
//   listaPacotesApps (pacotes completos, ja preenchidos)
//   listaFiltradaNomes (resultado da busca)
//   listaFiltradaPacotes (pacotes filtrados)
// ============================================================

String consulta = _charSeq.toString().toLowerCase().trim();

listaFiltradaNomes.clear();
listaFiltradaPacotes.clear();

if (consulta.length() == 0) {
    // Campo vazio: mostrar todos os apps
    listaFiltradaNomes.addAll(listaNomesApps);
    listaFiltradaPacotes.addAll(listaPacotesApps);
} else {
    // Filtrar por nome
    for (int i = 0; i < listaNomesApps.size(); i++) {
        if (listaNomesApps.get(i).toLowerCase().contains(consulta)) {
            listaFiltradaNomes.add(listaNomesApps.get(i));
            listaFiltradaPacotes.add(listaPacotesApps.get(i));
        }
    }
}

// Atualizar ListView com resultado
android.widget.ArrayAdapter<String> adapterFiltrado =
    new android.widget.ArrayAdapter<String>(
        this,
        android.R.layout.simple_list_item_1,
        listaFiltradaNomes
    );
listview_apps.setAdapter(adapterFiltrado);

// ============================================================
// IMPORTANTE:
// Quando o usuario clicar num app da lista filtrada,
// use listaFiltradaPacotes no onItemClick (nao listaPacotesApps)
//
// No evento onItemClick do listview_apps troque para:
//
// String pacote = listaFiltradaPacotes.get(_position);
// android.content.Intent launchIntent =
//     getPackageManager().getLaunchIntentForPackage(pacote);
// if (launchIntent != null) {
//     startActivity(launchIntent);
// }
// ============================================================
