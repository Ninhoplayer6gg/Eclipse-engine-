// Certifique-se de que a lista caminhosDosJogos existe nas suas variáveis do Sketchware
java.util.ArrayList<String> listaJogos = new java.util.ArrayList<>(); 
// (Apenas simulação aqui, você usaria a lista real do seu Scanner)

android.widget.LinearLayout[] meusTiles = {
    tile_1, tile_2, tile_3, tile_4, 
    tile_5, tile_6, tile_7, tile_8
};

for (int i = 0; i < meusTiles.length; i++) {
    // Pegando as views de dentro do Tile
    android.widget.ImageView imgCapa = (android.widget.ImageView) meusTiles[i].getChildAt(0);
    android.widget.TextView txtNome = (android.widget.TextView) meusTiles[i].getChildAt(1);

    if (i < listaJogos.size()) {
        // TEM JOGO NESSE ESPAÇO!
        java.io.File pastaDoJogo = new java.io.File(listaJogos.get(i));
        
        meusTiles[i].setAlpha(1f);
        txtNome.setText(pastaDoJogo.getName()); // Coloca o nome da pasta
        txtNome.setTextColor(android.graphics.Color.parseColor("#FFFFFF"));
        txtNome.setTypeface(null, android.graphics.Typeface.BOLD);

    } else {
        // SLOT VAZIO
        meusTiles[i].setAlpha(0.2f);
        txtNome.setText("Vazio");
        txtNome.setTextColor(android.graphics.Color.parseColor("#6C6C80"));
    }
}
