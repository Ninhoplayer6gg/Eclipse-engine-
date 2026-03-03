# MainActivity - Instrucoes de Layout

## Como montar no Editor Visual do Sketchware

### Passo 1: Container Principal
1. Selecione o layout raiz (ja vem um LinearLayout)
2. Renomeie para: linear_principal
3. Orientacao: VERTICAL, Largura: MATCH_PARENT, Altura: MATCH_PARENT, Padding: 0

### Passo 2: ScrollView
1. Dentro de linear_principal, adicione um ScrollView
2. Renomeie para: scroll_principal (MATCH_PARENT x MATCH_PARENT)
3. Dentro do ScrollView, adicione um LinearLayout vertical
4. Renomeie para: linear_conteudo
5. MATCH_PARENT x WRAP_CONTENT, Padding: 24 esquerda, 0 topo, 24 direita, 24 base

### Passo 3: Barra de Status (Chips)
1. Dentro de linear_conteudo, adicione LinearLayout horizontal: linear_statusBar
2. MATCH_PARENT x WRAP_CONTENT, Margem topo: 16, Gravity: CENTER_VERTICAL
3. Dentro, adicione 3 LinearLayouts horizontais (WRAP_CONTENT, margem direita 8):
   - linear_chipBateria com TextView txt_bateria
   - linear_chipRAM com TextView txt_ram
   - linear_chipRede com TextView txt_rede

### Passo 4: Bloco do Relogio
1. LinearLayout vertical: linear_relogio
2. MATCH_PARENT x WRAP_CONTENT, Gravity: CENTER_HORIZONTAL
3. Margem topo: 48, Margem base: 32
4. Dentro adicione:
   - TextView txt_hora
   - TextView txt_data (margem topo 4)

### Passo 5: Grade de Atalhos
1. LinearLayout vertical: linear_grade (margem base 24)
2. Dentro, 2 LinearLayouts horizontais:
   - linear_gradeRow1 (MATCH_PARENT, margem base 12)
   - linear_gradeRow2 (MATCH_PARENT)
3. Em cada linha, 4 LinearLayouts verticais (tile_1 ate tile_8)
4. Cada tile: gravity CENTER_HORIZONTAL, padding 8
5. Dentro de cada tile: ImageView 48x48dp + TextView 11sp (margem topo 4)

### Passo 6: Card de Performance
1. TextView: txt_perfTitulo (margem base 8)
2. LinearLayout vertical: linear_cardPerf (margem base 24)
3. Dentro, para CADA barra (CPU, RAM, Storage) adicione:
   a) TextView label: txt_cpuLabel / txt_ramLabel / txt_storageLabel (12sp, margem base 6)
   b) LinearLayout horizontal trilha: barra_cpuTrilha / barra_ramTrilha / barra_storageTrilha
      - MATCH_PARENT x 6dp, margem base 12
   c) Dentro da trilha, LinearLayout preenchimento: barra_cpuPreench / barra_ramPreench / barra_storagePreench
      - 0dp x MATCH_PARENT

## Arvore completa de IDs
linear_principal
scroll_principal
linear_conteudo
linear_statusBar
linear_chipBateria > txt_bateria
linear_chipRAM > txt_ram
linear_chipRede > txt_rede
linear_relogio
txt_hora
txt_data
linear_grade
linear_gradeRow1 > tile_1, tile_2, tile_3, tile_4
linear_gradeRow2 > tile_5, tile_6, tile_7, tile_8
txt_perfTitulo
linear_cardPerf
txt_cpuLabel > barra_cpuTrilha > barra_cpuPreench
txt_ramLabel > barra_ramTrilha > barra_ramPreench
txt_storageLabel > barra_storageTrilha > barra_storagePreench
## Variaveis para criar no Sketchware

| Nome | Tipo | Valor Inicial |
|------|------|---------------|
| ultimoMinuto | Number | -1 |
| touchInicioY | Number | 0 |
| touchFimY | Number | 0 |
| larguraTile | Number | 0 |

## Componentes para adicionar

| Nome | Tipo | Config |
|------|------|--------|
| timer_relogio | Timer | Intervalo: 1000 |
| timer_perf | Timer | Intervalo: 3000 |
| sp | SharedPreference | Nome: eclipse_prefs |
