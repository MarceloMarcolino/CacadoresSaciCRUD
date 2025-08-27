# 🌟 Caçadores de Saci - Sistema CRUD

Um sistema CRUD educacional em Java baseado no RPG brasileiro "Caçadores de Saci", desenvolvido para fins de ensino de programação orientada a objetos e conceitos de banco de dados.

## 📚 Sobre o Projeto

Este projeto implementa um sistema básico de **C**reate, **R**ead, **U**pdate e **D**elete (CRUD) para gerenciar uma coleção de Sacis místicos, personagens baseados no rico folclore brasileiro.

### 🎯 Objetivos Educacionais

- Demonstrar conceitos de **Programação Orientada a Objetos**
- Implementar padrões de projeto como **DAO** (Data Access Object) e **Service Layer**
- Praticar **validação de dados** e **tratamento de erros**
- Criar uma **interface de usuário** simples e funcional
- Utilizar **enums** para representar tipos fixos de dados

## 🏗️ Arquitetura do Sistema

```
src/
├── main/
│   └── Main.java              # Classe principal
├── model/
│   ├── Saci.java             # Classe modelo principal
│   ├── TipoSaci.java         # Enum com tipos de Saci
│   ├── Temperamento.java     # Enum com temperamentos
│   └── Rank.java             # Enum com classificações
├── dao/
│   └── SaciDAO.java          # Acesso aos dados (simulado em memória)
├── service/
│   └── SaciService.java      # Lógica de negócio e validações
└── view/
    └── SaciView.java         # Interface com usuário (console)
```

## 🎮 Elementos do Jogo

### 🧙‍♂️ Tipos de Saci
- **Valente** - Sacis corajosos e combativos
- **Rígido** - Sacis disciplinados e resistentes  
- **Neutro** - Sacis equilibrados
- **Sombrio** - Sacis misteriosos e furtivos
- **Caótico** - Sacis imprevisíveis e enérgicos
- **Cativante** - Sacis carismáticos e encantadores

### 😤 Temperamentos
- **Calmo** - Personalidade tranquila
- **Agressivo** - Personalidade combativa
- **Quieto** - Personalidade reservada
- **Resistente** - Personalidade persistente
- **Implacável** - Personalidade determinada
- **Agitado** - Personalidade inquieta

### ⭐ Sistema de Ranks
- ⭐ Uma Estrela
- ⭐⭐ Duas Estrelas  
- ⭐⭐⭐ Três Estrelas
- ⭐⭐⭐⭐ Quatro Estrelas

### ⚡ Atributos
- **PV** - Pontos de Vida
- **Potência** - Força de ataque
- **Agilidade** - Velocidade e esquiva
- **Resistência** - Defesa física
- **Magia** - Poder místico
- **Foco** - Concentração e precisão

## 🚀 Como Executar

### Pré-requisitos
- Java 8 ou superior
- IDE ou editor de código (recomendado: VS Code, IntelliJ, Eclipse)

### Compilação e Execução

1. **Via linha de comando:**
```bash
# Navegar para a pasta do projeto
cd "I:\etc\Projetos do Marcelinho\CacadoresSaciCRUD"

# Compilar
javac -cp . src/main/Main.java src/model/*.java src/dao/*.java src/service/*.java src/view/*.java

# Executar
java -cp src main.Main
```

2. **Via IDE:**
- Abra o projeto na sua IDE preferida
- Execute a classe `Main.java`

## 📋 Funcionalidades

### ✨ Operações CRUD Básicas
1. **Criar** - Adicionar novos Sacis com todos os atributos
2. **Consultar** - Buscar Sacis por ID, nome, tipo ou rank
3. **Listar** - Visualizar todos os Sacis cadastrados
4. **Atualizar** - Modificar informações de Sacis existentes
5. **Deletar** - Remover Sacis da coleção

### 📊 Funcionalidades Extras
- **Estatísticas** - Contagem por tipo e rank
- **Ranking** - Sacis mais poderosos por atributos
- **Validações** - Verificação de dados de entrada
- **Interface amigável** - Menu interativo com emojis

## 🎓 Conceitos Demonstrados

### 1. **Programação Orientada a Objetos**
- **Encapsulamento** - Atributos privados com getters/setters
- **Herança** - Não aplicada neste exemplo básico
- **Polimorfismo** - Uso de enums e interfaces
- **Abstração** - Separação de responsabilidades entre classes

### 2. **Padrões de Projeto**
- **DAO Pattern** - Separação do acesso aos dados
- **Service Layer** - Lógica de negócio centralizada
- **MVC Simplificado** - Model, View, Controller básico

### 3. **Validação e Tratamento de Erros**
- Validação de entrada do usuário
- Tratamento de exceções
- Mensagens de erro amigáveis

### 4. **Estruturas de Dados**
- ArrayList para armazenamento
- HashMap para estatísticas
- Stream API para filtros e ordenação

## 🔧 Melhorias Possíveis

Este é um projeto educacional básico. Algumas melhorias que podem ser implementadas:

### 🗄️ Persistência de Dados
- Integração com banco de dados (MySQL, PostgreSQL)
- Salvamento em arquivos (JSON, XML, CSV)
- Uso de frameworks como Hibernate

### 🌐 Interface Gráfica
- JavaFX para interface desktop
- Spring Boot + Thymeleaf para web
- API REST para integração

### 🧪 Testes
- Testes unitários com JUnit
- Testes de integração
- Cobertura de código

### ⚡ Performance
- Paginação de resultados
- Cache de consultas
- Índices para busca rápida

## 📖 Como Usar em Sala de Aula

### 🎯 Tópicos para Ensinar
1. **Conceitos básicos de CRUD**
2. **Estrutura de classes em Java**
3. **Uso de enums**
4. **Validação de dados**
5. **Padrões de projeto simples**
6. **Interface de usuário em console**

### 📝 Exercícios Sugeridos
1. Adicionar novos atributos aos Sacis
2. Implementar novas funcionalidades de busca
3. Criar relatórios mais detalhados
4. Adicionar validações mais complexas
5. Implementar exportação de dados

### 🔄 Evolução do Projeto
1. **Versão 1** - CRUD básico em memória
2. **Versão 2** - Persistência em arquivo
3. **Versão 3** - Banco de dados
4. **Versão 4** - Interface gráfica
5. **Versão 5** - Sistema web

## 🎨 Sacis de Exemplo

O sistema vem pré-carregado com alguns Sacis baseados no folclore:

### 🌳 Curupira
- **Tipo:** Valente
- **Temperamento:** Agressivo  
- **Rank:** ⭐⭐
- **Habilidade:** Protetor da Floresta

### 🐬 Boto
- **Tipo:** Cativante
- **Temperamento:** Calmo
- **Rank:** ⭐⭐⭐
- **Habilidade:** Encantamento Aquático

### 🦌 Caipora
- **Tipo:** Neutro
- **Temperamento:** Quieto
- **Rank:** ⭐
- **Habilidade:** Camuflagem Natural

## 🤝 Contribuições

Este é um projeto educacional aberto para melhorias! Sugestões são bem-vindas:

- Novos Sacis baseados no folclore
- Melhorias na interface
- Novas funcionalidades
- Correções de bugs
- Documentação adicional

## 📄 Licença

Projeto educacional de uso livre. Sinta-se à vontade para usar, modificar e distribuir para fins educacionais.

---

**🌟 Que os Sacis estejam com você na jornada do aprendizado! 🌟**
