// ============================================================
// ECLIPSE ENGINE PRO - Carregamento Assíncrono com Ícones
// ============================================================
// Substitua o código antigo de carregar apps por este.
// ============================================================

// 1. Feedback visual imediato (enquanto carrega em segundo plano)
txt_drawerTitulo.setText("ESCANEANDO SISTEMA...");

// 2. Criando uma Thread paralela para não travar a interface (Zero Lag)
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

            // Ordenar A-Z
            java.util.Collections.sort(apps, 
                new android.content.pm.ResolveInfo.DisplayNameComparator(pm));

            // Listas locais temporárias para a Thread
            final java.util.ArrayList<String> nomes = new java.util.ArrayList<>();
            final java.util.ArrayList<String> pacotes = new java.util.ArrayList<>();
            final java.util.ArrayList<android.graphics.drawable.Drawable> icones = new java.util.ArrayList<>();

            for (int i = 0; i < apps.size(); i++) {
                android.content.pm.ResolveInfo info = apps.get(i);
                nomes.add(info.loadLabel(pm).toString());
                pacotes.add(info.activityInfo.packageName);
                // O PULO DO GATO: Puxando a imagem do aplicativo
                icones.add(info.loadIcon(pm)); 
            }

            // 3. Voltar para a Thread Principal (UI) para injetar na tela
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Atualizar as listas globais (para o seu filtro de busca continuar funcionando)
                    listaNomesApps.clear();
                    listaPacotesApps.clear();
                    listaNomesApps.addAll(nomes);
                    listaPacotesApps.addAll(pacotes);
                    
                    // Atualiza o título com a contagem total
                    txt_drawerTitulo.setText("APPS (" + nomes.size() + ")");

                    // 4. O Adapter Customizado (Desenhando Ícone + Texto via código)
                    android.widget.BaseAdapter adapterPRO = new android.widget.BaseAdapter() {
                        @Override
                        public int getCount() { return nomes.size(); }
                        @Override
                        public Object getItem(int pos) { return nomes.get(pos); }
                        @Override
                        public long getItemId(int pos) { return pos; }
                        
                        @Override
                        public android.view.View getView(int pos, android.view.View view, android.view.ViewGroup parent) {
                            // Construindo a linha da lista matematicamente
                            float densidade = getResources().getDisplayMetrics().density;
                            
                            android.widget.LinearLayout layoutLinha = new android.widget.LinearLayout(AppDrawerActivity.this);
                            layoutLinha.setOrientation(android.widget.LinearLayout.HORIZONTAL);
                            layoutLinha.setGravity(android.view.Gravity.CENTER_VERTICAL);
                            int pad = (int)(12 * densidade);
                            layoutLinha.setPadding(pad, pad, pad, pad);

                            // O Ícone
                            android.widget.ImageView imgIcone = new android.widget.ImageView(AppDrawerActivity.this);
                            int iconSize = (int)(42 * densidade);
                            layoutLinha.addView(imgIcone, new android.widget.LinearLayout.LayoutParams(iconSize, iconSize));
                            imgIcone.setImageDrawable(icones.get(pos));

                            // O Texto
                            android.widget.TextView txtNome = new android.widget.TextView(AppDrawerActivity.this);
                            txtNome.setText(nomes.get(pos));
                            txtNome.setTextColor(android.graphics.Color.parseColor("#EAEAEA")); // corTextoClaro
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

        } catch (Exception e) {
            // Em caso de erro muito bizarro, evitar crash
        }
    }
}).start();
                    
