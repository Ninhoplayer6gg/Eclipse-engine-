// Mostra aviso na tela
txt_drawerTitulo.setText("ESCANEANDO SISTEMA...");

// Inicia busca em segundo plano (para não travar)
new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            final android.content.pm.PackageManager pm = getPackageManager();
            android.content.Intent mainIntent = new android.content.Intent(
                android.content.Intent.ACTION_MAIN, null
            );
            mainIntent.addCategory(android.content.Intent.CATEGORY_LAUNCHER);

            java.util.List<android.content.pm.ResolveInfo> apps = 
                pm.queryIntentActivities(mainIntent, 0);

            // Ordena A-Z
            java.util.Collections.sort(apps, 
                new android.content.pm.ResolveInfo.DisplayNameComparator(pm));

            // Listas temporárias
            final java.util.ArrayList<String> nomes = new java.util.ArrayList<>();
            final java.util.ArrayList<String> pacotes = new java.util.ArrayList<>();
            final java.util.ArrayList<android.graphics.drawable.Drawable> icones = new java.util.ArrayList<>();

            for (int i = 0; i < apps.size(); i++) {
                android.content.pm.ResolveInfo info = apps.get(i);
                nomes.add(info.loadLabel(pm).toString());
                pacotes.add(info.activityInfo.packageName);
                icones.add(info.loadIcon(pm)); // Puxa o ícone real do app
            }

            // Volta para a tela principal para mostrar a lista
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listaNomesApps.clear();
                    listaPacotesApps.clear();
                    listaNomesApps.addAll(nomes);
                    listaPacotesApps.addAll(pacotes);
                    
                    txt_drawerTitulo.setText("APPS (" + nomes.size() + ")");

                    // Desenha a lista com Ícone e Nome
                    android.widget.BaseAdapter adapterPRO = new android.widget.BaseAdapter() {
                        @Override
                        public int getCount() { return nomes.size(); }
                        @Override
                        public Object getItem(int pos) { return nomes.get(pos); }
                        @Override
                        public long getItemId(int pos) { return pos; }
                        
                        @Override
                        public android.view.View getView(int pos, android.view.View view, android.view.ViewGroup parent) {
                            float densidade = getResources().getDisplayMetrics().density;
                            
                            android.widget.LinearLayout layoutLinha = new android.widget.LinearLayout(AppDrawerActivity.this);
                            layoutLinha.setOrientation(android.widget.LinearLayout.HORIZONTAL);
                            layoutLinha.setGravity(android.view.Gravity.CENTER_VERTICAL);
                            int pad = (int)(12 * densidade);
                            layoutLinha.setPadding(pad, pad, pad, pad);

                            android.widget.ImageView imgIcone = new android.widget.ImageView(AppDrawerActivity.this);
                            int iconSize = (int)(42 * densidade);
                            layoutLinha.addView(imgIcone, new android.widget.LinearLayout.LayoutParams(iconSize, iconSize));
                            imgIcone.setImageDrawable(icones.get(pos));

                            android.widget.TextView txtNome = new android.widget.TextView(AppDrawerActivity.this);
                            txtNome.setText(nomes.get(pos));
                            txtNome.setTextColor(android.graphics.Color.parseColor("#EAEAEA"));
                            txtNome.setTextSize(14);
                            txtNome.setTypeface(null, android.graphics.Typeface.BOLD);
                            txtNome.setPadding((int)(16 * densidade), 0, 0, 0);
                            layoutLinha.addView(txtNome);

                            return layoutLinha;
                        }
                    };
                    listview_apps.setAdapter(adapterPRO);
                }
            });
        } catch (Exception e) {}
    }
}).start();
                                
