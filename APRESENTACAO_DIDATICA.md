# 🎯 **APRESENTAÇÃO DIDÁTICA - PROJETO CAÇADORES DE SACI**
*Sistema CRUD Educacional em Java*

---

## 📋 **ÍNDICE DA APRESENTAÇÃO**

1. [**VISÃO GERAL DO PROJETO**](#1-visão-geral-do-projeto)
2. [**CONCEITOS JAVA DEMONSTRADOS**](#2-conceitos-java-demonstrados)
3. [**ARQUITETURA DO SISTEMA**](#3-arquitetura-do-sistema)
4. [**ESTRUTURA DE CLASSES**](#4-estrutura-de-classes)
5. [**FUNCIONAMENTO DO SISTEMA**](#5-funcionamento-do-sistema)
6. [**EXEMPLOS PRÁTICOS**](#6-exemplos-práticos)
7. [**COMO EXECUTAR**](#7-como-executar)
8. [**EXERCÍCIOS SUGERIDOS**](#8-exercícios-sugeridos)

---

## 🌟 **1. VISÃO GERAL DO PROJETO**

### **O que é?**
Um sistema CRUD (Create, Read, Update, Delete) educacional baseado no RPG brasileiro "Caçadores de Saci", que utiliza criaturas do folclore nacional.

### **Objetivos Educacionais:**
- ✅ Demonstrar **Programação Orientada a Objetos**
- ✅ Implementar padrões **DAO** e **Service Layer**
- ✅ Praticar **validação de dados** e **tratamento de erros**
- ✅ Criar **interface de usuário** em console
- ✅ Utilizar **enums** e **coleções** Java

### **Elementos do Jogo:**
- 🧙‍♂️ **Sacis**: Criaturas místicas com tipos, ranks e atributos
- 👤 **Caçadores**: Personagens que capturam e treinam Sacis
- ⚔️ **Movimentos**: Ataques e habilidades dos Sacis
- 🎒 **Inventários**: Itens e recursos dos caçadores

---

## 💻 **2. CONCEITOS JAVA DEMONSTRADOS**

### **2.1 Programação Orientada a Objetos**

#### **Encapsulamento**
```java
public class Saci {
    private int id;           // Atributos privados
    private String nome;
    
    public String getNome() { // Getters/Setters públicos
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
}
```

#### **Herança e Polimorfismo**
```java
// Classes especializadas herdam comportamentos
public class SaciCompleto extends Saci {
    // Adiciona funcionalidades específicas
}
```

#### **Abstração**
```java
// Interfaces definem contratos
public interface CrudInterface<T> {
    T criar(T objeto);
    T buscarPorId(int id);
    boolean atualizar(T objeto);
    boolean deletar(int id);
}
```

### **2.2 Enumerações (Enums)**

#### **Tipos de Saci**
```java
public enum TipoSaci {
    VALENTE("Valente", "Corajosos e combativos"),
    RIGIDO("Rígido", "Disciplinados e resistentes"),
    NEUTRO("Neutro", "Equilibrados e versáteis"),
    SOMBRIO("Sombrio", "Misteriosos e furtivos"),
    CAOTICO("Caótico", "Imprevisíveis e enérgicos"),
    CATIVANTE("Cativante", "Carismáticos e curadores");
}
```

### **2.3 Coleções Java**
```java
// Listas para armazenamento
private List<Saci> sacis = new ArrayList<>();

// Maps para índices
private Map<Integer, Movimento> movimentos = new HashMap<>();

// Streams para filtros
return sacis.stream()
    .filter(s -> s.getTipo() == tipo)
    .collect(Collectors.toList());
```

### **2.4 Tratamento de Exceções**
```java
try {
    Saci saci = criarSaci(dados);
    System.out.println("✅ Saci criado com sucesso!");
} catch (ValidacaoException e) {
    System.out.println("❌ Erro: " + e.getMessage());
}
```

---

## 🏗️ **3. ARQUITETURA DO SISTEMA**

### **3.1 Padrão de Camadas (Layers)**

```
┌─────────────────────────────────────┐
│           VIEW (Interface)          │
│  ├─ SaciView.java                  │
│  └─ SaciViewSimples.java           │
└─────────────────────────────────────┘
                 ▼
┌─────────────────────────────────────┐
│       SERVICE (Lógica Negócio)      │
│  ├─ SaciService.java               │
│  ├─ CacadorService.java            │
│  └─ MovimentoService.java          │
└─────────────────────────────────────┘
                 ▼
┌─────────────────────────────────────┐
│         DAO (Acesso Dados)          │
│  ├─ SaciDAO.java                   │
│  ├─ CacadorDAO.java                │
│  └─ MovimentoDAO.java              │
└─────────────────────────────────────┘
                 ▼
┌─────────────────────────────────────┐
│      MODEL (Estrutura Dados)        │
│  ├─ Saci.java                      │
│  ├─ Cacador.java                   │
│  ├─ Movimento.java                 │
│  └─ Enums (TipoSaci, Rank, etc.)   │
└─────────────────────────────────────┘
```

### **3.2 Fluxo de Dados**

1. **VIEW** → Recebe entrada do usuário
2. **SERVICE** → Valida e processa regras de negócio
3. **DAO** → Acessa e manipula dados
4. **MODEL** → Estrutura os objetos de dados

---

## 📦 **4. ESTRUTURA DE CLASSES**

### **4.1 Camada MODEL**

#### **Classe Principal: Saci.java**
```java
public class Saci {
    // Identificação
    private int id;
    private String nome;
    
    // Características
    private TipoSaci tipo;
    private Temperamento temperamento;
    private Rank rank;
    
    // Atributos de Combate
    private int pv;           // Pontos de Vida
    private int potencia;     // Força de ataque
    private int agilidade;    // Velocidade
    private int resistencia;  // Defesa
    private int magia;        // Poder místico
    private int foco;         // Concentração
    
    // Funcionalidades
    private HabilidadeSaci habilidadeEspecial;
    private List<String> movimentos;
    private boolean podeEvoluir;
}
```

#### **Enums Principais**

**TipoSaci.java** - Tipos elementais
- `VALENTE` - Focado em ataques físicos
- `RIGIDO` - Especialista em defesa
- `NEUTRO` - Equilibrado
- `SOMBRIO` - Furtivo e místico
- `CAOTICO` - Imprevisível
- `CATIVANTE` - Suporte e cura

**Rank.java** - Sistema de classificação
- `UMA_ESTRELA` ⭐ - Iniciante
- `DUAS_ESTRELAS` ⭐⭐ - Intermediário  
- `TRES_ESTRELAS` ⭐⭐⭐ - Avançado
- `QUATRO_ESTRELAS` ⭐⭐⭐⭐ - Mestre

### **4.2 Camada DAO (Data Access Object)**

#### **SaciDAO.java** - Gerenciamento de Dados
```java
public class SaciDAO {
    private List<Saci> sacis;  // "Banco de dados" em memória
    private int proximoId;
    
    // Operações CRUD
    public Saci criar(Saci saci)         // CREATE
    public Saci buscarPorId(int id)      // READ
    public boolean atualizar(Saci saci)  // UPDATE  
    public boolean deletar(int id)       // DELETE
    
    // Consultas especializadas
    public List<Saci> buscarPorTipo(TipoSaci tipo)
    public List<Saci> buscarPorRank(Rank rank)
    public String obterEstatisticas()
}
```

### **4.3 Camada SERVICE**

#### **SaciService.java** - Lógica de Negócio
```java
public class SaciService {
    private SaciDAO saciDAO;
    
    // Validações e regras de negócio
    public Saci criarSaci(/* parâmetros */) {
        // 1. Validar dados de entrada
        // 2. Aplicar regras de negócio
        // 3. Chamar DAO para persistir
    }
    
    private String validarDadosSaci(/* parâmetros */) {
        // Validações:
        // - Nome não vazio
        // - Atributos dentro dos limites
        // - Tipo e rank válidos
    }
}
```

### **4.4 Camada VIEW**

#### **SaciView.java** - Interface do Usuário
```java
public class SaciView {
    private Scanner scanner;
    private SaciService saciService;
    
    public void iniciar() {
        // Loop principal do menu
        while (continuar) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();
            // Processar escolha do usuário
        }
    }
}
```

---

## ⚙️ **5. FUNCIONAMENTO DO SISTEMA**

### **5.1 Fluxo de Criação de um Saci**

```mermaid
graph TD
    A[Usuário escolhe "Criar Saci"] --> B[VIEW coleta dados]
    B --> C[SERVICE valida dados]
    C --> D{Dados válidos?}
    D -->|Não| E[Exibe erro e volta]
    D -->|Sim| F[SERVICE cria objeto Saci]
    F --> G[DAO persiste no "banco"]
    G --> H[Confirma criação para usuário]
```

### **5.2 Operações CRUD Implementadas**

#### **CREATE - Criar Novo Saci**
1. Usuário fornece: nome, tipo, temperamento, rank, atributos
2. Sistema valida dados
3. Cria objeto Saci com ID único
4. Armazena na lista (simula banco de dados)

#### **READ - Consultar Sacis**
- Buscar por ID específico
- Buscar por nome (parcial)
- Filtrar por tipo de Saci
- Filtrar por rank
- Listar todos os Sacis

#### **UPDATE - Atualizar Saci**
1. Busca Saci por ID
2. Permite alterar atributos
3. Valida novos dados
4. Atualiza registro

#### **DELETE - Remover Saci**
1. Busca Saci por ID
2. Confirma remoção
3. Remove da lista

### **5.3 Validações Implementadas**

```java
private String validarDadosSaci(String nome, TipoSaci tipo, 
                               Temperamento temperamento, Rank rank,
                               int pv, int potencia, int agilidade, 
                               int resistencia, int magia, int foco) {
    
    if (nome == null || nome.trim().isEmpty()) {
        return "Nome é obrigatório";
    }
    
    if (nome.length() > 50) {
        return "Nome deve ter no máximo 50 caracteres";
    }
    
    if (pv < 10 || pv > 100) {
        return "PV deve estar entre 10 e 100";
    }
    
    // Validações dos atributos (1-20)
    if (potencia < 1 || potencia > 20) {
        return "Potência deve estar entre 1 e 20";
    }
    
    return null; // Sem erros
}
```

---

## 🎮 **6. EXEMPLOS PRÁTICOS**

### **6.1 Sacis Pré-cadastrados**

#### **Curupira** - Guardião da Floresta
```java
Saci curupira = new Saci("Curupira", TipoSaci.VALENTE, 
                        Temperamento.AGRESSIVO, Rank.DUAS_ESTRELAS);
curupira.setPv(45);
curupira.setPotencia(18);  // Alto ataque
curupira.setAgilidade(15);
curupira.setResistencia(16); // Boa defesa
curupira.setMagia(8);      // Pouca magia
curupira.setFoco(12);
curupira.setHabilidadeEspecial("Protetor da Floresta");
```

#### **Boto** - Encantador Aquático  
```java
Saci boto = new Saci("Boto", TipoSaci.CATIVANTE,
                    Temperamento.CALMO, Rank.TRES_ESTRELAS);
boto.setPv(50);
boto.setPotencia(12);
boto.setAgilidade(14);
boto.setResistencia(13);
boto.setMagia(20);        // Alta magia
boto.setFoco(16);         // Alto foco
boto.setHabilidadeEspecial("Encantamento Aquático");
```

### **6.2 Sistema de Movimentos**

#### **Movimentos por Tipo**

**VALENTE** - Ataques de Fogo
```java
// Chamas - Movimento mágico básico
Movimento chamas = new Movimento("Chamas", TipoSaci.VALENTE, 
                                CategoriaMovimento.MAGICO);
chamas.setTesteAtaque("Agilidade + Magia + 1");
chamas.setEfeito("Pode causar queimadura");
```

**SOMBRIO** - Ataques Furtivos
```java
// Dentada - Ataque físico com veneno
Movimento dentada = new Movimento("Dentada", TipoSaci.SOMBRIO,
                                 CategoriaMovimento.FISICO);
dentada.setTesteAtaque("Potência + Agilidade + 1");
dentada.setEfeito("Pode envenenar o alvo");
```

### **6.3 Sistema de Caçadores**

```java
Cacador domador = new Cacador();
domador.setNome("João Silva");
domador.setIdade(25);
domador.setClasse(ClasseCacador.DOMADOR);
domador.setNivel(1);
domador.adicionarSaci(1); // Adiciona Saci por ID
```

---

## 🚀 **7. COMO EXECUTAR**

### **7.1 Pré-requisitos**
- Java 8 ou superior instalado
- IDE (VS Code, IntelliJ, Eclipse) ou terminal

### **7.2 Compilação Manual**
```bash
# Navegar para o diretório do projeto
cd "e:\Marcelo\CacadoresSaciCRUD"

# Compilar todos os arquivos Java
javac -cp . src/model/*.java src/dao/*.java src/service/*.java src/view/*.java src/main/Main.java

# Executar o programa
java -cp src main.Main
```

### **7.3 Usando Scripts**
```bash
# Windows
.\compilar.bat    # Compila o projeto
.\executar.bat    # Executa o programa
```

### **7.4 Interface do Sistema**

**Menu Principal:**
```
╔══════════════════════════════════════════════════════════╗
║                 🌟 CAÇADORES DE SACI 🌟                  ║
║                    Sistema CRUD                          ║
║                  Versão Educacional                     ║
╚══════════════════════════════════════════════════════════╝

┌──────────────────────────────────────────────────────────┐
│                      📋 MENU PRINCIPAL                   │
├──────────────────────────────────────────────────────────┤
│  1️⃣  - Gerenciar Sacis                                   │
│  2️⃣  - Gerenciar Caçadores                               │
│  3️⃣  - Gerenciar Movimentos                              │
│  4️⃣  - Gerenciar Inventários                             │
│  5️⃣  - Estatísticas Gerais                               │
│  0️⃣  - Sair                                              │
└──────────────────────────────────────────────────────────┘
```

---

## 📚 **8. EXERCÍCIOS SUGERIDOS**

### **8.1 Iniciante**
1. **Adicionar novo atributo**: Inclua um campo "idade" na classe Saci
2. **Nova validação**: Implemente validação de idade (1-100 anos)
3. **Busca personalizada**: Crie busca por atributo específico

### **8.2 Intermediário**
4. **Novo enum**: Crie enum `StatusSaci` (ATIVO, FERIDO, DESCANSANDO)
5. **Método calculado**: Implemente `calcularPoderTotal()` na classe Saci
6. **Ordenação**: Ordene lista de Sacis por poder total

### **8.3 Avançado**
7. **Sistema de evolução**: Implemente evolução de Sacis
8. **Persistência em arquivo**: Salve dados em arquivo JSON
9. **Interface gráfica**: Crie interface com JavaFX

### **8.4 Desafios**
10. **Sistema de batalha**: Implemente combate entre Sacis
11. **Banco de dados**: Integre com MySQL ou PostgreSQL
12. **API REST**: Transforme em serviço web com Spring Boot

---

## 🎯 **PONTOS-CHAVE PARA APRESENTAÇÃO**

### **Para Estudantes Iniciantes:**
- ✅ Foque na estrutura das classes e relacionamentos
- ✅ Explique o conceito de encapsulamento com getters/setters
- ✅ Demonstre o uso prático de enums
- ✅ Mostre como as validações protegem a integridade dos dados

### **Para Estudantes Intermediários:**
- ✅ Destaque os padrões DAO e Service Layer
- ✅ Explique a separação de responsabilidades
- ✅ Mostre o uso de coleções (List, Map) e Stream API
- ✅ Demonstre o tratamento de exceções

### **Para Estudantes Avançados:**
- ✅ Discuta possíveis melhorias arquiteturais
- ✅ Explore conceitos de Clean Code
- ✅ Sugira implementação de testes unitários
- ✅ Apresente caminhos para evolução do projeto

---

## 📖 **MATERIAL DE APOIO**

### **Documentos do Projeto:**
- `README.md` - Visão geral e instruções
- `GUIA_PEDAGOGICO.md` - Material para professores
- `INSTALACAO.md` - Guia de instalação detalhado
- `PROJETO_CONCLUIDO.md` - Documentação técnica completa

### **Arquivos Importantes:**
- `src/main/Main.java` - Ponto de entrada da aplicação
- `src/view/SaciViewSimples.java` - Demonstração simplificada
- `src/model/` - Todas as classes de modelo
- `compilar.bat` / `executar.bat` - Scripts de automação

---

## 🌟 **MENSAGEM FINAL**

Este projeto demonstra que aprender programação pode ser **divertido** e **culturalmente relevante**! 

Ao usar elementos do folclore brasileiro, conectamos tecnologia com nossa identidade cultural, tornando o aprendizado mais significativo e envolvente.

**"Que os Sacis estejam com você na jornada do aprendizado!"** 🌟

---

*Desenvolvido para fins educacionais - Use, modifique e compartilhe livremente!*
