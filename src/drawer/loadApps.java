// ============================================================
// ECLIPSE ENGINE - Carregar Lista de Apps
// ============================================================
// COMO USAR NO SKETCHWARE:
// Cole no bloco onCreate da AppDrawerActivity
// DEPOIS do codigo do onCreate.java
//
// VARIAVEIS List String necessarias:
//   listaNomesApps
//   listaPacotesApps
// ============================================================

try {
    android.content.pm.PackageManager pm = getPackageManager();
    android.content.Intent mainIntent = new android.content.Intent(
        android.content.Intent.ACTION_MAIN, null
    );
    mainIntent.addCategory(android.content.Intent.CATEGORY_LAUNCHER);

    java.util.List<android.content.pm.ResolveInfo> apps =
        pm.queryIntentActivities(mainIntent, 0);

    // Ordenar A-Z
    java.util.Collections.sort(apps,
        new android.content.pm.ResolveInfo.DisplayNameComparator(pm));

    // Limpar listas
    listaNomesApps.clear();
    listaPacotesApps.clear();

    for (int i = 0; i < apps.size(); i++) {
        android.content.pm.ResolveInfo info = apps.get(i);
        String nomeApp = info.loadLabel(pm).toString();
        String pacote = info.activityInfo.packageName;

        listaNomesApps.add(nomeApp);
        listaPacotesApps.add(pacote);
    }

    // Configurar ListView
    android.widget.ArrayAdapter<String> adapter =
        new android.widget.ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            listaNomesApps
        );
    listview_apps.setAdapter(adapter);

} catch (Exception e) {
    // Fallback se der erro
}

// ============================================================
// EVENTO DE CLICK NO APP
// ============================================================
// No Sketchware va em:
// Eventos > listview_apps > onItemClick
// Cole este codigo no bloco "add source directly":
//
// String pacote = listaPacotesApps.get(_position);
// android.content.Intent launchIntent =
//     getPackageManager().getLaunchIntentForPackage(pacote);
// if (launchIntent != null) {
//     startActivity(launchIntent);
// }
// ============================================================
