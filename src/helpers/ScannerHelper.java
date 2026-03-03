// Código para usar dentro de um MORE BLOCK no Sketchware
} // Fecha a função do More Block automaticamente gerada pelo Sketchware

// Cria a função recursiva de busca
private void escanearPastasInternas(java.io.File diretorio, java.util.ArrayList<String> listaResultados) {
    if (diretorio == null || !diretorio.isDirectory()) return;

    java.io.File[] arquivos = diretorio.listFiles();
    if (arquivos == null) return;

    boolean ehJogo = false;

    for (java.io.File arquivo : arquivos) {
        if (arquivo.isDirectory()) {
            String nomePasta = arquivo.getName();
            // Ignora pastas do Android para ser mais rápido
            if (nomePasta.equals("Android") || nomePasta.startsWith(".")) continue;
            escanearPastasInternas(arquivo, listaResultados); 
        } else {
            String nomeArquivo = arquivo.getName().toLowerCase();
            if (nomeArquivo.equals("game.ini") || nomeArquivo.equals("game.rgss3a") || nomeArquivo.equals("game.exe")) {
                ehJogo = true;
            }
        }
    }
    // Se achou o jogo, salva a pasta
    if (ehJogo) {
        listaResultados.add(diretorio.getAbsolutePath());
    }
}

// Reabre uma função vazia só para o Sketchware não dar erro de compilação
private void funcaoFantasma() { 
  
