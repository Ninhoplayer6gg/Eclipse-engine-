# Eclipse Engine

Premium Android Launcher built with Sketchware — gamer UI, zero libraries, max performance.

---

## Sobre

Eclipse Engine e um launcher Android premium com estetica gamer, construido inteiramente com componentes nativos do Sketchware. Zero bibliotecas externas, zero dependencias pesadas — apenas performance pura.

### Caracteristicas

- Interface escura AMOLED-friendly
- Consumo minimo de CPU e RAM
- Estetica gamer premium sem exagero
- Sistema de grid 8dp para espacamento perfeito
- Otimizado para economia de bateria
- Maximo 3 niveis de aninhamento de views

---

## Estrutura do Projeto
Eclipse-Engine/
├── src/
│   ├── main/          # Tela inicial (relogio, grade, status)
│   ├── drawer/        # Gaveta de apps com busca
│   ├── settings/      # Configuracoes
│   └── helpers/       # Funcoes utilitarias
├── docs/              # Documentacao
└── assets/            # Paleta de cores
## Paleta de Cores

| Funcao | Hex |
|--------|-----|
| Preto Vazio | #0D0D0D |
| Superficie Profunda | #1A1A2E |
| Superficie Elevada | #252540 |
| Ciano Destaque | #00E5FF |
| Ambar Alerta | #FFB300 |
| Texto Fantasma | #6C6C80 |
| Texto Claro | #EAEAEA |

## Como Usar

1. Abra o Sketchware Pro e crie um novo projeto
2. Navegue ate a pasta src/main/ neste repositorio
3. Siga as instrucoes do LAYOUT.md no editor visual
4. Copie o codigo dos arquivos .java nos blocos customizados
5. Repita para drawer/ e settings/

## Roadmap

- [x] Sistema de cores e design tokens
- [x] Componentes base (cards, botoes, chips)
- [x] Tela inicial com relogio
- [x] Monitor de performance
- [x] Gaveta de apps com busca
- [ ] Widget de clima
- [ ] Atalhos personalizaveis
- [ ] Temas por hora do dia

## Licenca

Este projeto esta sob a licenca MIT.
