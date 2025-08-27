# 🌟 Caçadores de Saci - Sistema CRUD Educacional

## 📋 Descrição

Este é um sistema CRUD (Create, Read, Update, Delete) educacional baseado no jogo brasileiro **Caçadores de Saci**. O projeto foi desenvolvido para demonstrar conceitos fundamentais de programação Java, incluindo programação orientada a objetos, padrões de design e organização de código.

## 🎮 Sobre o Jogo

**Caçadores de Saci** é um RPG brasileiro que combina elementos do folclore nacional com mecânicas de colecionar e treinar criaturas místicas. O jogo apresenta:

- **Sacis**: Criaturas místicas com diferentes tipos, temperamentos e ranks
- **Caçadores**: Personagens jogadores que capturam e treinam Sacis
- **Movimentos**: Habilidades e ataques que os Sacis podem usar em combate
- **Sistema de Evolução**: Sacis podem evoluir e aprender novas habilidades

## 🏗️ Arquitetura do Sistema

O projeto segue o padrão **MVC (Model-View-Controller)** e **DAO (Data Access Object)**:

```
src/
├── main/
│   └── Main.java              # Classe principal
├── model/                     # Entidades do sistema
│   ├── Saci.java             # Criatura principal
│   ├── Cacador.java          # Caçador/Treinador
│   ├── Movimento.java        # Habilidades de combate
│   ├── ClasseCacador.java    # Classes de caçadores
│   ├── TipoSaci.java         # Tipos de Saci
│   ├── Temperamento.java     # Temperamentos
│   ├── Rank.java             # Níveis de dificuldade
│   └── CategoriaMovimento.java # Categorias de movimentos
├── dao/                       # Acesso aos dados
│   ├── SaciDAO.java          # Operações CRUD para Sacis
│   ├── CacadorDAO.java       # Operações CRUD para Caçadores
│   └── MovimentoDAO.java     # Operações CRUD para Movimentos
├── service/                   # Lógica de negócio
│   ├── SaciService.java      # Validações e regras dos Sacis
│   ├── CacadorService.java   # Validações e regras dos Caçadores
│   └── MovimentoService.java # Validações e regras dos Movimentos
└── view/                      # Interface do usuário
    ├── SaciView.java         # Interface completa (em desenvolvimento)
    └── SaciViewSimples.java  # Interface simplificada para demonstração
```

## 🌟 Entidades do Sistema

### Saci
- **Atributos**: Nome, Tipo, Temperamento, Rank, PV, Potência, Agilidade, Resistência, Magia, Foco
- **Características Especiais**: Habilidade de suporte, lista de movimentos, capacidade de evolução
- **Tipos**: Valente, Rígido, Neutro, Sombrio, Caótico, Cativante

### Caçador
- **Atributos Básicos**: Nome, Idade, Classe, Nível, Experiência
- **Atributos Principais**: Coração, Músculo, Cérebro, Charme
- **Perícias**: Atenção, Tática, Arte, Empatia, Encrenca, Esportes, Mandar, Fingir
- **Classes Disponíveis**: Domador, Pesquisador, Torcedor, Artista, Brigão, Líder, Sapeca

### Movimento
- **Propriedades**: Nome, Tipo, Categoria, Teste de Ataque, Efeito, Descrição
- **Características**: Prioridade, Artimanha, Área de Efeito, Rank Mínimo
- **Categorias**: Físico, Mágico, Utilitário

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- Terminal/Prompt de comando

### Compilação
```bash
# Navegar para o diretório do projeto
cd CacadoresSaciCRUD

# Compilar o projeto
javac -cp src -d bin src/**/*.java

# Executar o sistema
java -cp bin main.Main
```

### Usando os Scripts de Conveniência
```bash
# Windows
.\compilar.bat    # Compila o projeto
.\executar.bat    # Executa o sistema

# Linux/Mac
chmod +x compilar.sh executar.sh
./compilar.sh     # Compila o projeto
./executar.sh     # Executa o sistema
```

## 🎯 Funcionalidades Implementadas

### Gestão de Sacis
- ✅ Criação de novos Sacis com validações
- ✅ Busca por ID, nome, tipo e rank
- ✅ Listagem completa com detalhes
- ✅ Atualização de atributos
- ✅ Remoção de registros
- ✅ Estatísticas e análises

### Gestão de Caçadores
- ✅ Cadastro de caçadores por classe
- ✅ Sistema de experiência e níveis
- ✅ Gestão de equipe de Sacis
- ✅ Atributos e perícias personalizadas
- ✅ Busca e filtros diversos

### Gestão de Movimentos
- ✅ Biblioteca completa de movimentos
- ✅ Categorização por tipo e efetividade
- ✅ Sistema de compatibilidade
- ✅ Movimentos especiais por rank

### Interface de Usuário
- ✅ Menu interativo em console
- ✅ Navegação intuitiva
- ✅ Exibição detalhada de informações
- ✅ Tratamento de erros amigável

## 📚 Conceitos Educacionais Demonstrados

### Programação Orientada a Objetos
- **Encapsulamento**: Atributos privados com getters/setters
- **Herança**: Estruturas de classe bem definidas
- **Polimorfismo**: Uso de interfaces e enums
- **Abstração**: Separação clara de responsabilidades

### Padrões de Design
- **MVC**: Separação entre Model, View e Controller
- **DAO**: Abstração de acesso aos dados
- **Service Layer**: Encapsulamento da lógica de negócio
- **Enum Pattern**: Tipo seguro para constantes

### Estruturas de Dados
- **ArrayList**: Para listas dinâmicas
- **HashMap**: Para armazenamento chave-valor
- **Streams**: Para processamento funcional de dados
- **Collections**: Para operações em conjuntos

### Validações e Tratamento de Erros
- Validações de entrada robustas
- Mensagens de erro informativas
- Tratamento de exceções
- Consistência de dados

## 🎓 Uso Educacional

Este projeto é ideal para:

- **Aulas de Programação Java**: Demonstra conceitos fundamentais de forma prática
- **Estudo de Padrões de Design**: Implementações claras e bem documentadas
- **Práticas de CRUD**: Operações completas com validações
- **Modelagem de Dados**: Relacionamentos entre entidades
- **Interface de Usuário**: Interação via console de forma organizada

### Exercícios Sugeridos para Estudantes

1. **Expansão de Funcionalidades**:
   - Adicionar sistema de combate entre Sacis
   - Implementar salvamento em arquivo
   - Criar interface gráfica com JavaFX

2. **Melhorias na Arquitetura**:
   - Implementar padrão Observer para notificações
   - Adicionar injeção de dependência
   - Criar testes unitários com JUnit

3. **Recursos Avançados**:
   - Integração com banco de dados
   - API REST com Spring Boot
   - Sistema de autenticação de usuários

## 📖 Documentação Adicional

- [📋 Guia de Instalação](INSTALACAO.md)
- [👨‍🏫 Guia Pedagógico](GUIA_PEDAGOGICO.md)
- [✅ Projeto Concluído](PROJETO_CONCLUIDO.md)

## 🤝 Contribuição

Este é um projeto educacional. Contribuições são bem-vindas na forma de:
- Melhorias na documentação
- Correções de bugs
- Sugestões de exercícios
- Exemplos adicionais

## 📝 Licença

Este projeto é distribuído para fins educacionais. Baseado no universo do jogo **Caçadores de Saci** com adaptações para ensino de programação.

---

**Desenvolvido com 💚 para ensinar programação Java através do folclore brasileiro!**
